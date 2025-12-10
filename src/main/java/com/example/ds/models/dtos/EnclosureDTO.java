package com.example.ds.models.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EnclosureDTO {
    private Integer id;
    private String habitat;
    private Double length;
    private Double width;
}
