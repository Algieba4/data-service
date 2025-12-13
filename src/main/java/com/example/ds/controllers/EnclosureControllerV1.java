package com.example.ds.controllers;

import com.example.ds.models.dtos.EnclosureDTO;
import com.example.ds.services.EnclosureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/{version}/enclosure", version = "1.0")
@RequiredArgsConstructor
@Slf4j
public class EnclosureControllerV1 {

    private final EnclosureService enclosureService;

    @GetMapping("/")
    public List<EnclosureDTO> getAllEnclosures() {
        return enclosureService.getAllEnclosures();
    }

    @GetMapping("/{id}")
    public EnclosureDTO getEnclosure(@PathVariable Integer id) {
        return enclosureService.getEnclosure(id);
    }

    @PostMapping("/")
    public EnclosureDTO createEnclosure(@RequestBody EnclosureDTO dto) {
        return enclosureService.createEnclosure(dto);
    }

    @PutMapping("/{id}")
    public EnclosureDTO updateEnclosure(
        @PathVariable Integer id,
        @RequestBody EnclosureDTO dto
    ) {
        return enclosureService.updateEnclosure(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteEnclosure(@PathVariable Integer id) {
        enclosureService.deleteEnclosure(id);
    }
}
