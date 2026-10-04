package springboot.infrastructure.chatconversation.adapters.in.rest.controllers;

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
import springboot.application.chatconversation.command.RegisterChatConversationCommand;
import springboot.application.chatconversation.command.UpdateChatConversationCommand;
import springboot.application.chatconversation.dto.ChatConversationResponse;
import springboot.application.chatconversation.usecase.DeleteChatConversationUseCase;
import springboot.application.chatconversation.usecase.GetChatConversationByIdUseCase;
import springboot.application.chatconversation.usecase.ListChatConversationUseCase;
import springboot.application.chatconversation.usecase.RegisterChatConversationUseCase;
import springboot.application.chatconversation.usecase.UpdateChatConversationUseCase;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;
import springboot.domain.priority.model.valueobject.PriorityId;
import springboot.infrastructure.chatconversation.adapters.in.rest.dtos.CreateChatConversationRequest;
import springboot.infrastructure.chatconversation.adapters.in.rest.dtos.UpdateChatConversationRequest;

@RestController
@RequestMapping("/api/chat-conversations")
public class ChatConversationController {
    private final RegisterChatConversationUseCase registerUseCase;
    private final GetChatConversationByIdUseCase getByIdUseCase;
    private final ListChatConversationUseCase listUseCase;
    private final UpdateChatConversationUseCase updateUseCase;
    private final DeleteChatConversationUseCase deleteUseCase;

    public ChatConversationController(RegisterChatConversationUseCase registerUseCase,
            GetChatConversationByIdUseCase getByIdUseCase, ListChatConversationUseCase listUseCase,
            UpdateChatConversationUseCase updateUseCase, DeleteChatConversationUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ChatConversationResponse> create(@Valid @RequestBody CreateChatConversationRequest request) {
        var command = new RegisterChatConversationCommand(
                        new ConversationStatusId(request.conversationStatusId()),
                        new PriorityId(request.priorityId()),
                        request.lastMessageAt(),
                        request.closed(),
                        request.closedAt(),
                        request.closedBy());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ChatConversationResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<ChatConversationResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ChatConversationId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatConversationResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateChatConversationRequest request) {
        var command = new UpdateChatConversationCommand(
                        new ChatConversationId(id),
                        new ConversationStatusId(request.conversationStatusId()),
                        new PriorityId(request.priorityId()),
                        request.lastMessageAt(),
                        request.closed(),
                        request.closedAt(),
                        request.closedBy());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ChatConversationId(id));
        return ResponseEntity.noContent().build();
    }
}
