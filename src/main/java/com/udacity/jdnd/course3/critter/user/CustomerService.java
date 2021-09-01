package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.transaction.Transactional;



@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }


    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Customer findCustomerById(long id){
        return customerRepository.findCustomerById(id);
    }

    public Customer findCustomerByPetId(Pet pet){
        return customerRepository.findCustomerByPets(pet);
    }




}
