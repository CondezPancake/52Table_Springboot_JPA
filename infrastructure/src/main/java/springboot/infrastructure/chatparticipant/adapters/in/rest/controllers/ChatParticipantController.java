package springboot.infrastructure.chatparticipant.adapters.in.rest.controllers;

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
import springboot.application.chatparticipant.command.RegisterChatParticipantCommand;
import springboot.application.chatparticipant.command.UpdateChatParticipantCommand;
import springboot.application.chatparticipant.dto.ChatParticipantResponse;
import springboot.application.chatparticipant.usecase.DeleteChatParticipantUseCase;
import springboot.application.chatparticipant.usecase.GetChatParticipantByIdUseCase;
import springboot.application.chatparticipant.usecase.ListChatParticipantUseCase;
import springboot.application.chatparticipant.usecase.RegisterChatParticipantUseCase;
import springboot.application.chatparticipant.usecase.UpdateChatParticipantUseCase;
import springboot.domain.chatparticipant.model.valueobject.ChatParticipantId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.sendertype.model.valueobject.SenderTypeId;
import springboot.infrastructure.chatparticipant.adapters.in.rest.dtos.CreateChatParticipantRequest;
import springboot.infrastructure.chatparticipant.adapters.in.rest.dtos.UpdateChatParticipantRequest;

@RestController
@RequestMapping("/api/chat-participants")
public class ChatParticipantController {
    private final RegisterChatParticipantUseCase registerUseCase;
    private final GetChatParticipantByIdUseCase getByIdUseCase;
    private final ListChatParticipantUseCase listUseCase;
    private final UpdateChatParticipantUseCase updateUseCase;
    private final DeleteChatParticipantUseCase deleteUseCase;

    public ChatParticipantController(RegisterChatParticipantUseCase registerUseCase,
            GetChatParticipantByIdUseCase getByIdUseCase, ListChatParticipantUseCase listUseCase,
            UpdateChatParticipantUseCase updateUseCase, DeleteChatParticipantUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ChatParticipantResponse> create(@Valid @RequestBody CreateChatParticipantRequest request) {
        var command = new RegisterChatParticipantCommand(
                        new ChatConversationId(request.conversationId()),
                        new SenderTypeId(request.participantTypeId()),
                        request.patientId() == null ? null : new PatientId(request.patientId()),
                        request.professionalId() == null ? null : new ProfessionalId(request.professionalId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ChatParticipantResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<ChatParticipantResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ChatParticipantId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatParticipantResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateChatParticipantRequest request) {
        var command = new UpdateChatParticipantCommand(
                        new ChatParticipantId(id),
                        new ChatConversationId(request.conversationId()),
                        new SenderTypeId(request.participantTypeId()),
                        request.patientId() == null ? null : new PatientId(request.patientId()),
                        request.professionalId() == null ? null : new ProfessionalId(request.professionalId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ChatParticipantId(id));
        return ResponseEntity.noContent().build();
    }
}
