package com.example.ds.models.dtos;

public record AnimalDTO(
    Integer id,
    String name,
    String species,
    String sex,
    Integer age,
    Integer enclosure
) {}
