package com.example.practica_2.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String identificationCard;
    /**
     * @Lob Specifies that a persistent property or field should be persisted as a
     *      large object to a database-supported large object type.
     */
    @Lob
    private String base64Image;
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Receipt> receiptsList;

    public Client() {
    }

    public String getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(String identificationCard) {
        this.identificationCard = identificationCard;
    }

    public Client(String name, String identificationCard) {
        this.setId(id);
        this.setName(name);
        this.setIdentificationCard(identificationCard);
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
}
