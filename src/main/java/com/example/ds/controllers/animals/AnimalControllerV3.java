package com.example.ds.controllers.animals;

import com.example.ds.metrics.AnimalMetrics;
import com.example.ds.models.dtos.animals.AnimalDTOV3;
import com.example.ds.services.animals.AnimalServiceV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/{version}/animal", version = "3.0")
@RequiredArgsConstructor
@Slf4j
public class AnimalControllerV3 {

    private final AnimalMetrics animalMetrics;
    private final AnimalServiceV3 animalServiceV3;

    @GetMapping("/")
    public List<AnimalDTOV3> getAllAnimals() {
        animalMetrics.incrementTotalAnimalCalls("v3");
        return animalServiceV3.getAllAnimals();
    }

    @GetMapping("/{id}")
    public AnimalDTOV3 getAnimal(@PathVariable Integer id) {
        animalMetrics.incrementTotalAnimalCalls("v3");
        return animalServiceV3.getAnimal(id);
    }

    @PostMapping("/")
    public AnimalDTOV3 createAnimal(@RequestBody AnimalDTOV3 dto) {
        animalMetrics.incrementTotalAnimalCalls("v3");
        return animalServiceV3.createAnimal(dto);
    }

    @PutMapping("/{id}")
    public AnimalDTOV3 updateAnimal(
        @PathVariable Integer id,
        @RequestBody AnimalDTOV3 dto
    ) {
        animalMetrics.incrementTotalAnimalCalls("v3");
        return animalServiceV3.updateAnimal(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Integer id) {
        animalMetrics.incrementTotalAnimalCalls("v3");
        animalServiceV3.deleteAnimal(id);
    }

}
