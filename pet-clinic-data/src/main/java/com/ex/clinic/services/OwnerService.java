package com.ex.clinic.services;

import com.ex.clinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
    
    List<Owner> findAllByLastNameLike(String lastName);
}
