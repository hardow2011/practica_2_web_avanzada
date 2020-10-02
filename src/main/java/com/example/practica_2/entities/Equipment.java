package com.example.practica_2.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Equipment {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int amountInExistence;
    private long rentByDayCost;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Receipt> receiptList;
}
