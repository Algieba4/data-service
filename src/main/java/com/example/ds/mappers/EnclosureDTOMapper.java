package com.example.ds.mappers;

import com.example.ds.models.dtos.EnclosureDTO;
import com.example.ds.models.entities.Enclosure;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnclosureDTOMapper {
    EnclosureDTO enclosureToDTO(Enclosure enclosure);
    Enclosure dtoToEnclosure(EnclosureDTO enclosureDTO);

}
