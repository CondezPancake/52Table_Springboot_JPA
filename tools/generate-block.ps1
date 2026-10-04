$ErrorActionPreference = 'Stop'
$ProjectRoot = Split-Path -Parent $PSScriptRoot
$Utf8NoBom = New-Object System.Text.UTF8Encoding($false)

function Field($name, $type, $column, $length, $sample, $refPackage = $null, $refEntity = $null) {
    [pscustomobject]@{ Name = $name; Type = $type; Column = $column; Length = $length; Sample = $sample; RefPackage = $refPackage; RefEntity = $refEntity }
}

$Contexts = @(
    [pscustomobject]@{
        Entity = 'Country'; Package = 'country'; Table = 'countries'; Endpoint = 'countries'
        Fields = @(
            (Field 'nameCountry' 'String' 'name_country' 50 '"Colombia"'),
            (Field 'codeCountry' 'String' 'code_country' 10 '"CO"'),
            (Field 'description' 'String' 'description' 100 '"Republic of Colombia"'),
            (Field 'active' 'boolean' 'is_active' 0 'true'),
            (Field 'telephonePrefix' 'String' 'telephone_prefix' 5 '"+57"')
        )
    },
    [pscustomobject]@{
        Entity = 'StateRegion'; Package = 'stateregion'; Table = 'state_regions'; Endpoint = 'state-regions'
        Fields = @(
            (Field 'nameRegion' 'String' 'name_region' 50 '"Cundinamarca"'),
            (Field 'codeRegion' 'String' 'code_region' 10 '"CUN"'),
            (Field 'description' 'String' 'description' 100 '"Central region"'),
            (Field 'active' 'boolean' 'is_active' 0 'true'),
            (Field 'countryId' 'CountryId' 'country_id' 36 'CountryId.generate()' 'country' 'Country')
        )
    },
    [pscustomobject]@{
        Entity = 'CityMunicipality'; Package = 'citymunicipality'; Table = 'city_municipalities'; Endpoint = 'city-municipalities'
        Fields = @(
            (Field 'nameCity' 'String' 'name_city' 50 '"Bogota"'),
            (Field 'codeCity' 'String' 'code_city' 10 '"BOG"'),
            (Field 'description' 'String' 'description' 100 '"Capital district"'),
            (Field 'active' 'boolean' 'is_active' 0 'true'),
            (Field 'regionId' 'StateRegionId' 'region_id' 36 'StateRegionId.generate()' 'stateregion' 'StateRegion')
        )
    }
)

function Write-Generated($relativePath, $content) {
    $path = Join-Path $ProjectRoot $relativePath
    if (Test-Path -LiteralPath $path) { throw "Refusing to overwrite $path" }
    $directory = Split-Path -Parent $path
    [System.IO.Directory]::CreateDirectory($directory) | Out-Null
    [System.IO.File]::WriteAllText($path, $content.Trim() + [Environment]::NewLine, $Utf8NoBom)
}

function Cap($value) { $value.Substring(0, 1).ToUpperInvariant() + $value.Substring(1) }
function Is-Ref($field) { $null -ne $field.RefEntity }
function Persistence-Type($field) { if (Is-Ref $field) { 'UUID' } else { $field.Type } }
function Response-Type($field) { if (Is-Ref $field) { 'UUID' } else { $field.Type } }
function Getter($field) { if ($field.Type -eq 'boolean') { 'is' + (Cap $field.Name) } else { 'get' + (Cap $field.Name) } }
function Ref-Imports($context) {
    (($context.Fields | Where-Object { Is-Ref $_ } | ForEach-Object { "import springboot.domain.$($_.RefPackage).model.valueobject.$($_.RefEntity)Id;" } | Sort-Object -Unique) -join "`n")
}
function Typed-Args($context, $indent = '            ') {
    (($context.Fields | ForEach-Object { "$($_.Type) $($_.Name)" }) -join ",`n$indent")
}
function Names($context, $prefix = '', $indent = '                ') {
    (($context.Fields | ForEach-Object { if ($prefix) { "$prefix.$($_.Name)()" } else { $_.Name } }) -join ",`n$indent")
}
function Response-Args($context, $variable, $indent = '                ') {
    $values = @("$variable.id().value()")
    $values += $context.Fields | ForEach-Object { if (Is-Ref $_) { "$variable.$($_.Name)().value()" } else { "$variable.$($_.Name)()" } }
    $values += @("$variable.createdAt()", "$variable.updatedAt()")
    $values -join ",`n$indent"
}
function Code-Field($context) {
    $context.Fields | Where-Object { $_.Name -match '^code' } | Select-Object -First 1
}

