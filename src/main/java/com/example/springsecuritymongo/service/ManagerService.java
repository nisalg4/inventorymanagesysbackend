package com.example.springsecuritymongo.service;

import com.example.springsecuritymongo.model.Employee;
import com.example.springsecuritymongo.model.Manager;
import com.example.springsecuritymongo.repository.EmployeeRepository;
import com.example.springsecuritymongo.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class ManagerService {
    @Autowired
    ManagerRepository managerRepository;
    public Manager getManagerById(String id) {
        return managerRepository.findManagerById(id).get();
    }

    public Manager addManager(Manager manager) {
        return managerRepository.save(manager);
    }
    public Manager getManagerByName(String name) {
        return getManagerByName(name);
    }



}
