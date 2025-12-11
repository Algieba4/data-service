package com.example.ds.models.dtos;

import com.example.ds.models.entities.Animal;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class EnclosureDTO {
    private Integer id;
    private String habitat;
    private Double length;
    private Double width;
    //List<Animal> animals;
}
