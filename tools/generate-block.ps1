param(
    [string[]]$ContextPackages
)

$ErrorActionPreference = 'Stop'
$ProjectRoot = Split-Path -Parent $PSScriptRoot
$Utf8NoBom = New-Object System.Text.UTF8Encoding($false)

function Field($name, $type, $column, $length, $sample, $refPackage = $null, $refEntity = $null, $nullable = $false, $columnDefinition = $null) {
    [pscustomobject]@{
        Name = $name; Type = $type; Column = $column; Length = $length; Sample = $sample
        RefPackage = $refPackage; RefEntity = $refEntity; Nullable = $nullable; ColumnDefinition = $columnDefinition
    }
}

$Contexts = @(
    [pscustomobject]@{
        Entity = 'Country'; Package = 'country'; Table = 'countries'; Endpoint = 'countries'; HasTimestamps = $true
        Fields = @(
            (Field 'nameCountry' 'String' 'name_country' 50 '"Colombia"'),
            (Field 'codeCountry' 'String' 'code_country' 10 '"CO"'),
            (Field 'description' 'String' 'description' 100 '"Republic of Colombia"'),
            (Field 'active' 'boolean' 'is_active' 0 'true'),
            (Field 'telephonePrefix' 'String' 'telephone_prefix' 5 '"+57"')
        )
    },
    [pscustomobject]@{
        Entity = 'StateRegion'; Package = 'stateregion'; Table = 'state_regions'; Endpoint = 'state-regions'; HasTimestamps = $true
        Fields = @(
            (Field 'nameRegion' 'String' 'name_region' 50 '"Cundinamarca"'),
            (Field 'codeRegion' 'String' 'code_region' 10 '"CUN"'),
            (Field 'description' 'String' 'description' 100 '"Central region"'),
            (Field 'active' 'boolean' 'is_active' 0 'true'),
            (Field 'countryId' 'CountryId' 'country_id' 36 'CountryId.generate()' 'country' 'Country')
        )
    },
    [pscustomobject]@{
        Entity = 'CityMunicipality'; Package = 'citymunicipality'; Table = 'city_municipalities'; Endpoint = 'city-municipalities'; HasTimestamps = $true
        Fields = @(
            (Field 'nameCity' 'String' 'name_city' 50 '"Bogota"'),
            (Field 'codeCity' 'String' 'code_city' 10 '"BOG"'),
            (Field 'description' 'String' 'description' 100 '"Capital district"'),
            (Field 'active' 'boolean' 'is_active' 0 'true'),
            (Field 'regionId' 'StateRegionId' 'region_id' 36 'StateRegionId.generate()' 'stateregion' 'StateRegion')
        )
    },
    [pscustomobject]@{
        Entity = 'DocumentType'; Package = 'documenttype'; Table = 'document_types'; Endpoint = 'document-types'; HasTimestamps = $true
        UniqueConstraint = 'uk_document_types_code'; UniqueColumn = 'code'
        Fields = @(
            (Field 'code' 'String' 'code' 20 '"CC"'),
            (Field 'name' 'String' 'name' 50 '"Citizenship card"'),
            (Field 'active' 'boolean' 'active' 0 'true')
        )
    },
    [pscustomobject]@{
        Entity = 'Gender'; Package = 'gender'; Table = 'genders'; Endpoint = 'genders'; HasTimestamps = $true
        UniqueConstraint = 'uk_genders_description'; UniqueColumn = 'description'
        Fields = @(
            (Field 'description' 'String' 'description' 50 '"Female"')
        )
    },
    [pscustomobject]@{
        Entity = 'RelationshipType'; Package = 'relationshiptype'; Table = 'relationship_types'; Endpoint = 'relationship-types'; HasTimestamps = $false
        UniqueConstraint = 'uk_relationship_types_description'; UniqueColumn = 'description'
        Fields = @(
            (Field 'description' 'String' 'description' 50 '"Parent"')
        )
    },
    [pscustomobject]@{
        Entity = 'ProfessionalType'; Package = 'professionaltype'; Table = 'professional_types'; Endpoint = 'professional-types'; HasTimestamps = $true
        UniqueConstraint = 'uk_professional_types_name'; UniqueColumn = 'name'
        Fields = @(
            (Field 'name' 'String' 'name' 40 '"Psychologist"')
        )
    },
    [pscustomobject]@{
        Entity = 'Study'; Package = 'study'; Table = 'studies'; Endpoint = 'studies'; HasTimestamps = $true
        Fields = @(
            (Field 'name' 'String' 'name' 40 '"Clinical Psychology"')
        )
    },
    [pscustomobject]@{
        Entity = 'Professional'; Package = 'professional'; Table = 'professionals'; Endpoint = 'professionals'; HasTimestamps = $true
        UniqueConstraints = @(
            [pscustomobject]@{ Name = 'uk_professionals_document_number'; Column = 'document_number' },
            [pscustomobject]@{ Name = 'uk_professionals_first_name'; Column = 'first_name' },
            [pscustomobject]@{ Name = 'uk_professionals_last_name'; Column = 'last_name' },
            [pscustomobject]@{ Name = 'uk_professionals_license_number'; Column = 'license_number' }
        )
        Fields = @(
            (Field 'documentTypeId' 'DocumentTypeId' 'document_type_id' 36 'DocumentTypeId.generate()' 'documenttype' 'DocumentType'),
            (Field 'documentNumber' 'String' 'document_number' 30 '"123456789"'),
            (Field 'firstName' 'String' 'first_name' 60 '"Ana"'),
            (Field 'lastName' 'String' 'last_name' 60 '"Ramirez"'),
            (Field 'professionalTypeId' 'ProfessionalTypeId' 'professional_type' 36 'ProfessionalTypeId.generate()' 'professionaltype' 'ProfessionalType'),
            (Field 'licenseNumber' 'String' 'license_number' 100 '"PSY-12345"'),
            (Field 'active' 'boolean' 'active' 0 'true'),
            (Field 'cityId' 'CityMunicipalityId' 'city_id' 36 'CityMunicipalityId.generate()' 'citymunicipality' 'CityMunicipality')
        )
    },
    [pscustomobject]@{
        Entity = 'Patient'; Package = 'patient'; Table = 'patients'; Endpoint = 'patients'; HasTimestamps = $true
        UniqueConstraints = @(
            [pscustomobject]@{ Name = 'uk_patients_email'; Column = 'email' }
        )
        Fields = @(
            (Field 'documentTypeId' 'DocumentTypeId' 'document_type_id' 36 'DocumentTypeId.generate()' 'documenttype' 'DocumentType'),
            (Field 'documentNumber' 'String' 'document_number' 30 '"987654321"'),
            (Field 'firstName' 'String' 'first_name' 50 '"Laura"'),
            (Field 'middleName' 'String' 'middle_name' 50 'null' $null $null $true),
            (Field 'lastName' 'String' 'last_name' 50 '"Gomez"'),
            (Field 'secondLastName' 'String' 'second_last_name' 50 'null' $null $null $true),
            (Field 'birthDate' 'LocalDate' 'birth_date' 0 'java.time.LocalDate.of(1990, 1, 1)'),
            (Field 'biologicalSexId' 'GenderId' 'biological_sex_id' 36 'GenderId.generate()' 'gender' 'Gender'),
            (Field 'genderIdentityId' 'GenderId' 'gender_identity' 36 'GenderId.generate()' 'gender' 'Gender'),
            (Field 'email' 'String' 'email' 150 '"laura@example.com"'),
            (Field 'phone' 'String' 'phone' 30 '"3001234567"'),
            (Field 'address' 'String' 'address' 250 '"Main Street 123"'),
            (Field 'active' 'boolean' 'active' 0 'true'),
            (Field 'createdBy' 'ProfessionalId' 'created_by' 36 'null' 'professional' 'Professional' $true),
            (Field 'updatedBy' 'ProfessionalId' 'updated_by' 36 'null' 'professional' 'Professional' $true),
            (Field 'cityId' 'CityMunicipalityId' 'city_id' 36 'CityMunicipalityId.generate()' 'citymunicipality' 'CityMunicipality')
        )
    },
    [pscustomobject]@{
        Entity = 'Contact'; Package = 'contact'; Table = 'contacts'; Endpoint = 'contacts'; HasTimestamps = $true
        UniqueConstraints = @(
            [pscustomobject]@{ Name = 'uk_contacts_email'; Column = 'email' }
        )
        Fields = @(
            (Field 'fullName' 'String' 'full_name' 200 '"Emergency Contact"'),
            (Field 'email' 'String' 'email' 150 '"contact@example.com"'),
            (Field 'notes' 'String' 'notes' 0 '"Primary contact"' $null $null $false 'text'),
            (Field 'cityId' 'CityMunicipalityId' 'city_id' 36 'CityMunicipalityId.generate()' 'citymunicipality' 'CityMunicipality'),
            (Field 'createdBy' 'ProfessionalId' 'created_by' 36 'ProfessionalId.generate()' 'professional' 'Professional'),
            (Field 'updatedBy' 'ProfessionalId' 'updated_by' 36 'null' 'professional' 'Professional' $true)
        )
    },
    [pscustomobject]@{
        Entity = 'PhoneContact'; Package = 'phonecontact'; Table = 'phone_contacts'; Endpoint = 'phone-contacts'; HasTimestamps = $false
        Fields = @(
            (Field 'contactId' 'ContactId' 'contact_id' 36 'ContactId.generate()' 'contact' 'Contact'),
            (Field 'phone' 'String' 'phone' 30 'null' $null $null $true),
            (Field 'notes' 'String' 'notes' 0 '"Call after 5 PM"' $null $null $false 'text')
        )
    },
    [pscustomobject]@{
        Entity = 'EmailContact'; Package = 'emailcontact'; Table = 'email_contacts'; Endpoint = 'email-contacts'; HasTimestamps = $true
        UniqueConstraints = @(
            [pscustomobject]@{ Name = 'uk_email_contacts_email'; Column = 'email' }
        )
        Fields = @(
            (Field 'contactId' 'ContactId' 'contact_id' 36 'ContactId.generate()' 'contact' 'Contact'),
            (Field 'email' 'String' 'email' 150 '"emergency@example.com"'),
            (Field 'notes' 'String' 'notes' 0 '"Preferred email"' $null $null $false 'text')
        )
    },
    [pscustomobject]@{
        Entity = 'PatientContact'; Package = 'patientcontact'; Table = 'patient_contacts'; Endpoint = 'patient-contacts'; HasTimestamps = $false
        Fields = @(
            (Field 'contactId' 'ContactId' 'contact_id' 36 'ContactId.generate()' 'contact' 'Contact'),
            (Field 'patientId' 'PatientId' 'patient_id' 36 'PatientId.generate()' 'patient' 'Patient'),
            (Field 'primaryContact' 'boolean' 'is_primary_contact' 0 'true'),
            (Field 'emergencyContact' 'boolean' 'is_emergency_contact' 0 'false'),
            (Field 'relationshipTypeId' 'RelationshipTypeId' 'relationship_type_id' 36 'RelationshipTypeId.generate()' 'relationshiptype' 'RelationshipType')
        )
    },
    [pscustomobject]@{
        Entity = 'PatientAllergy'; Package = 'patientallergy'; Table = 'patient_allergies'; Endpoint = 'patient-allergies'; HasTimestamps = $true
        Fields = @(
            (Field 'patientId' 'PatientId' 'patient_id' 36 'PatientId.generate()' 'patient' 'Patient'),
            (Field 'substance' 'String' 'substance' 200 '"Penicillin"'),
            (Field 'reaction' 'String' 'reaction' 0 'null' $null $null $true 'text'),
            (Field 'severity' 'String' 'severity' 20 '"HIGH"'),
            (Field 'active' 'boolean' 'active' 0 'true'),
            (Field 'recordedAt' 'LocalDateTime' 'recorded_at' 0 'java.time.LocalDateTime.of(2026, 1, 10, 8, 0)'),
            (Field 'recordedBy' 'ProfessionalId' 'recorded_by' 36 'ProfessionalId.generate()' 'professional' 'Professional')
        )
    },
    [pscustomobject]@{
        Entity = 'ProfessionalStudy'; Package = 'professionalstudy'; Table = 'professional_studies'; Endpoint = 'professional-studies'; HasTimestamps = $true
        Fields = @(
            (Field 'studyId' 'StudyId' 'study_id' 36 'StudyId.generate()' 'study' 'Study'),
            (Field 'professionalId' 'ProfessionalId' 'professional_id' 36 'ProfessionalId.generate()' 'professional' 'Professional'),
            (Field 'title' 'String' 'title' 100 '"Clinical Psychology"'),
            (Field 'university' 'String' 'university' 100 '"National University"'),
            (Field 'valid' 'boolean' 'is_valid' 0 'true'),
            (Field 'resolutionNumber' 'String' 'resolution_number' 60 'null' $null $null $true),
            (Field 'countryId' 'CountryId' 'country_id' 36 'CountryId.generate()' 'country' 'Country')
        )
    },
    [pscustomobject]@{
        Entity = 'ClinicalRecordStatus'; Package = 'clinicalrecordstatus'; Table = 'clinical_record_statuses'; Endpoint = 'clinical-record-statuses'; HasTimestamps = $true
        UniqueConstraints = @(
            [pscustomobject]@{ Name = 'uk_clinical_record_statuses_code'; Column = 'code' },
            [pscustomobject]@{ Name = 'uk_clinical_record_statuses_name'; Column = 'name' }
        )
        Fields = @(
            (Field 'code' 'String' 'code' 20 '"OPEN"'),
            (Field 'name' 'String' 'name' 50 '"Open"')
        )
    },
    [pscustomobject]@{
        Entity = 'EncounterType'; Package = 'encountertype'; Table = 'encounter_types'; Endpoint = 'encounter-types'; HasTimestamps = $true
        UniqueConstraints = @(
            [pscustomobject]@{ Name = 'uk_encounter_types_code'; Column = 'code' },
            [pscustomobject]@{ Name = 'uk_encounter_types_name'; Column = 'name' }
        )
        Fields = @(
            (Field 'code' 'String' 'code' 20 '"INITIAL"'),
            (Field 'name' 'String' 'name' 50 '"Initial consultation"'),
            (Field 'active' 'boolean' 'active' 0 'true')
        )
    },
    [pscustomobject]@{
        Entity = 'EncounterModality'; Package = 'encountermodality'; Table = 'encounter_modalities'; Endpoint = 'encounter-modalities'; HasTimestamps = $true
        UniqueConstraints = @(
            [pscustomobject]@{ Name = 'uk_encounter_modalities_code'; Column = 'code' },
            [pscustomobject]@{ Name = 'uk_encounter_modalities_name'; Column = 'name' }
        )
        Fields = @(
            (Field 'code' 'String' 'code' 20 '"IN_PERSON"'),
            (Field 'name' 'String' 'name' 50 '"In person"'),
            (Field 'active' 'boolean' 'active' 0 'true')
        )
    },
    [pscustomobject]@{
        Entity = 'EncounterStatus'; Package = 'encounterstatus'; Table = 'encounter_statuses'; Endpoint = 'encounter-statuses'; HasTimestamps = $true
        UniqueConstraints = @(
            [pscustomobject]@{ Name = 'uk_encounter_statuses_code'; Column = 'code' },
            [pscustomobject]@{ Name = 'uk_encounter_statuses_name'; Column = 'name' }
        )
        Fields = @(
            (Field 'code' 'String' 'code' 20 '"SCHEDULED"'),
            (Field 'name' 'String' 'name' 50 '"Scheduled"'),
            (Field 'active' 'boolean' 'active' 0 'true')
        )
    },
    [pscustomobject]@{
        Entity = 'RiskLevel'; Package = 'risklevel'; Table = 'risk_levels'; Endpoint = 'risk-levels'; HasTimestamps = $true
        UniqueConstraints = @(
            [pscustomobject]@{ Name = 'uk_risk_levels_code'; Column = 'code' },
            [pscustomobject]@{ Name = 'uk_risk_levels_name'; Column = 'name' }
        )
        Fields = @(
            (Field 'code' 'String' 'code' 20 '"HIGH"'),
            (Field 'name' 'String' 'name' 50 '"High"'),
            (Field 'active' 'boolean' 'active' 0 'true'),
            (Field 'severity' 'int' 'severity' 0 '3')
        )
    },
    [pscustomobject]@{
        Entity = 'TreatmentStatus'; Package = 'treatmentstatus'; Table = 'treatment_statuses'; Endpoint = 'treatment-statuses'; HasTimestamps = $true
        UniqueConstraints = @(
            [pscustomobject]@{ Name = 'uk_treatment_statuses_code'; Column = 'code' },
            [pscustomobject]@{ Name = 'uk_treatment_statuses_name'; Column = 'name' }
        )
        Fields = @(
            (Field 'code' 'String' 'code' 20 '"ACTIVE"'),
            (Field 'name' 'String' 'name' 50 '"Active"'),
            (Field 'active' 'boolean' 'active' 0 'true')
        )
    },
    [pscustomobject]@{
        Entity = 'TreatmentGoalStatus'; Package = 'treatmentgoalstatus'; Table = 'treatment_goal_statuses'; Endpoint = 'treatment-goal-statuses'; HasTimestamps = $true
        UniqueConstraints = @(
            [pscustomobject]@{ Name = 'uk_treatment_goal_statuses_code'; Column = 'code' },
            [pscustomobject]@{ Name = 'uk_treatment_goal_statuses_name'; Column = 'name' }
        )
        Fields = @(
            (Field 'code' 'String' 'code' 20 '"IN_PROGRESS"'),
            (Field 'name' 'String' 'name' 50 '"In progress"'),
            (Field 'active' 'boolean' 'active' 0 'true')
        )
    },
    [pscustomobject]@{
        Entity = 'MedicationRoute'; Package = 'medicationroute'; Table = 'medication_routes'; Endpoint = 'medication-routes'; HasTimestamps = $true
        UniqueConstraints = @(
            [pscustomobject]@{ Name = 'uk_medication_routes_code'; Column = 'code' }
        )
        Fields = @(
            (Field 'code' 'String' 'code' 20 '"ORAL"'),
            (Field 'name' 'String' 'name' 50 '"Oral"'),
            (Field 'active' 'boolean' 'active' 0 'true')
        )
    },
    [pscustomobject]@{
        Entity = 'AssessmentType'; Package = 'assessmenttype'; Table = 'assessment_types'; Endpoint = 'assessment-types'; HasTimestamps = $true
        UniqueConstraints = @(
            [pscustomobject]@{ Name = 'uk_assessment_types_code'; Column = 'code' }
        )
        Fields = @(
            (Field 'code' 'String' 'code' 20 '"INITIAL"'),
            (Field 'name' 'String' 'name' 50 '"Initial assessment"'),
            (Field 'active' 'boolean' 'active' 0 'true'),
            (Field 'description' 'String' 'description' 0 '"Initial clinical assessment"' $null $null $false 'text')
        )
    },
    [pscustomobject]@{
        Entity = 'ConsentType'; Package = 'consenttype'; Table = 'consent_types'; Endpoint = 'consent-types'; HasTimestamps = $true
        UniqueConstraints = @(
            [pscustomobject]@{ Name = 'uk_consent_types_code'; Column = 'code' }
        )
        Fields = @(
            (Field 'code' 'String' 'code' 20 '"TREATMENT"'),
            (Field 'name' 'String' 'name' 50 '"Treatment consent"'),
            (Field 'active' 'boolean' 'active' 0 'true'),
            (Field 'description' 'String' 'description' 0 '"Consent for treatment"' $null $null $false 'text')
        )
    },
    [pscustomobject]@{
        Entity = 'DiagnosticSystem'; Package = 'diagnosticsystem'; Table = 'diagnostic_systems'; Endpoint = 'diagnostic-systems'; HasTimestamps = $true
        UniqueConstraints = @(
            [pscustomobject]@{ Name = 'uk_diagnostic_systems_code'; Column = 'code' }
        )
        Fields = @(
            (Field 'code' 'String' 'code' 20 '"DSM"'),
            (Field 'name' 'String' 'name' 50 '"Diagnostic and Statistical Manual"'),
            (Field 'active' 'boolean' 'active' 0 'true'),
            (Field 'version' 'String' 'version' 20 '"5-TR"')
        )
    },
    [pscustomobject]@{
        Entity = 'ClinicalRecord'; Package = 'clinicalrecord'; Table = 'clinical_records'; Endpoint = 'clinical-records'; HasTimestamps = $false; HasCreatedAt = $true; HasUpdatedAt = $false
        Fields = @(
            (Field 'patientId' 'PatientId' 'patient_id' 36 'PatientId.generate()' 'patient' 'Patient'),
            (Field 'creationDate' 'LocalDateTime' 'creation_date' 0 'java.time.LocalDateTime.of(2026, 1, 10, 8, 0)'),
            (Field 'recordNumber' 'String' 'record_number' 50 '"CR-2026-0001"'),
            (Field 'openedAt' 'LocalDateTime' 'opened_at' 0 'java.time.LocalDateTime.of(2026, 1, 10, 8, 0)'),
            (Field 'closedAt' 'LocalDateTime' 'closed_at' 0 'java.time.LocalDateTime.of(2026, 1, 10, 9, 0)'),
            (Field 'statusId' 'ClinicalRecordStatusId' 'status_id' 36 'ClinicalRecordStatusId.generate()' 'clinicalrecordstatus' 'ClinicalRecordStatus'),
            (Field 'createdBy' 'ProfessionalId' 'created_by' 36 'ProfessionalId.generate()' 'professional' 'Professional')
        )
    },
    [pscustomobject]@{
        Entity = 'Encounter'; Package = 'encounter'; Table = 'encounters'; Endpoint = 'encounters'; HasTimestamps = $true
        Fields = @(
            (Field 'clinicalRecordId' 'ClinicalRecordId' 'clinical_record_id' 36 'ClinicalRecordId.generate()' 'clinicalrecord' 'ClinicalRecord'),
            (Field 'professionalId' 'ProfessionalId' 'professional_id' 36 'ProfessionalId.generate()' 'professional' 'Professional'),
            (Field 'encounterTypeId' 'EncounterTypeId' 'encounter_type_id' 36 'EncounterTypeId.generate()' 'encountertype' 'EncounterType'),
            (Field 'startedAt' 'LocalDateTime' 'started_at' 0 'java.time.LocalDateTime.of(2026, 1, 10, 8, 0)'),
            (Field 'endedAt' 'LocalDateTime' 'ended_at' 0 'java.time.LocalDateTime.of(2026, 1, 10, 9, 0)'),
            (Field 'reasonForVisit' 'String' 'reason_for_visit' 0 '"Initial consultation"' $null $null $false 'text'),
            (Field 'currentCondition' 'String' 'current_condition' 0 '"Stable"' $null $null $false 'text'),
            (Field 'modalityId' 'EncounterModalityId' 'modality_id' 36 'EncounterModalityId.generate()' 'encountermodality' 'EncounterModality'),
            (Field 'statusId' 'EncounterStatusId' 'status_id' 36 'EncounterStatusId.generate()' 'encounterstatus' 'EncounterStatus'),
            (Field 'createdBy' 'ProfessionalId' 'created_by' 36 'ProfessionalId.generate()' 'professional' 'Professional'),
            (Field 'updatedBy' 'ProfessionalId' 'updated_by' 36 'ProfessionalId.generate()' 'professional' 'Professional')
        )
    }
)

