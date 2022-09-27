package com.example.springsecuritymongo.service;

import com.example.springsecuritymongo.model.Asset;
import com.example.springsecuritymongo.model.Employee;
import com.example.springsecuritymongo.model.Inventory;
import com.example.springsecuritymongo.repository.EmployeeRepository;
import com.example.springsecuritymongo.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

@Service
public class InventoryService {

    @Autowired
    AssetService assetService ;
    @Autowired
    EmployeeService employeeService ;

    @Autowired
    InventoryRepository inventoryRepository;

    public Inventory findInventoryByEmployee(String name) {

        Employee employee=employeeService.getEmployeeByUsername(name);
        try {
            Inventory inventory=inventoryRepository.findInventoryByEmployee(employee).get();
            System.out.println("aaa"+inventory.getAssets());
            if (employee==null){
                return new Inventory();
            }else{
                return inventoryRepository.findInventoryByEmployee(employee).get();
            }

        }catch (NoSuchElementException e){
            return new Inventory();
        }
    }

    public Inventory findInventorybyAsset(String name) {
        Asset asset=assetService.getAssetByName(name);
        try {
            Inventory inventory=inventoryRepository.findInventoryByAssetsContaining(asset).get();
            System.out.println("aaa"+inventory.getAssets());
            if (asset==null){
                return new Inventory();
            }else{
                return inventoryRepository.findInventoryByAssetsContaining(asset).get();
            }

        }catch (NoSuchElementException e){
            return new Inventory();
        }

    }

    public Inventory addInventory(Inventory inventory) {

        return inventoryRepository.save(inventory);
    }


}
