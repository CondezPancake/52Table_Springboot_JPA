package springboot.application.patientcontact.dto;



import java.util.UUID;

public record PatientContactResponse(
        UUID id,
        UUID contactId,
        UUID patientId,
        boolean primaryContact,
        boolean emergencyContact,
        UUID relationshipTypeId
) {
}
