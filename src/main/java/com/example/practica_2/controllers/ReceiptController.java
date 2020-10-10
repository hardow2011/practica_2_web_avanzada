package com.example.practica_2.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.practica_2.entities.Client;
import com.example.practica_2.entities.Equipment;
import com.example.practica_2.entities.Receipt;
import com.example.practica_2.repository.EquipmentRepository;
import com.example.practica_2.repository.ReceiptRepository;
import com.example.practica_2.services.ClientServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("receipts")
public class ReceiptController {

    @Autowired
    private ClientServices clientServices;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    /**
     *
     * @param equipment
     * @param quantity
     * @param clientId
     * @param promisedReturnDate is received as a String from the HTML
     * @return
     * @throws ParseException
     */
    @PostMapping("/create")
    public String rentEquipment(Equipment equipment, @RequestParam(required = true) int quantity,
            @RequestParam(required = true) int clientId, @RequestParam(required = true) String promisedReturnDate)
            throws ParseException {

        Date parsedReturnDate = new SimpleDateFormat("yyyy-MM-dd").parse(promisedReturnDate);

        Client client = clientServices.findById(clientId);

        Receipt receipt = new Receipt(new Date(), parsedReturnDate, equipment, quantity, client);

        // System.out.println("\n\n\n"+receipt.getId()+" "+receipt.getClient().getId()+" "+receipt.getRentDate()+" "+receipt.getPromisedReturnDate()+"\n\n\n");

        receiptRepository.save(receipt);

        equipment.substractLentItems(quantity);

        equipmentRepository.save(equipment);

        System.out.println("\n\n"+equipment.getAmountInExistence()+"\n\n");

        return "redirect:/equipments/";
    }


}
