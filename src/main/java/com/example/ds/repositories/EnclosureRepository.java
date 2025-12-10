package com.example.ds.repositories;

import com.example.ds.models.entities.Enclosure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnclosureRepository extends CrudRepository<Enclosure, Integer> {

}
