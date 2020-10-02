package com.example.practica_2.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private List<User> usersList;
}
