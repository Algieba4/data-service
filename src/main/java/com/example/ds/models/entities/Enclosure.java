package com.example.ds.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "enclosures")
public class Enclosure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String habitat;
    private Double length;
    private Double width;

    //@OneToMany(mappedBy = "enclosure", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Animal> animals = new ArrayList<>();
}
