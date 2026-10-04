CREATE TABLE chat_conversation_ai_settings (
    id CHAR(36) NOT NULL,
    conversation_id CHAR(36) NOT NULL,
    ai_enabled BOOLEAN NOT NULL,
    default_model_id CHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_chat_conversation_ai_settings PRIMARY KEY (id),
    CONSTRAINT fk_chat_conversation_ai_settings_conversation
        FOREIGN KEY (conversation_id) REFERENCES chat_conversations (id),
    CONSTRAINT fk_chat_conversation_ai_settings_model
        FOREIGN KEY (default_model_id) REFERENCES ai_models (id)
) ENGINE = InnoDB;
