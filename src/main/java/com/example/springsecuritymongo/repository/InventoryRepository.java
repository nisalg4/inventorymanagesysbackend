package com.example.springsecuritymongo.repository;

import com.example.springsecuritymongo.model.Asset;
import com.example.springsecuritymongo.model.Employee;
import com.example.springsecuritymongo.model.Inventory;
import com.example.springsecuritymongo.model.Role;
import com.example.springsecuritymongo.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.springsecuritymongo.service.AssetService;

import java.util.Optional;


public interface InventoryRepository extends MongoRepository<Inventory, String> {


    Inventory findAllByEmployee();

    Optional<Inventory> findInventoryByEmployee(Employee name);
    Optional<Inventory> findInventoryByAssetsContaining(Asset asset);
    Inventory save(Inventory inventory);
}