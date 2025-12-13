package com.example.ds.services.enclosures;

import com.example.ds.enumerations.Biome;
import com.example.ds.mappers.EnclosureDTOMapper;
import com.example.ds.models.dtos.enclosures.EnclosureDTOV1;
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
        enclosure.setBiome(Biome.JUNGLE);
        enclosure.setLength(100.00);
        enclosure.setWidth(100.00);
    }

    @Test
    void test_create_enclosure() {
        EnclosureDTOV1 enclosureDTO = new EnclosureDTOV1(null, Biome.JUNGLE, 100.00, 100.00);

        Enclosure savedEnclosure = new Enclosure();
        savedEnclosure.setId(1);
        savedEnclosure.setBiome(Biome.JUNGLE);

        when(enclosureDTOMapper.dtoToEnclosure(enclosureDTO)).thenReturn(enclosure);
        when(enclosureRepository.save(enclosure)).thenReturn(savedEnclosure);
        when(enclosureDTOMapper.enclosureToDTO(savedEnclosure))
            .thenReturn(new EnclosureDTOV1(1, Biome.JUNGLE, 100.00, 100.00));

        EnclosureDTOV1 result = enclosureService.createEnclosure(enclosureDTO);

        assertThat(result.id()).isEqualTo(1);
        verify(enclosureDTOMapper).dtoToEnclosure(enclosureDTO);
        verify(enclosureRepository).save(enclosure);
        verify(enclosureDTOMapper).enclosureToDTO(savedEnclosure);
    }

    @Test
    void test_get_all_enclosures_service() {
        when(enclosureRepository.findAll()).thenReturn(List.of(enclosure));

        when(enclosureDTOMapper.enclosureToDTO(any(Enclosure.class)))
            .thenReturn(new EnclosureDTOV1(1, Biome.JUNGLE, 100.00, 100.00));


        List<EnclosureDTOV1> result = enclosureService.getAllEnclosures();

        verify(enclosureRepository).findAll();
        assertThat(result).hasSize(1);
        assertThat(result.getFirst().biome()).isEqualTo(Biome.JUNGLE);
    }

}
