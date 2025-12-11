package com.example.ds.models.entities;

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
    private String species;
    private String gender;
    private Integer age;
    private Integer enclosure;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "enclosure_id")
    //private Enclosure enclosure;
}
