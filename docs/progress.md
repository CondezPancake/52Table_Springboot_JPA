# MindConnect progress

Last updated: 2026-10-04 (America/Bogota)

## Status by block

| Block | Tables | Status |
|---:|---|---|
| 1 | countries, state_regions, city_municipalities | Complete and verified |
| 2 | document_types, genders, relationship_types, professional_types, studies | Complete and verified |
| 3 | professionals, patients, contacts, phone_contacts | Complete and verified |
| 4 | email_contacts, patient_contacts, patient_allergies, professional_studies | Complete and verified |
| 5 | clinical_record_statuses, encounter_types, encounter_modalities, encounter_statuses, risk_levels | Complete and verified |
| 6 | treatment_statuses, treatment_goal_statuses, medication_routes, assessment_types, consent_types, diagnostic_systems | Complete and verified |
| 7 | clinical_records, encounters | Complete and verified |
| 8 | risk_assessments, clinical_notes, mental_status_exams, treatment_plans | Complete and verified |
| 9 | treatment_goals, conversations_statuses, priorities, sender_types, message_types | Complete and verified |
| 10 | chat_conversations, chat_participants, chat_messages | Complete and verified |
| 11 | provider_models_ai, ai_models, chat_conversation_ai_settings, ai_runs_statuses | Complete and verified |
| 12 | chat_ai_runs, chat_ai_run_metrics, chat_ai_run_errors, escalations_statuses | Complete and verified |
| 13 | chat_escalations, chat_escalation_assignments, chat_escalation_status_history | Complete and verified |

## Checks completed

