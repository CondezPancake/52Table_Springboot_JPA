CREATE TABLE treatment_goals (
    id CHAR(36) NOT NULL,
    treatment_plan_id CHAR(36) NOT NULL,
    description TEXT NOT NULL,
    target_date DATE NOT NULL,
    completed_at TIMESTAMP NOT NULL,
    notes TEXT NOT NULL,
    treatment_goal_id CHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_treatment_goals PRIMARY KEY (id),
    CONSTRAINT fk_treatment_goals_plan
        FOREIGN KEY (treatment_plan_id) REFERENCES treatment_plans (id),
    CONSTRAINT fk_treatment_goals_status
        FOREIGN KEY (treatment_goal_id) REFERENCES treatment_goal_statuses (id)
) ENGINE = InnoDB;
