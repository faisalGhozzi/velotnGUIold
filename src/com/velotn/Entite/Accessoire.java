package com.velotn.Entite;

public class Accessoire extends Produit {
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((marque == null) ? 0 : marque.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Accessoire other = (Accessoire) obj;
        if (marque == null) {
            if (other.marque != null)
                return false;
        } else if (!marque.equals(other.marque))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }
    private String marque;
    private String type;


    public Accessoire(int id, String nomProduit, String description, float prix,int quantite, String marque, String type) {
        super(id, nomProduit, description, prix,quantite);
        this.marque = marque;
        this.type = type;
    }






    public Accessoire(int id, String nomProduit, String description, float prix, int quantite, String img_url) {
        super(id, nomProduit, description, prix, quantite, img_url);
        // TODO Auto-generated constructor stub
    }

    public Accessoire(String nomProduit, String description, float prix, int quantite, String img_url) {
        super(nomProduit, description, prix, quantite, img_url);
        // TODO Auto-generated constructor stub
    }
    public Accessoire(String nomProduit, String description, float prix, int quantite, String marque, String type) {
        super(nomProduit, description, prix, quantite);
        this.marque = marque;
        this.type = type;
    }

    public Accessoire(String nomProduit, String description, float prix, int quantite, String marque, String type,String img_url) {
        super(nomProduit, description, prix, quantite,img_url);
        this.marque = marque;
        this.type = type;
    }

    public Accessoire(int id,String nomProduit, String description, float prix, int quantite, String marque, String type,String img_url) {
        super(id,nomProduit, description, prix, quantite,img_url);
        this.marque = marque;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Accessoire [marque=" + marque + ", type=" + type +" ,"+ super.toString() + "]";
    }
    public String getMarque() {
        return marque;
    }
    public void setMarque(String marque) {
        this.marque = marque;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


}
