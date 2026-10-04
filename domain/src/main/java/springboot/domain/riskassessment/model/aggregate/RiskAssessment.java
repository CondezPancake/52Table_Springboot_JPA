package springboot.domain.riskassessment.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.riskassessment.event.RiskAssessmentRegisteredEvent;
import springboot.domain.riskassessment.event.RiskAssessmentUpdatedEvent;
import springboot.domain.riskassessment.model.valueobject.RiskAssessmentId;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.risklevel.model.valueobject.RiskLevelId;

public class RiskAssessment extends AggregateRoot {
    private final RiskAssessmentId id;
    private EncounterId encounterId;
    private RiskLevelId riskLevelId;
    private boolean suicidalIdeation;
    private boolean suicidePlan;
    private boolean suicideIntent;
    private boolean selfHarm;
    private boolean harmToOthers;
    private String riskFactors;
    private String protectiveFactors;
    private String clinicalActions;
    private String observations;
    private LocalDateTime assessedAt;
    private ProfessionalId assessedBy;


    private RiskAssessment(
            RiskAssessmentId id,
            EncounterId encounterId,
            RiskLevelId riskLevelId,
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
            ProfessionalId assessedBy) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.encounterId = Objects.requireNonNull(encounterId, "encounterId must not be null");
        this.riskLevelId = Objects.requireNonNull(riskLevelId, "riskLevelId must not be null");
        this.suicidalIdeation = suicidalIdeation;
        this.suicidePlan = suicidePlan;
        this.suicideIntent = suicideIntent;
        this.selfHarm = selfHarm;
        this.harmToOthers = harmToOthers;
        this.riskFactors = Objects.requireNonNull(riskFactors, "riskFactors must not be null");
        this.protectiveFactors = Objects.requireNonNull(protectiveFactors, "protectiveFactors must not be null");
        this.clinicalActions = Objects.requireNonNull(clinicalActions, "clinicalActions must not be null");
        this.observations = Objects.requireNonNull(observations, "observations must not be null");
        this.assessedAt = Objects.requireNonNull(assessedAt, "assessedAt must not be null");
        this.assessedBy = Objects.requireNonNull(assessedBy, "assessedBy must not be null");

    }

    public static RiskAssessment register(
            EncounterId encounterId,
            RiskLevelId riskLevelId,
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
            ProfessionalId assessedBy) {
        RiskAssessmentId id = RiskAssessmentId.generate();
        LocalDateTime occurredOn = LocalDateTime.now();
        RiskAssessment aggregate = new RiskAssessment(
                id,
                encounterId,
                riskLevelId,
                suicidalIdeation,
                suicidePlan,
                suicideIntent,
                selfHarm,
                harmToOthers,
                riskFactors,
                protectiveFactors,
                clinicalActions,
                observations,
                assessedAt,
                assessedBy);
        aggregate.recordEvent(new RiskAssessmentRegisteredEvent(id, occurredOn));
        return aggregate;
    }

    public static RiskAssessment restore(
            RiskAssessmentId id,
            EncounterId encounterId,
            RiskLevelId riskLevelId,
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
            ProfessionalId assessedBy) {
        return new RiskAssessment(
                id,
                encounterId,
                riskLevelId,
                suicidalIdeation,
                suicidePlan,
                suicideIntent,
                selfHarm,
                harmToOthers,
                riskFactors,
                protectiveFactors,
                clinicalActions,
                observations,
                assessedAt,
                assessedBy);
    }

    public void update(
            EncounterId encounterId,
            RiskLevelId riskLevelId,
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
            ProfessionalId assessedBy) {
        this.encounterId = Objects.requireNonNull(encounterId, "encounterId must not be null");
        this.riskLevelId = Objects.requireNonNull(riskLevelId, "riskLevelId must not be null");
        this.suicidalIdeation = suicidalIdeation;
        this.suicidePlan = suicidePlan;
        this.suicideIntent = suicideIntent;
        this.selfHarm = selfHarm;
        this.harmToOthers = harmToOthers;
        this.riskFactors = Objects.requireNonNull(riskFactors, "riskFactors must not be null");
        this.protectiveFactors = Objects.requireNonNull(protectiveFactors, "protectiveFactors must not be null");
        this.clinicalActions = Objects.requireNonNull(clinicalActions, "clinicalActions must not be null");
        this.observations = Objects.requireNonNull(observations, "observations must not be null");
        this.assessedAt = Objects.requireNonNull(assessedAt, "assessedAt must not be null");
        this.assessedBy = Objects.requireNonNull(assessedBy, "assessedBy must not be null");
        LocalDateTime occurredOn = LocalDateTime.now();
        recordEvent(new RiskAssessmentUpdatedEvent(
                        this.id,
                        this.encounterId,
                        this.riskLevelId,
                        this.suicidalIdeation,
                        this.suicidePlan,
                        this.suicideIntent,
                        this.selfHarm,
                        this.harmToOthers,
                        this.riskFactors,
                        this.protectiveFactors,
                        this.clinicalActions,
                        this.observations,
                        this.assessedAt,
                        this.assessedBy,
                        occurredOn));
    }

    public RiskAssessmentId id() {
        return id;
    }

    public EncounterId encounterId() {
        return encounterId;
    }

    public RiskLevelId riskLevelId() {
        return riskLevelId;
    }

    public boolean suicidalIdeation() {
        return suicidalIdeation;
    }

    public boolean suicidePlan() {
        return suicidePlan;
    }

    public boolean suicideIntent() {
        return suicideIntent;
    }

    public boolean selfHarm() {
        return selfHarm;
    }

    public boolean harmToOthers() {
        return harmToOthers;
    }

    public String riskFactors() {
        return riskFactors;
    }

    public String protectiveFactors() {
        return protectiveFactors;
    }

    public String clinicalActions() {
        return clinicalActions;
    }

    public String observations() {
        return observations;
    }

    public LocalDateTime assessedAt() {
        return assessedAt;
    }

    public ProfessionalId assessedBy() {
        return assessedBy;
    }


}
