package springboot.infrastructure.riskassessment.adapters.out.persistence.entity;


import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "risk_assessments")
public class RiskAssessmentJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "encounter_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID encounterId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "risk_level_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID riskLevelId;

    @Column(name = "suicidal_ideation", nullable = false)
    private boolean suicidalIdeation;

    @Column(name = "suicide_plan", nullable = false)
    private boolean suicidePlan;

    @Column(name = "suicide_intent", nullable = false)
    private boolean suicideIntent;

    @Column(name = "self_harm", nullable = false)
    private boolean selfHarm;

    @Column(name = "harm_to_others", nullable = false)
    private boolean harmToOthers;

    @Column(name = "risk_factors", nullable = false, columnDefinition = "text")
    private String riskFactors;

    @Column(name = "protective_factors", nullable = false, columnDefinition = "text")
    private String protectiveFactors;

    @Column(name = "clinical_actions", nullable = false, columnDefinition = "text")
    private String clinicalActions;

    @Column(name = "observations", nullable = false, columnDefinition = "text")
    private String observations;

    @Column(name = "assessed_at", nullable = false)
    private LocalDateTime assessedAt;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "assessed_by", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID assessedBy;



    public RiskAssessmentJpaEntity() { }
    public RiskAssessmentJpaEntity(
            UUID id,
            UUID encounterId,
            UUID riskLevelId,
            boolean suicidalIdeation,
            boolean suicidePlan,
            boolean suicideIntent,
            boolean selfHarm,
            boolean harmToOthers,
            String riskFactors,
            String protectiveFactors,
            String clinicalActions,
            String observations,
            LocalDateTime assessedAt,
            UUID assessedBy) {
        this.id = id;
        this.encounterId = encounterId;
        this.riskLevelId = riskLevelId;
        this.suicidalIdeation = suicidalIdeation;
        this.suicidePlan = suicidePlan;
        this.suicideIntent = suicideIntent;
        this.selfHarm = selfHarm;
        this.harmToOthers = harmToOthers;
        this.riskFactors = riskFactors;
        this.protectiveFactors = protectiveFactors;
        this.clinicalActions = clinicalActions;
        this.observations = observations;
        this.assessedAt = assessedAt;
        this.assessedBy = assessedBy;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(UUID encounterId) {
        this.encounterId = encounterId;
    }

    public UUID getRiskLevelId() {
        return riskLevelId;
    }

    public void setRiskLevelId(UUID riskLevelId) {
        this.riskLevelId = riskLevelId;
    }

    public boolean isSuicidalIdeation() {
        return suicidalIdeation;
    }

    public void setSuicidalIdeation(boolean suicidalIdeation) {
        this.suicidalIdeation = suicidalIdeation;
    }

    public boolean isSuicidePlan() {
        return suicidePlan;
    }

    public void setSuicidePlan(boolean suicidePlan) {
        this.suicidePlan = suicidePlan;
    }

    public boolean isSuicideIntent() {
        return suicideIntent;
    }

    public void setSuicideIntent(boolean suicideIntent) {
        this.suicideIntent = suicideIntent;
    }

    public boolean isSelfHarm() {
        return selfHarm;
    }

    public void setSelfHarm(boolean selfHarm) {
        this.selfHarm = selfHarm;
    }

    public boolean isHarmToOthers() {
        return harmToOthers;
    }

    public void setHarmToOthers(boolean harmToOthers) {
        this.harmToOthers = harmToOthers;
    }

    public String getRiskFactors() {
        return riskFactors;
    }

    public void setRiskFactors(String riskFactors) {
        this.riskFactors = riskFactors;
    }

    public String getProtectiveFactors() {
        return protectiveFactors;
    }

    public void setProtectiveFactors(String protectiveFactors) {
        this.protectiveFactors = protectiveFactors;
    }

    public String getClinicalActions() {
        return clinicalActions;
    }

    public void setClinicalActions(String clinicalActions) {
        this.clinicalActions = clinicalActions;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public LocalDateTime getAssessedAt() {
        return assessedAt;
    }

    public void setAssessedAt(LocalDateTime assessedAt) {
        this.assessedAt = assessedAt;
    }

    public UUID getAssessedBy() {
        return assessedBy;
    }

    public void setAssessedBy(UUID assessedBy) {
        this.assessedBy = assessedBy;
    }


}
