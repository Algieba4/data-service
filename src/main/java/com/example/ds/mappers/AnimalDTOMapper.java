package com.example.ds.mappers;

import com.example.ds.models.dtos.animals.AnimalDTOV1;
import com.example.ds.models.dtos.animals.AnimalDTOV2;
import com.example.ds.models.entities.Animal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnimalDTOMapper {
    AnimalDTOV1 animalToDTOV1(Animal animal);
    Animal dtoV1ToAnimal(AnimalDTOV1 animalDTO);

    AnimalDTOV2 animalToDTOV2(Animal animal);
    Animal dtoV2ToAnimal(AnimalDTOV2 animalDTO);
}
