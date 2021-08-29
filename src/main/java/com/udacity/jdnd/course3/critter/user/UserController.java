package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.UtilConvertClass;
import org.springframework.web.bind.annotation.*;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
   private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PetService petService;


    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = UtilConvertClass.convertDTOToCustomerEntity(customerDTO);
        List<Long> petIds = customerDTO.getPetIds();
        List<Pet> petList;
        if(petIds == null){
            petList = new ArrayList<>();
        }else{
            petList = petIds.stream().map(id -> petService.findPetById(id)).collect(Collectors.toList());
        }
        customer.setPets(petList);

        customer = customerService.save(customer);
        customerDTO.setId(customer.getId());
        return customerDTO;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customerList = customerService.findAll();
        List<CustomerDTO> customerDTOList = customerList.stream().map(customer ->
                UtilConvertClass.convertEntityToCustomerDTO(customer)).collect(Collectors.toList());
        return customerDTOList;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable Long petId){
        Pet pet = petService.findPetById(petId);
        if(pet == null){
            throw new UnsupportedOperationException("No pet found");
        }

        Customer customer = customerService.findCustomerByPetId(pet);
        CustomerDTO customerDTO = UtilConvertClass.convertEntityToCustomerDTO(customer);
        customerDTO.setPetIds(Arrays.asList(petId));
        return customerDTO;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = UtilConvertClass.convertDTOToEmployeeEntity(employeeDTO);
        employee = employeeService.save(employee);
        employeeDTO.setId(employee.getId());
        return employeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.findById(employeeId);
        return UtilConvertClass.convertEntityToEmployeeDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.empAvailable(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = employeeService.findEmpBySkillAndDate(employeeDTO.getSkills(), employeeDTO.getDate());
        List<EmployeeDTO> employeeDTOs = employees.stream().map(e->
                UtilConvertClass.convertEntityToEmployeeDTO(e)).collect(Collectors.toList());
        return employeeDTOs;
    }



/*
    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }



 */





}
