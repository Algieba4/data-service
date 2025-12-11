package com.example.ds.models.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AnimalDTO {
    private Integer id;
    private String name;
    private String species;
    private String gender;
    private Integer age;
    private Integer enclosure;
    //private Enclosure enclosure;
}