function Generate-Domain($context) {
    $entity = $context.Entity; $pkg = $context.Package; $refs = Ref-Imports $context
    $codeField = Code-Field $context
    $existsByCode = if ($null -ne $codeField) { '    boolean existsByCode(String code);' } else { '' }
    Write-Generated "domain/src/main/java/springboot/domain/$pkg/model/valueobject/${entity}Id.java" @"
package springboot.domain.$pkg.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ${entity}Id(UUID value) {
    public ${entity}Id {
        Objects.requireNonNull(value, "${entity}Id value must not be null");
    }

    public static ${entity}Id generate() {
        return new ${entity}Id(UUID.randomUUID());
    }
}
"@
    foreach ($suffix in @('Registered', 'Deleted')) {
        Write-Generated "domain/src/main/java/springboot/domain/$pkg/event/${entity}${suffix}Event.java" @"
package springboot.domain.$pkg.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.$pkg.model.valueobject.${entity}Id;

public record ${entity}${suffix}Event(
        ${entity}Id id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ${entity}${suffix}Event {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
"@
    }
    $eventFields = ($context.Fields | ForEach-Object { "$($_.Type) $($_.Name)" }) -join ",`n        "
    $eventChecks = ($context.Fields | Where-Object { $_.Type -ne 'boolean' } | ForEach-Object { "        Objects.requireNonNull($($_.Name), `"$($_.Name) must not be null`");" }) -join "`n"
    Write-Generated "domain/src/main/java/springboot/domain/$pkg/event/${entity}UpdatedEvent.java" @"
package springboot.domain.$pkg.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.$pkg.model.valueobject.${entity}Id;
$refs

public record ${entity}UpdatedEvent(
        ${entity}Id id,
        $eventFields,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ${entity}UpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
$eventChecks
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
"@
    $declarations = ($context.Fields | ForEach-Object { "    private $($_.Type) $($_.Name);" }) -join "`n"
    $assignments = ($context.Fields | ForEach-Object { if ($_.Type -eq 'boolean') { "        this.$($_.Name) = $($_.Name);" } else { "        this.$($_.Name) = Objects.requireNonNull($($_.Name), `"$($_.Name) must not be null`");" } }) -join "`n"
    $getters = ($context.Fields | ForEach-Object { "    public $($_.Type) $($_.Name)() {`n        return $($_.Name);`n    }" }) -join "`n`n"
    $updateEventArgs = ($context.Fields | ForEach-Object { "this.$($_.Name)" }) -join ",`n                        "
    $typedArgs = Typed-Args $context
    $names = Names $context
    Write-Generated "domain/src/main/java/springboot/domain/$pkg/model/aggregate/$entity.java" @"
package springboot.domain.$pkg.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.model.AggregateRoot;
import springboot.domain.$pkg.event.${entity}RegisteredEvent;
import springboot.domain.$pkg.event.${entity}UpdatedEvent;
import springboot.domain.$pkg.model.valueobject.${entity}Id;
$refs

public class $entity extends AggregateRoot {
    private final ${entity}Id id;
$declarations
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private $entity(
            ${entity}Id id,
            $typedArgs,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
$assignments
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static $entity register(
            $typedArgs) {
        ${entity}Id id = ${entity}Id.generate();
        LocalDateTime now = LocalDateTime.now();
        $entity aggregate = new $entity(
                id,
                $names,
                now,
                now);
        aggregate.recordEvent(new ${entity}RegisteredEvent(id, now));
        return aggregate;
    }

    public static $entity restore(
            ${entity}Id id,
            $typedArgs,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new $entity(
                id,
                $names,
                createdAt,
                updatedAt);
    }

    public void update(
            $typedArgs) {
$assignments
        this.updatedAt = LocalDateTime.now();
        recordEvent(new ${entity}UpdatedEvent(
                        this.id,
                        $updateEventArgs,
                        this.updatedAt));
    }

    public ${entity}Id id() {
        return id;
    }

$getters

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
"@
    Write-Generated "domain/src/main/java/springboot/domain/$pkg/port/repository/${entity}Repository.java" @"
package springboot.domain.$pkg.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.$pkg.model.aggregate.$entity;
import springboot.domain.$pkg.model.valueobject.${entity}Id;

public interface ${entity}Repository {
    $entity save($entity aggregate);
    Optional<$entity> findById(${entity}Id id);
    List<$entity> findAll();
$existsByCode
    void delete($entity aggregate);
}
"@
    Write-Generated "domain/src/main/java/springboot/domain/common/exception/${entity}NotFoundException.java" @"
package springboot.domain.common.exception;

import springboot.domain.$pkg.model.valueobject.${entity}Id;

public class ${entity}NotFoundException extends RuntimeException {
    public ${entity}NotFoundException(${entity}Id id) {
        super("$entity not found with id: " + id.value());
    }
}
"@
}

function Generate-Application($context) {
    $entity = $context.Entity; $pkg = $context.Package; $refs = Ref-Imports $context
    $fieldList = ($context.Fields | ForEach-Object { "$($_.Type) $($_.Name)" }) -join ",`n        "
    $checks = ($context.Fields | Where-Object { $_.Type -ne 'boolean' } | ForEach-Object { "        Objects.requireNonNull($($_.Name), `"$($_.Name) must not be null`");" }) -join "`n"
    Write-Generated "application/src/main/java/springboot/application/$pkg/command/Register${entity}Command.java" @"
package springboot.application.$pkg.command;

import java.util.Objects;

$refs

public record Register${entity}Command(
        $fieldList
) {
    public Register${entity}Command {
$checks
    }
}
"@
    Write-Generated "application/src/main/java/springboot/application/$pkg/command/Update${entity}Command.java" @"
package springboot.application.$pkg.command;

import java.util.Objects;

import springboot.domain.$pkg.model.valueobject.${entity}Id;
$refs

public record Update${entity}Command(
        ${entity}Id id,
        $fieldList
) {
    public Update${entity}Command {
        Objects.requireNonNull(id, "id must not be null");
$checks
    }
}
"@
    $responseFields = @('UUID id') + ($context.Fields | ForEach-Object { "$(Response-Type $_) $($_.Name)" }) + @('LocalDateTime createdAt', 'LocalDateTime updatedAt')
    $responseFieldsText = $responseFields -join ",`n        "
    Write-Generated "application/src/main/java/springboot/application/$pkg/dto/${entity}Response.java" @"
package springboot.application.$pkg.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ${entity}Response(
        $responseFieldsText
) {
}
"@
    Write-Generated "application/src/main/java/springboot/application/$pkg/exception/${entity}NotFoundApplicationException.java" @"
package springboot.application.$pkg.exception;

import springboot.application.common.exception.ApplicationException;

public class ${entity}NotFoundApplicationException extends ApplicationException {
    public ${entity}NotFoundApplicationException(String message) {
        super(message);
    }
}
"@
    $commandNames = Names $context 'command'
    $savedResponse = Response-Args $context 'saved'
    $aggregateResponse = Response-Args $context 'aggregate'
    $listResponse = Response-Args $context 'aggregate' '                                '
    $base = "application/src/main/java/springboot/application/$pkg/usecase"
    Write-Generated "$base/Register${entity}UseCase.java" @"
package springboot.application.$pkg.usecase;

import springboot.application.$pkg.command.Register${entity}Command;
import springboot.application.$pkg.dto.${entity}Response;
import springboot.domain.$pkg.model.aggregate.$entity;
import springboot.domain.$pkg.port.repository.${entity}Repository;

public class Register${entity}UseCase {
    private final ${entity}Repository repository;
    public Register${entity}UseCase(${entity}Repository repository) { this.repository = repository; }

    public ${entity}Response execute(Register${entity}Command command) {
        $entity aggregate = $entity.register(
                $commandNames);
        $entity saved = repository.save(aggregate);
        return new ${entity}Response(
                $savedResponse);
    }
}
"@
    Write-Generated "$base/Get${entity}ByIdUseCase.java" @"
package springboot.application.$pkg.usecase;

import springboot.application.$pkg.dto.${entity}Response;
import springboot.application.$pkg.exception.${entity}NotFoundApplicationException;
import springboot.domain.$pkg.model.valueobject.${entity}Id;
import springboot.domain.$pkg.port.repository.${entity}Repository;

public class Get${entity}ByIdUseCase {
    private final ${entity}Repository repository;
    public Get${entity}ByIdUseCase(${entity}Repository repository) { this.repository = repository; }

    public ${entity}Response execute(${entity}Id id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ${entity}NotFoundApplicationException(id.value().toString()));
        return new ${entity}Response(
                $aggregateResponse);
    }
}
"@
    Write-Generated "$base/List${entity}UseCase.java" @"
package springboot.application.$pkg.usecase;

import java.util.List;

import springboot.application.$pkg.dto.${entity}Response;
import springboot.domain.$pkg.port.repository.${entity}Repository;

public class List${entity}UseCase {
    private final ${entity}Repository repository;
    public List${entity}UseCase(${entity}Repository repository) { this.repository = repository; }

    public List<${entity}Response> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ${entity}Response(
                                $listResponse))
                .toList();
    }
}
"@
    Write-Generated "$base/Update${entity}UseCase.java" @"
package springboot.application.$pkg.usecase;

import springboot.application.$pkg.command.Update${entity}Command;
import springboot.application.$pkg.dto.${entity}Response;
import springboot.application.$pkg.exception.${entity}NotFoundApplicationException;
import springboot.domain.$pkg.port.repository.${entity}Repository;

public class Update${entity}UseCase {
    private final ${entity}Repository repository;
    public Update${entity}UseCase(${entity}Repository repository) { this.repository = repository; }

    public ${entity}Response execute(Update${entity}Command command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ${entity}NotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                $commandNames);
        var saved = repository.save(aggregate);
        return new ${entity}Response(
                $savedResponse);
    }
}
"@
    Write-Generated "$base/Delete${entity}UseCase.java" @"
package springboot.application.$pkg.usecase;

import java.time.LocalDateTime;

import springboot.application.$pkg.exception.${entity}NotFoundApplicationException;
import springboot.domain.$pkg.event.${entity}DeletedEvent;
import springboot.domain.$pkg.model.valueobject.${entity}Id;
import springboot.domain.$pkg.port.repository.${entity}Repository;

public class Delete${entity}UseCase {
    private final ${entity}Repository repository;
    public Delete${entity}UseCase(${entity}Repository repository) { this.repository = repository; }

    public ${entity}DeletedEvent execute(${entity}Id id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ${entity}NotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ${entity}DeletedEvent(id, LocalDateTime.now());
    }
}
"@
}

function Generate-Infrastructure($context) {
    $entity = $context.Entity; $pkg = $context.Package; $refs = Ref-Imports $context
    $codeField = Code-Field $context
    $jpaExistsByCode = if ($null -ne $codeField) { "    boolean existsBy$(Cap $codeField.Name)(String code);" } else { '' }
    $adapterExistsByCode = if ($null -ne $codeField) { "    @Override public boolean existsByCode(String code) { return jpaRepository.existsBy$(Cap $codeField.Name)(code); }" } else { '' }
    $requestFields = ($context.Fields | ForEach-Object {
        $type = if (Is-Ref $_) { 'UUID' } elseif ($_.Type -eq 'boolean') { 'Boolean' } else { $_.Type }
        $annotations = "@NotNull(message = `"$($_.Name) is required`")"
        if ($_.Length -gt 0 -and -not (Is-Ref $_)) { $annotations += "`n        @Size(max = $($_.Length), message = `"$($_.Name) must have at most $($_.Length) characters`")" }
        "$annotations`n        $type $($_.Name)"
    }) -join ",`n`n        "
    $uuidImport = if ($context.Fields | Where-Object { Is-Ref $_ }) { "import java.util.UUID;`n`n" } else { '' }
    foreach ($action in @('Create', 'Update')) {
        Write-Generated "infrastructure/src/main/java/springboot/infrastructure/$pkg/adapters/in/rest/dtos/${action}${entity}Request.java" @"
package springboot.infrastructure.$pkg.adapters.in.rest.dtos;

${uuidImport}import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ${action}${entity}Request(
        $requestFields
) {
}
"@
    }
    $requestArgs = ($context.Fields | ForEach-Object { if (Is-Ref $_) { "new $($_.RefEntity)Id(request.$($_.Name)())" } else { "request.$($_.Name)()" } }) -join ",`n                        "
    Write-Generated "infrastructure/src/main/java/springboot/infrastructure/$pkg/adapters/in/rest/controllers/${entity}Controller.java" @"
package springboot.infrastructure.$pkg.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import springboot.application.$pkg.command.Register${entity}Command;
import springboot.application.$pkg.command.Update${entity}Command;
import springboot.application.$pkg.dto.${entity}Response;
import springboot.application.$pkg.usecase.Delete${entity}UseCase;
import springboot.application.$pkg.usecase.Get${entity}ByIdUseCase;
import springboot.application.$pkg.usecase.List${entity}UseCase;
import springboot.application.$pkg.usecase.Register${entity}UseCase;
import springboot.application.$pkg.usecase.Update${entity}UseCase;
import springboot.domain.$pkg.model.valueobject.${entity}Id;
$refs
import springboot.infrastructure.$pkg.adapters.in.rest.dtos.Create${entity}Request;
import springboot.infrastructure.$pkg.adapters.in.rest.dtos.Update${entity}Request;

@RestController
@RequestMapping("/api/$($context.Endpoint)")
public class ${entity}Controller {
    private final Register${entity}UseCase registerUseCase;
    private final Get${entity}ByIdUseCase getByIdUseCase;
    private final List${entity}UseCase listUseCase;
    private final Update${entity}UseCase updateUseCase;
    private final Delete${entity}UseCase deleteUseCase;

    public ${entity}Controller(Register${entity}UseCase registerUseCase,
            Get${entity}ByIdUseCase getByIdUseCase, List${entity}UseCase listUseCase,
            Update${entity}UseCase updateUseCase, Delete${entity}UseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<${entity}Response> create(@Valid @RequestBody Create${entity}Request request) {
        var command = new Register${entity}Command(
                        $requestArgs);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<${entity}Response>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<${entity}Response> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ${entity}Id(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<${entity}Response> update(@PathVariable UUID id,
            @Valid @RequestBody Update${entity}Request request) {
        var command = new Update${entity}Command(
                        new ${entity}Id(id),
                        $requestArgs);
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ${entity}Id(id));
        return ResponseEntity.noContent().build();
    }
}
"@
    $columns = ($context.Fields | ForEach-Object {
        if (Is-Ref $_) { "    @JdbcTypeCode(SqlTypes.CHAR)`n    @Column(name = `"$($_.Column)`", nullable = false, length = 36, columnDefinition = `"char(36)`")`n    private UUID $($_.Name);" }
        else { $length = if ($_.Length -gt 0) { ", length = $($_.Length)" } else { '' }; "    @Column(name = `"$($_.Column)`", nullable = false$length)`n    private $($_.Type) $($_.Name);" }
    }) -join "`n`n"
    $ctorFields = @('UUID id') + ($context.Fields | ForEach-Object { "$(Persistence-Type $_) $($_.Name)" }) + @('LocalDateTime createdAt', 'LocalDateTime updatedAt')
    $ctorFieldsText = $ctorFields -join ",`n            "
    $ctorAssign = (@('        this.id = id;') + ($context.Fields | ForEach-Object { "        this.$($_.Name) = $($_.Name);" }) + @('        this.createdAt = createdAt;', '        this.updatedAt = updatedAt;')) -join "`n"
    $accessors = ($context.Fields | ForEach-Object { $pt = Persistence-Type $_; $cap = Cap $_.Name; $getter = Getter $_; "    public $pt $getter() {`n        return $($_.Name);`n    }`n`n    public void set$cap($pt $($_.Name)) {`n        this.$($_.Name) = $($_.Name);`n    }" }) -join "`n`n"
    Write-Generated "infrastructure/src/main/java/springboot/infrastructure/$pkg/adapters/out/persistence/entity/${entity}JpaEntity.java" @"
package springboot.infrastructure.$pkg.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "$($context.Table)")
public class ${entity}JpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

$columns

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ${entity}JpaEntity() { }
    public ${entity}JpaEntity(
            $ctorFieldsText) {
$ctorAssign
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

$accessors

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
"@
    $toJpa = ($context.Fields | ForEach-Object { $cap = Cap $_.Name; $suffix = if (Is-Ref $_) { '.value()' } else { '' }; "        jpa.set$cap(domain.$($_.Name)()$suffix);" }) -join "`n"
    $toDomain = ($context.Fields | ForEach-Object { $getter = Getter $_; if (Is-Ref $_) { "new $($_.RefEntity)Id(jpa.$getter())" } else { "jpa.$getter()" } }) -join ",`n                "
    Write-Generated "infrastructure/src/main/java/springboot/infrastructure/$pkg/adapters/out/persistence/mappers/${entity}PersistenceMapper.java" @"
package springboot.infrastructure.$pkg.adapters.out.persistence.mappers;

import springboot.domain.$pkg.model.aggregate.$entity;
import springboot.domain.$pkg.model.valueobject.${entity}Id;
$refs
import springboot.infrastructure.$pkg.adapters.out.persistence.entity.${entity}JpaEntity;

public class ${entity}PersistenceMapper {
    public ${entity}JpaEntity toJpa($entity domain) {
        if (domain == null) { return null; }
        ${entity}JpaEntity jpa = new ${entity}JpaEntity();
        jpa.setId(domain.id().value());
$toJpa
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public $entity toDomain(${entity}JpaEntity jpa) {
        if (jpa == null) { return null; }
        return $entity.restore(
                new ${entity}Id(jpa.getId()),
                $toDomain,
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
"@
    Write-Generated "infrastructure/src/main/java/springboot/infrastructure/$pkg/adapters/out/persistence/repositories/${entity}JpaRepository.java" @"
package springboot.infrastructure.$pkg.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.$pkg.adapters.out.persistence.entity.${entity}JpaEntity;

public interface ${entity}JpaRepository extends JpaRepository<${entity}JpaEntity, UUID> {
$jpaExistsByCode
}
"@
    Write-Generated "infrastructure/src/main/java/springboot/infrastructure/$pkg/adapters/out/persistence/repositories/${entity}RepositoryAdapter.java" @"
package springboot.infrastructure.$pkg.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.$pkg.model.aggregate.$entity;
import springboot.domain.$pkg.model.valueobject.${entity}Id;
import springboot.domain.$pkg.port.repository.${entity}Repository;
import springboot.infrastructure.$pkg.adapters.out.persistence.entity.${entity}JpaEntity;
import springboot.infrastructure.$pkg.adapters.out.persistence.mappers.${entity}PersistenceMapper;

public class ${entity}RepositoryAdapter implements ${entity}Repository {
    private final ${entity}JpaRepository jpaRepository;
    private final ${entity}PersistenceMapper mapper;
    public ${entity}RepositoryAdapter(${entity}JpaRepository jpaRepository, ${entity}PersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public $entity save($entity aggregate) {
        ${entity}JpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<$entity> findById(${entity}Id id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<$entity> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
$adapterExistsByCode
    @Override public void delete($entity aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
"@
    Write-Generated "infrastructure/src/main/java/springboot/infrastructure/$pkg/config/${entity}BeansConfig.java" @"
package springboot.infrastructure.$pkg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.$pkg.usecase.*;
import springboot.domain.$pkg.port.repository.${entity}Repository;
import springboot.infrastructure.$pkg.adapters.out.persistence.mappers.${entity}PersistenceMapper;
import springboot.infrastructure.$pkg.adapters.out.persistence.repositories.${entity}JpaRepository;
import springboot.infrastructure.$pkg.adapters.out.persistence.repositories.${entity}RepositoryAdapter;

@Configuration
public class ${entity}BeansConfig {
    @Bean public ${entity}PersistenceMapper ${pkg}PersistenceMapper() { return new ${entity}PersistenceMapper(); }
    @Bean public ${entity}Repository ${pkg}Repository(${entity}JpaRepository repository, ${entity}PersistenceMapper mapper) {
        return new ${entity}RepositoryAdapter(repository, mapper);
    }
    @Bean public Register${entity}UseCase register${entity}UseCase(${entity}Repository r) { return new Register${entity}UseCase(r); }
    @Bean public Get${entity}ByIdUseCase get${entity}ByIdUseCase(${entity}Repository r) { return new Get${entity}ByIdUseCase(r); }
    @Bean public List${entity}UseCase list${entity}UseCase(${entity}Repository r) { return new List${entity}UseCase(r); }
    @Bean public Update${entity}UseCase update${entity}UseCase(${entity}Repository r) { return new Update${entity}UseCase(r); }
    @Bean public Delete${entity}UseCase delete${entity}UseCase(${entity}Repository r) { return new Delete${entity}UseCase(r); }
}
"@
}

function Generate-Tests($context) {
    $entity = $context.Entity; $pkg = $context.Package; $refs = Ref-Imports $context
    $codeField = Code-Field $context
    $fakeExistsByCode = if ($null -ne $codeField) { '        @Override public boolean existsByCode(String code) { return false; }' } else { '' }
    $samples = ($context.Fields | ForEach-Object { $_.Sample }) -join ",`n                "
    Write-Generated "domain/src/test/java/springboot/domain/$pkg/model/aggregate/${entity}Test.java" @"
package springboot.domain.$pkg.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.$pkg.event.${entity}RegisteredEvent;
$refs

class ${entity}Test {
    @Test void shouldRegisterCreatedEvent() {
        $entity aggregate = $entity.register(
                $samples);
        assertEquals(1, aggregate.domainEvents().size());
        ${entity}RegisteredEvent event = assertInstanceOf(${entity}RegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
"@
    Write-Generated "application/src/test/java/springboot/application/$pkg/usecase/Delete${entity}UseCaseTest.java" @"
package springboot.application.$pkg.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.$pkg.exception.${entity}NotFoundApplicationException;
import springboot.domain.$pkg.event.${entity}DeletedEvent;
import springboot.domain.$pkg.model.aggregate.$entity;
import springboot.domain.$pkg.model.valueobject.${entity}Id;
import springboot.domain.$pkg.port.repository.${entity}Repository;
$refs

class Delete${entity}UseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        $entity aggregate = $entity.register(
                $samples);
        FakeRepository repository = new FakeRepository(aggregate);
        ${entity}DeletedEvent event = new Delete${entity}UseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(${entity}NotFoundApplicationException.class,
                () -> new Delete${entity}UseCase(repository).execute(${entity}Id.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ${entity}Repository {
        private final $entity aggregate; private $entity deletedAggregate;
        private FakeRepository($entity aggregate) { this.aggregate = aggregate; }
        @Override public $entity save($entity value) { return value; }
        @Override public Optional<$entity> findById(${entity}Id id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<$entity> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }
$fakeExistsByCode
        @Override public void delete($entity value) { deletedAggregate = value; }
        private $entity deletedAggregate() { return deletedAggregate; }
    }
}
"@
}

foreach ($context in $Contexts) {
    Generate-Domain $context
    Generate-Application $context
    Generate-Infrastructure $context
    Generate-Tests $context
    Write-Output "Generated $($context.Table) -> $($context.Entity) -> $($context.Package)"
}
