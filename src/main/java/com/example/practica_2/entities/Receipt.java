package com.example.practica_2.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.example.practica_2.services.EquipmentServices;

@Entity
public class Receipt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.DATE)
    private Date rentDate;
    @Temporal(TemporalType.DATE)
    private Date promisedReturnDate;
    @ManyToOne
    private Equipment equipment;
    private int quantity;
    @ManyToOne
    private Client client;
    private boolean hasBeenReturned;
    @Temporal(TemporalType.DATE)
    private Date returnedDate;
    private double totalCost;

    public Receipt() {
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Receipt(Date rentDate, Date promisedReturnDate, Equipment equipment, int quantity, Client client) {
        this.setId(id);
        this.setRentDate(rentDate);
        this.setEquipment(equipment);
        this.setQuantity(quantity);
        this.setPromisedReturnDate(promisedReturnDate);
        this.setClient(client);
        this.setHasBeenReturned(false);
    }

    public int recoverPartialItems(int quantityToBeRecovered){
        int newQuantity = quantity - quantityToBeRecovered;
        this.setQuantity(newQuantity);
        return newQuantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getPromisedReturnDate() {
        return promisedReturnDate;
    }

    public void setPromisedReturnDate(Date promisedReturnDate) {
        this.promisedReturnDate = promisedReturnDate;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public boolean isHasBeenReturned() {
        return hasBeenReturned;
    }

    public void setHasBeenReturned(boolean hasBeenReturned) {
        this.hasBeenReturned = hasBeenReturned;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }
}
