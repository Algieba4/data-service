package com.example.ds.controllers.enclosures;

import com.example.ds.models.dtos.enclosures.EnclosureDTOV1;
import com.example.ds.services.enclosures.EnclosureService;
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
    public List<EnclosureDTOV1> getAllEnclosures() {
        return enclosureService.getAllEnclosures();
    }

    @GetMapping("/{id}")
    public EnclosureDTOV1 getEnclosure(@PathVariable Integer id) {
        return enclosureService.getEnclosure(id);
    }

    @PostMapping("/")
    public EnclosureDTOV1 createEnclosure(@RequestBody EnclosureDTOV1 dto) {
        return enclosureService.createEnclosure(dto);
    }

    @PutMapping("/{id}")
    public EnclosureDTOV1 updateEnclosure(
        @PathVariable Integer id,
        @RequestBody EnclosureDTOV1 dto
    ) {
        return enclosureService.updateEnclosure(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteEnclosure(@PathVariable Integer id) {
        enclosureService.deleteEnclosure(id);
    }
}
