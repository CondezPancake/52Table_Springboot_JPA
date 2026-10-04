CREATE TABLE chat_escalation_assignments (
    id CHAR(36) NOT NULL,
    escalation_id CHAR(36) NOT NULL,
    professional_id CHAR(36) NOT NULL,
    assigned_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_chat_escalation_assignments PRIMARY KEY (id),
    CONSTRAINT fk_chat_escalation_assignments_escalation
        FOREIGN KEY (escalation_id) REFERENCES chat_escalations (id),
    CONSTRAINT fk_chat_escalation_assignments_professional
        FOREIGN KEY (professional_id) REFERENCES professionals (id)
) ENGINE = InnoDB;
