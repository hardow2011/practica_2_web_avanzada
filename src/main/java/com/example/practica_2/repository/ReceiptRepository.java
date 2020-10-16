package com.example.practica_2.repository;

import java.util.List;

import com.example.practica_2.entities.Receipt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReceiptRepository extends JpaRepository<Receipt, Long>{

    @Query(value = "SELECT r.* FROM RECEIPT AS r WHERE r.has_been_returned = FALSE ORDER BY r.rent_date ASC", nativeQuery = true)
    List<Receipt> getActiveReceiptsOldestFirst();

    @Query(value = "SELECT r.* FROM RECEIPT AS r WHERE r.has_been_returned = TRUE ORDER BY r.rent_date ASC", nativeQuery = true)
    List<Receipt> getInactiveReceiptsOldestFirst();

}
