package com.example.practica_2.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    /**
     * @Lob Specifies that a persistent
     * property or field should be persisted
     * as a large object to a database-supported
     * large object type.
     */
    @Lob
    private String base64Image;
    @OneToMany
    private List<Receipt> receiptsList;
}
