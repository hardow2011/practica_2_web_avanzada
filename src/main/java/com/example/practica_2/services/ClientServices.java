package com.example.practica_2.services;

import javax.transaction.Transactional;

import com.example.practica_2.entities.Client;
import com.example.practica_2.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServices {

    @Autowired
    private ClientRepository clientRepository;

    /**
     * Creating and updating clients
     * @param client
     * @return
     */
    @Transactional
    public Client save(Client client){
        clientRepository.save(client);
        return client;
    }

}
