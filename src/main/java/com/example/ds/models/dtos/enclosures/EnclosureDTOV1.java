package com.example.ds.models.dtos.enclosures;

import com.example.ds.enumerations.Biome;

public record EnclosureDTOV1(
    Integer id,
    Biome biome,
    Double length,
    Double width
) {}
