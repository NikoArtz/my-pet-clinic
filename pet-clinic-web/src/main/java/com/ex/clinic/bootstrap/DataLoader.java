package com.ex.clinic.bootstrap;

import com.ex.clinic.model.Owner;
import com.ex.clinic.model.Pet;
import com.ex.clinic.model.PetType;
import com.ex.clinic.model.Speciality;
import com.ex.clinic.model.Vet;
import com.ex.clinic.model.Visit;
import com.ex.clinic.services.OwnerService;
import com.ex.clinic.services.PetService;
import com.ex.clinic.services.PetTypeService;
import com.ex.clinic.services.SpecialityService;
import com.ex.clinic.services.VetService;
import com.ex.clinic.services.VisitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author martsiomchyk
 */

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCat = petTypeService.save(cat);

        Owner owner = new Owner();
        owner.setFirstName("Niko");
        owner.setLastName("Hons");
        owner.setAddress("Biskupcova 93");
        owner.setCity("Prague");
        owner.setTelephone("+420777888555");

        ownerService.save(owner);

        Owner owner2 = new Owner();
        owner2.setFirstName("Anna");
        owner2.setLastName("Sork");
        owner2.setAddress("Dekanska 12");
        owner2.setCity("Brno");
        owner2.setTelephone("+420777999444");

        ownerService.save(owner2);
        System.out.println("Loaded owners");


        Pet firstPet = new Pet();
        firstPet.setPetType(savedDog);
        firstPet.setBirthDate(LocalDate.now());
        firstPet.setOwner(owner);
        firstPet.setName("Rosco");
        owner.getPets().add(firstPet);
        petService.save(firstPet);

        Pet secondPet = new Pet();
        secondPet.setPetType(savedCat);
        secondPet.setBirthDate(LocalDate.now());
        secondPet.setOwner(owner2);
        secondPet.setName("Fiona");
        owner2.getPets().add(secondPet);
        petService.save(secondPet);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");

        Speciality savedRadiology = specialityService.save(radiology);
        Speciality savedSurgery = specialityService.save(surgery);
        Speciality savedDentistry = specialityService.save(dentistry);

        Vet vet = new Vet();
        vet.setFirstName("John");
        vet.setLastName("Icl");
        vet.getSpecialities().add(savedRadiology);
        vet.getSpecialities().add(savedDentistry);
        vetService.save(vet);

        Vet vet2 = new Vet();
        vet2.setFirstName("Mike");
        vet2.setLastName("Ret");
        vet2.getSpecialities().add(savedSurgery);
        vet2.getSpecialities().add(savedRadiology);
        vetService.save(vet2);
        System.out.println("Loaded vets");

        Visit visit = new Visit();
        visit.setDate(LocalDate.now());
        visit.setDescription("Visit description");
        visit.setPet(secondPet);
        visitService.save(visit);
    }
}
