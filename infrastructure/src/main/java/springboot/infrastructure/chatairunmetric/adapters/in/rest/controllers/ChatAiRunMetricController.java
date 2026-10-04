package springboot.infrastructure.chatairunmetric.adapters.in.rest.controllers;

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
import springboot.application.chatairunmetric.command.RegisterChatAiRunMetricCommand;
import springboot.application.chatairunmetric.command.UpdateChatAiRunMetricCommand;
import springboot.application.chatairunmetric.dto.ChatAiRunMetricResponse;
import springboot.application.chatairunmetric.usecase.DeleteChatAiRunMetricUseCase;
import springboot.application.chatairunmetric.usecase.GetChatAiRunMetricByIdUseCase;
import springboot.application.chatairunmetric.usecase.ListChatAiRunMetricUseCase;
import springboot.application.chatairunmetric.usecase.RegisterChatAiRunMetricUseCase;
import springboot.application.chatairunmetric.usecase.UpdateChatAiRunMetricUseCase;
import springboot.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;
import springboot.infrastructure.chatairunmetric.adapters.in.rest.dtos.CreateChatAiRunMetricRequest;
import springboot.infrastructure.chatairunmetric.adapters.in.rest.dtos.UpdateChatAiRunMetricRequest;

@RestController
@RequestMapping("/api/chat-ai-run-metrics")
public class ChatAiRunMetricController {
    private final RegisterChatAiRunMetricUseCase registerUseCase;
    private final GetChatAiRunMetricByIdUseCase getByIdUseCase;
    private final ListChatAiRunMetricUseCase listUseCase;
    private final UpdateChatAiRunMetricUseCase updateUseCase;
    private final DeleteChatAiRunMetricUseCase deleteUseCase;

    public ChatAiRunMetricController(RegisterChatAiRunMetricUseCase registerUseCase,
            GetChatAiRunMetricByIdUseCase getByIdUseCase, ListChatAiRunMetricUseCase listUseCase,
            UpdateChatAiRunMetricUseCase updateUseCase, DeleteChatAiRunMetricUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ChatAiRunMetricResponse> create(@Valid @RequestBody CreateChatAiRunMetricRequest request) {
        var command = new RegisterChatAiRunMetricCommand(
                        new ChatAiRunId(request.aiRunId()),
                        request.promptTokens(),
                        request.completionTokens(),
                        request.totalTokens(),
                        request.cost());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ChatAiRunMetricResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<ChatAiRunMetricResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ChatAiRunMetricId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatAiRunMetricResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateChatAiRunMetricRequest request) {
        var command = new UpdateChatAiRunMetricCommand(
                        new ChatAiRunMetricId(id),
                        new ChatAiRunId(request.aiRunId()),
                        request.promptTokens(),
                        request.completionTokens(),
                        request.totalTokens(),
                        request.cost());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ChatAiRunMetricId(id));
        return ResponseEntity.noContent().build();
    }
}
