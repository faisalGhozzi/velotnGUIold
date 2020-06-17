package com.velotn.Entite;

public class Produit {

    protected int id;
    protected String nomProduit;
    protected String description;
    protected float prix;
    protected int quantite;
    protected String img_url;


    public String getImg_url() {
        return img_url;
    }

    public Produit(int id) {
        this.id = id;
    }
    public Produit(int id,String img_url) {
        this.id = id;
        this.img_url = img_url ;
    }
    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Produit(int id, String nomProduit, String description, float prix, int quantite, String img_url) {
        super();
        this.id = id;
        this.nomProduit = nomProduit;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.img_url = img_url;
    }

    public Produit(String nomProduit, String description, float prix, int quantite, String img_url) {
        super();
        this.nomProduit = nomProduit;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.img_url = img_url;
    }

    public Produit(int id, String nomProduit, String description, float prix, int quantite) {
        super();
        this.id = id;
        this.nomProduit = nomProduit;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Produit(String nomProduit, String description, float prix, int quantite) {
        super();
        this.nomProduit = nomProduit;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
    }


    //User


    public int getQuantite() {
        return quantite;
    }


    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }


    public Produit(int id, String nomProduit, String description, float prix) {
        super();
        this.id = id;
        this.nomProduit = nomProduit;
        this.description = description;
        this.prix = prix;
    }


    public Produit(String nomProduit, String description, float prix) {
        super();
        this.nomProduit = nomProduit;
        this.description = description;
        this.prix = prix;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + id;
        result = prime * result + ((nomProduit == null) ? 0 : nomProduit.hashCode());
        result = prime * result + Float.floatToIntBits(prix);
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produit other = (Produit) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (id != other.id)
            return false;
        if (nomProduit == null) {
            if (other.nomProduit != null)
                return false;
        } else if (!nomProduit.equals(other.nomProduit))
            return false;
        if (Float.floatToIntBits(prix) != Float.floatToIntBits(other.prix))
            return false;
        return true;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nomProduit='" + nomProduit + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", quantite=" + quantite +
                ", img_url='" + img_url + '\'' +
                '}';
    }
}


