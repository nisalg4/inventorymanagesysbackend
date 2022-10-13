package com.example.springsecuritymongo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "Inventory")
@Getter
@Setter
@NoArgsConstructor
public class Inventory {
    private Employee employee;
    private ArrayList<Asset> assets ;

    public Inventory(ArrayList assets,Employee employee){
        this.assets=assets;
        this.employee=employee;
    }

    public void addAsset(Asset asset){
        this.assets.add(asset);
    }

    public void deleteAsset(Asset asset){
        ArrayList<Asset> newAssets = new ArrayList<>();
        for (Asset element:
             assets) {
            if (!element.getAssetId().equals(asset.getAssetId())){
                newAssets.add(element);
            }
        }

        this.assets=newAssets;
    }

}
