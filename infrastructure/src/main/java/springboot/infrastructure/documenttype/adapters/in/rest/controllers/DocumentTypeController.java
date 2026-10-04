package springboot.infrastructure.documenttype.adapters.in.rest.controllers;

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
import springboot.application.documenttype.command.RegisterDocumentTypeCommand;
import springboot.application.documenttype.command.UpdateDocumentTypeCommand;
import springboot.application.documenttype.dto.DocumentTypeResponse;
import springboot.application.documenttype.usecase.DeleteDocumentTypeUseCase;
import springboot.application.documenttype.usecase.GetDocumentTypeByIdUseCase;
import springboot.application.documenttype.usecase.ListDocumentTypeUseCase;
import springboot.application.documenttype.usecase.RegisterDocumentTypeUseCase;
import springboot.application.documenttype.usecase.UpdateDocumentTypeUseCase;
import springboot.domain.documenttype.model.valueobject.DocumentTypeId;

import springboot.infrastructure.documenttype.adapters.in.rest.dtos.CreateDocumentTypeRequest;
import springboot.infrastructure.documenttype.adapters.in.rest.dtos.UpdateDocumentTypeRequest;

@RestController
@RequestMapping("/api/document-types")
public class DocumentTypeController {
    private final RegisterDocumentTypeUseCase registerUseCase;
    private final GetDocumentTypeByIdUseCase getByIdUseCase;
    private final ListDocumentTypeUseCase listUseCase;
    private final UpdateDocumentTypeUseCase updateUseCase;
    private final DeleteDocumentTypeUseCase deleteUseCase;

    public DocumentTypeController(RegisterDocumentTypeUseCase registerUseCase,
            GetDocumentTypeByIdUseCase getByIdUseCase, ListDocumentTypeUseCase listUseCase,
            UpdateDocumentTypeUseCase updateUseCase, DeleteDocumentTypeUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<DocumentTypeResponse> create(@Valid @RequestBody CreateDocumentTypeRequest request) {
        var command = new RegisterDocumentTypeCommand(
                        request.code(),
                        request.name(),
                        request.active());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<DocumentTypeResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentTypeResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new DocumentTypeId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentTypeResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateDocumentTypeRequest request) {
        var command = new UpdateDocumentTypeCommand(
                        new DocumentTypeId(id),
                        request.code(),
                        request.name(),
                        request.active());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new DocumentTypeId(id));
        return ResponseEntity.noContent().build();
    }
}
