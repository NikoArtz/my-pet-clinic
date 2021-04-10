package com.ex.clinic.repositories;

import com.ex.clinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

/**
 * @author martsiomchyk
 */

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