function Write-Generated($relativePath, $content) {
    $path = Join-Path $ProjectRoot $relativePath
    if (Test-Path -LiteralPath $path) { throw "Refusing to overwrite $path" }
    $directory = Split-Path -Parent $path
    [System.IO.Directory]::CreateDirectory($directory) | Out-Null
    [System.IO.File]::WriteAllText($path, $content.Trim() + [Environment]::NewLine, $Utf8NoBom)
}

function Cap($value) { $value.Substring(0, 1).ToUpperInvariant() + $value.Substring(1) }
function Is-Ref($field) { $null -ne $field.RefEntity }
function Persistence-Type($field) { if (Is-Ref $field) { 'UUID' } else { $field.Type } }
function Response-Type($field) { if (Is-Ref $field) { 'UUID' } else { $field.Type } }
function Getter($field) { if ($field.Type -eq 'boolean') { 'is' + (Cap $field.Name) } else { 'get' + (Cap $field.Name) } }
function Ref-Imports($context) {
    (($context.Fields | Where-Object { Is-Ref $_ } | ForEach-Object { "import springboot.domain.$($_.RefPackage).model.valueobject.$($_.RefEntity)Id;" } | Sort-Object -Unique) -join "`n")
}
function Typed-Args($context, $indent = '            ') {
    (($context.Fields | ForEach-Object { "$($_.Type) $($_.Name)" }) -join ",`n$indent")
}
function Names($context, $prefix = '', $indent = '                ') {
    (($context.Fields | ForEach-Object { if ($prefix) { "$prefix.$($_.Name)()" } else { $_.Name } }) -join ",`n$indent")
}
function Response-Args($context, $variable, $indent = '                ') {
    $values = @("$variable.id().value()")
    $values += $context.Fields | ForEach-Object {
        if (Is-Ref $_) {
            if (Is-Nullable $_) { "$variable.$($_.Name)() == null ? null : $variable.$($_.Name)().value()" } else { "$variable.$($_.Name)().value()" }
        } else { "$variable.$($_.Name)()" }
    }
    if (Has-CreatedAt $context) { $values += "$variable.createdAt()" }
    if (Has-UpdatedAt $context) { $values += "$variable.updatedAt()" }
    $values -join ",`n$indent"
}
function Code-Field($context) {
    $context.Fields | Where-Object { $_.Name -match '^code' } | Select-Object -First 1
}
function Has-Timestamps($context) {
    [bool]$context.HasTimestamps
}
function Has-CreatedAt($context) {
    if ($null -ne $context.PSObject.Properties['HasCreatedAt']) { return [bool]$context.HasCreatedAt }
    Has-Timestamps $context
}
function Has-UpdatedAt($context) {
    if ($null -ne $context.PSObject.Properties['HasUpdatedAt']) { return [bool]$context.HasUpdatedAt }
    Has-Timestamps $context
}
function Has-TechnicalTimestamp($context) {
    (Has-CreatedAt $context) -or (Has-UpdatedAt $context)
}
function Is-Nullable($field) {
    [bool]$field.Nullable
}
function Is-Primitive($field) {
    $field.Type -in @('boolean', 'int', 'long', 'double')
}
function Request-Type($field) {
    if (Is-Ref $field) { return 'UUID' }
    switch ($field.Type) {
        'boolean' { 'Boolean' }
        'int' { 'Integer' }
        'long' { 'Long' }
        'double' { 'Double' }
        default { $field.Type }
    }
}
function Type-Imports($context, [string[]]$exclude = @()) {
    $imports = @()
    if ($context.Fields.Type -contains 'LocalDate' -and 'LocalDate' -notin $exclude) { $imports += 'import java.time.LocalDate;' }
    if ($context.Fields.Type -contains 'LocalDateTime' -and 'LocalDateTime' -notin $exclude) { $imports += 'import java.time.LocalDateTime;' }
    $imports -join "`n"
}
function Unique-Constraints($context) {
    if ($context.UniqueConstraints) { return @($context.UniqueConstraints) }
    if ($context.UniqueConstraint) {
        return @([pscustomobject]@{ Name = $context.UniqueConstraint; Column = $context.UniqueColumn })
    }
    @()
}

