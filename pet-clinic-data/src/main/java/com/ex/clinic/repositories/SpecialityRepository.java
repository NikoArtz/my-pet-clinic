package com.ex.clinic.repositories;

import com.ex.clinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

/**
 * @author martsiomchyk
 */

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
