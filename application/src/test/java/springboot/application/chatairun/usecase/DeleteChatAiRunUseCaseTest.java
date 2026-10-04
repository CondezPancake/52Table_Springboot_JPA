package springboot.application.chatairun.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.chatairun.exception.ChatAiRunNotFoundApplicationException;
import springboot.domain.chatairun.event.ChatAiRunDeletedEvent;
import springboot.domain.chatairun.model.aggregate.ChatAiRun;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;
import springboot.domain.chatairun.port.repository.ChatAiRunRepository;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.airunstatus.model.valueobject.AiRunStatusId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.chatmessage.model.valueobject.ChatMessageId;

class DeleteChatAiRunUseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        ChatAiRun aggregate = ChatAiRun.register(
                ChatConversationId.generate(),
                ChatMessageId.generate(),
                AiModelId.generate(),
                AiRunStatusId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        ChatAiRunDeletedEvent event = new DeleteChatAiRunUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ChatAiRunNotFoundApplicationException.class,
                () -> new DeleteChatAiRunUseCase(repository).execute(ChatAiRunId.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ChatAiRunRepository {
        private final ChatAiRun aggregate; private ChatAiRun deletedAggregate;
        private FakeRepository(ChatAiRun aggregate) { this.aggregate = aggregate; }
        @Override public ChatAiRun save(ChatAiRun value) { return value; }
        @Override public Optional<ChatAiRun> findById(ChatAiRunId id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<ChatAiRun> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }

        @Override public void delete(ChatAiRun value) { deletedAggregate = value; }
        private ChatAiRun deletedAggregate() { return deletedAggregate; }
    }
}
