package com.example.practica_2.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Receipt {
    @Id
    @GeneratedValue
    private long id;
    @Temporal(TemporalType.DATE)
    private Date rentDate;
    @Temporal(TemporalType.DATE)
    private Date promisedReturnDate;
    @ManyToMany
    private List<Equipment> equipmentsList;
    private int quantity;
    @ManyToOne
    private Client client;
    private boolean hasBeenReturned;
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    public Receipt(long id, Date rentDate, Date promisedReturnDate, Client client) {
        this.setId(id);
        this.setRentDate(rentDate);
        this.setPromisedReturnDate(promisedReturnDate);
        this.setClient(client);
        this.setHasBeenReturned(false);
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

    public List<Equipment> getEquipmentsList() {
        return equipmentsList;
    }

    public void setEquipmentsList(List<Equipment> equipmentsList) {
        this.equipmentsList = equipmentsList;
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

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
