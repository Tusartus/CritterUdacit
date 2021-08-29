package com.udacity.jdnd.course3.critter.user;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee save(Employee employee);

    Employee findById(long id);

    List<Employee> findAllBySkills(EmployeeSkill employeeSkill);

}