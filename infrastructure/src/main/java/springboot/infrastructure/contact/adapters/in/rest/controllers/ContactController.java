package springboot.infrastructure.contact.adapters.in.rest.controllers;

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
import springboot.application.contact.command.RegisterContactCommand;
import springboot.application.contact.command.UpdateContactCommand;
import springboot.application.contact.dto.ContactResponse;
import springboot.application.contact.usecase.DeleteContactUseCase;
import springboot.application.contact.usecase.GetContactByIdUseCase;
import springboot.application.contact.usecase.ListContactUseCase;
import springboot.application.contact.usecase.RegisterContactUseCase;
import springboot.application.contact.usecase.UpdateContactUseCase;
import springboot.domain.contact.model.valueobject.ContactId;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.infrastructure.contact.adapters.in.rest.dtos.CreateContactRequest;
import springboot.infrastructure.contact.adapters.in.rest.dtos.UpdateContactRequest;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private final RegisterContactUseCase registerUseCase;
    private final GetContactByIdUseCase getByIdUseCase;
    private final ListContactUseCase listUseCase;
    private final UpdateContactUseCase updateUseCase;
    private final DeleteContactUseCase deleteUseCase;

    public ContactController(RegisterContactUseCase registerUseCase,
            GetContactByIdUseCase getByIdUseCase, ListContactUseCase listUseCase,
            UpdateContactUseCase updateUseCase, DeleteContactUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ContactResponse> create(@Valid @RequestBody CreateContactRequest request) {
        var command = new RegisterContactCommand(
                        request.fullName(),
                        request.email(),
                        request.notes(),
                        new CityMunicipalityId(request.cityId()),
                        new ProfessionalId(request.createdBy()),
                        request.updatedBy() == null ? null : new ProfessionalId(request.updatedBy()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ContactResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ContactId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateContactRequest request) {
        var command = new UpdateContactCommand(
                        new ContactId(id),
                        request.fullName(),
                        request.email(),
                        request.notes(),
                        new CityMunicipalityId(request.cityId()),
                        new ProfessionalId(request.createdBy()),
                        request.updatedBy() == null ? null : new ProfessionalId(request.updatedBy()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ContactId(id));
        return ResponseEntity.noContent().build();
    }
}
