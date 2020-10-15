package com.example.practica_2.services;

import java.util.List;

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

    public List<User> findAll(){
        return userRepository.findAll();
    }

	public User findById(Long userId) {
		return userRepository.findById(userId).get();
	}

	public void delete(User user) {
        userRepository.delete(user);
	}

}
