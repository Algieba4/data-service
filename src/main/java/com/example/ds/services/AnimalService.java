package com.example.ds.services;

import com.example.ds.mappers.AnimalDTOMapper;
import com.example.ds.models.dtos.AnimalDTO;
import com.example.ds.repositories.AnimalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalDTOMapper animalDTOMapper;

    public AnimalService(AnimalRepository animalRepository, AnimalDTOMapper animalDTOMapper) {
        this.animalRepository = animalRepository;
        this.animalDTOMapper = animalDTOMapper;
    }

    @Transactional(readOnly = true)
    public List<AnimalDTO> getAllAnimals() {
        return animalRepository.findAll().stream()
            .map(animalDTOMapper::animalToDTO)
            .toList();
    }

}
