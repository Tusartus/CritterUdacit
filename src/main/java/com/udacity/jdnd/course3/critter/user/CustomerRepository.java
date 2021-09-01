package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.pet.Pet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer save(Customer customer);

    List<Customer> findAll();

    Customer findCustomerById(long id);

     Customer findCustomerByPets(Pet pet);

}
