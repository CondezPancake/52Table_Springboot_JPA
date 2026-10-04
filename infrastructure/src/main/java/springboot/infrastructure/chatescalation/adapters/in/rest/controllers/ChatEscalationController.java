package springboot.infrastructure.chatescalation.adapters.in.rest.controllers;

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
import springboot.application.chatescalation.command.RegisterChatEscalationCommand;
import springboot.application.chatescalation.command.UpdateChatEscalationCommand;
import springboot.application.chatescalation.dto.ChatEscalationResponse;
import springboot.application.chatescalation.usecase.DeleteChatEscalationUseCase;
import springboot.application.chatescalation.usecase.GetChatEscalationByIdUseCase;
import springboot.application.chatescalation.usecase.ListChatEscalationUseCase;
import springboot.application.chatescalation.usecase.RegisterChatEscalationUseCase;
import springboot.application.chatescalation.usecase.UpdateChatEscalationUseCase;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;
import springboot.infrastructure.chatescalation.adapters.in.rest.dtos.CreateChatEscalationRequest;
import springboot.infrastructure.chatescalation.adapters.in.rest.dtos.UpdateChatEscalationRequest;

@RestController
@RequestMapping("/api/chat-escalations")
public class ChatEscalationController {
    private final RegisterChatEscalationUseCase registerUseCase;
    private final GetChatEscalationByIdUseCase getByIdUseCase;
    private final ListChatEscalationUseCase listUseCase;
    private final UpdateChatEscalationUseCase updateUseCase;
    private final DeleteChatEscalationUseCase deleteUseCase;

    public ChatEscalationController(RegisterChatEscalationUseCase registerUseCase,
            GetChatEscalationByIdUseCase getByIdUseCase, ListChatEscalationUseCase listUseCase,
            UpdateChatEscalationUseCase updateUseCase, DeleteChatEscalationUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ChatEscalationResponse> create(@Valid @RequestBody CreateChatEscalationRequest request) {
        var command = new RegisterChatEscalationCommand(
                        new ChatConversationId(request.conversationId()),
                        new EscalationStatusId(request.statusId()),
                        request.fromAi(),
                        request.reason());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ChatEscalationResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<ChatEscalationResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ChatEscalationId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatEscalationResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateChatEscalationRequest request) {
        var command = new UpdateChatEscalationCommand(
                        new ChatEscalationId(id),
                        new ChatConversationId(request.conversationId()),
                        new EscalationStatusId(request.statusId()),
                        request.fromAi(),
                        request.reason());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ChatEscalationId(id));
        return ResponseEntity.noContent().build();
    }
}
