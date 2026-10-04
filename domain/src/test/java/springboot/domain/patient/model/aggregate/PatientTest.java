package springboot.domain.patient.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.patient.event.PatientRegisteredEvent;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.documenttype.model.valueobject.DocumentTypeId;
import springboot.domain.gender.model.valueobject.GenderId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

class PatientTest {
    @Test void shouldRegisterCreatedEvent() {
        Patient aggregate = Patient.register(
                DocumentTypeId.generate(),
                "987654321",
                "Laura",
                null,
                "Gomez",
                null,
                java.time.LocalDate.of(1990, 1, 1),
                GenderId.generate(),
                GenderId.generate(),
                "laura@example.com",
                "3001234567",
                "Main Street 123",
                true,
                null,
                null,
                CityMunicipalityId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        PatientRegisteredEvent event = assertInstanceOf(PatientRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreNullableFieldsWithoutRecordingCreationEvent() {
        var createdAt = java.time.LocalDateTime.of(2026, 1, 10, 8, 0);
        var updatedAt = java.time.LocalDateTime.of(2026, 1, 11, 9, 30);
        Patient aggregate = Patient.restore(
                PatientId.generate(),
                DocumentTypeId.generate(),
                "987654321",
                "Laura",
                null,
                "Gomez",
                null,
                java.time.LocalDate.of(1990, 1, 1),
                GenderId.generate(),
                GenderId.generate(),
                "laura@example.com",
                "3001234567",
                "Main Street 123",
                true,
                null,
                null,
                CityMunicipalityId.generate(),
                createdAt,
                updatedAt);

        assertNull(aggregate.middleName());
        assertNull(aggregate.secondLastName());
        assertNull(aggregate.createdBy());
        assertNull(aggregate.updatedBy());
        assertEquals(createdAt, aggregate.createdAt());
        assertEquals(updatedAt, aggregate.updatedAt());
        assertTrue(aggregate.domainEvents().isEmpty());
    }

    @Test
    void shouldPreserveCreatedAtWhenUpdatingComplexPatient() {
        Patient aggregate = Patient.register(
                DocumentTypeId.generate(),
                "987654321",
                "Laura",
                null,
                "Gomez",
                null,
                java.time.LocalDate.of(1990, 1, 1),
                GenderId.generate(),
                GenderId.generate(),
                "laura@example.com",
                "3001234567",
                "Main Street 123",
                true,
                null,
                null,
                CityMunicipalityId.generate());
        var createdAt = aggregate.createdAt();
        aggregate.clearDomainEvents();
        var updatedBy = ProfessionalId.generate();

        aggregate.update(
                aggregate.documentTypeId(),
                aggregate.documentNumber(),
                aggregate.firstName(),
                null,
                aggregate.lastName(),
                null,
                aggregate.birthDate(),
                aggregate.biologicalSexId(),
                aggregate.genderIdentityId(),
                aggregate.email(),
                aggregate.phone(),
                aggregate.address(),
                aggregate.active(),
                null,
                updatedBy,
                aggregate.cityId());

        assertEquals(createdAt, aggregate.createdAt());
        assertEquals(updatedBy, aggregate.updatedBy());
        assertEquals(1, aggregate.domainEvents().size());
    }
}
