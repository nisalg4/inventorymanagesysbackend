package com.example.springsecuritymongo.controller;

import com.example.springsecuritymongo.model.*;
import com.example.springsecuritymongo.response.AssetAssignmentResponse;
import com.example.springsecuritymongo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class WebController {
    @Autowired
    AssetService assetService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    ManagerService managerService;
    @Autowired
    InventoryService inventoryService;

    @Autowired
    UserService userService;

    @GetMapping("/home")
    public String getHome() {
        return "This is home page";
    }

    @GetMapping("/dashboard")
    public String getDashboard() {
        return "This is dashboard page";
    }

    @GetMapping("/manage")
    public String getManage() {
        return "This is manage page";
    }

    @GetMapping("/Asset/{ID}")
    public ResponseEntity<Asset> getAssetbyID(@PathVariable String ID) {
        Asset asset = assetService.getAssetById(ID);
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @PostMapping("/addAsset")
    public ResponseEntity<Asset> addAsset(@RequestBody Asset asset) {
        Asset asset1 = assetService.addAsset(asset);
        return new ResponseEntity<>(asset1, HttpStatus.CREATED);
    }

    @GetMapping("/Asset/delete/{ID}")
    public ResponseEntity<Asset> deleteAsset(@PathVariable String ID) {
        Asset asset = assetService.deleteAssetById(ID);
        return new ResponseEntity<>(asset, HttpStatus.OK);
    }

    @GetMapping("/Asset/getAll")
    public ResponseEntity<List> getAllAssets() {
        List<Asset> assets = assetService.getAllAssets();
        return new ResponseEntity<>(assets, HttpStatus.OK);
    }

    @PostMapping("/updateAsset")
    public ResponseEntity<Asset> updateAsset(@RequestBody Asset asset) {
        Asset oldAsset=assetService.getAssetById(asset.getAssetId());
        inventoryService.updateInventoryAsset(asset,oldAsset);
       Asset asset1=assetService.updateAsset(asset);

        return new ResponseEntity<>(asset1, HttpStatus.CREATED);
    }



    @GetMapping("/Employee/{ID}")
    public ResponseEntity<Employee> getEmployeebyID(@PathVariable String ID) {
        Employee employee = employeeService.getEmployeeById(ID);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee employee1 = employeeService.addEmployee(employee);
        inventoryService.addInventory(new Inventory(new ArrayList<Asset>(),employee1));
        return new ResponseEntity<>(employee1, HttpStatus.CREATED);
    }


    @GetMapping("/Employee/delete/{ID}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable String ID) {
        inventoryService.deleteInventory(employeeService.getEmployeeById(ID).getUsername());
        userService.deleteUser(employeeService.getEmployeeById(ID));
        Employee employee = employeeService.deleteEmployeeById(ID);

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/Employee/getAll")
    public ResponseEntity<List> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/updateEmployee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee oldEmployee=employeeService.getEmployeeById(employee.getId());
        inventoryService.updateInventoryEmployee(employee,oldEmployee);
        Employee employee1=employeeService.updateEmployee(employee);
        return new ResponseEntity<>(employee1, HttpStatus.CREATED);
    }


    @GetMapping("/Manager/{ID}")
    public ResponseEntity<Manager> getManagerbyID(@PathVariable String ID) {
        Manager manager = managerService.getManagerById(ID);
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }

    @PostMapping("/addManager")
    public ResponseEntity<Manager> addManager(@RequestBody Manager manager) {
        Manager manager1 = managerService.addManager(manager);
        return new ResponseEntity<>(manager1, HttpStatus.CREATED);
    }

    @GetMapping("/AssetAssignment/employeeName/{ID}")
    public ResponseEntity<List> getAssetAssignmentByName(@PathVariable String ID) {
        Inventory inventory = inventoryService.findInventoryByEmployee(ID);
        return new ResponseEntity<>(inventory.getAssets(), HttpStatus.OK);
    }

    @GetMapping("/AssetAssignment/asset/{ID}")
    public ResponseEntity<Inventory> getAssetAssignmentbyAsset(@PathVariable String ID) {
        Inventory inventory = inventoryService.findInventorybyAsset(ID);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @PostMapping("/addAssetAssignment")
    public ResponseEntity<Inventory> addAssetAssignment(@RequestBody Inventory inventory) {
        Inventory inventory1 = inventoryService.addInventory(inventory);
        return new ResponseEntity<>(inventory1, HttpStatus.CREATED);
    }

    @GetMapping("/AssetAssignment/delete/{ID}")
    public ResponseEntity<Inventory> deleteAssetAssignmentbyEmployee(@PathVariable String ID) {
        Inventory inventory = inventoryService.deleteInventory(ID);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @PostMapping("/deleteAssetAssignmentAsset")
    public ResponseEntity<Inventory> deleteAssetAssignmentAsset(@RequestBody AssetAssignmentResponse assetAssignmentResponse) {
        Inventory inventory1 = inventoryService.deleteInventoryAsset(assetAssignmentResponse);
        return new ResponseEntity<>(inventory1, HttpStatus.CREATED);
    }

    @PostMapping("/addAssetAssignmentforemployee")
    public ResponseEntity<Inventory> addAssetAssignmentforemployee(@RequestBody AssetAssignmentResponse assetAssignmentResponse) {
        Inventory inventory=new Inventory(new ArrayList<>(),employeeService.getEmployeeByUsername(assetAssignmentResponse.getEmployee()));
        inventory.addAsset(assetService.getAssetByName(assetAssignmentResponse.getAsset()));
        Inventory inventory1 = inventoryService.addAssettoEmployeeinterface(inventory.getEmployee().getId(),inventory.getAssets().get(0).getName());
        return new ResponseEntity<>(inventory1, HttpStatus.CREATED);
    }


    @GetMapping("/AssetAssignment/getAll")
    public ResponseEntity<List> getAllAssetAssignments() {
        //List<Inventory> inventories = inventoryService.getAllAssetAssignments();

        List<AssetAssignmentResponse> assetAssignmentResponses=inventoryService.getAllAssetAssignmentsProcessing();
        return new ResponseEntity<>(assetAssignmentResponses, HttpStatus.OK);
    }


}
