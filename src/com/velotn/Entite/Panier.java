package com.velotn.Entite;

public class Panier {
    private int id;
    private int produit_id;
    private int qte;
    private double prix_unitaire;
    private double prix_total;
    private int user_id;
    private String url;

    public Panier(int id) {
        this.id = id;
    }

    public Panier(int id, int produit_id, int qte, double prix_unitaire, double prix_total, int user_id, String url) {
        this.id = id;
        this.produit_id = produit_id;
        this.qte = qte;
        this.prix_unitaire = prix_unitaire;
        this.prix_total = prix_total;
        this.user_id = user_id;
        this.url = url;
    }

    public Panier(int id, int qte) {
        this.id = id;
        this.qte = qte;
    }

    public Panier(int id, int produit_id, int qte, double prix_unitaire, int user_id) {
        this.id = id;
        this.produit_id = produit_id;
        this.qte = qte;
        this.prix_unitaire = prix_unitaire;
        this.prix_total = qte*prix_unitaire;
        this.user_id = user_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Panier(int produit_id, int qte, double prix_unitaire, int user_id) {
        this.produit_id = produit_id;
        this.qte = qte;
        this.prix_unitaire = prix_unitaire;
        this.prix_total = qte*prix_unitaire;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public double getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public double getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(double prix_total) {
        this.prix_total = prix_total;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
