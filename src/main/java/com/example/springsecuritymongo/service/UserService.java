package com.example.springsecuritymongo.service;

import com.example.springsecuritymongo.model.User;
import com.example.springsecuritymongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    public Optional<User> addUser(User user) {


        if(!userRepository.existsByUsername(user.getUsername())){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return Optional.ofNullable(userRepository.save(user));
        }
        return Optional.of(new User());
    }

}
