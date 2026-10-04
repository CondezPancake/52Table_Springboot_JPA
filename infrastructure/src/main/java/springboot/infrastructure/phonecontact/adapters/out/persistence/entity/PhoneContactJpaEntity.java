package springboot.infrastructure.phonecontact.adapters.out.persistence.entity;



import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "phone_contacts")
public class PhoneContactJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "contact_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID contactId;

    @Column(name = "phone", nullable = true, length = 30)
    private String phone;

    @Column(name = "notes", nullable = false, columnDefinition = "text")
    private String notes;



    public PhoneContactJpaEntity() { }
    public PhoneContactJpaEntity(
            UUID id,
            UUID contactId,
            String phone,
            String notes) {
        this.id = id;
        this.contactId = contactId;
        this.phone = phone;
        this.notes = notes;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getContactId() {
        return contactId;
    }

    public void setContactId(UUID contactId) {
        this.contactId = contactId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
