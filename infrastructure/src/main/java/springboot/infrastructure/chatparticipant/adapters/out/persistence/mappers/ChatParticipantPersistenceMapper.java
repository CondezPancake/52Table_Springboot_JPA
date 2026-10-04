package springboot.infrastructure.chatparticipant.adapters.out.persistence.mappers;

import springboot.domain.chatparticipant.model.aggregate.ChatParticipant;
import springboot.domain.chatparticipant.model.valueobject.ChatParticipantId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.sendertype.model.valueobject.SenderTypeId;
import springboot.infrastructure.chatparticipant.adapters.out.persistence.entity.ChatParticipantJpaEntity;

public class ChatParticipantPersistenceMapper {
    public ChatParticipantJpaEntity toJpa(ChatParticipant domain) {
        if (domain == null) { return null; }
        ChatParticipantJpaEntity jpa = new ChatParticipantJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setConversationId(domain.conversationId().value());
        jpa.setParticipantTypeId(domain.participantTypeId().value());
        jpa.setPatientId(domain.patientId() == null ? null : domain.patientId().value());
        jpa.setProfessionalId(domain.professionalId() == null ? null : domain.professionalId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public ChatParticipant toDomain(ChatParticipantJpaEntity jpa) {
        if (jpa == null) { return null; }
        return ChatParticipant.restore(
                new ChatParticipantId(jpa.getId()),
                new ChatConversationId(jpa.getConversationId()),
                new SenderTypeId(jpa.getParticipantTypeId()),
                jpa.getPatientId() == null ? null : new PatientId(jpa.getPatientId()),
                jpa.getProfessionalId() == null ? null : new ProfessionalId(jpa.getProfessionalId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
