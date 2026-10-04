package springboot.application.phonecontact.usecase;

import java.util.List;

import springboot.application.phonecontact.dto.PhoneContactResponse;
import springboot.domain.phonecontact.port.repository.PhoneContactRepository;

public class ListPhoneContactUseCase {
    private final PhoneContactRepository repository;
    public ListPhoneContactUseCase(PhoneContactRepository repository) { this.repository = repository; }

    public List<PhoneContactResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new PhoneContactResponse(
                                aggregate.id().value(),
                                aggregate.contactId().value(),
                                aggregate.phone(),
                                aggregate.notes()))
                .toList();
    }
}
