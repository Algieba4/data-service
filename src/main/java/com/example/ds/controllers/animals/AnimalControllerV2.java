package com.example.ds.controllers.animals;

import com.example.ds.metrics.AnimalMetrics;
import com.example.ds.models.dtos.animals.AnimalDTOV2;
import com.example.ds.services.animals.AnimalServiceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/// AnimalControllerV2 uses the AnimalDTOV2 class for the following attributes
/// - Integer id
/// - String name
/// - String sex
/// - String species
/// - Integer age
/// - Integer enclosure
///
/// @deprecated
/// This controller is marked as depreciated. Although it will stick around for
/// the next release, we recommend moving over to the v3 version.
@Deprecated(since = "v3")
@RestController
@RequestMapping(path = "/api/{version}/animal", version = "2.0")
@RequiredArgsConstructor
@Slf4j
public class AnimalControllerV2 {

    private final AnimalMetrics animalMetrics;
    private final AnimalServiceV2 animalServiceV2;

    @GetMapping("/")
    public List<AnimalDTOV2> getAllAnimals() {
        animalMetrics.incrementTotalAnimalCalls("v2");
        return animalServiceV2.getAllAnimals();
    }

    @GetMapping("/{id}")
    public AnimalDTOV2 getAnimal(@PathVariable Integer id) {
        animalMetrics.incrementTotalAnimalCalls("v2");
        return animalServiceV2.getAnimal(id);
    }

    @PostMapping("/")
    public AnimalDTOV2 createAnimal(@RequestBody AnimalDTOV2 dto) {
        animalMetrics.incrementTotalAnimalCalls("v2");
        return animalServiceV2.createAnimal(dto);
    }

    @PutMapping("/{id}")
    public AnimalDTOV2 updateAnimal(
        @PathVariable Integer id,
        @RequestBody AnimalDTOV2 dto
    ) {
        animalMetrics.incrementTotalAnimalCalls("v2");
        return animalServiceV2.updateAnimal(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Integer id) {
        animalMetrics.incrementTotalAnimalCalls("v2");
        animalServiceV2.deleteAnimal(id);
    }

}
