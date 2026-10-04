package springboot.infrastructure.study.adapters.in.rest.controllers;

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
import springboot.application.study.command.RegisterStudyCommand;
import springboot.application.study.command.UpdateStudyCommand;
import springboot.application.study.dto.StudyResponse;
import springboot.application.study.usecase.DeleteStudyUseCase;
import springboot.application.study.usecase.GetStudyByIdUseCase;
import springboot.application.study.usecase.ListStudyUseCase;
import springboot.application.study.usecase.RegisterStudyUseCase;
import springboot.application.study.usecase.UpdateStudyUseCase;
import springboot.domain.study.model.valueobject.StudyId;

import springboot.infrastructure.study.adapters.in.rest.dtos.CreateStudyRequest;
import springboot.infrastructure.study.adapters.in.rest.dtos.UpdateStudyRequest;

@RestController
@RequestMapping("/api/studies")
public class StudyController {
    private final RegisterStudyUseCase registerUseCase;
    private final GetStudyByIdUseCase getByIdUseCase;
    private final ListStudyUseCase listUseCase;
    private final UpdateStudyUseCase updateUseCase;
    private final DeleteStudyUseCase deleteUseCase;

    public StudyController(RegisterStudyUseCase registerUseCase,
            GetStudyByIdUseCase getByIdUseCase, ListStudyUseCase listUseCase,
            UpdateStudyUseCase updateUseCase, DeleteStudyUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<StudyResponse> create(@Valid @RequestBody CreateStudyRequest request) {
        var command = new RegisterStudyCommand(
                        request.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<StudyResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<StudyResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new StudyId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudyResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateStudyRequest request) {
        var command = new UpdateStudyCommand(
                        new StudyId(id),
                        request.name());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new StudyId(id));
        return ResponseEntity.noContent().build();
    }
}
