package springboot.infrastructure.security.persistence.entity;

import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "auth_roles")
public class AuthRoleJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @Column(name = "name", nullable = false, unique = true, length = 40)
    private String name;

    @Column(name = "authority", nullable = false, unique = true, length = 60)
    private String authority;

    protected AuthRoleJpaEntity() { }

    public AuthRoleJpaEntity(UUID id, String name, String authority) {
        this.id = id;
        this.name = name;
        this.authority = authority;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getAuthority() { return authority; }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof AuthRoleJpaEntity role
                && id != null && Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() { return id == null ? 0 : id.hashCode(); }
}
