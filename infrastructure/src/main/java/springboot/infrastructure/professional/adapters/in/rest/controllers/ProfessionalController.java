package springboot.infrastructure.professional.adapters.in.rest.controllers;

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
import springboot.application.professional.command.RegisterProfessionalCommand;
import springboot.application.professional.command.UpdateProfessionalCommand;
import springboot.application.professional.dto.ProfessionalResponse;
import springboot.application.professional.usecase.DeleteProfessionalUseCase;
import springboot.application.professional.usecase.GetProfessionalByIdUseCase;
import springboot.application.professional.usecase.ListProfessionalUseCase;
import springboot.application.professional.usecase.RegisterProfessionalUseCase;
import springboot.application.professional.usecase.UpdateProfessionalUseCase;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.documenttype.model.valueobject.DocumentTypeId;
import springboot.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import springboot.infrastructure.professional.adapters.in.rest.dtos.CreateProfessionalRequest;
import springboot.infrastructure.professional.adapters.in.rest.dtos.UpdateProfessionalRequest;

@RestController
@RequestMapping("/api/professionals")
public class ProfessionalController {
    private final RegisterProfessionalUseCase registerUseCase;
    private final GetProfessionalByIdUseCase getByIdUseCase;
    private final ListProfessionalUseCase listUseCase;
    private final UpdateProfessionalUseCase updateUseCase;
    private final DeleteProfessionalUseCase deleteUseCase;

    public ProfessionalController(RegisterProfessionalUseCase registerUseCase,
            GetProfessionalByIdUseCase getByIdUseCase, ListProfessionalUseCase listUseCase,
            UpdateProfessionalUseCase updateUseCase, DeleteProfessionalUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ProfessionalResponse> create(@Valid @RequestBody CreateProfessionalRequest request) {
        var command = new RegisterProfessionalCommand(
                        new DocumentTypeId(request.documentTypeId()),
                        request.documentNumber(),
                        request.firstName(),
                        request.lastName(),
                        new ProfessionalTypeId(request.professionalTypeId()),
                        request.licenseNumber(),
                        request.active(),
                        new CityMunicipalityId(request.cityId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ProfessionalId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateProfessionalRequest request) {
        var command = new UpdateProfessionalCommand(
                        new ProfessionalId(id),
                        new DocumentTypeId(request.documentTypeId()),
                        request.documentNumber(),
                        request.firstName(),
                        request.lastName(),
                        new ProfessionalTypeId(request.professionalTypeId()),
                        request.licenseNumber(),
                        request.active(),
                        new CityMunicipalityId(request.cityId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ProfessionalId(id));
        return ResponseEntity.noContent().build();
    }
}
