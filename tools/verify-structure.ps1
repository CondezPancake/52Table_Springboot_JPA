[CmdletBinding()]
param(
    [string]$ProjectRoot
)

$ErrorActionPreference = 'Stop'
if ([string]::IsNullOrWhiteSpace($ProjectRoot)) {
    $ProjectRoot = Split-Path -Parent $PSScriptRoot
}
$ProjectRoot = [System.IO.Path]::GetFullPath($ProjectRoot)
$manifestPath = Join-Path $PSScriptRoot 'structure-manifest.json'
$manifest = Get-Content -Raw -LiteralPath $manifestPath | ConvertFrom-Json
$errors = [System.Collections.Generic.List[string]]::new()
$checkedJava = [System.Collections.Generic.HashSet[string]]::new([System.StringComparer]::OrdinalIgnoreCase)

function Add-Error([string]$message) {
    $script:errors.Add($message)
}

function Expand-ContextValue([string]$value, $context) {
    if ($null -eq $value) { return $null }
    return $value.Replace('{entity}', $context.entity).Replace('{package}', $context.package)
}

function Expected-Package([string]$relativePath) {
    $normalized = $relativePath.Replace('\', '/')
    if ($normalized -notmatch '/src/(?:main|test)/java/(.+)/[^/]+\.java$') { return $null }
    return $Matches[1].Replace('/', '.')
}

function Test-JavaFile([string]$relativePath, [string]$name, [string]$kind, [string]$extendsName, [string]$implementsName) {
    $fullPath = Join-Path $ProjectRoot $relativePath
    [void]$script:checkedJava.Add([System.IO.Path]::GetFullPath($fullPath))
    if (-not (Test-Path -LiteralPath $fullPath -PathType Leaf)) {
        Add-Error "Missing Java file: $relativePath"
        return
    }

    $content = Get-Content -Raw -LiteralPath $fullPath
    $expectedPackage = Expected-Package $relativePath
    if ($content -notmatch "(?m)^\s*package\s+$([regex]::Escape($expectedPackage));\s*$") {
        Add-Error "Package/path mismatch: $relativePath (expected $expectedPackage)"
    }

    $escapedName = [regex]::Escape($name)
    $declarationPattern = switch ($kind) {
        'abstract class' { "(?m)^\s*public\s+abstract\s+class\s+$escapedName\b" }
        'class' { "(?m)^\s*(?:public\s+)?class\s+$escapedName\b" }
        'record' { "(?m)^\s*(?:public\s+)?record\s+$escapedName\b" }
        'interface' { "(?m)^\s*(?:public\s+)?interface\s+$escapedName\b" }
        default { throw "Unsupported declaration kind in manifest: $kind" }
    }
    if ($content -notmatch $declarationPattern) {
        Add-Error "Wrong declaration type/name: $relativePath (expected $kind $name)"
    }
    if ($extendsName -and $content -notmatch "\bextends\s+$([regex]::Escape($extendsName))\b") {
        Add-Error "Missing essential inheritance in ${relativePath}: extends $extendsName"
    }
    if ($implementsName -and $content -notmatch "\bimplements\s+$([regex]::Escape($implementsName))\b") {
        Add-Error "Missing essential interface in ${relativePath}: implements $implementsName"
    }
}

function Test-ExactContextFiles([string]$baseRelative, $patterns, $context) {
    $base = Join-Path $ProjectRoot $baseRelative
    $expected = @($patterns | ForEach-Object {
        [System.IO.Path]::GetFullPath((Join-Path $base (Expand-ContextValue $_.path $context)))
    })
    $actual = if (Test-Path -LiteralPath $base) {
        @(Get-ChildItem -LiteralPath $base -Recurse -File -Filter '*.java' | ForEach-Object { $_.FullName })
    } else { @() }
    foreach ($extra in @($actual | Where-Object { $_ -notin $expected })) {
        Add-Error "Unexpected context file: $($extra.Substring($ProjectRoot.Length + 1))"
    }
}

foreach ($module in $manifest.modules) {
    $modulePath = Join-Path $ProjectRoot $module
    if (-not (Test-Path -LiteralPath $modulePath -PathType Container)) { Add-Error "Missing root module: $module" }
    if (-not (Test-Path -LiteralPath (Join-Path $modulePath 'pom.xml') -PathType Leaf)) { Add-Error "Missing module POM: $module/pom.xml" }
}

$rootPomPath = Join-Path $ProjectRoot 'pom.xml'
if (-not (Test-Path -LiteralPath $rootPomPath -PathType Leaf)) {
    Add-Error 'Missing root pom.xml'
} else {
    [xml]$rootPom = Get-Content -Raw -LiteralPath $rootPomPath
    $declaredModules = @($rootPom.project.modules.module | ForEach-Object { [string]$_ })
    if (@(Compare-Object -ReferenceObject @($manifest.modules) -DifferenceObject $declaredModules).Count -ne 0) {
        Add-Error "Root POM modules differ from manifest: $($declaredModules -join ', ')"
    }
    if ([string]$rootPom.project.packaging -ne 'pom') { Add-Error 'Root POM packaging must be pom' }
}

foreach ($module in $manifest.modules) {
    $modulePom = Get-Content -Raw -LiteralPath (Join-Path $ProjectRoot "$module/pom.xml")
    if ($modulePom -notmatch '<relativePath>\.\./pom\.xml</relativePath>') {
        Add-Error "$module/pom.xml must use ../pom.xml as parent relativePath"
    }
}

foreach ($legacyModule in $manifest.modules) {
    $legacyPath = Join-Path $ProjectRoot "src/main/java/$legacyModule"
    if (Test-Path -LiteralPath $legacyPath) { Add-Error "Nested Maven module is forbidden: src/main/java/$legacyModule" }
}

foreach ($file in $manifest.sharedJava) {
    Test-JavaFile $file.path $file.name $file.kind $file.extends $file.implements
}

foreach ($context in $manifest.contexts) {
    $entity = [string]$context.entity
    $package = [string]$context.package
    foreach ($file in $manifest.applicationFiles) {
        $relative = "application/src/main/java/springboot/application/$package/$(Expand-ContextValue $file.path $context)"
        Test-JavaFile $relative (Expand-ContextValue $file.name $context) $file.kind (Expand-ContextValue $file.extends $context) (Expand-ContextValue $file.implements $context)
    }
    foreach ($file in $manifest.domainFiles) {
        $relative = "domain/src/main/java/springboot/domain/$package/$(Expand-ContextValue $file.path $context)"
        Test-JavaFile $relative (Expand-ContextValue $file.name $context) $file.kind (Expand-ContextValue $file.extends $context) (Expand-ContextValue $file.implements $context)
    }
    foreach ($file in $manifest.infrastructureFiles) {
        $relative = "infrastructure/src/main/java/springboot/infrastructure/$package/$(Expand-ContextValue $file.path $context)"
        Test-JavaFile $relative (Expand-ContextValue $file.name $context) $file.kind (Expand-ContextValue $file.extends $context) (Expand-ContextValue $file.implements $context)
    }

    Test-JavaFile "domain/src/main/java/springboot/domain/common/exception/${entity}NotFoundException.java" "${entity}NotFoundException" 'class' 'RuntimeException' $null
    Test-JavaFile "application/src/test/java/springboot/application/$package/usecase/Delete${entity}UseCaseTest.java" "Delete${entity}UseCaseTest" 'class' $null $null
    Test-JavaFile "domain/src/test/java/springboot/domain/$package/model/aggregate/${entity}Test.java" "${entity}Test" 'class' $null $null

    Test-ExactContextFiles "application/src/main/java/springboot/application/$package" $manifest.applicationFiles $context
    Test-ExactContextFiles "domain/src/main/java/springboot/domain/$package" $manifest.domainFiles $context
    Test-ExactContextFiles "infrastructure/src/main/java/springboot/infrastructure/$package" $manifest.infrastructureFiles $context

    $useCases = @('Register', 'Get' + $entity + 'ById', 'List', 'Update', 'Delete')
    $useCaseFiles = @(
        "Register${entity}UseCase.java",
        "Get${entity}ByIdUseCase.java",
        "List${entity}UseCase.java",
        "Update${entity}UseCase.java",
        "Delete${entity}UseCase.java"
    )
    foreach ($useCaseFile in $useCaseFiles) {
        $path = Join-Path $ProjectRoot "application/src/main/java/springboot/application/$package/usecase/$useCaseFile"
        if (Test-Path -LiteralPath $path) {
            $content = Get-Content -Raw -LiteralPath $path
            if ($content -notmatch "private\s+final\s+${entity}Repository\s+\w+") { Add-Error "$useCaseFile must keep a private final ${entity}Repository" }
            if ($content -notmatch "public\s+[^\s]+(?:<[^>]+>)?\s+execute\s*\(") { Add-Error "$useCaseFile must expose execute(...)" }
        }
    }

    $controllerPath = Join-Path $ProjectRoot "infrastructure/src/main/java/springboot/infrastructure/$package/adapters/in/rest/controllers/${entity}Controller.java"
    $controller = Get-Content -Raw -LiteralPath $controllerPath
    if ($controller -notmatch '@RestController') { Add-Error "${entity}Controller must be a RestController" }
    $expectedRequestMapping = '@RequestMapping\("' + [regex]::Escape([string]$context.endpoint) + '"\)'
    if ($controller -notmatch $expectedRequestMapping) { Add-Error "Wrong endpoint for ${entity}Controller" }
    foreach ($method in @('create', 'findAll', 'findById', 'update', 'delete')) {
        if ($controller -notmatch "\b$method\s*\(") { Add-Error "${entity}Controller is missing $method" }
    }

    $configPath = Join-Path $ProjectRoot "infrastructure/src/main/java/springboot/infrastructure/$package/config/${entity}BeansConfig.java"
    $beanCount = ([regex]::Matches((Get-Content -Raw -LiteralPath $configPath), '@Bean\b')).Count
    if ($beanCount -ne 7) { Add-Error "${entity}BeansConfig must declare exactly 7 @Bean methods (found $beanCount)" }

    $mapperPath = Join-Path $ProjectRoot "infrastructure/src/main/java/springboot/infrastructure/$package/adapters/out/persistence/mappers/${entity}PersistenceMapper.java"
    $mapper = Get-Content -Raw -LiteralPath $mapperPath
    if ($mapper -notmatch 'if\s*\([^)]*==\s*null\)') { Add-Error "${entity}PersistenceMapper must preserve null mapping" }
    if ($mapper -notmatch "${entity}\.restore\s*\(") { Add-Error "${entity}PersistenceMapper must rebuild with ${entity}.restore" }

    $adapterPath = Join-Path $ProjectRoot "infrastructure/src/main/java/springboot/infrastructure/$package/adapters/out/persistence/repositories/${entity}RepositoryAdapter.java"
    $adapter = Get-Content -Raw -LiteralPath $adapterPath
    if ($adapter -notmatch 'deleteById\s*\(') { Add-Error "${entity}RepositoryAdapter must delete through deleteById" }

    $jpaEntityPath = Join-Path $ProjectRoot "infrastructure/src/main/java/springboot/infrastructure/$package/adapters/out/persistence/entity/${entity}JpaEntity.java"
    $jpaEntity = Get-Content -Raw -LiteralPath $jpaEntityPath
    $mappedColumns = @([regex]::Matches($jpaEntity, '@Column\(name\s*=\s*"([^"]+)"') | ForEach-Object { $_.Groups[1].Value } | Sort-Object)
    $expectedColumns = @($context.expectedColumns | Sort-Object)
    if (@(Compare-Object -ReferenceObject $expectedColumns -DifferenceObject $mappedColumns).Count -ne 0) {
        Add-Error "${entity}JpaEntity columns differ from the manifest/SQL contract"
    }
    $uniqueConstraints = @()
    if ($context.uniqueConstraints) {
        $uniqueConstraints = @($context.uniqueConstraints)
    } elseif ($context.uniqueConstraint) {
        $uniqueConstraints = @([pscustomobject]@{ name = $context.uniqueConstraint; column = $context.uniqueColumn })
    }
    foreach ($constraint in $uniqueConstraints) {
        $uniqueColumnPattern = 'columnNames\s*=\s*"' + [regex]::Escape([string]$constraint.column) + '"'
        if ($jpaEntity -notmatch [regex]::Escape([string]$constraint.name) -or $jpaEntity -notmatch $uniqueColumnPattern) {
            Add-Error "${entity}JpaEntity is missing UNIQUE $($constraint.name) on $($constraint.column)"
        }
    }
    $nullableColumns = @($context.nullableColumns)
    foreach ($column in $expectedColumns) {
        $columnPattern = '@Column\(name\s*=\s*"' + [regex]::Escape([string]$column) + '"[^)]*nullable\s*=\s*(true|false)'
        $columnMatch = [regex]::Match($jpaEntity, $columnPattern)
        if (-not $columnMatch.Success) {
            Add-Error "${entity}JpaEntity does not declare nullability for $column"
        } else {
            $expectedNullable = $column -in $nullableColumns
            $actualNullable = $columnMatch.Groups[1].Value -eq 'true'
            if ($expectedNullable -ne $actualNullable) { Add-Error "${entity}JpaEntity nullability differs for $column" }
        }
    }
    foreach ($column in @($context.uuidColumns | Where-Object { $_ })) {
        $uuidPattern = '@Column\(name\s*=\s*"' + [regex]::Escape([string]$column) + '"[^)]*columnDefinition\s*=\s*"char\(36\)"'
        if ($jpaEntity -notmatch $uuidPattern) { Add-Error "${entity}JpaEntity must map $column as CHAR(36)" }
    }
    foreach ($column in @($context.textColumns | Where-Object { $_ })) {
        $textPattern = '@Column\(name\s*=\s*"' + [regex]::Escape([string]$column) + '"[^)]*columnDefinition\s*=\s*"text"'
        if ($jpaEntity -notmatch $textPattern) { Add-Error "${entity}JpaEntity must map $column as TEXT" }
    }
    foreach ($column in @($context.jsonColumns | Where-Object { $_ })) {
        $jsonPattern = '(?s)@JdbcTypeCode\(SqlTypes\.JSON\)\s*@Column\(name\s*=\s*"' + [regex]::Escape([string]$column) + '"[^)]*columnDefinition\s*=\s*"json"'
        if ($jpaEntity -notmatch $jsonPattern) { Add-Error "${entity}JpaEntity must map $column as JSON" }
    }
    foreach ($decimal in @($context.decimalColumns | Where-Object { $_ })) {
        $decimalPattern = '@Column\(name\s*=\s*"' + [regex]::Escape([string]$decimal.column) + '"[^)]*precision\s*=\s*' + [regex]::Escape([string]$decimal.precision) + '[^)]*scale\s*=\s*' + [regex]::Escape([string]$decimal.scale)
        if ($jpaEntity -notmatch $decimalPattern) { Add-Error "${entity}JpaEntity precision/scale differs for $($decimal.column)" }
    }
    if ($context.javaTypes) {
        foreach ($javaType in $context.javaTypes.PSObject.Properties) {
            $typePattern = '(?s)@Column\(name\s*=\s*"' + [regex]::Escape([string]$javaType.Name) + '"[^)]*\)\s*private\s+' + [regex]::Escape([string]$javaType.Value) + '\s+'
            if ($jpaEntity -notmatch $typePattern) { Add-Error "${entity}JpaEntity must map $($javaType.Name) as $($javaType.Value)" }
        }
    }
    if ($context.lengths) {
        foreach ($length in $context.lengths.PSObject.Properties) {
            $lengthPattern = '@Column\(name\s*=\s*"' + [regex]::Escape([string]$length.Name) + '"[^)]*length\s*=\s*' + [regex]::Escape([string]$length.Value) + '(?:\D|\))'
            if ($jpaEntity -notmatch $lengthPattern) { Add-Error "${entity}JpaEntity length differs for $($length.Name)" }
        }
    }
    foreach ($property in @($context.nullableReferenceProperties | Where-Object { $_ })) {
        $nullSafePattern = 'request\.' + [regex]::Escape([string]$property) + '\(\)\s*==\s*null\s*\?\s*null\s*:'
        if ($controller -notmatch $nullSafePattern) { Add-Error "${entity}Controller must preserve null for optional reference $property" }
    }
    $expectsCreatedAt = if ($null -ne $context.PSObject.Properties['hasCreatedAt']) { [bool]$context.hasCreatedAt } else { [bool]$context.hasTimestamps }
    $expectsUpdatedAt = if ($null -ne $context.PSObject.Properties['hasUpdatedAt']) { [bool]$context.hasUpdatedAt } else { [bool]$context.hasTimestamps }
    $mapsCreatedAt = $jpaEntity -match '@Column\(name\s*=\s*"created_at"'
    $mapsUpdatedAt = $jpaEntity -match '@Column\(name\s*=\s*"updated_at"'
    if ($expectsCreatedAt -ne $mapsCreatedAt) { Add-Error "${entity}JpaEntity created_at presence differs from SQL" }
    if ($expectsUpdatedAt -ne $mapsUpdatedAt) { Add-Error "${entity}JpaEntity updated_at presence differs from SQL" }

    if ($context.codeProperty) {
        $domainRepository = Get-Content -Raw -LiteralPath (Join-Path $ProjectRoot "domain/src/main/java/springboot/domain/$package/port/repository/${entity}Repository.java")
        $jpaRepository = Get-Content -Raw -LiteralPath (Join-Path $ProjectRoot "infrastructure/src/main/java/springboot/infrastructure/$package/adapters/out/persistence/repositories/${entity}JpaRepository.java")
        if ($domainRepository -notmatch 'boolean\s+existsByCode\s*\(String\s+code\)') { Add-Error "${entity}Repository must expose existsByCode" }
        $codeProperty = [string]$context.codeProperty
        $derivedQuerySuffix = $codeProperty.Substring(0, 1).ToUpperInvariant() + $codeProperty.Substring(1)
        if ($jpaRepository -notmatch "boolean\s+existsBy$([regex]::Escape($derivedQuerySuffix))\s*\(String\s+code\)") { Add-Error "${entity}JpaRepository must query the real property $codeProperty" }
        if ($adapter -notmatch 'boolean\s+existsByCode\s*\(') { Add-Error "${entity}RepositoryAdapter must implement existsByCode" }
    } else {
        $domainRepository = Get-Content -Raw -LiteralPath (Join-Path $ProjectRoot "domain/src/main/java/springboot/domain/$package/port/repository/${entity}Repository.java")
        $jpaRepository = Get-Content -Raw -LiteralPath (Join-Path $ProjectRoot "infrastructure/src/main/java/springboot/infrastructure/$package/adapters/out/persistence/repositories/${entity}JpaRepository.java")
        if ($domainRepository -match 'existsByCode' -or $jpaRepository -match 'existsByCode') { Add-Error "${entity} must not invent existsByCode without a code field" }
    }
}

foreach ($resource in $manifest.requiredResources) {
    if (-not (Test-Path -LiteralPath (Join-Path $ProjectRoot $resource) -PathType Leaf)) { Add-Error "Missing required resource/config: $resource" }
}

$allJava = @(Get-ChildItem -LiteralPath $ProjectRoot -Recurse -File -Filter '*.java' | Where-Object {
    $_.FullName -notmatch '[\\/]target[\\/]' -and $_.FullName -notmatch '[\\/]\.maven-cache[\\/]'
})
$fqns = @{}
foreach ($java in $allJava) {
    $content = Get-Content -Raw -LiteralPath $java.FullName
    if ($content -match '(?m)^\s*package\s+([\w.]+);') {
        $fqn = "$($Matches[1]).$($java.BaseName)"
        if ($fqns.ContainsKey($fqn)) { Add-Error "Duplicate fully qualified class: $fqn" } else { $fqns[$fqn] = $java.FullName }
    } else {
        Add-Error "Java file without package: $($java.FullName.Substring($ProjectRoot.Length + 1))"
    }
}

foreach ($layer in @('domain', 'application')) {
    $layerRoot = Join-Path $ProjectRoot "$layer/src/main/java"
    foreach ($java in Get-ChildItem -LiteralPath $layerRoot -Recurse -File -Filter '*.java') {
        $content = Get-Content -Raw -LiteralPath $java.FullName
        if ($content -match '(?m)^\s*import\s+(?:org\.springframework|jakarta\.(?:persistence|validation))\.') {
            Add-Error "$layer must be free of Spring/JPA/Validation imports: $($java.FullName.Substring($ProjectRoot.Length + 1))"
        }
    }
}

$migrationRoot = Join-Path $ProjectRoot 'infrastructure/src/main/resources/db/migration'
$migrations = @(Get-ChildItem -LiteralPath $migrationRoot -File -Filter 'V*.sql')
if ($migrations.Count -ne 52) { Add-Error "Expected exactly 52 Flyway migrations, found $($migrations.Count)" }
$versions = @($migrations | ForEach-Object { if ($_.Name -match '^V(\d+)__') { [int]$Matches[1] } } | Sort-Object)
if (@(Compare-Object -ReferenceObject @(1..52) -DifferenceObject $versions).Count -ne 0) { Add-Error 'Flyway migration versions must be exactly V1 through V52' }

$applicationYaml = Get-Content -Raw -LiteralPath (Join-Path $ProjectRoot 'infrastructure/src/main/resources/application.yml')
foreach ($required in @('com.mysql.cj.jdbc.Driver', 'enabled: true', 'ddl-auto: validate')) {
    if ($applicationYaml -notmatch [regex]::Escape($required)) { Add-Error "application.yml is missing: $required" }
}
if ($applicationYaml -match '(?i)create-drop|postgresql') { Add-Error 'application.yml contains obsolete PostgreSQL/create-drop configuration' }

$generator = Get-Content -Raw -LiteralPath (Join-Path $ProjectRoot 'tools/generate-block.ps1')
if ($generator -match 'Join-Path\s+\$ProjectRoot\s+''src/main/java''') { Add-Error 'Generator still targets nested modules under src/main/java' }
if ($generator -notmatch '\$path\s*=\s*Join-Path\s+\$ProjectRoot\s+\$relativePath') { Add-Error 'Generator does not resolve output from the project root' }

if ($errors.Count -gt 0) {
    Write-Host "STRUCTURE VERIFICATION FAILED ($($errors.Count) error(s))" -ForegroundColor Red
    $errors | ForEach-Object { Write-Host " - $_" }
    exit 1
}

Write-Host "STRUCTURE VERIFICATION PASSED" -ForegroundColor Green
Write-Host "Contexts: $($manifest.contexts.Count); migrations: $($migrations.Count); Java files: $($allJava.Count)"
