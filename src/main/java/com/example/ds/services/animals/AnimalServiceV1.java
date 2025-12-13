package com.example.ds.services.animals;

import com.example.ds.mappers.AnimalDTOMapper;
import com.example.ds.models.dtos.animals.AnimalDTOV1;
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
public class AnimalServiceV1 {

    private final AnimalRepository animalRepository;
    private final AnimalDTOMapper animalDTOMapper;

    public AnimalDTOV1 createAnimal(AnimalDTOV1 dto) {
        Animal animal = animalDTOMapper.dtoV1ToAnimal(dto);
        Animal saved = animalRepository.save(animal);
        return animalDTOMapper.animalToDTOV1(saved);
    }

    public void deleteAnimal(Integer id) {
        animalRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<AnimalDTOV1> getAllAnimals() {
        return animalRepository.findAll().stream()
            .map(animalDTOMapper::animalToDTOV1)
            .toList();
    }

    @Transactional(readOnly = true)
    public AnimalDTOV1 getAnimal(Integer id) {
        Animal animal = animalRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Animal not found"));

        return animalDTOMapper.animalToDTOV1(animal);
    }

    public AnimalDTOV1 updateAnimal(Integer id, AnimalDTOV1 dto) {
        Animal currentAnimal = animalRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Animal not found"));

        currentAnimal.setName(dto.name());
        currentAnimal.setSpecies(dto.species());
        currentAnimal.setSex(dto.sex());
        currentAnimal.setAge(dto.age());
        currentAnimal.setEnclosure(dto.enclosure());

        Animal updatedAnimal = animalRepository.save(currentAnimal);
        return animalDTOMapper.animalToDTOV1(updatedAnimal);
    }

}
