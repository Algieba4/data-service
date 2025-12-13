package com.example.ds.repositories;

import com.example.ds.models.entities.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AnimalRepositoryTest {

    @Autowired
    private AnimalRepository animalRepository;

    private Animal animal;

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
    void test_create_animal_in_database() {
        Animal saved = animalRepository.save(animal);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void test_read_animal_from_database() {
        Animal saved = animalRepository.save(animal);
        Optional<Animal> found = animalRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Tundra");
    }

    @Test
    void test_delete_animal_from_database() {
        Animal saved = animalRepository.save(animal);
        animalRepository.deleteById(saved.getId());

        assertThat(animalRepository.findById(saved.getId())).isNotPresent();
    }

    @Test
    void test_update_animal_in_database() {
        Animal saved = animalRepository.save(animal);
        saved.setName("Tundra2");
        Animal newRecord = animalRepository.save(saved);

        assertThat(newRecord.getId()).isNotNull();
        assertThat(newRecord.getName()).isEqualTo("Tundra2");
    }

}
