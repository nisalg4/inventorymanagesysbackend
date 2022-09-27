package com.example.springsecuritymongo.repository;

import com.example.springsecuritymongo.model.Asset;
import com.example.springsecuritymongo.model.Manager;
import com.example.springsecuritymongo.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ManagerRepository extends MongoRepository<Manager, String> {
    Manager getManagerById(String id);
    Manager getManagerByName(String name);

    Optional<Manager> findManagerById(String id);
    Manager save(Manager manager);
}