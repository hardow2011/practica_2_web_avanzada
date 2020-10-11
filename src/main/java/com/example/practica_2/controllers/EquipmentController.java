package com.example.practica_2.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.example.practica_2.entities.Client;
import com.example.practica_2.entities.Equipment;
import com.example.practica_2.entities.Receipt;
import com.example.practica_2.repository.ReceiptRepository;
import com.example.practica_2.services.ClientServices;
import com.example.practica_2.services.EquipmentServices;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.hibernate.sql.Insert;
import org.joda.time.Days;
import org.joda.time.ReadableInstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.util.Duration;

@Controller
@RequestMapping("equipments")
public class EquipmentController {

    @Autowired
    private EquipmentServices equipmentServices;

    @Autowired
    private ReceiptRepository receiptRepository;

    @GetMapping({ "/", "" })
    public String listEquipments() {
        return "redirect:/equipments/inStock";
    }

    @GetMapping("/inStock")
    public String listEquipmentsInStock(Model model) {
        List<Equipment> equipmentsList = equipmentServices.findAll();
        model.addAttribute("equipmentsList", equipmentsList);
        model.addAttribute("action", "Lista de equipos disponibles");
        model.addAttribute("status", "inStock");
        return "listEquipments";
    }

    @GetMapping("/rented")
    public String listRentedEquipments(Model model) throws ParseException {
        List<Receipt> receiptsList = receiptRepository.getActiveReceiptsOldestFirst();
        model.addAttribute("equipmentsList", receiptsList);

        List<Long> differenceInDays = new ArrayList<Long>();

        Date today = new SimpleDateFormat("yyyy-MM-dd").parse(new Date().toString());

        System.out.println("\n\n");
        for (Receipt receipt: receiptsList) {

            long diff = today.getTime() - receipt.getRentDate().getTime();
            System.out.println(today+" "+receipt.getRentDate()+" "+diff+" "+TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            differenceInDays.add(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        }

        model.addAttribute("receiptsList", receiptsList);
        model.addAttribute("differenceInDays", differenceInDays);
        model.addAttribute("action", "Lista de equipos alquilados");
        model.addAttribute("status", "rented");
        return "listEquipments";
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping("/create")
    public String createEquipment(Model model) {
        model.addAttribute("equipment", new Equipment());
        model.addAttribute("action", "Crear equipo");
        model.addAttribute("postAddress", "/equipments/create");
        return "createUpdateViewEquipment";
    }

    /**
     *
     * @param equipment
     * @return
     * @throws IOException
     */
    @PostMapping("/create")
    public String createEquipment(@RequestParam("rawImage") MultipartFile rawImage, Equipment equipment)
            throws IOException {

                // If raw image is not empty, otherwise, the image of the client would be "data:image/png;base64," and I want it to be null if non existent
        if(!rawImage.isEmpty()){
            // To build the final enconded string
            StringBuilder sb = new StringBuilder();
            sb.append("data:image/png;base64,");

            // Append to encoded version of the MultipartFile rawImage to the Stringbuilder
            sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(rawImage.getBytes(), false)));

            // Set the pimage of the client to the string of StringBuilder
            equipment.setBase64Image(sb.toString());
        }

        equipmentServices.save(equipment);
        return "redirect:/equipments/";
    }

    /**
     *
     * @param model
     * @param equipmentId
     * @return
     */
    @GetMapping("/update/{equipmentId}")
    public String updateEquipment(Model model, @PathVariable() Long equipmentId) {
        Equipment equipment = equipmentServices.findById(equipmentId);
        model.addAttribute("action", "Update equipment: " + equipmentId.toString());
        model.addAttribute("postAddress", "/equipments/update");
        model.addAttribute("equipment", equipment);
        // model.addAttribute("update", true);
        return "createUpdateViewEquipment";
    }

    /**
     *
     * @param equipment
     * @return
     * @throws IOException
     */
    @PostMapping("/update")
    public String updateEquipment(@RequestParam("rawImage") MultipartFile rawImage, Equipment equipment)
            throws IOException {

        if(!rawImage.isEmpty()){
            // To build the final enconded string
            StringBuilder sb = new StringBuilder();
            sb.append("data:image/png;base64,");

            // Append to encoded version of the MultipartFile rawImage to the Stringbuilder
            sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(rawImage.getBytes(), false)));

            // Set the pimage of the client to the string of StringBuilder
            equipment.setBase64Image(sb.toString());
        }

        equipmentServices.save(equipment);
        return "redirect:/equipments/";
    }

    /**
     *
     * @param model
     * @param equipmentId
     * @return
     */
    @GetMapping("/view/{equipmentId}")
    public String viewEquipment(Model model, @PathVariable() Long equipmentId) {
        Equipment equipment = equipmentServices.findById(equipmentId);
        model.addAttribute("action", "View equipment: " + equipmentId.toString());
        model.addAttribute("equipment", equipment);
        model.addAttribute("view", true);
        return "createUpdateViewEquipment";
    }

    /**
     *
     * @param equipmentId
     * @return
     */
    @GetMapping("/delete/{equipmentId}")
    public String deleteEquipment(@PathVariable() Long equipmentId) {
        Equipment equipment = equipmentServices.findById(equipmentId);
        equipmentServices.delete(equipment);
        return "redirect:/equipments/";
    }

    /**
     *
     * @param model
     * @param equipmentId
     * @return
     */
    @GetMapping("/rent/{equipmentId}")
    public String rentEquipment(Model model, @PathVariable() Long equipmentId) {
        Equipment equipment = equipmentServices.findById(equipmentId);
        model.addAttribute("action", "View equipment: " + equipmentId.toString());
        model.addAttribute("equipment", equipment);
        model.addAttribute("view", true);
        model.addAttribute("rent", true);
        model.addAttribute("postAddress", "/receipts/create");
        return "createUpdateViewEquipment";
    }
}
