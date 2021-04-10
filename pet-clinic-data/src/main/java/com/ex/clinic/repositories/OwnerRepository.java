package com.ex.clinic.repositories;

import com.ex.clinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameContainingIgnoreCase(String lastName);
}
