package com.example.ds.models.dtos.animals;

@Deprecated(since = "v2", forRemoval = true)
public record AnimalDTOV1(
    Integer id,
    String name,
    String sex,
    String species,
    Integer age
) {}
