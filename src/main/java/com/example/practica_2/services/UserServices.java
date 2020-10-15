package com.example.practica_2.services;

import javax.transaction.Transactional;

import com.example.practica_2.entities.User;
import com.example.practica_2.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User save(User user){
        userRepository.save(user);
        return user;
    }

}
