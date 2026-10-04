package springboot.infrastructure.stateregion.adapters.in.rest.controllers;

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
import springboot.application.stateregion.command.RegisterStateRegionCommand;
import springboot.application.stateregion.command.UpdateStateRegionCommand;
import springboot.application.stateregion.dto.StateRegionResponse;
import springboot.application.stateregion.usecase.DeleteStateRegionUseCase;
import springboot.application.stateregion.usecase.GetStateRegionByIdUseCase;
import springboot.application.stateregion.usecase.ListStateRegionUseCase;
import springboot.application.stateregion.usecase.RegisterStateRegionUseCase;
import springboot.application.stateregion.usecase.UpdateStateRegionUseCase;
import springboot.domain.stateregion.model.valueobject.StateRegionId;
import springboot.domain.country.model.valueobject.CountryId;
import springboot.infrastructure.stateregion.adapters.in.rest.dtos.CreateStateRegionRequest;
import springboot.infrastructure.stateregion.adapters.in.rest.dtos.UpdateStateRegionRequest;

@RestController
@RequestMapping("/api/state-regions")
public class StateRegionController {
    private final RegisterStateRegionUseCase registerUseCase;
    private final GetStateRegionByIdUseCase getByIdUseCase;
    private final ListStateRegionUseCase listUseCase;
    private final UpdateStateRegionUseCase updateUseCase;
    private final DeleteStateRegionUseCase deleteUseCase;

    public StateRegionController(RegisterStateRegionUseCase registerUseCase,
            GetStateRegionByIdUseCase getByIdUseCase, ListStateRegionUseCase listUseCase,
            UpdateStateRegionUseCase updateUseCase, DeleteStateRegionUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<StateRegionResponse> create(@Valid @RequestBody CreateStateRegionRequest request) {
        var command = new RegisterStateRegionCommand(
                        request.nameRegion(),
                        request.codeRegion(),
                        request.description(),
                        request.active(),
                        new CountryId(request.countryId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<StateRegionResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<StateRegionResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new StateRegionId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateRegionResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateStateRegionRequest request) {
        var command = new UpdateStateRegionCommand(
                        new StateRegionId(id),
                        request.nameRegion(),
                        request.codeRegion(),
                        request.description(),
                        request.active(),
                        new CountryId(request.countryId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new StateRegionId(id));
        return ResponseEntity.noContent().build();
    }
}
