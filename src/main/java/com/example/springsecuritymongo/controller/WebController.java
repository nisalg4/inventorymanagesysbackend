package com.example.springsecuritymongo.controller;

import com.example.springsecuritymongo.model.Asset;
import com.example.springsecuritymongo.model.Employee;
import com.example.springsecuritymongo.model.Inventory;
import com.example.springsecuritymongo.model.Manager;
import com.example.springsecuritymongo.service.AssetService;
import com.example.springsecuritymongo.service.EmployeeService;
import com.example.springsecuritymongo.service.InventoryService;
import com.example.springsecuritymongo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/Employee/{ID}")
    public ResponseEntity<Employee> getEmployeebyID(@PathVariable String ID) {
        Employee employee = employeeService.getEmployeeById(ID);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee employee1 = employeeService.addEmployee(employee);
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

    @GetMapping("/Inventory/employeeName/{ID}")
    public ResponseEntity<Inventory> getInventoryByName(@PathVariable String ID) {
        Inventory inventory = inventoryService.findInventoryByEmployee(ID);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @GetMapping("/Inventory/asset/{ID}")
    public ResponseEntity<Inventory> getInventorybyAsset(@PathVariable String ID) {
        Inventory inventory = inventoryService.findInventorybyAsset(ID);
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }

    @PostMapping("/addInventory")
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) {
        Inventory inventory1 = inventoryService.addInventory(inventory);
        return new ResponseEntity<>(inventory1, HttpStatus.CREATED);
    }


}
