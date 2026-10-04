CREATE TABLE chat_escalation_status_history (
    id CHAR(36) NOT NULL,
    escalation_id CHAR(36) NOT NULL,
    escalation_status_id CHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    changed_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_chat_escalation_status_history PRIMARY KEY (id),
    CONSTRAINT fk_chat_escalation_status_history_escalation
        FOREIGN KEY (escalation_id) REFERENCES chat_escalations (id),
    CONSTRAINT fk_chat_escalation_status_history_status
        FOREIGN KEY (escalation_status_id) REFERENCES escalations_statuses (id)
) ENGINE = InnoDB;
