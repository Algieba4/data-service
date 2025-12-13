package com.example.ds.controllers;

import com.example.ds.models.dtos.AnimalDTO;
import com.example.ds.services.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/{version}/animal", version = "1.0")
@RequiredArgsConstructor
@Slf4j
public class AnimalControllerV1 {

    private final AnimalService animalService;

    @GetMapping("/")
    public List<AnimalDTO> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/{id}")
    public AnimalDTO getAnimal(@PathVariable Integer id) {
        return animalService.getAnimal(id);
    }

    @PostMapping("/")
    public AnimalDTO createAnimal(@RequestBody AnimalDTO dto) {
        return animalService.createAnimal(dto);
    }

    @PutMapping("/{id}")
    public AnimalDTO updateAnimal(
        @PathVariable Integer id,
        @RequestBody AnimalDTO dto
    ) {
        return animalService.updateAnimal(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Integer id) {
        animalService.deleteAnimal(id);
    }

}
