package com.velotn.Entite;

public class Reclamation {

    private int id;
    private int idUser;
    private String descriptionR;
    private int etat;


    public Reclamation(int idUser, String descriptionR, int etat) {
        this.idUser = idUser;
        this.descriptionR = descriptionR;
        this.etat = etat;
    }

    public Reclamation(int id, int idUser, String descriptionR, int etat) {
        this.id = id;
        this.idUser = idUser;
        this.descriptionR = descriptionR;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", idUser=" + idUser + ", descriptionR=" + descriptionR + ", etat=" + etat + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDescriptionR() {
        return descriptionR;
    }

    public void setDescriptionR(String descriptionR) {
        this.descriptionR = descriptionR;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
