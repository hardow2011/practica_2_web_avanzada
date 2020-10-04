package com.example.practica_2.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Equipment implements Serializable{
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int amountInExistence;
    private long rentByDayCost;

    public Equipment(long id, String name, int amountInExistence, long rentByDayCost) {
        this.setId(id);
        this.setName(name);
        this.setAmountInExistence(amountInExistence);
        this.setRentByDayCost(rentByDayCost);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountInExistence() {
        return amountInExistence;
    }

    public void setAmountInExistence(int amountInExistence) {
        this.amountInExistence = amountInExistence;
    }

    public long getRentByDayCost() {
        return rentByDayCost;
    }

    public void setRentByDayCost(long rentByDayCost) {
        this.rentByDayCost = rentByDayCost;
    }
}
