package com.example.practica_2.controllers;

import java.io.IOException;

import com.example.practica_2.entities.Client;
import com.example.practica_2.services.ClientServices;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientServices clientServices;

    /**
     * GET method for creating a client
     *
     * @param model
     * @return
     */
    @GetMapping("/create")
    public String createClient(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("action", "Crear");
        model.addAttribute("postAddress", "/clients/create");
        return "createEditViewClient";
    }

    @ResponseBody
    @PostMapping("/create")
    public Client createClient(@RequestParam("rawImage") MultipartFile rawImage, Client client) throws IOException {

        // If raw image is not null, otherwise, the image of the client will be "data:image/png;base64,"
        if(rawImage != null){
            // To build the final enconded 4 string
            StringBuilder sb = new StringBuilder();
            sb.append("data:image/png;base64,");

            // Append to encoded version of the MultipartFile rawImage to the Stringbuilder
            sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(rawImage.getBytes(), false)));

            // Set the pimage of the client to the string of StringBuilder
            client.setBase64Image(sb.toString());
        }

        clientServices.save(client);
        return client;
    }

}
