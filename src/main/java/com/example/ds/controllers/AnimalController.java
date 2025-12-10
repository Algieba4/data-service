package com.example.ds.controllers;

import com.example.ds.mappers.AnimalDTOMapper;
import com.example.ds.models.dtos.AnimalDTO;
import com.example.ds.repositories.AnimalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/{version}/animal", version = "1.0")
@Slf4j
public class AnimalController {

    private final AnimalDTOMapper animalDTOMapper;
    private final AnimalRepository animalRepository;

    public AnimalController(AnimalDTOMapper animalDTOMapper, AnimalRepository animalRepository) {
        this.animalDTOMapper = animalDTOMapper;
        this.animalRepository = animalRepository;
    }

    @GetMapping("/")
    public List<AnimalDTO> getAnimals() {
        return animalRepository.findAll();
    }

}
