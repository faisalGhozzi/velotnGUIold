/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velotn.Entite;

import java.util.Date;

/**
 *
 * @author Farah GABSI
 */
public class Groups {
        private static int Groupe_courant;

    public static int getGroupe_courant() {
        return Groupe_courant;
    }

    public static void setGroupe_courant(int Groupe_courant) {
        Groups.Groupe_courant = Groupe_courant;
    }

     private int id;
    private String nom;
    private int nbrMembre;
    private String Description;
    private Date date_de_creation;
    private String image;
    private int nbr_signal;
    private int IdUser;
    private String lieu;

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Groups(String nom, String Description, String image) {
        this.nom = nom;
        this.Description = Description;
        this.image = image;
    }
     public Groups(String nom, String Description) {
        this.nom = nom;
        this.Description = Description;
        
    }

    public Groups(String nom, int nbrMembre, String Description,  String image, int nbr_signal) {
        this.nom = nom;
        this.nbrMembre = nbrMembre;
        this.Description = Description;
        this.image = image;
        this.nbr_signal = nbr_signal;
    }
     public Groups(){}

   
     

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

    public int getNbrMembre() {
        return nbrMembre;
    }

    public void setNbrMembre(int nbrMembre) {
        this.nbrMembre = nbrMembre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getDate_de_creation() {
        return date_de_creation;
    }

    public void setDate_de_creation(Date date_de_creation) {
        this.date_de_creation = date_de_creation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbr_signal() {
        return nbr_signal;
    }

    public void setNbr_signal(int nbr_signal) {
        this.nbr_signal = nbr_signal;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    @Override
    public String toString() {
        return "Groups{" + "id=" + id + ", nom=" + nom + ", nbrMembre=" + nbrMembre + ", Description=" + Description + ", date_de_creation=" + date_de_creation + ", image=" + image + ", nbr_signal=" + nbr_signal + ", IdUser=" + IdUser + '}';
    }
    
    
}
