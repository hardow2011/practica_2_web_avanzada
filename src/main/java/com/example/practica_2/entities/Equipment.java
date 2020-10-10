package com.example.practica_2.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Equipment implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int amountInExistence;
    private long rentByDayCost;
    @OneToMany(mappedBy = "equipment")
    private List<Receipt> receiptsList;
    @Lob
    private String base64Image;

    public Equipment() {
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public List<Receipt> getReceiptsList() {
        return receiptsList;
    }

    public void setReceiptsList(List<Receipt> receiptsList) {
        this.receiptsList = receiptsList;
    }

    public Equipment(long id, String name, int amountInExistence, long rentByDayCost) {
        this.setId(id);
        this.setName(name);
        this.setAmountInExistence(amountInExistence);
        this.setRentByDayCost(rentByDayCost);
    }

    /**
     * Substract from inventory when borrowed
     * @param quantity
     * @return
     */
    public int substractLentItems(int quantity){
        int NewAmountInExistence = amountInExistence - quantity;
        this.setAmountInExistence(NewAmountInExistence);
        return NewAmountInExistence;
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
