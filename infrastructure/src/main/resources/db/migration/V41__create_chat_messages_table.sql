CREATE TABLE chat_messages (
    id CHAR(36) NOT NULL,
    conversation_id CHAR(36) NOT NULL,
    message_type_id CHAR(36) NOT NULL,
    participant_id CHAR(36) NOT NULL,
    content JSON NOT NULL,
    metadata JSON NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_chat_messages PRIMARY KEY (id),
    CONSTRAINT fk_chat_messages_conversation
        FOREIGN KEY (conversation_id) REFERENCES chat_conversations (id),
    CONSTRAINT fk_chat_messages_type
        FOREIGN KEY (message_type_id) REFERENCES message_types (id),
    CONSTRAINT fk_chat_messages_participant
        FOREIGN KEY (participant_id) REFERENCES chat_participants (id)
) ENGINE = InnoDB;
