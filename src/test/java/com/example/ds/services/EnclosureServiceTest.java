package com.example.ds.services;

import com.example.ds.mappers.EnclosureDTOMapper;
import com.example.ds.models.dtos.EnclosureDTO;
import com.example.ds.models.entities.Enclosure;
import com.example.ds.repositories.EnclosureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnclosureServiceTest {

    Enclosure enclosure;

    @Mock
    EnclosureDTOMapper enclosureDTOMapper;

    @Mock
    EnclosureRepository enclosureRepository;

    @InjectMocks
    EnclosureService enclosureService;

    @BeforeEach
    void setup() {
        enclosure = new Enclosure();
        enclosure.setHabitat("Jungle");
        enclosure.setLength(100.00);
        enclosure.setWidth(100.00);
    }

    @Test
    void test_get_all_enclosures_service() {
        when(enclosureRepository.findAll()).thenReturn(List.of(enclosure));

        when(enclosureDTOMapper.enclosureToDTO(any(Enclosure.class)))
            .thenReturn(new EnclosureDTO(1, "Jungle", 100.00, 100.00));


        List<EnclosureDTO> result = enclosureService.getAllEnclosures();

        verify(enclosureRepository).findAll();
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().habitat()).isEqualTo("Jungle");
    }

}
