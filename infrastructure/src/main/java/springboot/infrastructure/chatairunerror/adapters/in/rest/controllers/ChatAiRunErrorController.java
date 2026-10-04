package springboot.infrastructure.chatairunerror.adapters.in.rest.controllers;

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
import springboot.application.chatairunerror.command.RegisterChatAiRunErrorCommand;
import springboot.application.chatairunerror.command.UpdateChatAiRunErrorCommand;
import springboot.application.chatairunerror.dto.ChatAiRunErrorResponse;
import springboot.application.chatairunerror.usecase.DeleteChatAiRunErrorUseCase;
import springboot.application.chatairunerror.usecase.GetChatAiRunErrorByIdUseCase;
import springboot.application.chatairunerror.usecase.ListChatAiRunErrorUseCase;
import springboot.application.chatairunerror.usecase.RegisterChatAiRunErrorUseCase;
import springboot.application.chatairunerror.usecase.UpdateChatAiRunErrorUseCase;
import springboot.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;
import springboot.infrastructure.chatairunerror.adapters.in.rest.dtos.CreateChatAiRunErrorRequest;
import springboot.infrastructure.chatairunerror.adapters.in.rest.dtos.UpdateChatAiRunErrorRequest;

@RestController
@RequestMapping("/api/chat-ai-run-errors")
public class ChatAiRunErrorController {
    private final RegisterChatAiRunErrorUseCase registerUseCase;
    private final GetChatAiRunErrorByIdUseCase getByIdUseCase;
    private final ListChatAiRunErrorUseCase listUseCase;
    private final UpdateChatAiRunErrorUseCase updateUseCase;
    private final DeleteChatAiRunErrorUseCase deleteUseCase;

    public ChatAiRunErrorController(RegisterChatAiRunErrorUseCase registerUseCase,
            GetChatAiRunErrorByIdUseCase getByIdUseCase, ListChatAiRunErrorUseCase listUseCase,
            UpdateChatAiRunErrorUseCase updateUseCase, DeleteChatAiRunErrorUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ChatAiRunErrorResponse> create(@Valid @RequestBody CreateChatAiRunErrorRequest request) {
        var command = new RegisterChatAiRunErrorCommand(
                        new ChatAiRunId(request.aiRunId()),
                        request.errorMessage(),
                        request.errorCode(),
                        request.providerErrorId());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ChatAiRunErrorResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<ChatAiRunErrorResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ChatAiRunErrorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatAiRunErrorResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateChatAiRunErrorRequest request) {
        var command = new UpdateChatAiRunErrorCommand(
                        new ChatAiRunErrorId(id),
                        new ChatAiRunId(request.aiRunId()),
                        request.errorMessage(),
                        request.errorCode(),
                        request.providerErrorId());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ChatAiRunErrorId(id));
        return ResponseEntity.noContent().build();
    }
}
