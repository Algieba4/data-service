package com.example.ds.services.enclosures;

import com.example.ds.mappers.EnclosureDTOMapper;
import com.example.ds.models.dtos.enclosures.EnclosureDTOV1;
import com.example.ds.models.entities.Enclosure;
import com.example.ds.repositories.EnclosureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class EnclosureService {

    private final EnclosureRepository enclosureRepository;
    private final EnclosureDTOMapper enclosureDTOMapper;

    public EnclosureDTOV1 createEnclosure(EnclosureDTOV1 dto) {
        Enclosure enclosure = enclosureDTOMapper.dtoToEnclosure(dto);
        Enclosure saved = enclosureRepository.save(enclosure);
        return enclosureDTOMapper.enclosureToDTO(saved);
    }

    public void deleteEnclosure(Integer id) {
        enclosureRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<EnclosureDTOV1> getAllEnclosures() {
        return enclosureRepository.findAll().stream()
            .map(enclosureDTOMapper::enclosureToDTO)
            .toList();
    }

    @Transactional(readOnly = true)
    public EnclosureDTOV1 getEnclosure(Integer id) {
        Enclosure enclosure = enclosureRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Enclosure not found"));

        return enclosureDTOMapper.enclosureToDTO(enclosure);
    }

    public EnclosureDTOV1 updateEnclosure(Integer id, EnclosureDTOV1 dto) {
        Enclosure currentEnclosure = enclosureRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Animal not found"));

        currentEnclosure.setBiome(dto.biome());
        currentEnclosure.setWidth(dto.width());
        currentEnclosure.setLength(dto.length());

        Enclosure updatedEnclosure = enclosureRepository.save(currentEnclosure);
        return enclosureDTOMapper.enclosureToDTO(updatedEnclosure);
    }
}
