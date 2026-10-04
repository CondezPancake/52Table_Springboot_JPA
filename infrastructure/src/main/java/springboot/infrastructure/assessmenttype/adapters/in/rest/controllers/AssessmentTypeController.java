package springboot.infrastructure.assessmenttype.adapters.in.rest.controllers;

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
import springboot.application.assessmenttype.command.RegisterAssessmentTypeCommand;
import springboot.application.assessmenttype.command.UpdateAssessmentTypeCommand;
import springboot.application.assessmenttype.dto.AssessmentTypeResponse;
import springboot.application.assessmenttype.usecase.DeleteAssessmentTypeUseCase;
import springboot.application.assessmenttype.usecase.GetAssessmentTypeByIdUseCase;
import springboot.application.assessmenttype.usecase.ListAssessmentTypeUseCase;
import springboot.application.assessmenttype.usecase.RegisterAssessmentTypeUseCase;
import springboot.application.assessmenttype.usecase.UpdateAssessmentTypeUseCase;
import springboot.domain.assessmenttype.model.valueobject.AssessmentTypeId;

import springboot.infrastructure.assessmenttype.adapters.in.rest.dtos.CreateAssessmentTypeRequest;
import springboot.infrastructure.assessmenttype.adapters.in.rest.dtos.UpdateAssessmentTypeRequest;

@RestController
@RequestMapping("/api/assessment-types")
public class AssessmentTypeController {
    private final RegisterAssessmentTypeUseCase registerUseCase;
    private final GetAssessmentTypeByIdUseCase getByIdUseCase;
    private final ListAssessmentTypeUseCase listUseCase;
    private final UpdateAssessmentTypeUseCase updateUseCase;
    private final DeleteAssessmentTypeUseCase deleteUseCase;

    public AssessmentTypeController(RegisterAssessmentTypeUseCase registerUseCase,
            GetAssessmentTypeByIdUseCase getByIdUseCase, ListAssessmentTypeUseCase listUseCase,
            UpdateAssessmentTypeUseCase updateUseCase, DeleteAssessmentTypeUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<AssessmentTypeResponse> create(@Valid @RequestBody CreateAssessmentTypeRequest request) {
        var command = new RegisterAssessmentTypeCommand(
                        request.code(),
                        request.name(),
                        request.active(),
                        request.description());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<AssessmentTypeResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<AssessmentTypeResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new AssessmentTypeId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssessmentTypeResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateAssessmentTypeRequest request) {
        var command = new UpdateAssessmentTypeCommand(
                        new AssessmentTypeId(id),
                        request.code(),
                        request.name(),
                        request.active(),
                        request.description());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new AssessmentTypeId(id));
        return ResponseEntity.noContent().build();
    }
}
