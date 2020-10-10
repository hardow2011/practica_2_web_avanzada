package com.example.practica_2.repository;

import java.util.List;

import com.example.practica_2.entities.Equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    @Query(value = "SELECT e.* FROM Equipment as e join receipt as r on r.equipment_id = e.id and r.has_been_returned = false order by r.rent_date desc", nativeQuery = true)
    List<Equipment> getEquipmentsOldestFirst();

}
