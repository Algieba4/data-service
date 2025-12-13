package com.example.ds.services;

import com.example.ds.mappers.AnimalDTOMapper;
import com.example.ds.models.dtos.AnimalDTO;
import com.example.ds.models.entities.Animal;
import com.example.ds.repositories.AnimalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalDTOMapper animalDTOMapper;

    public AnimalDTO createAnimal(AnimalDTO dto) {
        Animal animal = animalDTOMapper.dtoToAnimal(dto);
        Animal saved = animalRepository.save(animal);
        return animalDTOMapper.animalToDTO(saved);
    }

    public void deleteAnimal(Integer id) {
        animalRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<AnimalDTO> getAllAnimals() {
        return animalRepository.findAll().stream()
            .map(animalDTOMapper::animalToDTO)
            .toList();
    }

    @Transactional(readOnly = true)
    public AnimalDTO getAnimal(Integer id) {
        Animal animal = animalRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Animal not found"));

        return animalDTOMapper.animalToDTO(animal);
    }

    public AnimalDTO updateAnimal(Integer id, AnimalDTO dto) {
        Animal currentAnimal = animalRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Animal not found"));

        currentAnimal.setName(dto.name());
        currentAnimal.setSpecies(dto.species());
        currentAnimal.setSex(dto.sex());
        currentAnimal.setAge(dto.age());
        currentAnimal.setEnclosure(dto.enclosure());

        Animal updatedAnimal = animalRepository.save(currentAnimal);
        return animalDTOMapper.animalToDTO(updatedAnimal);
    }

}
