CREATE TABLE chat_ai_runs (
    id CHAR(36) NOT NULL,
    conversation_id CHAR(36) NOT NULL,
    message_id CHAR(36) NOT NULL,
    model_id CHAR(36) NOT NULL,
    ai_run_status_id CHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_chat_ai_runs PRIMARY KEY (id),
    CONSTRAINT fk_chat_ai_runs_conversation
        FOREIGN KEY (conversation_id) REFERENCES chat_conversations (id),
    CONSTRAINT fk_chat_ai_runs_message
        FOREIGN KEY (message_id) REFERENCES chat_messages (id),
    CONSTRAINT fk_chat_ai_runs_model
        FOREIGN KEY (model_id) REFERENCES ai_models (id),
    CONSTRAINT fk_chat_ai_runs_status
        FOREIGN KEY (ai_run_status_id) REFERENCES ai_runs_statuses (id)
) ENGINE = InnoDB;
