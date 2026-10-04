package springboot.infrastructure.professionaltype.adapters.in.rest.controllers;

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
import springboot.application.professionaltype.command.RegisterProfessionalTypeCommand;
import springboot.application.professionaltype.command.UpdateProfessionalTypeCommand;
import springboot.application.professionaltype.dto.ProfessionalTypeResponse;
import springboot.application.professionaltype.usecase.DeleteProfessionalTypeUseCase;
import springboot.application.professionaltype.usecase.GetProfessionalTypeByIdUseCase;
import springboot.application.professionaltype.usecase.ListProfessionalTypeUseCase;
import springboot.application.professionaltype.usecase.RegisterProfessionalTypeUseCase;
import springboot.application.professionaltype.usecase.UpdateProfessionalTypeUseCase;
import springboot.domain.professionaltype.model.valueobject.ProfessionalTypeId;

import springboot.infrastructure.professionaltype.adapters.in.rest.dtos.CreateProfessionalTypeRequest;
import springboot.infrastructure.professionaltype.adapters.in.rest.dtos.UpdateProfessionalTypeRequest;

@RestController
@RequestMapping("/api/professional-types")
public class ProfessionalTypeController {
    private final RegisterProfessionalTypeUseCase registerUseCase;
    private final GetProfessionalTypeByIdUseCase getByIdUseCase;
    private final ListProfessionalTypeUseCase listUseCase;
    private final UpdateProfessionalTypeUseCase updateUseCase;
    private final DeleteProfessionalTypeUseCase deleteUseCase;

    public ProfessionalTypeController(RegisterProfessionalTypeUseCase registerUseCase,
            GetProfessionalTypeByIdUseCase getByIdUseCase, ListProfessionalTypeUseCase listUseCase,
            UpdateProfessionalTypeUseCase updateUseCase, DeleteProfessionalTypeUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ProfessionalTypeResponse> create(@Valid @RequestBody CreateProfessionalTypeRequest request) {
        var command = new RegisterProfessionalTypeCommand(
                        request.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalTypeResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalTypeResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ProfessionalTypeId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalTypeResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateProfessionalTypeRequest request) {
        var command = new UpdateProfessionalTypeCommand(
                        new ProfessionalTypeId(id),
                        request.name());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ProfessionalTypeId(id));
        return ResponseEntity.noContent().build();
    }
}
