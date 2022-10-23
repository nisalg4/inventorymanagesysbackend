package com.example.springsecuritymongo.repository;

import com.example.springsecuritymongo.model.Asset;
import com.example.springsecuritymongo.model.Employee;
import com.example.springsecuritymongo.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Employee findEmployeeByusername(String username);
    Employee getEmployeeByDateOfBirth(String dateofbirth);

    Optional<Employee> findEmployeeByid(String id);
    Employee save(Employee employee);

    Employee deleteEmployeeById(String id);

    Boolean existsByUsername(String username);

    @Override
    List<Employee> findAll();


}