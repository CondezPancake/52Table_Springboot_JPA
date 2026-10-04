package springboot.infrastructure.chatescalationstatushistory.adapters.in.rest.controllers;

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
import springboot.application.chatescalationstatushistory.command.RegisterChatEscalationStatusHistoryCommand;
import springboot.application.chatescalationstatushistory.command.UpdateChatEscalationStatusHistoryCommand;
import springboot.application.chatescalationstatushistory.dto.ChatEscalationStatusHistoryResponse;
import springboot.application.chatescalationstatushistory.usecase.DeleteChatEscalationStatusHistoryUseCase;
import springboot.application.chatescalationstatushistory.usecase.GetChatEscalationStatusHistoryByIdUseCase;
import springboot.application.chatescalationstatushistory.usecase.ListChatEscalationStatusHistoryUseCase;
import springboot.application.chatescalationstatushistory.usecase.RegisterChatEscalationStatusHistoryUseCase;
import springboot.application.chatescalationstatushistory.usecase.UpdateChatEscalationStatusHistoryUseCase;
import springboot.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;
import springboot.infrastructure.chatescalationstatushistory.adapters.in.rest.dtos.CreateChatEscalationStatusHistoryRequest;
import springboot.infrastructure.chatescalationstatushistory.adapters.in.rest.dtos.UpdateChatEscalationStatusHistoryRequest;

@RestController
@RequestMapping("/api/chat-escalation-status-history")
public class ChatEscalationStatusHistoryController {
    private final RegisterChatEscalationStatusHistoryUseCase registerUseCase;
    private final GetChatEscalationStatusHistoryByIdUseCase getByIdUseCase;
    private final ListChatEscalationStatusHistoryUseCase listUseCase;
    private final UpdateChatEscalationStatusHistoryUseCase updateUseCase;
    private final DeleteChatEscalationStatusHistoryUseCase deleteUseCase;

    public ChatEscalationStatusHistoryController(RegisterChatEscalationStatusHistoryUseCase registerUseCase,
            GetChatEscalationStatusHistoryByIdUseCase getByIdUseCase, ListChatEscalationStatusHistoryUseCase listUseCase,
            UpdateChatEscalationStatusHistoryUseCase updateUseCase, DeleteChatEscalationStatusHistoryUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ChatEscalationStatusHistoryResponse> create(@Valid @RequestBody CreateChatEscalationStatusHistoryRequest request) {
        var command = new RegisterChatEscalationStatusHistoryCommand(
                        new ChatEscalationId(request.escalationId()),
                        new EscalationStatusId(request.escalationStatusId()),
                        request.changedAt());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ChatEscalationStatusHistoryResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<ChatEscalationStatusHistoryResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ChatEscalationStatusHistoryId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatEscalationStatusHistoryResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateChatEscalationStatusHistoryRequest request) {
        var command = new UpdateChatEscalationStatusHistoryCommand(
                        new ChatEscalationStatusHistoryId(id),
                        new ChatEscalationId(request.escalationId()),
                        new EscalationStatusId(request.escalationStatusId()),
                        request.changedAt());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ChatEscalationStatusHistoryId(id));
        return ResponseEntity.noContent().build();
    }
}
