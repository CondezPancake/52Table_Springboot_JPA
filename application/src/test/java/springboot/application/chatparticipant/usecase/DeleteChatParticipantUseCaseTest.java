package springboot.application.chatparticipant.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.chatparticipant.exception.ChatParticipantNotFoundApplicationException;
import springboot.domain.chatparticipant.event.ChatParticipantDeletedEvent;
import springboot.domain.chatparticipant.model.aggregate.ChatParticipant;
import springboot.domain.chatparticipant.model.valueobject.ChatParticipantId;
import springboot.domain.chatparticipant.port.repository.ChatParticipantRepository;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.sendertype.model.valueobject.SenderTypeId;

class DeleteChatParticipantUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        ChatParticipant aggregate = ChatParticipant.register(
                ChatConversationId.generate(),
                SenderTypeId.generate(),
                null,
                null);
        FakeRepository repository = new FakeRepository(aggregate);
        ChatParticipantDeletedEvent event = new DeleteChatParticipantUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ChatParticipantNotFoundApplicationException.class,
                () -> new DeleteChatParticipantUseCase(repository).execute(ChatParticipantId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ChatParticipantRepository {
        private final ChatParticipant aggregate; private ChatParticipant deletedAggregate;
        private FakeRepository(ChatParticipant aggregate) { this.aggregate = aggregate; }
        @Override public ChatParticipant save(ChatParticipant value) { return value; }
        @Override public Optional<ChatParticipant> findById(ChatParticipantId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<ChatParticipant> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(ChatParticipant value) { deletedAggregate = value; }
        private ChatParticipant deletedAggregate() { return deletedAggregate; }
    }
}
