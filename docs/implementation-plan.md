# MindConnect implementation plan

## Sources and fixed decisions

- Architecture/style source: `demo-ddd-main.zip` (source files only; `target` and `.vscode` ignored).
- Data contract: the unmodified migrations V1 through V52 from `Migration_flyway-developer.zip`.
- Root packages: `springboot.domain`, `springboot.application`, and `springboot.infrastructure`.
- Physical module locations: root-level `domain`, `application`, and `infrastructure` directories, each with its own Maven `src/main` and `src/test` tree.
- One bounded context per table in each Maven module. Context packages are lowercase concatenations shown below.
- Domain and application have no Spring/JPA dependencies. Infrastructure owns REST, JPA, Flyway, MySQL, and bean wiring.
- Java is centralized at release 21 because the installed compiler is `javac 21.0.12.1`; Maven currently launches with the installed Java 25 runtime, which can compile with `--release 21`.
- UUID identifiers remain value objects in domain and map explicitly as textual `CHAR(36)` in JPA.
- Foreign keys remain identifiers; no JPA object graph or cascade is invented.
- `created_at` is assigned on registration and preserved on update; `updated_at` is advanced on update where present.
- `tools/structure-manifest.json` is the structural contract derived from the professor's template. `tools/verify-structure.ps1` must pass before a context or block is marked complete.
- The manifest lists only implemented contexts. A future block must add its contexts to the manifest in the same change that generates their explicit files.

## Complete table-to-context map

| V | Table | Entity | Package | Direct FK dependencies | Block |
|---:|---|---|---|---|---:|
| 1 | `countries` | `Country` | `country` | — | 1 |
| 2 | `state_regions` | `StateRegion` | `stateregion` | Country | 1 |
| 3 | `city_municipalities` | `CityMunicipality` | `citymunicipality` | StateRegion | 1 |
| 4 | `document_types` | `DocumentType` | `documenttype` | — | 2 |
| 5 | `genders` | `Gender` | `gender` | — | 2 |
| 6 | `relationship_types` | `RelationshipType` | `relationshiptype` | — | 2 |
| 7 | `professional_types` | `ProfessionalType` | `professionaltype` | — | 2 |
| 8 | `studies` | `Study` | `study` | — | 2 |
| 9 | `professionals` | `Professional` | `professional` | DocumentType, ProfessionalType, CityMunicipality | 3 |
| 10 | `patients` | `Patient` | `patient` | DocumentType, Gender (two FKs), Professional (two optional FKs), CityMunicipality | 3 |
| 11 | `contacts` | `Contact` | `contact` | CityMunicipality, Professional (two FKs; updatedBy optional) | 3 |
| 12 | `phone_contacts` | `PhoneContact` | `phonecontact` | Contact | 3 |
| 13 | `email_contacts` | `EmailContact` | `emailcontact` | Contact | 4 |
| 14 | `patient_contacts` | `PatientContact` | `patientcontact` | Contact, Patient, RelationshipType | 4 |
| 15 | `patient_allergies` | `PatientAllergy` | `patientallergy` | Patient, Professional | 4 |
| 16 | `professional_studies` | `ProfessionalStudy` | `professionalstudy` | Study, Professional, Country | 4 |
| 17 | `clinical_record_statuses` | `ClinicalRecordStatus` | `clinicalrecordstatus` | — | 5 |
| 19 | `encounter_types` | `EncounterType` | `encountertype` | — | 5 |
| 20 | `encounter_modalities` | `EncounterModality` | `encountermodality` | — | 5 |
| 21 | `encounter_statuses` | `EncounterStatus` | `encounterstatus` | — | 5 |
| 23 | `risk_levels` | `RiskLevel` | `risklevel` | — | 5 |
| 27 | `treatment_statuses` | `TreatmentStatus` | `treatmentstatus` | — | 6 |
| 29 | `treatment_goal_statuses` | `TreatmentGoalStatus` | `treatmentgoalstatus` | — | 6 |
| 31 | `medication_routes` | `MedicationRoute` | `medicationroute` | — | 6 |
| 32 | `assessment_types` | `AssessmentType` | `assessmenttype` | — | 6 |
| 33 | `consent_types` | `ConsentType` | `consenttype` | — | 6 |
| 34 | `diagnostic_systems` | `DiagnosticSystem` | `diagnosticsystem` | — | 6 |
| 18 | `clinical_records` | `ClinicalRecord` | `clinicalrecord` | Patient, ClinicalRecordStatus, Professional | 7 |
| 22 | `encounters` | `Encounter` | `encounter` | ClinicalRecord, Professional, EncounterType, EncounterModality, EncounterStatus | 7 |
| 24 | `risk_assessments` | `RiskAssessment` | `riskassessment` | Encounter, RiskLevel, Professional | 8 |
| 25 | `clinical_notes` | `ClinicalNote` | `clinicalnote` | Encounter, Professional | 8 |
| 26 | `mental_status_exams` | `MentalStatusExam` | `mentalstatusexam` | Encounter, Professional | 8 |
| 28 | `treatment_plans` | `TreatmentPlan` | `treatmentplan` | Encounter, Professional, TreatmentStatus | 8 |
| 30 | `treatment_goals` | `TreatmentGoal` | `treatmentgoal` | TreatmentPlan, TreatmentGoalStatus | 9 |
| 35 | `conversations_statuses` | `ConversationStatus` | `conversationstatus` | — | 9 |
| 36 | `priorities` | `Priority` | `priority` | — | 9 |
| 37 | `sender_types` | `SenderType` | `sendertype` | — | 9 |
| 38 | `message_types` | `MessageType` | `messagetype` | — | 9 |
| 39 | `chat_conversations` | `ChatConversation` | `chatconversation` | ConversationStatus, Priority | 10 |
| 40 | `chat_participants` | `ChatParticipant` | `chatparticipant` | ChatConversation, SenderType, optional Patient, optional Professional | 10 |
| 41 | `chat_messages` | `ChatMessage` | `chatmessage` | ChatConversation, MessageType, ChatParticipant | 10 |
| 42 | `provider_models_ai` | `ProviderModelAi` | `providermodelai` | — | 11 |
| 43 | `ai_models` | `AiModel` | `aimodel` | ProviderModelAi | 11 |
| 44 | `chat_conversation_ai_settings` | `ChatConversationAiSetting` | `chatconversationaisetting` | ChatConversation, AiModel | 11 |
| 45 | `ai_runs_statuses` | `AiRunStatus` | `airunstatus` | — | 11 |
| 46 | `chat_ai_runs` | `ChatAiRun` | `chatairun` | ChatConversation, ChatMessage, AiModel, AiRunStatus | 12 |
| 47 | `chat_ai_run_metrics` | `ChatAiRunMetric` | `chatairunmetric` | ChatAiRun | 12 |
| 48 | `chat_ai_run_errors` | `ChatAiRunError` | `chatairunerror` | ChatAiRun | 12 |
| 49 | `escalations_statuses` | `EscalationStatus` | `escalationstatus` | — | 12 |
| 50 | `chat_escalations` | `ChatEscalation` | `chatescalation` | ChatConversation, EscalationStatus | 13 |
| 51 | `chat_escalation_assignments` | `ChatEscalationAssignment` | `chatescalationassignment` | ChatEscalation, Professional | 13 |
| 52 | `chat_escalation_status_history` | `ChatEscalationStatusHistory` | `chatescalationstatushistory` | ChatEscalation, EscalationStatus | 13 |

