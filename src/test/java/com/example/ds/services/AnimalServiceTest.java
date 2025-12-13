package com.example.ds.services;

import com.example.ds.mappers.AnimalDTOMapper;
import com.example.ds.models.dtos.AnimalDTO;
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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

    Animal animal;

    @Mock
    AnimalDTOMapper animalDTOMapper;

    @Mock
    AnimalRepository animalRepository;

    @InjectMocks
    AnimalService animalService;

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
    void test_get_all_animals_service() {
        when(animalRepository.findAll()).thenReturn(List.of(animal));

        when(animalDTOMapper.animalToDTO(any(Animal.class)))
            .thenReturn(new AnimalDTO(1, "Tundra", "Tiger", "Female", 5, 1));

        List<AnimalDTO> result = animalService.getAllAnimals();

        verify(animalRepository).findAll();
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().name()).isEqualTo("Tundra");
    }

}
