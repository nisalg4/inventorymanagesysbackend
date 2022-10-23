package com.example.springsecuritymongo.model;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "Asset")
@Getter
@Setter
@NoArgsConstructor
public class Asset {
    @Id
    private String assetId;
    @Indexed(unique = true)
    private String name;



    public Asset(String id, String name) {
        this.assetId = id;
        this.name = name;
    }
}