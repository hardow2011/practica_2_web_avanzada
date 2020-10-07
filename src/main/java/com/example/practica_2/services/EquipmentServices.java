package com.example.practica_2.services;

import java.util.List;

import javax.transaction.Transactional;

import com.example.practica_2.entities.Equipment;
import com.example.practica_2.repository.EquipmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentServices {
    
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Transactional
    public Equipment save(Equipment equipment){
        equipmentRepository.save(equipment);
        return equipment;
    }

    public List<Equipment> findAll(){
        return equipmentRepository.findAll();
    }

    public Equipment findById(long equipmentId){
        Equipment equipment = equipmentRepository.findById(equipmentId).get();
        return equipment;
    }

    public void delete(Equipment equipment) {
        equipmentRepository.delete(equipment);
    }

}
