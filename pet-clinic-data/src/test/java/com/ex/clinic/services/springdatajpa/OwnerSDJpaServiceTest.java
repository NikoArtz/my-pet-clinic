package com.ex.clinic.services.springdatajpa;

import com.ex.clinic.model.Owner;
import com.ex.clinic.repositories.OwnerRepository;
import com.ex.clinic.repositories.PetRepository;
import com.ex.clinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    OwnerSDJpaService service;

    private final String lastName = "TestLastName";
    private final Long id = 1L;

    Owner mockOwner;

    @BeforeEach
    void setUp() {
        mockOwner = Owner.builder().id(id).lastName(lastName).build();
    }

    @Test
    void findByLastName() {

        when(service.findByLastName(any())).thenReturn(mockOwner);

        Owner owner = service.findByLastName(lastName);
        assertNotNull(owner);
        assertEquals(id, owner.getId());
        assertEquals(lastName, owner.getLastName());
        verify(ownerRepository, times(1)).findByLastName(any());

    }

    @Test
    void findAll() {
        Set<Owner> mockOwners = new HashSet<>();
        mockOwners.add(Owner.builder().id(4L).build());
        mockOwners.add(Owner.builder().id(5L).build());

        when(service.findAll()).thenReturn(mockOwners);

        Set<Owner> owners = service.findAll();
        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(mockOwner));
        Owner owner = service.findById(id);
        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(1L);

        assertNull(owner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(mockOwner);
        Owner owner = service.save(new Owner());
        assertNotNull(owner);
    }

    @Test
    void delete() {
        service.delete(mockOwner);
        verify(ownerRepository).delete(any());
        assertEquals(0, service.findAll().size());
    }

    @Test
    void deleteById() {
        service.deleteById(id);
        verify(ownerRepository).deleteById(any());
        assertEquals(0, service.findAll().size());
    }
}