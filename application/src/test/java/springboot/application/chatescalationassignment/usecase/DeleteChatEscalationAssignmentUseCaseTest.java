package springboot.application.chatescalationassignment.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.chatescalationassignment.exception.ChatEscalationAssignmentNotFoundApplicationException;
import springboot.domain.chatescalationassignment.event.ChatEscalationAssignmentDeletedEvent;
import springboot.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import springboot.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import springboot.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

class DeleteChatEscalationAssignmentUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        ChatEscalationAssignment aggregate = ChatEscalationAssignment.register(
                ChatEscalationId.generate(),
                ProfessionalId.generate(),
                java.time.LocalDateTime.of(2026, 1, 10, 10, 0));
        FakeRepository repository = new FakeRepository(aggregate);
        ChatEscalationAssignmentDeletedEvent event = new DeleteChatEscalationAssignmentUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ChatEscalationAssignmentNotFoundApplicationException.class,
                () -> new DeleteChatEscalationAssignmentUseCase(repository).execute(ChatEscalationAssignmentId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ChatEscalationAssignmentRepository {
        private final ChatEscalationAssignment aggregate; private ChatEscalationAssignment deletedAggregate;
        private FakeRepository(ChatEscalationAssignment aggregate) { this.aggregate = aggregate; }
        @Override public ChatEscalationAssignment save(ChatEscalationAssignment value) { return value; }
        @Override public Optional<ChatEscalationAssignment> findById(ChatEscalationAssignmentId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<ChatEscalationAssignment> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(ChatEscalationAssignment value) { deletedAggregate = value; }
        private ChatEscalationAssignment deletedAggregate() { return deletedAggregate; }
    }
}
