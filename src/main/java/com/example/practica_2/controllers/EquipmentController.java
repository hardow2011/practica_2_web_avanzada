package com.example.practica_2.controllers;

import java.util.List;

import com.example.practica_2.entities.Equipment;
import com.example.practica_2.services.EquipmentServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("equipments")
public class EquipmentController {

    @Autowired
    private EquipmentServices equipmentServices;

    @GetMapping({"/", ""})
    public String listEquipments(Model model) {
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
     */
    @PostMapping("/create")
    public String createEquipment(Equipment equipment){

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
        model.addAttribute("action", "Update equipment: "+equipmentId.toString());
        model.addAttribute("postAddress", "/equipments/update");
        model.addAttribute("equipment", equipment);
        // model.addAttribute("update", true);
        return "createUpdateViewEquipment";
    }

    /**
     *
     * @param equipment
     * @return
     */
    @PostMapping("/update")
    public String updateEquipment(Equipment equipment) {

        equipmentServices.save(equipment);
        return "redirect:/equipments/";
    }

    @GetMapping("/view/{equipmentId}")
    public String viewEquipment(Model model, @PathVariable() Long equipmentId) {
        Equipment equipment = equipmentServices.findById(equipmentId);
        model.addAttribute("action", "View equipment: "+equipmentId.toString());
        model.addAttribute("equipment", equipment);
        model.addAttribute("view", true);
        return "createUpdateViewEquipment";
    }

    @GetMapping("/delete/{equipmentId}")
    public String deleteEquipment(@PathVariable() Long equipmentId) {
        Equipment equipment = equipmentServices.findById(equipmentId);
        equipmentServices.delete(equipment);
        return "redirect:/equipments/";
    }

}
