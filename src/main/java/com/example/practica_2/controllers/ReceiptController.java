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
import com.example.practica_2.services.EquipmentServices;
import com.example.practica_2.services.ReceiptServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("receipts")
public class ReceiptController {

    @Autowired
    private ClientServices clientServices;

    @Autowired
    private ReceiptServices receiptServices;

    @Autowired
    private EquipmentServices equipmentServices;

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

        equipment.substractLentItems(quantity);

        equipmentServices.save(equipment);

        receiptServices.save(receipt);

        return "redirect:/equipments/";
    }

    @GetMapping("/recover/{receiptId}")
    public String updateEquipment(Model model, @PathVariable() Long receiptId) {
        Receipt receipt = receiptServices.findById(receiptId);
        model.addAttribute("action", "Recuperar equipos");
        model.addAttribute("postAddress", "/receipts/recover");
        model.addAttribute("receipt", receipt);
        return "createUpdateViewReceipt";
    }

    @PostMapping("/recover")
    public String updateEquipment(Receipt receipt, @RequestParam(required = true) int quantityToBeRecovered)
            throws ParseException {

        // Define the date pattern
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        // Get today's date in String and convert it to Date
        String todayString = simpleDateFormat.format(new Date());
        Date today = simpleDateFormat.parse(todayString);

        // ! VERY IMPORTANT. OR ESLE THE RELATIONS CANNTO BE FECTHED.
        receipt = receiptServices.findById(receipt.getId());

        // Add back the recovered items to the Equipments inventory
        Equipment equipment = receipt.getEquipment();
        equipment.addRecoverItems(quantityToBeRecovered);

        // If all the items are to be returned, set this receipt to returned.
        // Else, if a portion of the items are beong recovered, clone the receipt...
        // and set it to return.
        if(quantityToBeRecovered == receipt.getQuantity()){
            receipt.setHasBeenReturned(true);
            receipt.setReturnedDate(today);
        }else{
            Receipt newReceipt = new Receipt(receipt.getRentDate(), receipt.getPromisedReturnDate(), receipt.getEquipment(), quantityToBeRecovered, receipt.getClient());
            newReceipt.setHasBeenReturned(true);
            newReceipt.setReturnedDate(new Date());
            receiptServices.save(newReceipt);

            receipt.recoverPartialItems(quantityToBeRecovered);
        }

        equipmentServices.save(equipment);
        receiptServices.save(receipt);

        return "redirect:/equipments/rented";
    }


}
