package com.example.springsecuritymongo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Manager")
@Getter
@Setter
@NoArgsConstructor
public class Manager {
    @Id
    private String id;
    private String name;


    public Manager(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
