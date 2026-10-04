package springboot.infrastructure.mentalstatusexam.adapters.out.persistence.entity;

import java.time.LocalDateTime;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "mental_status_exams")
public class MentalStatusExamJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "encounter_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID encounterId;

    @Column(name = "appearance", nullable = false, columnDefinition = "text")
    private String appearance;

    @Column(name = "behavior", nullable = false, columnDefinition = "text")
    private String behavior;

    @Column(name = "attitude", nullable = false, columnDefinition = "text")
    private String attitude;

    @Column(name = "consciousness", nullable = false, columnDefinition = "text")
    private String consciousness;

    @Column(name = "orientation", nullable = false, columnDefinition = "text")
    private String orientation;

    @Column(name = "attention", nullable = false, columnDefinition = "text")
    private String attention;

    @Column(name = "memory", nullable = false, columnDefinition = "text")
    private String memory;

    @Column(name = "speech", nullable = false, columnDefinition = "text")
    private String speech;

    @Column(name = "mood", nullable = false, columnDefinition = "text")
    private String mood;

    @Column(name = "affect", nullable = false, columnDefinition = "text")
    private String affect;

    @Column(name = "thought_process", nullable = false, columnDefinition = "text")
    private String thoughtProcess;

    @Column(name = "thought_content", nullable = false, columnDefinition = "text")
    private String thoughtContent;

    @Column(name = "perception", nullable = false, columnDefinition = "text")
    private String perception;

    @Column(name = "judgment", nullable = false, columnDefinition = "text")
    private String judgment;

    @Column(name = "insight", nullable = false, columnDefinition = "text")
    private String insight;

    @Column(name = "psychomotor_activity", nullable = false, columnDefinition = "text")
    private String psychomotorActivity;

    @Column(name = "observations", nullable = false, columnDefinition = "text")
    private String observations;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "created_by", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID createdBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public MentalStatusExamJpaEntity() { }
    public MentalStatusExamJpaEntity(
            UUID id,
            UUID encounterId,
            String appearance,
            String behavior,
            String attitude,
            String consciousness,
            String orientation,
            String attention,
            String memory,
            String speech,
            String mood,
            String affect,
            String thoughtProcess,
            String thoughtContent,
            String perception,
            String judgment,
            String insight,
            String psychomotorActivity,
            String observations,
            UUID createdBy,
            LocalDateTime createdAt) {
        this.id = id;
        this.encounterId = encounterId;
        this.appearance = appearance;
        this.behavior = behavior;
        this.attitude = attitude;
        this.consciousness = consciousness;
        this.orientation = orientation;
        this.attention = attention;
        this.memory = memory;
        this.speech = speech;
        this.mood = mood;
        this.affect = affect;
        this.thoughtProcess = thoughtProcess;
        this.thoughtContent = thoughtContent;
        this.perception = perception;
        this.judgment = judgment;
        this.insight = insight;
        this.psychomotorActivity = psychomotorActivity;
        this.observations = observations;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(UUID encounterId) {
        this.encounterId = encounterId;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }

    public String getConsciousness() {
        return consciousness;
    }

    public void setConsciousness(String consciousness) {
        this.consciousness = consciousness;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getAffect() {
        return affect;
    }

    public void setAffect(String affect) {
        this.affect = affect;
    }

    public String getThoughtProcess() {
        return thoughtProcess;
    }

    public void setThoughtProcess(String thoughtProcess) {
        this.thoughtProcess = thoughtProcess;
    }

    public String getThoughtContent() {
        return thoughtContent;
    }

    public void setThoughtContent(String thoughtContent) {
        this.thoughtContent = thoughtContent;
    }

    public String getPerception() {
        return perception;
    }

    public void setPerception(String perception) {
        this.perception = perception;
    }

    public String getJudgment() {
        return judgment;
    }

    public void setJudgment(String judgment) {
        this.judgment = judgment;
    }

    public String getInsight() {
        return insight;
    }

    public void setInsight(String insight) {
        this.insight = insight;
    }

    public String getPsychomotorActivity() {
        return psychomotorActivity;
    }

    public void setPsychomotorActivity(String psychomotorActivity) {
        this.psychomotorActivity = psychomotorActivity;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
