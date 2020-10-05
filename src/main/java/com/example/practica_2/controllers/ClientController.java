package com.example.practica_2.controllers;

import java.io.IOException;
import java.util.List;

import com.example.practica_2.entities.Client;
import com.example.practica_2.services.ClientServices;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientServices clientServices;

    /**
     *
     * @param model
     * @return
     */
    @GetMapping({"/", ""})
    public String listClients(Model model) {
        List<Client> clientList = clientServices.findAll();
        model.addAttribute("clientList", clientList);
        model.addAttribute("action", "Lista de clientes");
        return "listClients";
    }

    /**
     * GET method for creating a client
     *
     * @param model
     * @return
     */
    @GetMapping("/create")
    public String createClient(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("action", "Crear cliente");
        model.addAttribute("postAddress", "/clients/create");
        return "createUpdateViewClient";
    }

    /**
     *
     * @param rawImage
     * @param client
     * @return
     * @throws IOException
     */
    @PostMapping("/create")
    public String createClient(@RequestParam("rawImage") MultipartFile rawImage, Client client) throws IOException {

        // If raw image is not empty, otherwise, the image of the client would be "data:image/png;base64," and I want it to be null if non existent
        if(!rawImage.isEmpty()){
            // To build the final enconded string
            StringBuilder sb = new StringBuilder();
            sb.append("data:image/png;base64,");

            // Append to encoded version of the MultipartFile rawImage to the Stringbuilder
            sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(rawImage.getBytes(), false)));

            // Set the pimage of the client to the string of StringBuilder
            client.setBase64Image(sb.toString());
        }

        clientServices.save(client);
        return "redirect:/clients/";
    }

    /**
     *
     * @param model
     * @param clientId
     * @return
     */
    @GetMapping("/update/{clientId}")
    public String updateClient(Model model, @PathVariable() Long clientId) {
        Client client = clientServices.findById(clientId);
        model.addAttribute("action", "Update client: "+clientId.toString());
        model.addAttribute("postAddress", "/clients/update");
        model.addAttribute("client", client);
        // model.addAttribute("update", true);
        return "createUpdateViewClient";
    }

    @PostMapping("/update")
    public String updateClient(@RequestParam("rawImage") MultipartFile rawImage, Client client) throws IOException {
        // If raw image is not empty, otherwise, the image of the client would be "data:image/png;base64," and I want it to be null if non existent
        if(!rawImage.isEmpty()){
            // To build the final enconded string
            StringBuilder sb = new StringBuilder();
            sb.append("data:image/png;base64,");

            // Append to encoded version of the MultipartFile rawImage to the Stringbuilder
            sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(rawImage.getBytes(), false)));

            // Set the pimage of the client to the string of StringBuilder
            client.setBase64Image(sb.toString());
        }

        clientServices.save(client);
        return "redirect:/clients/";
    }

    @GetMapping("/view/{clientId}")
    public String viewClient(Model model, @PathVariable() Long clientId) {
        Client client = clientServices.findById(clientId);
        model.addAttribute("action", "View client: "+clientId.toString());
        model.addAttribute("client", client);
        model.addAttribute("view", true);
        return "createUpdateViewClient";
    }

}