The ordering is dependency-driven, so migration numbers appear out of numerical order in blocks 5–9. Every table appears exactly once.

## Necessary deviations from the template

- PostgreSQL dependencies, dialect, schema placeholders, and `create-drop` are replaced by MySQL 8, Flyway enabled, and `ddl-auto: validate`.
- Country fields and lengths are replaced by the real V1 columns; StateRegion and CityMunicipality follow V2/V3 including their FK identifiers and timestamps.
- The template's `existsByCode` is preserved for V1–V3 because all three tables have a real code column. JPA derived-query names use the actual properties (`codeCountry`, `codeRegion`, and `codeCity`); this does not add a UNIQUE constraint absent from SQL.
- JPA UUIDs use Hibernate's character JDBC type plus explicit `CHAR(36)` column definitions; the template's unqualified UUID mapping is not compatible with the supplied MySQL DDL.
- Request validation enforces SQL nullability and maximum lengths. It does not invent `NOT BLANK` semantics where SQL only declares `NOT NULL`.
- Java version declarations are removed from child POMs and centralized at Java 21. Surefire is pinned because the BOM alone does not provide plugin management for the template's JUnit 5 tests.
- `DemoApplication` keeps the template name and scans the agreed `springboot` prefix. Entities and JPA repositories remain below `springboot.infrastructure`, so Spring Boot's normal entity/repository discovery covers them without duplicate scan annotations.
- The supplied Windows Maven wrapper is kept at 3.3.4, with its null `Target` check made safe because the original script crashes when `~/.m2` is a normal directory rather than a symbolic link.

## Structural acceptance rule

- Maven modules exist only at root-level `domain`, `application`, and `infrastructure`; each owns its standard `src/main` and `src/test` tree.
- Context inventories are exactly 9 application files, 6 domain files, 8 infrastructure files, one domain common exception, and the two equivalent context tests.
- Shared bases and `DemoApplication` exist once. Packages must match their paths, declaration kinds and essential inheritance must match the manifest, and fully qualified names must not be duplicated.
- Domain and application remain free of Spring, JPA, and Jakarta Validation imports.
- Run `powershell.exe -NoProfile -ExecutionPolicy Bypass -File .\tools\verify-structure.ps1` before Maven verification for every future block.
