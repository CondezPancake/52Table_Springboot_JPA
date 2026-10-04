package springboot.infrastructure.relationshiptype.adapters.out.persistence.entity;


import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "relationship_types", uniqueConstraints = @UniqueConstraint(name = "uk_relationship_types_description", columnNames = "description"))
public class RelationshipTypeJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @Column(name = "description", nullable = false, length = 50)
    private String description;



    public RelationshipTypeJpaEntity() { }
    public RelationshipTypeJpaEntity(
            UUID id,
            String description) {
        this.id = id;
        this.description = description;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
