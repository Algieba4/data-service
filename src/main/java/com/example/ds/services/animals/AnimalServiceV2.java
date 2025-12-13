package com.example.ds.services.animals;

import com.example.ds.mappers.AnimalDTOMapper;
import com.example.ds.models.dtos.animals.AnimalDTOV1;
import com.example.ds.models.dtos.animals.AnimalDTOV2;
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
public class AnimalServiceV2 {

    private final AnimalRepository animalRepository;
    private final AnimalDTOMapper animalDTOMapper;

    public AnimalDTOV2 createAnimal(AnimalDTOV2 dto) {
        Animal animal = animalDTOMapper.dtoV2ToAnimal(dto);
        Animal saved = animalRepository.save(animal);
        return animalDTOMapper.animalToDTOV2(saved);
    }

    public void deleteAnimal(Integer id) {
        animalRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<AnimalDTOV2> getAllAnimals() {
        return animalRepository.findAll().stream()
            .map(animalDTOMapper::animalToDTOV2)
            .toList();
    }

    @Transactional(readOnly = true)
    public AnimalDTOV2 getAnimal(Integer id) {
        Animal animal = animalRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Animal not found"));

        return animalDTOMapper.animalToDTOV2(animal);
    }

    public AnimalDTOV2 updateAnimal(Integer id, AnimalDTOV2 dto) {
        Animal currentAnimal = animalRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Animal not found"));

        currentAnimal.setName(dto.name());
        currentAnimal.setSpecies(dto.species());
        currentAnimal.setSex(dto.sex());
        currentAnimal.setStatus(dto.status());
        currentAnimal.setAge(dto.age());
        currentAnimal.setEnclosure(dto.enclosure());

        Animal updatedAnimal = animalRepository.save(currentAnimal);
        return animalDTOMapper.animalToDTOV2(updatedAnimal);
    }

}
