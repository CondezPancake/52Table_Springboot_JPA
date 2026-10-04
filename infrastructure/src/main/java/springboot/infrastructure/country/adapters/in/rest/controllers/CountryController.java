package springboot.infrastructure.country.adapters.in.rest.controllers;

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
import springboot.application.country.command.RegisterCountryCommand;
import springboot.application.country.command.UpdateCountryCommand;
import springboot.application.country.dto.CountryResponse;
import springboot.application.country.usecase.DeleteCountryUseCase;
import springboot.application.country.usecase.GetCountryByIdUseCase;
import springboot.application.country.usecase.ListCountryUseCase;
import springboot.application.country.usecase.RegisterCountryUseCase;
import springboot.application.country.usecase.UpdateCountryUseCase;
import springboot.domain.country.model.valueobject.CountryId;

import springboot.infrastructure.country.adapters.in.rest.dtos.CreateCountryRequest;
import springboot.infrastructure.country.adapters.in.rest.dtos.UpdateCountryRequest;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final RegisterCountryUseCase registerUseCase;
    private final GetCountryByIdUseCase getByIdUseCase;
    private final ListCountryUseCase listUseCase;
    private final UpdateCountryUseCase updateUseCase;
    private final DeleteCountryUseCase deleteUseCase;

    public CountryController(RegisterCountryUseCase registerUseCase,
            GetCountryByIdUseCase getByIdUseCase, ListCountryUseCase listUseCase,
            UpdateCountryUseCase updateUseCase, DeleteCountryUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<CountryResponse> create(@Valid @RequestBody CreateCountryRequest request) {
        var command = new RegisterCountryCommand(
                        request.nameCountry(),
                        request.codeCountry(),
                        request.description(),
                        request.active(),
                        request.telephonePrefix());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<CountryResponse>> findAll() { return ResponseEntity.ok(listUseCase.execute()); }

    @GetMapping("/{id}")
    public ResponseEntity<CountryResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new CountryId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateCountryRequest request) {
        var command = new UpdateCountryCommand(
                        new CountryId(id),
                        request.nameCountry(),
                        request.codeCountry(),
                        request.description(),
                        request.active(),
                        request.telephonePrefix());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new CountryId(id));
        return ResponseEntity.noContent().build();
    }
}
