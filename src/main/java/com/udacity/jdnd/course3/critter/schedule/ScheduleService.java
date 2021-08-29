package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PetRepository petRepository;

    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findScheduleByPet(Pet pet) {
        return scheduleRepository.findAllByPetsContains(pet);
    }

    public List<Schedule> findScheduleByEmployee(Employee employee) {
        return scheduleRepository.findAllByEmployees(employee);
    }

    public List<Schedule> findScheduleByCustomer(Customer customer) {
        List<Pet> pets = customer.getPets();
        return scheduleRepository.findAllByPetsIn(pets);
    }

    public Schedule createSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employees = employeeRepository.findAllById(employeeIds);
        List<Pet> pets = petRepository.findAllById(petIds);
        schedule.setEmployees(employees);
        schedule.setPets(pets);
        if (schedule == null) {
            schedule = new Schedule();
        }
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getScheduleforEmployee(long employeeId) {
        List<Schedule> scheduleList = scheduleRepository.getDetailsByEmployees(
                employeeRepository.getOne(employeeId));
        return scheduleList;
    }

}
