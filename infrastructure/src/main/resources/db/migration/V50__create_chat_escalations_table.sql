CREATE TABLE chat_escalations (
    id CHAR(36) NOT NULL,
    conversation_id CHAR(36) NOT NULL,
    status_id CHAR(36) NOT NULL,
    from_ai BOOLEAN NOT NULL,
    reason TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_chat_escalations PRIMARY KEY (id),
    CONSTRAINT fk_chat_escalations_conversation
        FOREIGN KEY (conversation_id) REFERENCES chat_conversations (id),
    CONSTRAINT fk_chat_escalations_status
        FOREIGN KEY (status_id) REFERENCES escalations_statuses (id)
) ENGINE = InnoDB;
