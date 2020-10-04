package com.example.practica_2.controllers;

import com.example.practica_2.entities.Client;
import com.example.practica_2.services.ClientServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientServices clientServices;

    /**
     * GET method for creating a client
     * @param model
     * @return
     */
    @GetMapping("/create")
    public String createClient(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("action", "Crear");
        model.addAttribute("postAddress", "/clients/create");
        return "createClient";
    }

    @ResponseBody
    @PostMapping("/create")
    public Client createClient(Client client) {
        
        System.out.println("\n\n\n"+client.getBase64Image()+"\n\n\n");
        clientServices.save(client);
        return client;
    }

}
