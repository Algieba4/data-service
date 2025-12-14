package com.example.ds.services.animals;

import com.example.ds.enumerations.AnimalStatus;
import com.example.ds.mappers.AnimalDTOMapper;
import com.example.ds.models.dtos.animals.AnimalDTOV2;
import com.example.ds.models.entities.Animal;
import com.example.ds.repositories.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalServiceV2Test {

    Animal animal;

    @Mock
    AnimalDTOMapper animalDTOMapper;

    @Mock
    AnimalRepository animalRepository;

    @InjectMocks
    AnimalServiceV2 animalService;

    @BeforeEach
    void setup() {
        animal = new Animal();
        animal.setName("Tundra");
        animal.setSpecies("Tiger");
        animal.setSex("Female");
        animal.setAge(5);
        animal.setEnclosure(1);
    }

    @Test
    void test_create_animal() {
        var animalDTO = new AnimalDTOV2(
            null, "Tundra",  "Female", "Tiger", 5, 1
        );

        Animal savedAnimal = new Animal();
        savedAnimal.setId(1);
        savedAnimal.setName("Tundra");

        when(animalDTOMapper.dtoV2ToAnimal(animalDTO)).thenReturn(animal);
        when(animalRepository.save(animal)).thenReturn(savedAnimal);
        when(animalDTOMapper.animalToDTOV2(savedAnimal))
            .thenReturn(new AnimalDTOV2(1, "Tundra", "Female","Tiger", 5, 1));

        var result = animalService.createAnimal(animalDTO);

        assertThat(result.id()).isEqualTo(1);
        verify(animalDTOMapper).dtoV2ToAnimal(animalDTO);
        verify(animalRepository).save(animal);
        verify(animalDTOMapper).animalToDTOV2(savedAnimal);
    }

    @Test
    void test_get_all_animals_service() {
        when(animalRepository.findAll()).thenReturn(List.of(animal));

        when(animalDTOMapper.animalToDTOV2(any(Animal.class)))
            .thenReturn(new AnimalDTOV2(1, "Tundra", "Female","Tiger", 5, 1));

        List<AnimalDTOV2> result = animalService.getAllAnimals();

        verify(animalRepository).findAll();
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().name()).isEqualTo("Tundra");
    }

}
