package springboot.application.phonecontact.dto;



import java.util.UUID;

public record PhoneContactResponse(
        UUID id,
        UUID contactId,
        String phone,
        String notes
) {
}
