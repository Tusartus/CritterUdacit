package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.UtilConvertClass;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {


    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ScheduleService scheduleService;

    /*
    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        throw new UnsupportedOperationException();
    }
*/

    private ScheduleDTO convertScheduleToScheduleDTO(Schedule schedule) {
        List<Long> employeeIds = schedule.getEmployees().stream().map(employee -> employee.getId())
                .collect(Collectors.toList());
        List<Long> petIds = schedule.getPets().stream().map(pet -> pet.getId())
                .collect(Collectors.toList());
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        scheduleDTO.setId(schedule.getId());
        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);
        return scheduleDTO;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = UtilConvertClass.convertDTOToScheduleEntity(scheduleDTO);
        return convertScheduleToScheduleDTO(
                scheduleService.createSchedule(schedule, scheduleDTO.getEmployeeIds(),
                        scheduleDTO.getPetIds()));
    }

    /*
    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
    }
    */


    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> allSchedules = scheduleService.findAll();

        List<ScheduleDTO> scheduleDTOs = convertList(allSchedules);

        return scheduleDTOs;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        Pet pet = petService.findPetById(petId);
        if (pet == null) {
            return new ArrayList<>();
        }

        List<Schedule> schedules = scheduleService.findScheduleByPet(pet);

        List<ScheduleDTO> scheduleDTOs = convertList(schedules);
        return scheduleDTOs;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (employee == null) {
            return new ArrayList<>();
        }

        List<Schedule> schedules = scheduleService.findScheduleByEmployee(employee);
        List<ScheduleDTO> scheduleDTOs = convertList(schedules);
        return scheduleDTOs;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        if (customer == null) {
            return new ArrayList<>();
        }

        List<Schedule> schedules = scheduleService.findScheduleByCustomer(customer);
        List<ScheduleDTO> scheduleDTOs = convertList(schedules);
        return scheduleDTOs;
    }

    public List<ScheduleDTO> convertList(List<Schedule> schedules) {
        return schedules.stream().map(schedule ->
                UtilConvertClass.convertEntityToScheduleDTO(schedule))
                .collect(Collectors.toList());
    }







}
