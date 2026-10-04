package springboot.infrastructure.diagnosticsystem.adapters.in.rest.controllers;

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
import springboot.application.diagnosticsystem.command.RegisterDiagnosticSystemCommand;
import springboot.application.diagnosticsystem.command.UpdateDiagnosticSystemCommand;
import springboot.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import springboot.application.diagnosticsystem.usecase.DeleteDiagnosticSystemUseCase;
import springboot.application.diagnosticsystem.usecase.GetDiagnosticSystemByIdUseCase;
import springboot.application.diagnosticsystem.usecase.ListDiagnosticSystemUseCase;
import springboot.application.diagnosticsystem.usecase.RegisterDiagnosticSystemUseCase;
import springboot.application.diagnosticsystem.usecase.UpdateDiagnosticSystemUseCase;
import springboot.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

import springboot.infrastructure.diagnosticsystem.adapters.in.rest.dtos.CreateDiagnosticSystemRequest;
import springboot.infrastructure.diagnosticsystem.adapters.in.rest.dtos.UpdateDiagnosticSystemRequest;

@RestController
@RequestMapping("/api/diagnostic-systems")
public class DiagnosticSystemController {
    private final RegisterDiagnosticSystemUseCase registerUseCase;
    private final GetDiagnosticSystemByIdUseCase getByIdUseCase;
    private final ListDiagnosticSystemUseCase listUseCase;
    private final UpdateDiagnosticSystemUseCase updateUseCase;
    private final DeleteDiagnosticSystemUseCase deleteUseCase;

    public DiagnosticSystemController(RegisterDiagnosticSystemUseCase registerUseCase,
            GetDiagnosticSystemByIdUseCase getByIdUseCase, ListDiagnosticSystemUseCase listUseCase,
            UpdateDiagnosticSystemUseCase updateUseCase, DeleteDiagnosticSystemUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<DiagnosticSystemResponse> create(@Valid @RequestBody CreateDiagnosticSystemRequest request) {
        var command = new RegisterDiagnosticSystemCommand(
                        request.code(),
                        request.name(),
                        request.active(),
                        request.version());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<DiagnosticSystemResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticSystemResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new DiagnosticSystemId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticSystemResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateDiagnosticSystemRequest request) {
        var command = new UpdateDiagnosticSystemCommand(
                        new DiagnosticSystemId(id),
                        request.code(),
                        request.name(),
                        request.active(),
                        request.version());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new DiagnosticSystemId(id));
        return ResponseEntity.noContent().build();
    }
}
