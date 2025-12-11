package com.example.ds.services;

import com.example.ds.mappers.EnclosureDTOMapper;
import com.example.ds.models.dtos.EnclosureDTO;
import com.example.ds.repositories.EnclosureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class EnclosureService {

    private final EnclosureRepository enclosureRepository;
    private final EnclosureDTOMapper enclosureDTOMapper;

    public EnclosureService(EnclosureRepository enclosureRepository, EnclosureDTOMapper enclosureDTOMapper) {
        this.enclosureRepository = enclosureRepository;
        this.enclosureDTOMapper = enclosureDTOMapper;
    }

    @Transactional(readOnly = true)
    public List<EnclosureDTO> getAllEnclosures() {
        return enclosureRepository.findAll().stream()
            .map(enclosureDTOMapper::enclosureToDTO)
            .toList();
    }
}
