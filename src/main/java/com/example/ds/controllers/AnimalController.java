package com.example.ds.controllers;

import com.example.ds.models.dtos.AnimalDTO;
import com.example.ds.services.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/{version}/animal", version = "1.0")
@Slf4j
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/")
    public List<AnimalDTO> getAllAnimals() {
        return animalService.getAllAnimals();
    }

}
