package com.example.springsecuritymongo.repository;

import com.example.springsecuritymongo.model.Asset;
import com.example.springsecuritymongo.model.Employee;
import com.example.springsecuritymongo.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;


public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Employee findEmployeeByUsername(String name);
    Employee getEmployeeByDateOfBirth(String dateofbirth);

    Optional<Employee> findEmployeeById(String id);
    Employee save(Employee employee);
}