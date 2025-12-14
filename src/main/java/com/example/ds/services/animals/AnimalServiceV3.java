package com.example.ds.services.animals;

import com.example.ds.mappers.AnimalDTOMapper;
import com.example.ds.models.dtos.animals.AnimalDTOV3;
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
public class AnimalServiceV3 {

    private final AnimalRepository animalRepository;
    private final AnimalDTOMapper animalDTOMapper;

    public AnimalDTOV3 createAnimal(AnimalDTOV3 dto) {
        Animal animal = animalDTOMapper.dtoV3ToAnimal(dto);
        Animal saved = animalRepository.save(animal);
        return animalDTOMapper.animalToDTOV3(saved);
    }

    public void deleteAnimal(Integer id) {
        animalRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<AnimalDTOV3> getAllAnimals() {
        return animalRepository.findAll().stream()
            .map(animalDTOMapper::animalToDTOV3)
            .toList();
    }

    @Transactional(readOnly = true)
    public AnimalDTOV3 getAnimal(Integer id) {
        Animal animal = animalRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Animal not found"));

        return animalDTOMapper.animalToDTOV3(animal);
    }

    public AnimalDTOV3 updateAnimal(Integer id, AnimalDTOV3 dto) {
        Animal currentAnimal = animalRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Animal not found"));

        currentAnimal.setName(dto.name());
        currentAnimal.setSpecies(dto.species());
        currentAnimal.setSex(dto.sex());
        currentAnimal.setStatus(dto.status());
        currentAnimal.setAge(dto.age());
        currentAnimal.setEnclosure(dto.enclosure());

        Animal updatedAnimal = animalRepository.save(currentAnimal);
        return animalDTOMapper.animalToDTOV3(updatedAnimal);
    }

}
