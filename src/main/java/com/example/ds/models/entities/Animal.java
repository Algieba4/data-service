package com.example.ds.models.entities;

import com.example.ds.enumerations.AnimalStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String sex;
    private String species;
    private AnimalStatus status;
    private Integer age;
    private Integer enclosure;

}


