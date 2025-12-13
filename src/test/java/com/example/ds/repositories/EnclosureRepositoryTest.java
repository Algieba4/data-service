package com.example.ds.repositories;

import com.example.ds.models.entities.Enclosure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EnclosureRepositoryTest {

    @Autowired
    private EnclosureRepository enclosureRepository;

    private Enclosure enclosure;

    @BeforeEach
    void setup() {
        enclosure = new Enclosure();
        enclosure.setHabitat("Jungle");
        enclosure.setLength(100.00);
        enclosure.setWidth(100.00);
    }

    @Test
    void test_create_enclosure_in_database() {
        Enclosure saved = enclosureRepository.save(enclosure);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void test_read_enclosure_from_database() {
        Enclosure saved = enclosureRepository.save(enclosure);
        Optional<Enclosure> found = enclosureRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getHabitat()).isEqualTo("Jungle");
    }

    @Test
    void test_delete_enclosure_from_database() {
        Enclosure saved = enclosureRepository.save(enclosure);
        enclosureRepository.deleteById(saved.getId());

        assertThat(enclosureRepository.findById(saved.getId())).isNotPresent();
    }

    @Test
    void test_update_enclosure_in_database() {
        Enclosure saved = enclosureRepository.save(enclosure);
        saved.setHabitat("Grasslands");
        Enclosure newRecord = enclosureRepository.save(saved);

        assertThat(newRecord.getId()).isNotNull();
        assertThat(newRecord.getHabitat()).isEqualTo("Grasslands");
    }



}
