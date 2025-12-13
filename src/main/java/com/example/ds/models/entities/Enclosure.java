package com.example.ds.models.entities;

import com.example.ds.enumerations.Biome;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "enclosures")
public class Enclosure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Biome biome;
    private Double length;
    private Double width;
}
