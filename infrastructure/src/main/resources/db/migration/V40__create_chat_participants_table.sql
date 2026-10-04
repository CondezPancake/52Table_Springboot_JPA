CREATE TABLE chat_participants (
    id CHAR(36) NOT NULL,
    conversation_id CHAR(36) NOT NULL,
    participant_type_id CHAR(36) NOT NULL,
    patient_id CHAR(36),
    professional_id CHAR(36),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_chat_participants PRIMARY KEY (id),
    CONSTRAINT fk_chat_participants_conversation
        FOREIGN KEY (conversation_id) REFERENCES chat_conversations (id),
    CONSTRAINT fk_chat_participants_type
        FOREIGN KEY (participant_type_id) REFERENCES sender_types (id),
    CONSTRAINT fk_chat_participants_patient
        FOREIGN KEY (patient_id) REFERENCES patients (id),
    CONSTRAINT fk_chat_participants_professional
        FOREIGN KEY (professional_id) REFERENCES professionals (id)
) ENGINE = InnoDB;
