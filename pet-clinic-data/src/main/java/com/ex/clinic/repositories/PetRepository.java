package com.ex.clinic.repositories;

import com.ex.clinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

/**
 * @author martsiomchyk
 */

public interface PetRepository extends CrudRepository<Pet, Long> {
}
