package com.example.ds.models.dtos.animals;

import com.example.ds.enumerations.AnimalStatus;

public record AnimalDTOV2(
    Integer id,
    String name,
    String sex,
    String species,
    AnimalStatus status,
    Integer age,
    Integer enclosure
) {}
