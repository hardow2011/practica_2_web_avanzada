package com.example.practica_2.repository;

import java.util.List;

import com.example.practica_2.entities.Receipt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReceiptRepository extends JpaRepository<Receipt, Long>{

    @Query(value = "SELECT * FROM Receipt as r", nativeQuery = true)
    List<Receipt> getActiveReceiptsOldestFirst();

}
