package com.example.ds.controllers;

import com.example.ds.mappers.EnclosureDTOMapper;
import com.example.ds.models.dtos.EnclosureDTO;
import com.example.ds.repositories.EnclosureRepository;
import com.example.ds.services.EnclosureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/{version}/enclosure", version = "1.0")
@Slf4j
public class EnclosureController {

    private final EnclosureService enclosureService;

    public EnclosureController(EnclosureService enclosureService) {
        this.enclosureService = enclosureService;
    }

    @GetMapping("/")
    public List<EnclosureDTO> getEnclosures() {
        return enclosureService.getAllEnclosures();
    }
}
