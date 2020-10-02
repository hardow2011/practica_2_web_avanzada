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
}
