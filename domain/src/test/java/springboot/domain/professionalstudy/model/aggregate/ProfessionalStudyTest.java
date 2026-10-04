package springboot.domain.professionalstudy.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.professionalstudy.event.ProfessionalStudyRegisteredEvent;
import springboot.domain.country.model.valueobject.CountryId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.study.model.valueobject.StudyId;

class ProfessionalStudyTest {
    @Test void shouldRegisterCreatedEvent() {
        ProfessionalStudy aggregate = ProfessionalStudy.register(
                StudyId.generate(),
                ProfessionalId.generate(),
                "Clinical Psychology",
                "National University",
                true,
                null,
                CountryId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        ProfessionalStudyRegisteredEvent event = assertInstanceOf(ProfessionalStudyRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
