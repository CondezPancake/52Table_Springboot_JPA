package springboot.infrastructure.patientcontact.adapters.in.rest.controllers;

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
import springboot.application.patientcontact.command.RegisterPatientContactCommand;
import springboot.application.patientcontact.command.UpdatePatientContactCommand;
import springboot.application.patientcontact.dto.PatientContactResponse;
import springboot.application.patientcontact.usecase.DeletePatientContactUseCase;
import springboot.application.patientcontact.usecase.GetPatientContactByIdUseCase;
import springboot.application.patientcontact.usecase.ListPatientContactUseCase;
import springboot.application.patientcontact.usecase.RegisterPatientContactUseCase;
import springboot.application.patientcontact.usecase.UpdatePatientContactUseCase;
import springboot.domain.patientcontact.model.valueobject.PatientContactId;
import springboot.domain.contact.model.valueobject.ContactId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import springboot.infrastructure.patientcontact.adapters.in.rest.dtos.CreatePatientContactRequest;
import springboot.infrastructure.patientcontact.adapters.in.rest.dtos.UpdatePatientContactRequest;

@RestController
@RequestMapping("/api/patient-contacts")
public class PatientContactController {
    private final RegisterPatientContactUseCase registerUseCase;
    private final GetPatientContactByIdUseCase getByIdUseCase;
    private final ListPatientContactUseCase listUseCase;
    private final UpdatePatientContactUseCase updateUseCase;
    private final DeletePatientContactUseCase deleteUseCase;

    public PatientContactController(RegisterPatientContactUseCase registerUseCase,
            GetPatientContactByIdUseCase getByIdUseCase, ListPatientContactUseCase listUseCase,
            UpdatePatientContactUseCase updateUseCase, DeletePatientContactUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<PatientContactResponse> create(@Valid @RequestBody CreatePatientContactRequest request) {
        var command = new RegisterPatientContactCommand(
                        new ContactId(request.contactId()),
                        new PatientId(request.patientId()),
                        request.primaryContact(),
                        request.emergencyContact(),
                        new RelationshipTypeId(request.relationshipTypeId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<PatientContactResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<PatientContactResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new PatientContactId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientContactResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdatePatientContactRequest request) {
        var command = new UpdatePatientContactCommand(
                        new PatientContactId(id),
                        new ContactId(request.contactId()),
                        new PatientId(request.patientId()),
                        request.primaryContact(),
                        request.emergencyContact(),
                        new RelationshipTypeId(request.relationshipTypeId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new PatientContactId(id));
        return ResponseEntity.noContent().build();
    }
}
