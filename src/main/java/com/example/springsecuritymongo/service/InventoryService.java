package com.example.springsecuritymongo.service;

import com.example.springsecuritymongo.model.Asset;
import com.example.springsecuritymongo.model.AssetAssignmentResponse;
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

import java.util.*;
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
        catch (NullPointerException x){
            return new Inventory();
        }

    }

    public Inventory addInventory(Inventory inventory) {

        try {
            if (this.findInventoryByEmployee(inventory.getEmployee().getUsername()).getEmployee().getId().equals(inventory.getEmployee().getId())) {
                return new Inventory(null,null);
            } else {
                return inventoryRepository.save(inventory);
            }

        }catch(NullPointerException e ){
            return inventoryRepository.save(inventory);
        }
    }

    public Inventory deleteInventory(String employee) {
        return inventoryRepository.deleteInventoryByEmployee_Username(employeeService.getEmployeeByUsername(employee).getUsername());
    }

    public Inventory addAssettoEmployeeinterface(String employeeid,String assetName) {
        try {
            if(employeeService.getEmployeeById(employeeid)==null){return new Inventory();}else {
                return addAssettoEmployee(employeeService.getEmployeeById(employeeid), assetService.getAssetByName(assetName));
            }
        }catch (NoSuchElementException e){
            return new Inventory();        }
        }

    public Inventory addAssettoEmployee(Employee employee,Asset asset) {

      Inventory inventory=this.findInventoryByEmployee(employee.getUsername());
      try{
      if(inventory.getEmployee().getId()==null){

      }else {
          if(assetService.getAssetById(asset.getAssetId()).getAssetId().equals(asset.getAssetId())){

              if(this.findInventorybyAsset(asset.getName()).getEmployee()==null) {
                  try {
                      inventory.addAsset(asset);
                      deleteInventory(employee.getUsername());
                      addInventory(inventory);
                  }catch (NoSuchElementException e){
                      addInventory(inventory);
                  }
              }
          }else {
              System.out.println("asset doesnt exist");
          }
      }
      }catch (NullPointerException e){


          Inventory inventory2=new Inventory(new ArrayList<>(),employee);
          try {
              if(findInventorybyAsset(asset.getName())==null){

              }
          }catch (NullPointerException x){
              inventory2.addAsset(asset);
             // addInventory(inventory2);
          }
          addInventory(inventory2);

      }
        return inventory;
    }

    public List getAllAssetAssignments() {
        return inventoryRepository.findAll();
    }

    public List getAllAssetAssignmentsProcessing() {
        List<Inventory> inventories=getAllAssetAssignments();
        List<AssetAssignmentResponse> assetAssignmentResponses = new ArrayList<>();
        for (Inventory inventory:inventories) {
String assetowner=inventory.getEmployee().getUsername();
        for(Asset asset:inventory.getAssets()){
            String assetname=asset.getName();
            assetAssignmentResponses.add(new AssetAssignmentResponse(assetowner,assetname));
        }
        }
        return assetAssignmentResponses;
    }


}
