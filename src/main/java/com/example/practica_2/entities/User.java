package com.example.practica_2.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String password;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Role> rolesList;
}
