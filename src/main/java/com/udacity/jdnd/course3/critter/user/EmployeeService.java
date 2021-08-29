package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

     @Autowired
     private   EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee findById(long id){
        return employeeRepository.findById(id);
    }

    public void empAvailable(Set<DayOfWeek> daysAvailable, long empId){
        Employee employ = this.findById(empId);
        if(employ != null){
            Set<DayOfWeek> days = employ.getDaysAvailable();
            if(days == null){
                days = new HashSet<>();
            }
            days.addAll(daysAvailable);
            employ.setDaysAvailable(days);
            this.save(employ);
        }
    }

    public List<Employee> findEmpBySkillAndDate(Set<EmployeeSkill> skills, LocalDate date){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        List<Employee> resEmploy = new ArrayList<>();
        for(EmployeeSkill skill : skills){

            List<Employee> employeeSkill = employeeRepository.findAllBySkills(skill);

            for(Employee emp : employeeSkill){

                if(!resEmploy.contains(emp) && emp.getDaysAvailable().contains(dayOfWeek) && emp.getSkills().containsAll(skills)){
                    resEmploy.add(emp);
                }
            }
        }
        return resEmploy;
    }

}
