package com.example.practica_2.repository;

import com.example.practica_2.entities.Equipment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    
}
