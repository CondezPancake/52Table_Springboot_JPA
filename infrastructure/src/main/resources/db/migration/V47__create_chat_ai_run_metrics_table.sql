CREATE TABLE chat_ai_run_metrics (
    id CHAR(36) NOT NULL,
    ai_run_id CHAR(36) NOT NULL,
    prompt_tokens INTEGER NOT NULL,
    completion_tokens INTEGER NOT NULL,
    total_tokens INTEGER NOT NULL,
    cost DECIMAL(10,6) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_chat_ai_run_metrics PRIMARY KEY (id),
    CONSTRAINT fk_chat_ai_run_metrics_ai_run
        FOREIGN KEY (ai_run_id) REFERENCES chat_ai_runs (id)
) ENGINE = InnoDB;
