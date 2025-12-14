package com.example.ds.services.animals;

import com.example.ds.mappers.AnimalDTOMapper;
import com.example.ds.models.dtos.animals.AnimalDTOV1;
import com.example.ds.models.entities.Animal;
import com.example.ds.repositories.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalServiceV1Test {

    Animal animal;

    @Spy
    AnimalDTOMapper animalDTOMapper = Mappers.getMapper(AnimalDTOMapper.class);

    @Mock
    AnimalRepository animalRepository;

    @InjectMocks
    AnimalServiceV1 animalServiceV1;

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
        AnimalDTOV1 animalDTOV1 = new AnimalDTOV1(null, "Tundra", "Tiger", "Female", 5);

        Animal savedAnimal = new Animal();
        savedAnimal.setId(1);
        savedAnimal.setName("Tundra");

        when(animalDTOMapper.dtoV1ToAnimal(animalDTOV1)).thenReturn(animal);
        when(animalRepository.save(animal)).thenReturn(savedAnimal);
        when(animalDTOMapper.animalToDTOV1(savedAnimal))
            .thenReturn(new AnimalDTOV1(1, "Tundra", "Tiger", "Female", 5));

        AnimalDTOV1 result = animalServiceV1.createAnimal(animalDTOV1);

        assertThat(result.id()).isEqualTo(1);
        verify(animalDTOMapper).dtoV1ToAnimal(animalDTOV1);
        verify(animalRepository).save(animal);
        verify(animalDTOMapper).animalToDTOV1(savedAnimal);
    }

    @Test
    void test_get_all_animals_service() {
        when(animalRepository.findAll()).thenReturn(List.of(animal));

        when(animalDTOMapper.animalToDTOV1(any(Animal.class)))
            .thenReturn(new AnimalDTOV1(1, "Tundra", "Tiger", "Female", 5));

        List<AnimalDTOV1> result = animalServiceV1.getAllAnimals();

        verify(animalRepository).findAll();
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().name()).isEqualTo("Tundra");
    }

}
