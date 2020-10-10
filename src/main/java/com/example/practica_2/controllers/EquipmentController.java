package com.example.practica_2.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.practica_2.entities.Client;
import com.example.practica_2.entities.Equipment;
import com.example.practica_2.entities.Receipt;
import com.example.practica_2.services.ClientServices;
import com.example.practica_2.services.EquipmentServices;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("equipments")
public class EquipmentController {

    @Autowired
    private EquipmentServices equipmentServices;

    @GetMapping({ "/", "" })
    public String listEquipments(Model model) {
        return "redirect:/equipments/inStock";
    }

    @GetMapping("/inStock")
    public String listEquipmentsInStock(Model model) {
        List<Equipment> equipmentsList = equipmentServices.findAll();
        model.addAttribute("equipmentsList", equipmentsList);
        model.addAttribute("action", "Lista de equipos");
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
