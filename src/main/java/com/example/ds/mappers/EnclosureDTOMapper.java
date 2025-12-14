package com.example.ds.mappers;

import com.example.ds.models.dtos.enclosures.EnclosureDTOV1;
import com.example.ds.models.entities.Enclosure;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnclosureDTOMapper {
    EnclosureDTOV1 enclosureToDTO(Enclosure enclosure);
    Enclosure dtoToEnclosure(EnclosureDTOV1 enclosureDTO);
}
