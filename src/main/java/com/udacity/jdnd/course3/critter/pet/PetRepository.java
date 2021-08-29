package com.udacity.jdnd.course3.critter.pet;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    Pet save(Pet pet);

    Pet findPetById(long id);

    List<Pet> findAll();

    List<Pet> findAllByCustomerId(long ownerId);

}
