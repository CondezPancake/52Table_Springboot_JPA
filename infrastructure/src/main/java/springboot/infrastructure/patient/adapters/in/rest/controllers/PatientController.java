package springboot.infrastructure.patient.adapters.in.rest.controllers;

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
import springboot.application.patient.command.RegisterPatientCommand;
import springboot.application.patient.command.UpdatePatientCommand;
import springboot.application.patient.dto.PatientResponse;
import springboot.application.patient.usecase.DeletePatientUseCase;
import springboot.application.patient.usecase.GetPatientByIdUseCase;
import springboot.application.patient.usecase.ListPatientUseCase;
import springboot.application.patient.usecase.RegisterPatientUseCase;
import springboot.application.patient.usecase.UpdatePatientUseCase;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.documenttype.model.valueobject.DocumentTypeId;
import springboot.domain.gender.model.valueobject.GenderId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.infrastructure.patient.adapters.in.rest.dtos.CreatePatientRequest;
import springboot.infrastructure.patient.adapters.in.rest.dtos.UpdatePatientRequest;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final RegisterPatientUseCase registerUseCase;
    private final GetPatientByIdUseCase getByIdUseCase;
    private final ListPatientUseCase listUseCase;
    private final UpdatePatientUseCase updateUseCase;
    private final DeletePatientUseCase deleteUseCase;

    public PatientController(RegisterPatientUseCase registerUseCase,
            GetPatientByIdUseCase getByIdUseCase, ListPatientUseCase listUseCase,
            UpdatePatientUseCase updateUseCase, DeletePatientUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<PatientResponse> create(@Valid @RequestBody CreatePatientRequest request) {
        var command = new RegisterPatientCommand(
                        new DocumentTypeId(request.documentTypeId()),
                        request.documentNumber(),
                        request.firstName(),
                        request.middleName(),
                        request.lastName(),
                        request.secondLastName(),
                        request.birthDate(),
                        new GenderId(request.biologicalSexId()),
                        new GenderId(request.genderIdentityId()),
                        request.email(),
                        request.phone(),
                        request.address(),
                        request.active(),
                        request.createdBy() == null ? null : new ProfessionalId(request.createdBy()),
                        request.updatedBy() == null ? null : new ProfessionalId(request.updatedBy()),
                        new CityMunicipalityId(request.cityId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new PatientId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdatePatientRequest request) {
        var command = new UpdatePatientCommand(
                        new PatientId(id),
                        new DocumentTypeId(request.documentTypeId()),
                        request.documentNumber(),
                        request.firstName(),
                        request.middleName(),
                        request.lastName(),
                        request.secondLastName(),
                        request.birthDate(),
                        new GenderId(request.biologicalSexId()),
                        new GenderId(request.genderIdentityId()),
                        request.email(),
                        request.phone(),
                        request.address(),
                        request.active(),
                        request.createdBy() == null ? null : new ProfessionalId(request.createdBy()),
                        request.updatedBy() == null ? null : new ProfessionalId(request.updatedBy()),
                        new CityMunicipalityId(request.cityId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new PatientId(id));
        return ResponseEntity.noContent().build();
    }
}
