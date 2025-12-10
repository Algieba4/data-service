package com.example.ds.controllers;

import com.example.ds.repositories.AnimalRepository;
import com.example.ds.repositories.EnclosureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/enclosure")
@Slf4j
public class EnclosureController {

    private final EnclosureRepository enclosureRepository;

    public EnclosureController(EnclosureRepository enclosureRepository) {
        this.enclosureRepository = enclosureRepository;
    }

    @PostMapping(path = "/dtl")
    public void initialDataLoad() {
        log.debug("Populating Enclosures");

        log.debug("Finished populating Enclosures");
    }
}
