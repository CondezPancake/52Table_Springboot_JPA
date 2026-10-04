package springboot.infrastructure.encountertype.adapters.in.rest.controllers;

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
import springboot.application.encountertype.command.RegisterEncounterTypeCommand;
import springboot.application.encountertype.command.UpdateEncounterTypeCommand;
import springboot.application.encountertype.dto.EncounterTypeResponse;
import springboot.application.encountertype.usecase.DeleteEncounterTypeUseCase;
import springboot.application.encountertype.usecase.GetEncounterTypeByIdUseCase;
import springboot.application.encountertype.usecase.ListEncounterTypeUseCase;
import springboot.application.encountertype.usecase.RegisterEncounterTypeUseCase;
import springboot.application.encountertype.usecase.UpdateEncounterTypeUseCase;
import springboot.domain.encountertype.model.valueobject.EncounterTypeId;

import springboot.infrastructure.encountertype.adapters.in.rest.dtos.CreateEncounterTypeRequest;
import springboot.infrastructure.encountertype.adapters.in.rest.dtos.UpdateEncounterTypeRequest;

@RestController
@RequestMapping("/api/encounter-types")
public class EncounterTypeController {
    private final RegisterEncounterTypeUseCase registerUseCase;
    private final GetEncounterTypeByIdUseCase getByIdUseCase;
    private final ListEncounterTypeUseCase listUseCase;
    private final UpdateEncounterTypeUseCase updateUseCase;
    private final DeleteEncounterTypeUseCase deleteUseCase;

    public EncounterTypeController(RegisterEncounterTypeUseCase registerUseCase,
            GetEncounterTypeByIdUseCase getByIdUseCase, ListEncounterTypeUseCase listUseCase,
            UpdateEncounterTypeUseCase updateUseCase, DeleteEncounterTypeUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<EncounterTypeResponse> create(@Valid @RequestBody CreateEncounterTypeRequest request) {
        var command = new RegisterEncounterTypeCommand(
                        request.code(),
                        request.name(),
                        request.active());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<EncounterTypeResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<EncounterTypeResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new EncounterTypeId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EncounterTypeResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateEncounterTypeRequest request) {
        var command = new UpdateEncounterTypeCommand(
                        new EncounterTypeId(id),
                        request.code(),
                        request.name(),
                        request.active());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new EncounterTypeId(id));
        return ResponseEntity.noContent().build();
    }
}
