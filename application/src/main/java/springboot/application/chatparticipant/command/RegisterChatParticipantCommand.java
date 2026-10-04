package springboot.application.chatparticipant.command;

import java.util.Objects;


import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.sendertype.model.valueobject.SenderTypeId;

public record RegisterChatParticipantCommand(
        ChatConversationId conversationId,
        SenderTypeId participantTypeId,
        PatientId patientId,
        ProfessionalId professionalId
) {
    public RegisterChatParticipantCommand {
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(participantTypeId, "participantTypeId must not be null");
    }
}
