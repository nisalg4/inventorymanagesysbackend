package com.example.springsecuritymongo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "Employee")
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String dateOfBirth;

    public Employee(String username, String id, String dateOfBirth) {
        this.username = username;
        this.id = id;
        this.dateOfBirth = dateOfBirth;
    }
}