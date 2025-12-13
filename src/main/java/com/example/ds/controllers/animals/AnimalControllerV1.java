package com.example.ds.controllers.animals;

import com.example.ds.models.dtos.animals.AnimalDTOV1;
import com.example.ds.services.animals.AnimalServiceV1;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/{version}/animal", version = "1.0")
@RequiredArgsConstructor
@Slf4j
public class AnimalControllerV1 {

    private final AnimalServiceV1 animalServiceV1;

    @GetMapping("/")
    public List<AnimalDTOV1> getAllAnimals() {
        return animalServiceV1.getAllAnimals();
    }

    @GetMapping("/{id}")
    public AnimalDTOV1 getAnimal(@PathVariable Integer id) {
        return animalServiceV1.getAnimal(id);
    }

    @PostMapping("/")
    public AnimalDTOV1 createAnimal(@RequestBody AnimalDTOV1 dto) {
        return animalServiceV1.createAnimal(dto);
    }

    @PutMapping("/{id}")
    public AnimalDTOV1 updateAnimal(
        @PathVariable Integer id,
        @RequestBody AnimalDTOV1 dto
    ) {
        return animalServiceV1.updateAnimal(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Integer id) {
        animalServiceV1.deleteAnimal(id);
    }

}
