CREATE TABLE auth_users (
    id CHAR(36) NOT NULL,
    email VARCHAR(254) NOT NULL,
    password_hash VARCHAR(100) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT pk_auth_users PRIMARY KEY (id),
    CONSTRAINT uk_auth_users_email UNIQUE (email),
    CONSTRAINT ck_auth_users_status CHECK (status IN ('ACTIVE', 'BLOCKED', 'INACTIVE'))
);
