package springboot.infrastructure.chatescalationassignment.adapters.out.persistence.mappers;

import springboot.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import springboot.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.infrastructure.chatescalationassignment.adapters.out.persistence.entity.ChatEscalationAssignmentJpaEntity;

public class ChatEscalationAssignmentPersistenceMapper {
    public ChatEscalationAssignmentJpaEntity toJpa(ChatEscalationAssignment domain) {
        if (domain == null) { return null; }
        ChatEscalationAssignmentJpaEntity jpa = new ChatEscalationAssignmentJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setEscalationId(domain.escalationId().value());
        jpa.setProfessionalId(domain.professionalId().value());
        jpa.setAssignedAt(domain.assignedAt());

        return jpa;
    }

    public ChatEscalationAssignment toDomain(ChatEscalationAssignmentJpaEntity jpa) {
        if (jpa == null) { return null; }
        return ChatEscalationAssignment.restore(
                new ChatEscalationAssignmentId(jpa.getId()),
                new ChatEscalationId(jpa.getEscalationId()),
                new ProfessionalId(jpa.getProfessionalId()),
                jpa.getAssignedAt());
    }
}
