package com.example.springsecuritymongo.service;

import com.example.springsecuritymongo.model.Asset;
import com.example.springsecuritymongo.model.Employee;
import com.example.springsecuritymongo.model.Inventory;
import com.example.springsecuritymongo.repository.AssetRepository;
import com.example.springsecuritymongo.repository.EmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

@Service
public class EmployeeService  {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserService userService;
    public Employee getEmployeeById(String id) {
        return employeeRepository.findEmployeeByid(id).get();
    }

    public Employee addEmployee(Employee employee) {

        if(!employeeRepository.existsByUsername(employee.getUsername())){
            return employeeRepository.save(employee);
        }
        else return new Employee();

    }

    public Employee getEmployeeByUsername(String name) {
        return employeeRepository.findEmployeeByusername(name);
    }


    public Employee getEmployeeByDateOfBirth(String dateofbirth) {
        return getEmployeeByDateOfBirth(dateofbirth);
    }

    public Employee deleteEmployeeById(String id) {
        return employeeRepository.deleteEmployeeById(id);
    }

    public Employee updateEmployee(Employee employee) {
        try {
            Employee employee1=this.getEmployeeById(employee.getId());
            if (employee1.getId()==null){

            }else {
                userService.updateUser(employee1.getUsername(),employee.getUsername());
                this.deleteEmployeeById(employee1.getId());
                this.addEmployee(employee);
            }
        }catch (NoSuchElementException e){
            this.addEmployee(employee);
        }

        return employee;
    }

    public List getAllEmployees() {
        return employeeRepository.findAll();
    }

}
