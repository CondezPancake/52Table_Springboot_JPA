package springboot.infrastructure.conversationstatus.adapters.in.rest.controllers;

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
import springboot.application.conversationstatus.command.RegisterConversationStatusCommand;
import springboot.application.conversationstatus.command.UpdateConversationStatusCommand;
import springboot.application.conversationstatus.dto.ConversationStatusResponse;
import springboot.application.conversationstatus.usecase.DeleteConversationStatusUseCase;
import springboot.application.conversationstatus.usecase.GetConversationStatusByIdUseCase;
import springboot.application.conversationstatus.usecase.ListConversationStatusUseCase;
import springboot.application.conversationstatus.usecase.RegisterConversationStatusUseCase;
import springboot.application.conversationstatus.usecase.UpdateConversationStatusUseCase;
import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;

import springboot.infrastructure.conversationstatus.adapters.in.rest.dtos.CreateConversationStatusRequest;
import springboot.infrastructure.conversationstatus.adapters.in.rest.dtos.UpdateConversationStatusRequest;

@RestController
@RequestMapping("/api/conversation-statuses")
public class ConversationStatusController {
    private final RegisterConversationStatusUseCase registerUseCase;
    private final GetConversationStatusByIdUseCase getByIdUseCase;
    private final ListConversationStatusUseCase listUseCase;
    private final UpdateConversationStatusUseCase updateUseCase;
    private final DeleteConversationStatusUseCase deleteUseCase;

    public ConversationStatusController(RegisterConversationStatusUseCase registerUseCase,
            GetConversationStatusByIdUseCase getByIdUseCase, ListConversationStatusUseCase listUseCase,
            UpdateConversationStatusUseCase updateUseCase, DeleteConversationStatusUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ConversationStatusResponse> create(@Valid @RequestBody CreateConversationStatusRequest request) {
        var command = new RegisterConversationStatusCommand(
                        request.nameStatus());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ConversationStatusResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<ConversationStatusResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ConversationStatusId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConversationStatusResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateConversationStatusRequest request) {
        var command = new UpdateConversationStatusCommand(
                        new ConversationStatusId(id),
                        request.nameStatus());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ConversationStatusId(id));
        return ResponseEntity.noContent().build();
    }
}
