package springboot.infrastructure.medicationroute.adapters.in.rest.controllers;

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
import springboot.application.medicationroute.command.RegisterMedicationRouteCommand;
import springboot.application.medicationroute.command.UpdateMedicationRouteCommand;
import springboot.application.medicationroute.dto.MedicationRouteResponse;
import springboot.application.medicationroute.usecase.DeleteMedicationRouteUseCase;
import springboot.application.medicationroute.usecase.GetMedicationRouteByIdUseCase;
import springboot.application.medicationroute.usecase.ListMedicationRouteUseCase;
import springboot.application.medicationroute.usecase.RegisterMedicationRouteUseCase;
import springboot.application.medicationroute.usecase.UpdateMedicationRouteUseCase;
import springboot.domain.medicationroute.model.valueobject.MedicationRouteId;

import springboot.infrastructure.medicationroute.adapters.in.rest.dtos.CreateMedicationRouteRequest;
import springboot.infrastructure.medicationroute.adapters.in.rest.dtos.UpdateMedicationRouteRequest;

@RestController
@RequestMapping("/api/medication-routes")
public class MedicationRouteController {
    private final RegisterMedicationRouteUseCase registerUseCase;
    private final GetMedicationRouteByIdUseCase getByIdUseCase;
    private final ListMedicationRouteUseCase listUseCase;
    private final UpdateMedicationRouteUseCase updateUseCase;
    private final DeleteMedicationRouteUseCase deleteUseCase;

    public MedicationRouteController(RegisterMedicationRouteUseCase registerUseCase,
            GetMedicationRouteByIdUseCase getByIdUseCase, ListMedicationRouteUseCase listUseCase,
            UpdateMedicationRouteUseCase updateUseCase, DeleteMedicationRouteUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<MedicationRouteResponse> create(@Valid @RequestBody CreateMedicationRouteRequest request) {
        var command = new RegisterMedicationRouteCommand(
                        request.code(),
                        request.name(),
                        request.active());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<MedicationRouteResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationRouteResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new MedicationRouteId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicationRouteResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateMedicationRouteRequest request) {
        var command = new UpdateMedicationRouteCommand(
                        new MedicationRouteId(id),
                        request.code(),
                        request.name(),
                        request.active());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new MedicationRouteId(id));
        return ResponseEntity.noContent().build();
    }
}
