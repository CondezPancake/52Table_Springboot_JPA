package springboot.domain.chatparticipant.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.chatparticipant.event.ChatParticipantRegisteredEvent;
import springboot.domain.chatparticipant.event.ChatParticipantUpdatedEvent;
import springboot.domain.chatparticipant.model.valueobject.ChatParticipantId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.sendertype.model.valueobject.SenderTypeId;

public class ChatParticipant extends AggregateRoot {
    private final ChatParticipantId id;
    private ChatConversationId conversationId;
    private SenderTypeId participantTypeId;
    private PatientId patientId;
    private ProfessionalId professionalId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ChatParticipant(
            ChatParticipantId id,
            ChatConversationId conversationId,
            SenderTypeId participantTypeId,
            PatientId patientId,
            ProfessionalId professionalId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.participantTypeId = Objects.requireNonNull(participantTypeId, "participantTypeId must not be null");
        this.patientId = patientId;
        this.professionalId = professionalId;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static ChatParticipant register(
            ChatConversationId conversationId,
            SenderTypeId participantTypeId,
            PatientId patientId,
            ProfessionalId professionalId) {
        ChatParticipantId id = ChatParticipantId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatParticipant aggregate = new ChatParticipant(
                id,
                conversationId,
                participantTypeId,
                patientId,
                professionalId,
                now,
                now);
        aggregate.recordEvent(new ChatParticipantRegisteredEvent(id, now));
        return aggregate;
    }

    public static ChatParticipant restore(
            ChatParticipantId id,
            ChatConversationId conversationId,
            SenderTypeId participantTypeId,
            PatientId patientId,
            ProfessionalId professionalId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ChatParticipant(
                id,
                conversationId,
                participantTypeId,
                patientId,
                professionalId,
                createdAt,
                updatedAt);
    }

    public void update(
            ChatConversationId conversationId,
            SenderTypeId participantTypeId,
            PatientId patientId,
            ProfessionalId professionalId) {
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.participantTypeId = Objects.requireNonNull(participantTypeId, "participantTypeId must not be null");
        this.patientId = patientId;
        this.professionalId = professionalId;
        this.updatedAt = LocalDateTime.now();
        recordEvent(new ChatParticipantUpdatedEvent(
                        this.id,
                        this.conversationId,
                        this.participantTypeId,
                        this.patientId,
                        this.professionalId,
                        this.updatedAt));
    }

    public ChatParticipantId id() {
        return id;
    }

    public ChatConversationId conversationId() {
        return conversationId;
    }

    public SenderTypeId participantTypeId() {
        return participantTypeId;
    }

    public PatientId patientId() {
        return patientId;
    }

    public ProfessionalId professionalId() {
        return professionalId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
