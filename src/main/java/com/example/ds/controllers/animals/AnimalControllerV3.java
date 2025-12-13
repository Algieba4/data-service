package com.example.ds.controllers.animals;

import com.example.ds.models.dtos.animals.AnimalDTOV2;
import com.example.ds.services.animals.AnimalServiceV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/{version}/animal", version = "3.0")
@RequiredArgsConstructor
@Slf4j
public class AnimalControllerV3 {

    private final AnimalServiceV2 animalServiceV2;

    @GetMapping("/")
    public List<AnimalDTOV2> getAllAnimals() {
        return animalServiceV2.getAllAnimals();
    }

    @GetMapping("/{id}")
    public AnimalDTOV2 getAnimal(@PathVariable Integer id) {
        return animalServiceV2.getAnimal(id);
    }

    @PostMapping("/")
    public AnimalDTOV2 createAnimal(@RequestBody AnimalDTOV2 dto) {
        return animalServiceV2.createAnimal(dto);
    }

    @PutMapping("/{id}")
    public AnimalDTOV2 updateAnimal(
        @PathVariable Integer id,
        @RequestBody AnimalDTOV2 dto
    ) {
        return animalServiceV2.updateAnimal(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Integer id) {
        animalServiceV2.deleteAnimal(id);
    }

}
