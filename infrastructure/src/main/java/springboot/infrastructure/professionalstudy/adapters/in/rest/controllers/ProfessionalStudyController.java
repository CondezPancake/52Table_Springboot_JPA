package springboot.infrastructure.professionalstudy.adapters.in.rest.controllers;

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
import springboot.application.professionalstudy.command.RegisterProfessionalStudyCommand;
import springboot.application.professionalstudy.command.UpdateProfessionalStudyCommand;
import springboot.application.professionalstudy.dto.ProfessionalStudyResponse;
import springboot.application.professionalstudy.usecase.DeleteProfessionalStudyUseCase;
import springboot.application.professionalstudy.usecase.GetProfessionalStudyByIdUseCase;
import springboot.application.professionalstudy.usecase.ListProfessionalStudyUseCase;
import springboot.application.professionalstudy.usecase.RegisterProfessionalStudyUseCase;
import springboot.application.professionalstudy.usecase.UpdateProfessionalStudyUseCase;
import springboot.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import springboot.domain.country.model.valueobject.CountryId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.study.model.valueobject.StudyId;
import springboot.infrastructure.professionalstudy.adapters.in.rest.dtos.CreateProfessionalStudyRequest;
import springboot.infrastructure.professionalstudy.adapters.in.rest.dtos.UpdateProfessionalStudyRequest;

@RestController
@RequestMapping("/api/professional-studies")
public class ProfessionalStudyController {
    private final RegisterProfessionalStudyUseCase registerUseCase;
    private final GetProfessionalStudyByIdUseCase getByIdUseCase;
    private final ListProfessionalStudyUseCase listUseCase;
    private final UpdateProfessionalStudyUseCase updateUseCase;
    private final DeleteProfessionalStudyUseCase deleteUseCase;

    public ProfessionalStudyController(RegisterProfessionalStudyUseCase registerUseCase,
            GetProfessionalStudyByIdUseCase getByIdUseCase, ListProfessionalStudyUseCase listUseCase,
            UpdateProfessionalStudyUseCase updateUseCase, DeleteProfessionalStudyUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ProfessionalStudyResponse> create(@Valid @RequestBody CreateProfessionalStudyRequest request) {
        var command = new RegisterProfessionalStudyCommand(
                        new StudyId(request.studyId()),
                        new ProfessionalId(request.professionalId()),
                        request.title(),
                        request.university(),
                        request.valid(),
                        request.resolutionNumber(),
                        new CountryId(request.countryId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalStudyResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalStudyResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ProfessionalStudyId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalStudyResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateProfessionalStudyRequest request) {
        var command = new UpdateProfessionalStudyCommand(
                        new ProfessionalStudyId(id),
                        new StudyId(request.studyId()),
                        new ProfessionalId(request.professionalId()),
                        request.title(),
                        request.university(),
                        request.valid(),
                        request.resolutionNumber(),
                        new CountryId(request.countryId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ProfessionalStudyId(id));
        return ResponseEntity.noContent().build();
    }
}
