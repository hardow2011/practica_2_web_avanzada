package com.example.practica_2.services;

import javax.transaction.Transactional;

import com.example.practica_2.entities.Receipt;
import com.example.practica_2.repository.ReceiptRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptServices {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Transactional
    public Receipt save(Receipt receipt){
        receiptRepository.save(receipt);
        return receipt;
    }

}
