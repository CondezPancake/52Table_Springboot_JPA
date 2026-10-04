CREATE TABLE chat_conversations (
    id CHAR(36) NOT NULL,
    conversation_status_id CHAR(36) NOT NULL,
    priority_id CHAR(36) NOT NULL,
    last_message_at TIMESTAMP,
    closed BOOLEAN,
    closed_at TIMESTAMP,
    closed_by CHAR(36),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_chat_conversations PRIMARY KEY (id),
    CONSTRAINT fk_chat_conversations_status
        FOREIGN KEY (conversation_status_id) REFERENCES conversations_statuses (id),
    CONSTRAINT fk_chat_conversations_priority
        FOREIGN KEY (priority_id) REFERENCES priorities (id)
) ENGINE = InnoDB;
