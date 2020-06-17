package com.velotn.Entite;

public class Velo extends Produit {

    private String marque;
    //select marque, type from velos v INNER JOIN produits p on v.id=p.id
    private String type;

    public Velo(int id) {
        super(id);
    }

    public Velo(int id, String  marque, String type,String img_url) {
        super(id,img_url);
        this.marque = marque ;
        this.type = type ;

    }

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
        Velo other = (Velo) obj;
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



    @Override
    public String toString() {
        return "Velo [marque=" + marque + ", type=" + type + ", " + super.toString() + "]";
    }



    public Velo(int id, String nomProduit, String description, float prix, int quantite, String img_url) {
        super(id, nomProduit, description, prix, quantite, img_url);

    }



    public Velo(int id, String nomProduit, String description, float prix,int quantite, String marque, String type,String img_url) {
        super(id, nomProduit, description, prix,quantite,img_url);
        this.setMarque(marque);
        this.setType(type);
    }
    public Velo(String nomProduit, String description, float prix, int quantite, String marque, String type,String img_url) {
        super(nomProduit, description, prix,quantite,img_url);
        this.setMarque(marque);
        this.setType(type);
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
