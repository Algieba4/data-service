package com.example.ds.controllers.animals;

import com.example.ds.metrics.AnimalMetrics;
import com.example.ds.models.dtos.animals.AnimalDTOV1;
import com.example.ds.services.animals.AnimalServiceV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/// AnimalControllerV1 uses the AnimalDTOV1 class for the following attributes
/// - Integer id,
/// - String name,
/// - String sex,
/// - String species,
/// - Integer age
///
/// @deprecated
/// This controller is marked as depreciated and will be removed
/// during the next deployment
@Deprecated(since = "v2", forRemoval = true)
@RestController
@RequestMapping(path = "/api/{version}/animal", version = "1.0")
@RequiredArgsConstructor
@Slf4j
public class AnimalControllerV1 {

    private final AnimalMetrics animalMetrics;
    private final AnimalServiceV1 animalServiceV1;

    @GetMapping("/")
    public ResponseEntity<List<AnimalDTOV1>> getAllAnimals() {
        animalMetrics.incrementTotalAnimalCalls("v1");
        return ResponseEntity
            .status(HttpStatus.OK)
            .header("Is-Depreciated", "true")
            .header("Marked-For-Removal", "true")
            .body(animalServiceV1.getAllAnimals());
    }

    @GetMapping("/{id}")
    public AnimalDTOV1 getAnimal(@PathVariable Integer id) {
        animalMetrics.incrementTotalAnimalCalls("v1");
        return animalServiceV1.getAnimal(id);
    }

    @PostMapping("/")
    public AnimalDTOV1 createAnimal(@RequestBody AnimalDTOV1 dto) {
        animalMetrics.incrementTotalAnimalCalls("v1");
        return animalServiceV1.createAnimal(dto);
    }

    @PutMapping("/{id}")
    public AnimalDTOV1 updateAnimal(
        @PathVariable Integer id,
        @RequestBody AnimalDTOV1 dto
    ) {
        animalMetrics.incrementTotalAnimalCalls("v1");
        return animalServiceV1.updateAnimal(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Integer id) {
        animalMetrics.incrementTotalAnimalCalls("v1");
        animalServiceV1.deleteAnimal(id);
    }

}