function Generate-Domain($context) {
    $entity = $context.Entity; $pkg = $context.Package; $refs = Ref-Imports $context
    $typeImports = Type-Imports $context @('LocalDateTime')
    $codeField = Code-Field $context
    $existsByCode = if ($null -ne $codeField) { '    boolean existsByCode(String code);' } else { '' }
    Write-Generated "domain/src/main/java/springboot/domain/$pkg/model/valueobject/${entity}Id.java" @"
package springboot.domain.$pkg.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ${entity}Id(UUID value) {
    public ${entity}Id {
        Objects.requireNonNull(value, "${entity}Id value must not be null");
    }

    public static ${entity}Id generate() {
        return new ${entity}Id(UUID.randomUUID());
    }
}
"@
    foreach ($suffix in @('Registered', 'Deleted')) {
        Write-Generated "domain/src/main/java/springboot/domain/$pkg/event/${entity}${suffix}Event.java" @"
package springboot.domain.$pkg.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.$pkg.model.valueobject.${entity}Id;

public record ${entity}${suffix}Event(
        ${entity}Id id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ${entity}${suffix}Event {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
"@
    }
    $eventFields = ($context.Fields | ForEach-Object { "$($_.Type) $($_.Name)" }) -join ",`n        "
    $eventChecks = ($context.Fields | Where-Object { -not (Is-Primitive $_) -and -not (Is-Nullable $_) } | ForEach-Object { "        Objects.requireNonNull($($_.Name), `"$($_.Name) must not be null`");" }) -join "`n"
    Write-Generated "domain/src/main/java/springboot/domain/$pkg/event/${entity}UpdatedEvent.java" @"
package springboot.domain.$pkg.event;

import java.time.LocalDateTime;
import java.util.Objects;
$typeImports

import springboot.domain.common.event.DomainEvent;
import springboot.domain.$pkg.model.valueobject.${entity}Id;
$refs

public record ${entity}UpdatedEvent(
        ${entity}Id id,
        $eventFields,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ${entity}UpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
$eventChecks
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
"@
    $declarations = ($context.Fields | ForEach-Object { "    private $($_.Type) $($_.Name);" }) -join "`n"
    $assignments = ($context.Fields | ForEach-Object { if ((Is-Primitive $_) -or (Is-Nullable $_)) { "        this.$($_.Name) = $($_.Name);" } else { "        this.$($_.Name) = Objects.requireNonNull($($_.Name), `"$($_.Name) must not be null`");" } }) -join "`n"
    $getters = ($context.Fields | ForEach-Object { "    public $($_.Type) $($_.Name)() {`n        return $($_.Name);`n    }" }) -join "`n`n"
    $updateEventArgs = ($context.Fields | ForEach-Object { "this.$($_.Name)" }) -join ",`n                        "
    $typedArgs = Typed-Args $context
    $names = Names $context
    $timestampDeclarations = @()
    if (Has-CreatedAt $context) { $timestampDeclarations += '    private final LocalDateTime createdAt;' }
    if (Has-UpdatedAt $context) { $timestampDeclarations += '    private LocalDateTime updatedAt;' }
    $timestampDeclarations = $timestampDeclarations -join "`n"
    $constructorTimestampArguments = ''
    if (Has-CreatedAt $context) { $constructorTimestampArguments += ",`n            LocalDateTime createdAt" }
    if (Has-UpdatedAt $context) { $constructorTimestampArguments += ",`n            LocalDateTime updatedAt" }
    $timestampAssignments = @()
    if (Has-CreatedAt $context) { $timestampAssignments += '        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");' }
    if (Has-UpdatedAt $context) { $timestampAssignments += '        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");' }
    $timestampAssignments = $timestampAssignments -join "`n"
    $registerTime = if (Has-TechnicalTimestamp $context) { '        LocalDateTime now = LocalDateTime.now();' } else { '        LocalDateTime occurredOn = LocalDateTime.now();' }
    $registerTimestampArguments = ''
    if (Has-CreatedAt $context) { $registerTimestampArguments += ",`n                now" }
    if (Has-UpdatedAt $context) { $registerTimestampArguments += ",`n                now" }
    $registeredEventTime = if (Has-TechnicalTimestamp $context) { 'now' } else { 'occurredOn' }
    $restoreTimestampArguments = ''
    $restoreConstructorTimestampArguments = ''
    if (Has-CreatedAt $context) { $restoreTimestampArguments += ",`n            LocalDateTime createdAt"; $restoreConstructorTimestampArguments += ",`n                createdAt" }
    if (Has-UpdatedAt $context) { $restoreTimestampArguments += ",`n            LocalDateTime updatedAt"; $restoreConstructorTimestampArguments += ",`n                updatedAt" }
    $updateTime = if (Has-UpdatedAt $context) { '        this.updatedAt = LocalDateTime.now();' } else { '        LocalDateTime occurredOn = LocalDateTime.now();' }
    $updatedEventTime = if (Has-UpdatedAt $context) { 'this.updatedAt' } else { 'occurredOn' }
    $timestampGetters = @()
    if (Has-CreatedAt $context) { $timestampGetters += @"
    public LocalDateTime createdAt() {
        return createdAt;
    }
"@ }
    if (Has-UpdatedAt $context) { $timestampGetters += @"
    public LocalDateTime updatedAt() {
        return updatedAt;
    }
"@ }
    $timestampGetters = $timestampGetters -join "`n`n"
    Write-Generated "domain/src/main/java/springboot/domain/$pkg/model/aggregate/$entity.java" @"
package springboot.domain.$pkg.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
$typeImports

import springboot.domain.common.model.AggregateRoot;
import springboot.domain.$pkg.event.${entity}RegisteredEvent;
import springboot.domain.$pkg.event.${entity}UpdatedEvent;
import springboot.domain.$pkg.model.valueobject.${entity}Id;
$refs

public class $entity extends AggregateRoot {
    private final ${entity}Id id;
$declarations
$timestampDeclarations

    private $entity(
            ${entity}Id id,
            $typedArgs$constructorTimestampArguments) {
        this.id = Objects.requireNonNull(id, "id must not be null");
$assignments
$timestampAssignments
    }

    public static $entity register(
            $typedArgs) {
        ${entity}Id id = ${entity}Id.generate();
$registerTime
        $entity aggregate = new $entity(
                id,
                $names$registerTimestampArguments);
        aggregate.recordEvent(new ${entity}RegisteredEvent(id, $registeredEventTime));
        return aggregate;
    }

    public static $entity restore(
            ${entity}Id id,
            $typedArgs$restoreTimestampArguments) {
        return new $entity(
                id,
                $names$restoreConstructorTimestampArguments);
    }

    public void update(
            $typedArgs) {
$assignments
$updateTime
        recordEvent(new ${entity}UpdatedEvent(
                        this.id,
                        $updateEventArgs,
                        $updatedEventTime));
    }

    public ${entity}Id id() {
        return id;
    }

$getters

$timestampGetters
}
"@
    Write-Generated "domain/src/main/java/springboot/domain/$pkg/port/repository/${entity}Repository.java" @"
package springboot.domain.$pkg.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.$pkg.model.aggregate.$entity;
import springboot.domain.$pkg.model.valueobject.${entity}Id;

public interface ${entity}Repository {
    $entity save($entity aggregate);
    Optional<$entity> findById(${entity}Id id);
    List<$entity> findAll();
$existsByCode
    void delete($entity aggregate);
}
"@
    Write-Generated "domain/src/main/java/springboot/domain/common/exception/${entity}NotFoundException.java" @"
package springboot.domain.common.exception;

import springboot.domain.$pkg.model.valueobject.${entity}Id;

public class ${entity}NotFoundException extends RuntimeException {
    public ${entity}NotFoundException(${entity}Id id) {
        super("$entity not found with id: " + id.value());
    }
}
"@
}

function Generate-Application($context) {
    $entity = $context.Entity; $pkg = $context.Package; $refs = Ref-Imports $context
    $typeImports = Type-Imports $context
    $fieldList = ($context.Fields | ForEach-Object { "$($_.Type) $($_.Name)" }) -join ",`n        "
    $checks = ($context.Fields | Where-Object { -not (Is-Primitive $_) -and -not (Is-Nullable $_) } | ForEach-Object { "        Objects.requireNonNull($($_.Name), `"$($_.Name) must not be null`");" }) -join "`n"
    Write-Generated "application/src/main/java/springboot/application/$pkg/command/Register${entity}Command.java" @"
package springboot.application.$pkg.command;

import java.util.Objects;
$typeImports

$refs

public record Register${entity}Command(
        $fieldList
) {
    public Register${entity}Command {
$checks
    }
}
"@
    Write-Generated "application/src/main/java/springboot/application/$pkg/command/Update${entity}Command.java" @"
package springboot.application.$pkg.command;

import java.util.Objects;
$typeImports

import springboot.domain.$pkg.model.valueobject.${entity}Id;
$refs

public record Update${entity}Command(
        ${entity}Id id,
        $fieldList
) {
    public Update${entity}Command {
        Objects.requireNonNull(id, "id must not be null");
$checks
    }
}
"@
    $responseFields = @('UUID id') + ($context.Fields | ForEach-Object { "$(Response-Type $_) $($_.Name)" })
    if (Has-CreatedAt $context) { $responseFields += 'LocalDateTime createdAt' }
    if (Has-UpdatedAt $context) { $responseFields += 'LocalDateTime updatedAt' }
    $responseFieldsText = $responseFields -join ",`n        "
    $responseTimeImport = if (Has-TechnicalTimestamp $context) { 'import java.time.LocalDateTime;' } else { '' }
    $responseTypeImports = if (Has-TechnicalTimestamp $context) { Type-Imports $context @('LocalDateTime') } else { Type-Imports $context }
    Write-Generated "application/src/main/java/springboot/application/$pkg/dto/${entity}Response.java" @"
package springboot.application.$pkg.dto;

$responseTimeImport
$responseTypeImports
import java.util.UUID;

public record ${entity}Response(
        $responseFieldsText
) {
}
"@
    Write-Generated "application/src/main/java/springboot/application/$pkg/exception/${entity}NotFoundApplicationException.java" @"
package springboot.application.$pkg.exception;

import springboot.application.common.exception.ApplicationException;

public class ${entity}NotFoundApplicationException extends ApplicationException {
    public ${entity}NotFoundApplicationException(String message) {
        super(message);
    }
}
"@
    $commandNames = Names $context 'command'
    $savedResponse = Response-Args $context 'saved'
    $aggregateResponse = Response-Args $context 'aggregate'
    $listResponse = Response-Args $context 'aggregate' '                                '
    $base = "application/src/main/java/springboot/application/$pkg/usecase"
    Write-Generated "$base/Register${entity}UseCase.java" @"
package springboot.application.$pkg.usecase;

import springboot.application.$pkg.command.Register${entity}Command;
import springboot.application.$pkg.dto.${entity}Response;
import springboot.domain.$pkg.model.aggregate.$entity;
import springboot.domain.$pkg.port.repository.${entity}Repository;

public class Register${entity}UseCase {
    private final ${entity}Repository repository;
    public Register${entity}UseCase(${entity}Repository repository) { this.repository = repository; }

    public ${entity}Response execute(Register${entity}Command command) {
        $entity aggregate = $entity.register(
                $commandNames);
        $entity saved = repository.save(aggregate);
        return new ${entity}Response(
                $savedResponse);
    }
}
"@
    Write-Generated "$base/Get${entity}ByIdUseCase.java" @"
package springboot.application.$pkg.usecase;

import springboot.application.$pkg.dto.${entity}Response;
import springboot.application.$pkg.exception.${entity}NotFoundApplicationException;
import springboot.domain.$pkg.model.valueobject.${entity}Id;
import springboot.domain.$pkg.port.repository.${entity}Repository;

public class Get${entity}ByIdUseCase {
    private final ${entity}Repository repository;
    public Get${entity}ByIdUseCase(${entity}Repository repository) { this.repository = repository; }

    public ${entity}Response execute(${entity}Id id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ${entity}NotFoundApplicationException(id.value().toString()));
        return new ${entity}Response(
                $aggregateResponse);
    }
}
"@
    Write-Generated "$base/List${entity}UseCase.java" @"
package springboot.application.$pkg.usecase;

import java.util.List;

import springboot.application.$pkg.dto.${entity}Response;
import springboot.domain.$pkg.port.repository.${entity}Repository;

public class List${entity}UseCase {
    private final ${entity}Repository repository;
    public List${entity}UseCase(${entity}Repository repository) { this.repository = repository; }

    public List<${entity}Response> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ${entity}Response(
                                $listResponse))
                .toList();
    }
}
"@
    Write-Generated "$base/Update${entity}UseCase.java" @"
package springboot.application.$pkg.usecase;

import springboot.application.$pkg.command.Update${entity}Command;
import springboot.application.$pkg.dto.${entity}Response;
import springboot.application.$pkg.exception.${entity}NotFoundApplicationException;
import springboot.domain.$pkg.port.repository.${entity}Repository;

public class Update${entity}UseCase {
    private final ${entity}Repository repository;
    public Update${entity}UseCase(${entity}Repository repository) { this.repository = repository; }

    public ${entity}Response execute(Update${entity}Command command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ${entity}NotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                $commandNames);
        var saved = repository.save(aggregate);
        return new ${entity}Response(
                $savedResponse);
    }
}
"@
    Write-Generated "$base/Delete${entity}UseCase.java" @"
package springboot.application.$pkg.usecase;

import java.time.LocalDateTime;

import springboot.application.$pkg.exception.${entity}NotFoundApplicationException;
import springboot.domain.$pkg.event.${entity}DeletedEvent;
import springboot.domain.$pkg.model.valueobject.${entity}Id;
import springboot.domain.$pkg.port.repository.${entity}Repository;

public class Delete${entity}UseCase {
    private final ${entity}Repository repository;
    public Delete${entity}UseCase(${entity}Repository repository) { this.repository = repository; }

    public ${entity}DeletedEvent execute(${entity}Id id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ${entity}NotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ${entity}DeletedEvent(id, LocalDateTime.now());
    }
}
"@
}

function Generate-Infrastructure($context) {
    $entity = $context.Entity; $pkg = $context.Package; $refs = Ref-Imports $context
    $typeImports = Type-Imports $context
    $codeField = Code-Field $context
    $jpaExistsByCode = if ($null -ne $codeField) { "    boolean existsBy$(Cap $codeField.Name)(String code);" } else { '' }
    $adapterExistsByCode = if ($null -ne $codeField) { "    @Override public boolean existsByCode(String code) { return jpaRepository.existsBy$(Cap $codeField.Name)(code); }" } else { '' }
    $requestFields = ($context.Fields | ForEach-Object {
        $type = Request-Type $_
        $annotations = @()
        if (-not (Is-Nullable $_)) { $annotations += "@NotNull(message = `"$($_.Name) is required`")" }
        if ($_.Length -gt 0 -and -not (Is-Ref $_)) { $annotations += "@Size(max = $($_.Length), message = `"$($_.Name) must have at most $($_.Length) characters`")" }
        $annotationText = if ($annotations.Count -gt 0) { ($annotations -join "`n        ") + "`n        " } else { '' }
        "$annotationText$type $($_.Name)"
    }) -join ",`n`n        "
    $uuidImport = if ($context.Fields | Where-Object { Is-Ref $_ }) { "import java.util.UUID;`n`n" } else { '' }
    foreach ($action in @('Create', 'Update')) {
        Write-Generated "infrastructure/src/main/java/springboot/infrastructure/$pkg/adapters/in/rest/dtos/${action}${entity}Request.java" @"
package springboot.infrastructure.$pkg.adapters.in.rest.dtos;

${uuidImport}$typeImports
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ${action}${entity}Request(
        $requestFields
) {
}
"@
    }
    $requestArgs = ($context.Fields | ForEach-Object {
        if (Is-Ref $_) {
            if (Is-Nullable $_) { "request.$($_.Name)() == null ? null : new $($_.RefEntity)Id(request.$($_.Name)())" }
            else { "new $($_.RefEntity)Id(request.$($_.Name)())" }
        } else { "request.$($_.Name)()" }
    }) -join ",`n                        "
    Write-Generated "infrastructure/src/main/java/springboot/infrastructure/$pkg/adapters/in/rest/controllers/${entity}Controller.java" @"
package springboot.infrastructure.$pkg.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import springboot.application.$pkg.command.Register${entity}Command;
import springboot.application.$pkg.command.Update${entity}Command;
import springboot.application.$pkg.dto.${entity}Response;
import springboot.application.$pkg.usecase.Delete${entity}UseCase;
import springboot.application.$pkg.usecase.Get${entity}ByIdUseCase;
import springboot.application.$pkg.usecase.List${entity}UseCase;
import springboot.application.$pkg.usecase.Register${entity}UseCase;
import springboot.application.$pkg.usecase.Update${entity}UseCase;
import springboot.domain.$pkg.model.valueobject.${entity}Id;
$refs
import springboot.infrastructure.$pkg.adapters.in.rest.dtos.Create${entity}Request;
import springboot.infrastructure.$pkg.adapters.in.rest.dtos.Update${entity}Request;

@RestController
@RequestMapping("/api/$($context.Endpoint)")
public class ${entity}Controller {
    private final Register${entity}UseCase registerUseCase;
    private final Get${entity}ByIdUseCase getByIdUseCase;
    private final List${entity}UseCase listUseCase;
    private final Update${entity}UseCase updateUseCase;
    private final Delete${entity}UseCase deleteUseCase;

    public ${entity}Controller(Register${entity}UseCase registerUseCase,
            Get${entity}ByIdUseCase getByIdUseCase, List${entity}UseCase listUseCase,
            Update${entity}UseCase updateUseCase, Delete${entity}UseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<${entity}Response> create(@Valid @RequestBody Create${entity}Request request) {
        var command = new Register${entity}Command(
                        $requestArgs);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<${entity}Response>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<${entity}Response> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ${entity}Id(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<${entity}Response> update(@PathVariable UUID id,
            @Valid @RequestBody Update${entity}Request request) {
        var command = new Update${entity}Command(
                        new ${entity}Id(id),
                        $requestArgs);
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ${entity}Id(id));
        return ResponseEntity.noContent().build();
    }
}
"@
    $columns = ($context.Fields | ForEach-Object {
        $nullable = if (Is-Nullable $_) { 'true' } else { 'false' }
        if (Is-Ref $_) { "    @JdbcTypeCode(SqlTypes.CHAR)`n    @Column(name = `"$($_.Column)`", nullable = $nullable, length = 36, columnDefinition = `"char(36)`")`n    private UUID $($_.Name);" }
        else {
            $length = if ($_.Length -gt 0) { ", length = $($_.Length)" } else { '' }
            $columnDefinition = if ($_.ColumnDefinition) { ", columnDefinition = `"$($_.ColumnDefinition)`"" } else { '' }
            "    @Column(name = `"$($_.Column)`", nullable = $nullable$length$columnDefinition)`n    private $($_.Type) $($_.Name);"
        }
    }) -join "`n`n"
    $ctorFields = @('UUID id') + ($context.Fields | ForEach-Object { "$(Persistence-Type $_) $($_.Name)" })
    if (Has-CreatedAt $context) { $ctorFields += 'LocalDateTime createdAt' }
    if (Has-UpdatedAt $context) { $ctorFields += 'LocalDateTime updatedAt' }
    $ctorFieldsText = $ctorFields -join ",`n            "
    $ctorAssignments = @('        this.id = id;') + ($context.Fields | ForEach-Object { "        this.$($_.Name) = $($_.Name);" })
    if (Has-CreatedAt $context) { $ctorAssignments += '        this.createdAt = createdAt;' }
    if (Has-UpdatedAt $context) { $ctorAssignments += '        this.updatedAt = updatedAt;' }
    $ctorAssign = $ctorAssignments -join "`n"
    $accessors = ($context.Fields | ForEach-Object { $pt = Persistence-Type $_; $cap = Cap $_.Name; $getter = Getter $_; "    public $pt $getter() {`n        return $($_.Name);`n    }`n`n    public void set$cap($pt $($_.Name)) {`n        this.$($_.Name) = $($_.Name);`n    }" }) -join "`n`n"
    $timeImport = if (Has-TechnicalTimestamp $context) { 'import java.time.LocalDateTime;' } else { '' }
    $entityTypeImports = if (Has-TechnicalTimestamp $context) { Type-Imports $context @('LocalDateTime') } else { Type-Imports $context }
    $timestampColumns = @()
    if (Has-CreatedAt $context) { $timestampColumns += @"
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
"@ }
    if (Has-UpdatedAt $context) { $timestampColumns += @"
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
"@ }
    $timestampColumns = $timestampColumns -join "`n`n"
    $timestampAccessors = @()
    if (Has-CreatedAt $context) { $timestampAccessors += @"
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
"@ }
    if (Has-UpdatedAt $context) { $timestampAccessors += @"
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
"@ }
    $timestampAccessors = $timestampAccessors -join "`n`n"
    $uniqueConstraints = @(Unique-Constraints $context)
    $uniqueConstraintImport = if ($uniqueConstraints.Count -gt 0) { 'import jakarta.persistence.UniqueConstraint;' } else { '' }
    $tableAnnotation = if ($uniqueConstraints.Count -gt 0) {
        $annotations = @($uniqueConstraints | ForEach-Object { '@UniqueConstraint(name = "{0}", columnNames = "{1}")' -f $_.Name, $_.Column })
        $constraintValue = if ($annotations.Count -eq 1) { $annotations[0] } else { '{ ' + ($annotations -join ', ') + ' }' }
        '@Table(name = "{0}", uniqueConstraints = {1})' -f $context.Table, $constraintValue
    } else {
        '@Table(name = "{0}")' -f $context.Table
    }
    Write-Generated "infrastructure/src/main/java/springboot/infrastructure/$pkg/adapters/out/persistence/entity/${entity}JpaEntity.java" @"
package springboot.infrastructure.$pkg.adapters.out.persistence.entity;

$timeImport
$entityTypeImports
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
$uniqueConstraintImport

@Entity
$tableAnnotation
public class ${entity}JpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

$columns

$timestampColumns

    public ${entity}JpaEntity() { }
    public ${entity}JpaEntity(
            $ctorFieldsText) {
$ctorAssign
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

$accessors

$timestampAccessors
}
"@
    $toJpa = ($context.Fields | ForEach-Object {
        $cap = Cap $_.Name
        if (Is-Ref $_) {
            if (Is-Nullable $_) { "        jpa.set$cap(domain.$($_.Name)() == null ? null : domain.$($_.Name)().value());" }
            else { "        jpa.set$cap(domain.$($_.Name)().value());" }
        } else { "        jpa.set$cap(domain.$($_.Name)());" }
    }) -join "`n"
    $toDomain = ($context.Fields | ForEach-Object {
        $getter = Getter $_
        if (Is-Ref $_) {
            if (Is-Nullable $_) { "jpa.$getter() == null ? null : new $($_.RefEntity)Id(jpa.$getter())" }
            else { "new $($_.RefEntity)Id(jpa.$getter())" }
        } else { "jpa.$getter()" }
    }) -join ",`n                "
    $mapperToJpaTimestamps = @()
    if (Has-CreatedAt $context) { $mapperToJpaTimestamps += '        jpa.setCreatedAt(domain.createdAt());' }
    if (Has-UpdatedAt $context) { $mapperToJpaTimestamps += '        jpa.setUpdatedAt(domain.updatedAt());' }
    $mapperToJpaTimestamps = $mapperToJpaTimestamps -join "`n"
    $mapperToDomainTimestamps = ''
    if (Has-CreatedAt $context) { $mapperToDomainTimestamps += ",`n                jpa.getCreatedAt()" }
    if (Has-UpdatedAt $context) { $mapperToDomainTimestamps += ",`n                jpa.getUpdatedAt()" }
    Write-Generated "infrastructure/src/main/java/springboot/infrastructure/$pkg/adapters/out/persistence/mappers/${entity}PersistenceMapper.java" @"
package springboot.infrastructure.$pkg.adapters.out.persistence.mappers;

import springboot.domain.$pkg.model.aggregate.$entity;
import springboot.domain.$pkg.model.valueobject.${entity}Id;
$refs
import springboot.infrastructure.$pkg.adapters.out.persistence.entity.${entity}JpaEntity;

public class ${entity}PersistenceMapper {
    public ${entity}JpaEntity toJpa($entity domain) {
        if (domain == null) { return null; }
        ${entity}JpaEntity jpa = new ${entity}JpaEntity();
        jpa.setId(domain.id().value());
$toJpa
$mapperToJpaTimestamps
        return jpa;
    }

    public $entity toDomain(${entity}JpaEntity jpa) {
        if (jpa == null) { return null; }
        return $entity.restore(
                new ${entity}Id(jpa.getId()),
                $toDomain$mapperToDomainTimestamps);
    }
}
"@
    Write-Generated "infrastructure/src/main/java/springboot/infrastructure/$pkg/adapters/out/persistence/repositories/${entity}JpaRepository.java" @"
package springboot.infrastructure.$pkg.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.$pkg.adapters.out.persistence.entity.${entity}JpaEntity;

public interface ${entity}JpaRepository extends JpaRepository<${entity}JpaEntity, UUID> {
$jpaExistsByCode
}
"@
    Write-Generated "infrastructure/src/main/java/springboot/infrastructure/$pkg/adapters/out/persistence/repositories/${entity}RepositoryAdapter.java" @"
package springboot.infrastructure.$pkg.adapters.out.persistence.repositories;

import java.util.List;
import java.util.Optional;

import springboot.domain.$pkg.model.aggregate.$entity;
import springboot.domain.$pkg.model.valueobject.${entity}Id;
import springboot.domain.$pkg.port.repository.${entity}Repository;
import springboot.infrastructure.$pkg.adapters.out.persistence.entity.${entity}JpaEntity;
import springboot.infrastructure.$pkg.adapters.out.persistence.mappers.${entity}PersistenceMapper;

public class ${entity}RepositoryAdapter implements ${entity}Repository {
    private final ${entity}JpaRepository jpaRepository;
    private final ${entity}PersistenceMapper mapper;
    public ${entity}RepositoryAdapter(${entity}JpaRepository jpaRepository, ${entity}PersistenceMapper mapper) {
        this.jpaRepository = jpaRepository; this.mapper = mapper;
    }
    @Override public $entity save($entity aggregate) {
        ${entity}JpaEntity saved = jpaRepository.save(mapper.toJpa(aggregate)); return mapper.toDomain(saved);
    }
    @Override public Optional<$entity> findById(${entity}Id id) {
        return jpaRepository.findById(id.value()).map(mapper::toDomain);
    }
    @Override public List<$entity> findAll() {
        return jpaRepository.findAll().stream().map(mapper::toDomain).toList();
    }
$adapterExistsByCode
    @Override public void delete($entity aggregate) { jpaRepository.deleteById(aggregate.id().value()); }
}
"@
    Write-Generated "infrastructure/src/main/java/springboot/infrastructure/$pkg/config/${entity}BeansConfig.java" @"
package springboot.infrastructure.$pkg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.$pkg.usecase.*;
import springboot.domain.$pkg.port.repository.${entity}Repository;
import springboot.infrastructure.$pkg.adapters.out.persistence.mappers.${entity}PersistenceMapper;
import springboot.infrastructure.$pkg.adapters.out.persistence.repositories.${entity}JpaRepository;
import springboot.infrastructure.$pkg.adapters.out.persistence.repositories.${entity}RepositoryAdapter;

@Configuration
public class ${entity}BeansConfig {
    @Bean public ${entity}PersistenceMapper ${pkg}PersistenceMapper() { return new ${entity}PersistenceMapper(); }
    @Bean public ${entity}Repository ${pkg}Repository(${entity}JpaRepository repository, ${entity}PersistenceMapper mapper) {
        return new ${entity}RepositoryAdapter(repository, mapper);
    }
    @Bean public Register${entity}UseCase register${entity}UseCase(${entity}Repository r) { return new Register${entity}UseCase(r); }
    @Bean public Get${entity}ByIdUseCase get${entity}ByIdUseCase(${entity}Repository r) { return new Get${entity}ByIdUseCase(r); }
    @Bean public List${entity}UseCase list${entity}UseCase(${entity}Repository r) { return new List${entity}UseCase(r); }
    @Bean public Update${entity}UseCase update${entity}UseCase(${entity}Repository r) { return new Update${entity}UseCase(r); }
    @Bean public Delete${entity}UseCase delete${entity}UseCase(${entity}Repository r) { return new Delete${entity}UseCase(r); }
}
"@
}

function Generate-Tests($context) {
    $entity = $context.Entity; $pkg = $context.Package; $refs = Ref-Imports $context
    $codeField = Code-Field $context
    $fakeExistsByCode = if ($null -ne $codeField) { '        @Override public boolean existsByCode(String code) { return false; }' } else { '' }
    $samples = ($context.Fields | ForEach-Object { $_.Sample }) -join ",`n                "
    Write-Generated "domain/src/test/java/springboot/domain/$pkg/model/aggregate/${entity}Test.java" @"
package springboot.domain.$pkg.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.$pkg.event.${entity}RegisteredEvent;
$refs

class ${entity}Test {
    @Test void shouldRegisterCreatedEvent() {
        $entity aggregate = $entity.register(
                $samples);
        assertEquals(1, aggregate.domainEvents().size());
        ${entity}RegisteredEvent event = assertInstanceOf(${entity}RegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
"@
    Write-Generated "application/src/test/java/springboot/application/$pkg/usecase/Delete${entity}UseCaseTest.java" @"
package springboot.application.$pkg.usecase;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import springboot.application.$pkg.exception.${entity}NotFoundApplicationException;
import springboot.domain.$pkg.event.${entity}DeletedEvent;
import springboot.domain.$pkg.model.aggregate.$entity;
import springboot.domain.$pkg.model.valueobject.${entity}Id;
import springboot.domain.$pkg.port.repository.${entity}Repository;
$refs

class Delete${entity}UseCaseTest {
    @Test void shouldDeleteExistingAggregate() {
        $entity aggregate = $entity.register(
                $samples);
        FakeRepository repository = new FakeRepository(aggregate);
        ${entity}DeletedEvent event = new Delete${entity}UseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate()); assertEquals(aggregate.id(), event.id()); assertNotNull(event.occurredOn());
    }
    @Test void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(${entity}NotFoundApplicationException.class,
                () -> new Delete${entity}UseCase(repository).execute(${entity}Id.generate()));
        assertNull(repository.deletedAggregate());
    }
    private static final class FakeRepository implements ${entity}Repository {
        private final $entity aggregate; private $entity deletedAggregate;
        private FakeRepository($entity aggregate) { this.aggregate = aggregate; }
        @Override public $entity save($entity value) { return value; }
        @Override public Optional<$entity> findById(${entity}Id id) { return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id)); }
        @Override public List<$entity> findAll() { return aggregate == null ? List.of() : List.of(aggregate); }
$fakeExistsByCode
        @Override public void delete($entity value) { deletedAggregate = value; }
        private $entity deletedAggregate() { return deletedAggregate; }
    }
}
"@
}

$contextsToGenerate = if ($ContextPackages -and $ContextPackages.Count -gt 0) {
    @($Contexts | Where-Object { $_.Package -in $ContextPackages })
} else {
    @($Contexts)
}

$unknownPackages = @($ContextPackages | Where-Object { $_ -notin $Contexts.Package })
if ($unknownPackages.Count -gt 0) {
    throw "Unknown context package(s): $($unknownPackages -join ', ')"
}

foreach ($context in $contextsToGenerate) {
    Generate-Domain $context
    Generate-Application $context
    Generate-Infrastructure $context
    Generate-Tests $context
    Write-Output "Generated $($context.Table) -> $($context.Entity) -> $($context.Package)"
}
