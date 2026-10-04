CREATE TABLE chat_ai_run_errors (
    id CHAR(36) NOT NULL,
    ai_run_id CHAR(36) NOT NULL,
    error_message TEXT NOT NULL,
    error_code VARCHAR(80) NOT NULL,
    provider_error_id VARCHAR(120) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_chat_ai_run_errors PRIMARY KEY (id),
    CONSTRAINT fk_chat_ai_run_errors_ai_run
        FOREIGN KEY (ai_run_id) REFERENCES chat_ai_runs (id)
) ENGINE = InnoDB;
