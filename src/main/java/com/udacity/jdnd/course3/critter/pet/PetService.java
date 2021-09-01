package com.udacity.jdnd.course3.critter.pet;



import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Pet save(Pet pet){
        Pet returnPetWithCus = petRepository.save(pet);

        //save Pet of this customer (relationship)
        Customer customer = returnPetWithCus.getCustomer();
        customer.addPet(returnPetWithCus);
        customerRepository.save(customer);

        return returnPetWithCus;
    }

    public Pet findPetById(long id){
        return petRepository.findPetById(id);
    }

    public List<Pet> findAll(){
        return petRepository.findAll();
    }

    public List<Pet> findAllByCustomerId(long id){
        return petRepository.findAllByCustomerId(id);
    }

    public void addPetToCustomer(Pet pet, Customer customer){

        List<Pet> petsToCus = customer.getPets();

        if (petsToCus == null) {
            petsToCus = new ArrayList<Pet>();
        }
        petsToCus.add(pet);
        customer.setPets(petsToCus);

        customerRepository.save(customer);
    }
}
