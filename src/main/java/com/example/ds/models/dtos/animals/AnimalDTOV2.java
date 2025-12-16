package com.example.ds.models.dtos.animals;

@Deprecated(since = "v3")
public record AnimalDTOV2(
    Integer id,
    String name,
    String sex,
    String species,
    Integer age,
    Integer enclosure
) {}
