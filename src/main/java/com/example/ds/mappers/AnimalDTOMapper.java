package com.example.ds.mappers;

import com.example.ds.models.dtos.AnimalDTO;
import com.example.ds.models.entities.Animal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnimalDTOMapper {
    AnimalDTO animalToDTO(Animal animal);
    Animal dtoToAnimal(AnimalDTO userDTO);
}
