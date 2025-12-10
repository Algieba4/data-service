package com.example.ds.controllers;

import com.example.ds.mappers.EnclosureDTOMapper;
import com.example.ds.models.dtos.EnclosureDTO;
import com.example.ds.repositories.EnclosureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/{version}/enclosure", version = "1.0")
@Slf4j
public class EnclosureController {

    private final EnclosureDTOMapper enclosureDTOMapper;
    private final EnclosureRepository enclosureRepository;

    public EnclosureController(EnclosureDTOMapper enclosureDTOMapper, EnclosureRepository enclosureRepository) {
        this.enclosureDTOMapper = enclosureDTOMapper;
        this.enclosureRepository = enclosureRepository;
    }

    @GetMapping("/")
    public List<EnclosureDTO> getEnclosures() {
        return enclosureRepository.findAll();
    }
}
