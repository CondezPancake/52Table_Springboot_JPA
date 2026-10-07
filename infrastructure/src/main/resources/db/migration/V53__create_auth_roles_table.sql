CREATE TABLE auth_roles (
    id CHAR(36) NOT NULL,
    name VARCHAR(40) NOT NULL,
    authority VARCHAR(60) NOT NULL,
    CONSTRAINT pk_auth_roles PRIMARY KEY (id),
    CONSTRAINT uk_auth_roles_name UNIQUE (name),
    CONSTRAINT uk_auth_roles_authority UNIQUE (authority)
);

INSERT INTO auth_roles (id, name, authority) VALUES
    ('00000000-0000-0000-0000-000000000001', 'USER', 'ROLE_USER'),
    ('00000000-0000-0000-0000-000000000002', 'ADMIN', 'ROLE_ADMIN'),
    ('00000000-0000-0000-0000-000000000003', 'MODERATOR', 'ROLE_MODERATOR');
