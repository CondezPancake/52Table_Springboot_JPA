package springboot.infrastructure.escalationstatus.adapters.in.rest.controllers;

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
import springboot.application.escalationstatus.command.RegisterEscalationStatusCommand;
import springboot.application.escalationstatus.command.UpdateEscalationStatusCommand;
import springboot.application.escalationstatus.dto.EscalationStatusResponse;
import springboot.application.escalationstatus.usecase.DeleteEscalationStatusUseCase;
import springboot.application.escalationstatus.usecase.GetEscalationStatusByIdUseCase;
import springboot.application.escalationstatus.usecase.ListEscalationStatusUseCase;
import springboot.application.escalationstatus.usecase.RegisterEscalationStatusUseCase;
import springboot.application.escalationstatus.usecase.UpdateEscalationStatusUseCase;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;

import springboot.infrastructure.escalationstatus.adapters.in.rest.dtos.CreateEscalationStatusRequest;
import springboot.infrastructure.escalationstatus.adapters.in.rest.dtos.UpdateEscalationStatusRequest;

@RestController
@RequestMapping("/api/escalation-statuses")
public class EscalationStatusController {
    private final RegisterEscalationStatusUseCase registerUseCase;
    private final GetEscalationStatusByIdUseCase getByIdUseCase;
    private final ListEscalationStatusUseCase listUseCase;
    private final UpdateEscalationStatusUseCase updateUseCase;
    private final DeleteEscalationStatusUseCase deleteUseCase;

    public EscalationStatusController(RegisterEscalationStatusUseCase registerUseCase,
            GetEscalationStatusByIdUseCase getByIdUseCase, ListEscalationStatusUseCase listUseCase,
            UpdateEscalationStatusUseCase updateUseCase, DeleteEscalationStatusUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<EscalationStatusResponse> create(@Valid @RequestBody CreateEscalationStatusRequest request) {
        var command = new RegisterEscalationStatusCommand(
                        request.nameStatus());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<EscalationStatusResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<EscalationStatusResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new EscalationStatusId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscalationStatusResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateEscalationStatusRequest request) {
        var command = new UpdateEscalationStatusCommand(
                        new EscalationStatusId(id),
                        request.nameStatus());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new EscalationStatusId(id));
        return ResponseEntity.noContent().build();
    }
}
