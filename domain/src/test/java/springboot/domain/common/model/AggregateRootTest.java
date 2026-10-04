package springboot.domain.common.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import springboot.domain.common.event.DomainEvent;

class AggregateRootTest {
    @Test
    void shouldRecordDomainEvent() {
        TestAggregateRoot aggregate = new TestAggregateRoot();
        DomainEvent event = new TestDomainEvent(LocalDateTime.now());
        aggregate.record(event);
        assertEquals(List.of(event), aggregate.domainEvents());
    }

    @Test
    void shouldExposeImmutableDomainEvents() {
        TestAggregateRoot aggregate = new TestAggregateRoot();
        aggregate.record(new TestDomainEvent(LocalDateTime.now()));
        assertThrows(UnsupportedOperationException.class, () -> aggregate.domainEvents().clear());
    }

    @Test
    void shouldRejectNullDomainEvent() {
        TestAggregateRoot aggregate = new TestAggregateRoot();
        assertThrows(NullPointerException.class, () -> aggregate.record(null));
    }

    @Test
    void shouldClearDomainEvents() {
        TestAggregateRoot aggregate = new TestAggregateRoot();
        aggregate.record(new TestDomainEvent(LocalDateTime.now()));
        aggregate.clearDomainEvents();
        assertTrue(aggregate.domainEvents().isEmpty());
    }

    private static final class TestAggregateRoot extends AggregateRoot {
        private void record(DomainEvent event) {
            recordEvent(event);
        }
    }

    private record TestDomainEvent(LocalDateTime occurredOn) implements DomainEvent {
    }
}
