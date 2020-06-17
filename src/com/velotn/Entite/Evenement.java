package com.velotn.Entite;

import java.sql.Date;
import java.sql.Time;

public class Evenement {

    private int id;
    private String nom;
    private Time heure;
    private Date date;
    private String description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "evenement{" + "id=" + id + ", nom=" + nom + ", heure=" + heure + ", date=" + date + ", description=" + description + '}';
    }

    public Evenement(int id, String nom, Time heure, Date date, String description) {
        this.id = id;
        this.nom = nom;
        this.heure = heure;
        this.date = date;
        this.description = description;
    }

    public Evenement(String nom, Time heure, Date date, String description) {
        this.nom = nom;
        this.heure = heure;
        this.date = date;
        this.description = description;
    }
}
