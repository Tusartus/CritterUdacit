package com.udacity.jdnd.course3.critter.schedule;


import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Schedule save(Schedule schedule);

    List<Schedule> findAll();

    List<Schedule> findAllByPetsContains(Pet pet);

    List<Schedule> findAllByEmployees(Employee employee);

    List<Schedule> findAllByPetsIn(List<Pet> pets);

    public List<Schedule> getDetailsByPets(Pet pet);

    public List<Schedule> getDetailsByEmployees(Employee employee);

    public List<Schedule> getAllByPetsIn(List<Pet> pets);


}