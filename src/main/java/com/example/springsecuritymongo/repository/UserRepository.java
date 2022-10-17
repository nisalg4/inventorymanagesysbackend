package com.example.springsecuritymongo.repository;

import com.example.springsecuritymongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserById(String Id);

    Optional<User> deleteUserById(String id);

    User save(User user);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}