package com.example.ds.repositories;

import com.example.ds.models.entities.Enclosure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnclosureRepository extends JpaRepository<Enclosure, Integer> {

}
