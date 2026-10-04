package springboot.infrastructure.mentalstatusexam.adapters.in.rest.controllers;

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
import springboot.application.mentalstatusexam.command.RegisterMentalStatusExamCommand;
import springboot.application.mentalstatusexam.command.UpdateMentalStatusExamCommand;
import springboot.application.mentalstatusexam.dto.MentalStatusExamResponse;
import springboot.application.mentalstatusexam.usecase.DeleteMentalStatusExamUseCase;
import springboot.application.mentalstatusexam.usecase.GetMentalStatusExamByIdUseCase;
import springboot.application.mentalstatusexam.usecase.ListMentalStatusExamUseCase;
import springboot.application.mentalstatusexam.usecase.RegisterMentalStatusExamUseCase;
import springboot.application.mentalstatusexam.usecase.UpdateMentalStatusExamUseCase;
import springboot.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.infrastructure.mentalstatusexam.adapters.in.rest.dtos.CreateMentalStatusExamRequest;
import springboot.infrastructure.mentalstatusexam.adapters.in.rest.dtos.UpdateMentalStatusExamRequest;

@RestController
@RequestMapping("/api/mental-status-exams")
public class MentalStatusExamController {
    private final RegisterMentalStatusExamUseCase registerUseCase;
    private final GetMentalStatusExamByIdUseCase getByIdUseCase;
    private final ListMentalStatusExamUseCase listUseCase;
    private final UpdateMentalStatusExamUseCase updateUseCase;
    private final DeleteMentalStatusExamUseCase deleteUseCase;

    public MentalStatusExamController(RegisterMentalStatusExamUseCase registerUseCase,
            GetMentalStatusExamByIdUseCase getByIdUseCase, ListMentalStatusExamUseCase listUseCase,
            UpdateMentalStatusExamUseCase updateUseCase, DeleteMentalStatusExamUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<MentalStatusExamResponse> create(@Valid @RequestBody CreateMentalStatusExamRequest request) {
        var command = new RegisterMentalStatusExamCommand(
                        new EncounterId(request.encounterId()),
                        request.appearance(),
                        request.behavior(),
                        request.attitude(),
                        request.consciousness(),
                        request.orientation(),
                        request.attention(),
                        request.memory(),
                        request.speech(),
                        request.mood(),
                        request.affect(),
                        request.thoughtProcess(),
                        request.thoughtContent(),
                        request.perception(),
                        request.judgment(),
                        request.insight(),
                        request.psychomotorActivity(),
                        request.observations(),
                        new ProfessionalId(request.createdBy()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<MentalStatusExamResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<MentalStatusExamResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new MentalStatusExamId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MentalStatusExamResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateMentalStatusExamRequest request) {
        var command = new UpdateMentalStatusExamCommand(
                        new MentalStatusExamId(id),
                        new EncounterId(request.encounterId()),
                        request.appearance(),
                        request.behavior(),
                        request.attitude(),
                        request.consciousness(),
                        request.orientation(),
                        request.attention(),
                        request.memory(),
                        request.speech(),
                        request.mood(),
                        request.affect(),
                        request.thoughtProcess(),
                        request.thoughtContent(),
                        request.perception(),
                        request.judgment(),
                        request.insight(),
                        request.psychomotorActivity(),
                        request.observations(),
                        new ProfessionalId(request.createdBy()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new MentalStatusExamId(id));
        return ResponseEntity.noContent().build();
    }
}
