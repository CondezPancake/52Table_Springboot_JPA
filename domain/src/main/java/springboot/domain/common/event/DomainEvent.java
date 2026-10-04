package springboot.domain.common.event;

import java.time.LocalDateTime;

public interface DomainEvent {
    LocalDateTime occurredOn();
}
