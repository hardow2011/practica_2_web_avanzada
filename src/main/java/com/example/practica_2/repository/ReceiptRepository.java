package com.example.practica_2.repository;

import java.util.List;

import com.example.practica_2.entities.Receipt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReceiptRepository extends JpaRepository<Receipt, Long>{

    @Query(value = "SELECT r.* FROM Receipt as r on r.has_been_returned = false order by r.rent_date desc", nativeQuery = true)
    List<Receipt> getActiveReceiptsOldestFirst();

}