- Inspected both ZIP archives directly; ignored template `target` and compiled classes, and reproduced the relevant `.vscode` settings and launch configuration.
- Confirmed the data archive contains exactly V1–V52 and recorded every table/FK in the implementation plan.
- Environment: `java 25.0.4.1`, `javac 21.0.12.1`, empty `JAVA_HOME`, and no system Maven. Java release is therefore centralized at 21 and a Maven wrapper is required.
- Root project uses the template's three-module dependency direction and physical layout: root-level `domain`, `application`, and `infrastructure` modules, each retaining its independent Maven source trees and `springboot.*` packages.
- Removed the empty obsolete root `src` tree after moving the complete modules; no nested Maven module or duplicate source remains.
- Renamed the only boot class to `springboot.infrastructure.DemoApplication`, updated the infrastructure POM and VS Code launcher, and retained root package scanning.
- Added the template's Spring configuration metadata, with matching environment-backed queue-delay and CORS properties but no queue behavior.
- Restored `existsByCode` for the initial three contexts using their real JPA properties, without altering the non-unique SQL constraints.
- Added `tools/structure-manifest.json` and `tools/verify-structure.ps1`; after block 13 the verifier passes for all 52 contexts, 1,357 Java files, exact declaration types/packages, 7 beans per context, layer boundaries, SQL columns, lengths, precision/scale, nullability, UUID/TEXT/DATE/JSON/timestamp mappings, and 52 migration versions.
- Maven wrapper 3.3.4 verified with `JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot`; Maven 3.9.16 runs on Java 21.0.12.1.
- Copied all 52 original migrations once into infrastructure. Source/destination SHA-256 comparison reports 52 matches and zero mismatches; there is no template Country migration.
- Implemented full Country-pattern contexts for `countries`, `state_regions`, and `city_municipalities`: 9 application files, 6 domain files, 8 infrastructure files, one common domain exception, and 2 equivalent tests per context.
- Compared V1–V3 with JPA mappings: each entity has exactly the expected 8 SQL columns, with no missing or extra mapped columns. FK identifiers are `CountryId`/`StateRegionId` in domain and textual UUID columns in persistence.
- Implemented complete Country-pattern contexts for V4–V8: `DocumentType`, `Gender`, `RelationshipType`, `ProfessionalType`, and `Study`, each with 9 application files, 6 domain files, 8 infrastructure files, its domain common exception, and 2 equivalent tests.
- Verified V4–V8 against SQL: all columns are mapped exactly; named UNIQUE constraints are preserved for document type code, gender description, relationship description, and professional type name. `Study.name` remains non-unique.
- `RelationshipType` correctly omits `createdAt`/`updatedAt` throughout domain, application, persistence, and mapper because V6 has no timestamp columns. The other four block-2 contexts preserve both timestamps.
- `existsByCode` is present only for `DocumentType`, the only block-2 table with a code column. No code field or code query was invented for the other catalogs.
- The generator now supports context selection, optional timestamps, named single-column UNIQUE constraints, and still refuses to overwrite existing files.
- Implemented complete Country-pattern contexts for V9–V12: `Professional`, `Patient`, `Contact`, and `PhoneContact`, each with 9 application files, 6 domain files, 8 infrastructure files, its domain common exception, and 2 equivalent test classes.
- Verified V9–V12 against SQL: FK values remain typed identifiers in domain and explicit `CHAR(36)` UUID columns in JPA; all six named UNIQUE constraints, string lengths, required/optional columns, `DATE`, and `TEXT` mappings match the migrations.
- `Patient` preserves nullable middle/second names and nullable professional audit identifiers through requests, commands, domain, responses, controllers, JPA, and mapper. Its complex aggregate test also verifies restore-without-event and preservation of `createdAt` on update.
- `Contact.updatedBy` and both patient audit FKs use null-safe REST and persistence conversions. No cascade or JPA relationship was introduced.
- `PhoneContact` correctly omits timestamps because V12 defines none; its optional phone and required TEXT notes follow the SQL contract.
- Extended the generator with nullable fields/references, `LocalDate`, TEXT column definitions, multiple named UNIQUE constraints, and null-safe UUID conversions, while retaining selective generation and overwrite protection.
- Implemented complete Country-pattern contexts for V13–V16: `EmailContact`, `PatientContact`, `PatientAllergy`, and `ProfessionalStudy`, each with 9 application files, 6 domain files, 8 infrastructure files, its domain common exception, and 2 equivalent test classes.
- Verified V13–V16 against SQL: every FK remains a typed identifier/`CHAR(36)` UUID, email uniqueness and all lengths/nullability match, and TEXT is explicit for email notes and the optional allergy reaction.
- `PatientContact` correctly omits technical timestamps because V14 has none. `PatientAllergy.recordedAt` is a required business `LocalDateTime` distinct from `createdAt`/`updatedAt`.
- `ProfessionalStudy.resolutionNumber` and `PatientAllergy.reaction` remain nullable across requests, commands, domain, events, responses, JPA, and mappers.
- Extended generator type imports to support `LocalDateTime` fields without duplicating the technical timestamp import.
- Implemented complete Country-pattern contexts for V17, V19, V20, V21, and V23: `ClinicalRecordStatus`, `EncounterType`, `EncounterModality`, `EncounterStatus`, and `RiskLevel`, each with 9 application files, 6 domain files, 8 infrastructure files, its domain common exception, and 2 equivalent test classes.
- Verified block 5 against SQL: all five UUID identifiers map to `CHAR(36)`, every code/name length and named UNIQUE constraint matches its migration, and all five contexts preserve `createdAt`/`updatedAt`.
- `existsByCode` is present in all five block-5 ports, JPA repositories, and adapters because every corresponding table has a real code column. `ClinicalRecordStatus` correctly has no invented `active` field.
- `RiskLevel.severity` remains primitive `int` through domain/application/JPA and is exposed as validated `Integer` in REST requests so SQL nullability is enforced before unboxing.
- Extended the selective generator to handle numeric primitive fields and their REST validation wrappers without weakening its overwrite protection.
- Implemented complete Country-pattern contexts for V27, V29, V31, V32, V33, and V34: `TreatmentStatus`, `TreatmentGoalStatus`, `MedicationRoute`, `AssessmentType`, `ConsentType`, and `DiagnosticSystem`, each with 9 application files, 6 domain files, 8 infrastructure files, its domain common exception, and 2 equivalent test classes.
- Verified block 6 against SQL: all identifiers map to `CHAR(36)`; required fields, lengths, timestamps, and named UNIQUE constraints match exactly. Only treatment and treatment-goal statuses have the additional name uniqueness declared by their migrations.
- `AssessmentType.description` and `ConsentType.description` are required explicit MySQL `TEXT` mappings. `DiagnosticSystem.version` remains required `VARCHAR(20)` across every layer.
- `existsByCode` is present in all six block-6 ports, JPA repositories, and adapters because each table has a real code column; no `existsByName` contract was invented.
- Implemented complete Country-pattern contexts for V18 and V22: `ClinicalRecord` and `Encounter`, each with 9 application files, 6 domain files, 8 infrastructure files, its domain common exception, and 2 equivalent test classes.
- Verified V18/V22 against SQL: all FK values remain typed identifiers in domain and explicit `CHAR(36)` UUID columns in JPA; all business timestamps, required fields, and `record_number VARCHAR(50)` match exactly.
- `ClinicalRecord` models `created_at` without inventing an `updated_at`; its technical creation timestamp is preserved during updates. The generator and verifier now support independent technical creation/update timestamp presence.
- `Encounter.reasonForVisit` and `Encounter.currentCondition` are required explicit MySQL `TEXT` mappings. Its seven FK fields plus aggregate identifier use textual UUID persistence, and both technical timestamps are retained.
- Neither block-7 table has a code column, so no `existsByCode` method was introduced.
- Implemented complete Country-pattern contexts for V24, V25, V26, and V28: `RiskAssessment`, `ClinicalNote`, `MentalStatusExam`, and `TreatmentPlan`, each with 9 application files, 6 domain files, 8 infrastructure files, its domain common exception, and 2 equivalent test classes.
- Verified block 8 against SQL: all FK values remain typed identifiers/`CHAR(36)` UUIDs, all required booleans and business timestamps are preserved, and the 27 declared `TEXT` columns are explicitly mapped without artificial lengths.
- `RiskAssessment` correctly has no technical timestamps; `MentalStatusExam` has `createdAt` only; `ClinicalNote` and `TreatmentPlan` preserve both `createdAt` and `updatedAt`.
- `TreatmentPlan.startDate` and `endDate` use `LocalDate`, while its title keeps `VARCHAR(200)` and description uses required MySQL `TEXT`.
- None of the block-8 tables has a code column or UNIQUE constraint, so no code-existence query or uniqueness was invented.
- Implemented complete Country-pattern contexts for V30 and V35–V38: `TreatmentGoal`, `ConversationStatus`, `Priority`, `SenderType`, and `MessageType`, each with 9 application files, 6 domain files, 8 infrastructure files, its domain common exception, and 2 equivalent test classes.
- Verified block 9 against SQL: every identifier uses `CHAR(36)`, every catalog name remains required `VARCHAR(50)`, and all five contexts preserve both technical timestamps.
- `TreatmentGoal.description` and `notes` are required explicit `TEXT`; `targetDate` uses `LocalDate` and `completedAt` uses `LocalDateTime`.
- SQL column `treatment_goal_id` is preserved exactly while its domain value is the correctly typed `TreatmentGoalStatusId`, reflecting the actual FK to `treatment_goal_statuses`.
- No block-9 table declares a code column or UNIQUE constraint, so no code query or uniqueness was invented.
- Implemented complete Country-pattern contexts for V39–V41: `ChatConversation`, `ChatParticipant`, and `ChatMessage`, each with 9 application files, 6 domain files, 8 infrastructure files, its domain common exception, and 2 equivalent test classes.
- Verified block 10 against SQL: all declared FK values remain typed identifiers/`CHAR(36)` UUIDs, optional fields preserve null through every layer, and no cascade or JPA relationship graph was introduced.
- `ChatConversation.closed` is nullable `Boolean`. Its nullable `closedBy` is a plain `UUID` mapped to `CHAR(36)` because SQL does not declare a foreign key for `closed_by`; no relationship was invented.
- `ChatParticipant.patientId` and `professionalId` remain independently optional and use null-safe REST/mapping conversions.
- `ChatMessage.content` and `metadata` remain domain `String` values and map explicitly with Hibernate's JSON JDBC type and MySQL `json` column definition. `ChatMessage` has `createdAt` only and does not invent `updatedAt`.
- Extended the generator and structural verifier with raw UUID fields and explicit JSON mapping checks while retaining selective generation and overwrite protection.
- Implemented complete Country-pattern contexts for V42–V45: `ProviderModelAi`, `AiModel`, `ChatConversationAiSetting`, and `AiRunStatus`, each with 9 application files, 6 domain files, 8 infrastructure files, its domain common exception, and 2 equivalent test classes.
- Verified block 11 against SQL: all FK values remain typed identifiers/`CHAR(36)` UUIDs, all lengths and required fields match, and every context preserves both technical timestamps.
- `AiModel.inputTokenPrice` and `outputTokenPrice` use `BigDecimal`, explicit JPA precision 12/scale 8, and REST `@Digits(integer = 4, fraction = 8)`. Integer token limits remain primitive internally and validated wrappers at REST.
- `ProviderModelAi.sitioWeb` is required `TEXT`; mixed-case SQL column `isActive` is preserved explicitly. No provider behavior, AI call, code lookup, or uniqueness was invented.
- Extended the generator and verifier with decimal precision/scale and `BigDecimal` support.
- Implemented complete Country-pattern contexts for V46–V49: `ChatAiRun`, `ChatAiRunMetric`, `ChatAiRunError`, and `EscalationStatus`, each with 9 application files, 6 domain files, 8 infrastructure files, its domain common exception, and 2 equivalent test classes.
- Verified block 12 against SQL: `ChatAiRun` retains all four FK identifiers and both technical timestamps; no JPA relationships, cascades, or AI execution behavior were added.
- `ChatAiRunMetric.cost` uses `BigDecimal`, explicit JPA precision 10/scale 6, and matching REST `@Digits(integer = 4, fraction = 6)`. Its three token counts remain required integers.
- `ChatAiRunMetric` and `ChatAiRunError` preserve only `createdAt`, without inventing `updatedAt`. Error message uses required `TEXT`, while error code/provider identifier retain `VARCHAR(80)`/`VARCHAR(120)`.
- None of the block-12 tables declares a code column or UNIQUE constraint, so no existence query or uniqueness was invented.
- Implemented complete Country-pattern contexts for V50–V52: `ChatEscalation`, `ChatEscalationAssignment`, and `ChatEscalationStatusHistory`, each with 9 application files, 6 domain files, 8 infrastructure files, its domain common exception, and 2 equivalent test classes.
- Verified block 13 against SQL: all FK values remain typed identifiers/`CHAR(36)` UUIDs, escalation reason is required `TEXT`, and the required `fromAi` boolean is preserved.
- `ChatEscalation` and `ChatEscalationStatusHistory` have only technical `createdAt`; the latter keeps `changedAt` as a separate required business timestamp. `ChatEscalationAssignment` has no technical timestamps and keeps `assignedAt` as business state.
- None of the final tables declares a code column or UNIQUE constraint, so no existence query or uniqueness was invented.
- All 52 planned table contexts are now implemented exactly once across domain, application, and infrastructure.
- Confirmed domain/application source contains no Spring, JPA, or Jakarta Validation imports. No legacy package, PostgreSQL setting, `create-drop`, TODO, or stub implementation remains in main source.
- Confirmed the implementation plan has exactly 52 table mapping rows.

## Verification states

- Compiled: yes. `mvnw.cmd -o clean package -DskipTests` succeeded for the full four-project reactor with Maven 3.9.16 on JDK 21.0.12.1 and Java release 21.
- Tests executed: yes, limited to the current block as requested. Nine critical block-13 tests ran (3 domain registration-event tests and 6 application deletion tests), with zero failures, errors, or skips. Historical-context tests were not rerun.
- Persistence verified: no. There is no `.env`, `DB_URL`, MySQL client, Docker command, or confirmed dedicated MySQL database in this environment. Flyway/Hibernate startup was intentionally not pointed at the default or an unknown shared database.

## Next action

No table contexts remain pending: all 52 are implemented. The remaining optional verification is Flyway/Hibernate startup against a confirmed dedicated MySQL 8 database; do not point it at an unknown or shared database. A broader regression suite can also be run if explicitly requested.
