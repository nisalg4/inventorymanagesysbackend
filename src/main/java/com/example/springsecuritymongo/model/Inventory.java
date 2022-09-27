package com.example.springsecuritymongo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "Inventory")
@Getter
@Setter
@NoArgsConstructor
public class Inventory {
    private Employee employee;
    private List<Asset> assets ;

    public Inventory(List assets,Employee employee){
        this.assets=assets;
        this.employee=employee;
    }
}
