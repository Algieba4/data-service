package com.example.ds.controllers;

import com.example.ds.repositories.AnimalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/animal")
@Slf4j
public class AnimalController {

    private final AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @PostMapping(path = "/dtl")
    public void initialDataLoad() {
        log.debug("Populating Animals");

        log.debug("Finished populating Animals");
    }

}
