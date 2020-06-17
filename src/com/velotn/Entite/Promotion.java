package com.velotn.Entite;

/**
 *
 * @author khalil
 */
public class Promotion {
    private int id ;
    private String type ;
    private float taux ;
    private Produit pro ;

    public Promotion(int id, String type, float taux) {
        this.id = id;
        this.type = type;
        this.taux = taux;
    }
    public Promotion(int id, String type, float taux ,Produit pr ) {
        this.id = id;
        this.type = type;
        this.taux = taux;
        this.pro= pr ;
    }
    public Promotion( String type, float taux ,Produit pr ) {

        this.type = type;
        this.taux = taux;
        this.pro= pr ;
    }


    public Produit getPro() {
        return pro;
    }

    public void setPro(Produit pro) {
        this.pro = pro;
    }


    public Promotion(String type, float taux) {
        this.type = type;
        this.taux = taux;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getTaux() {
        return taux;
    }

    public void setTaux(float taux) {
        this.taux = taux;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", type=" + type + ", taux=" + taux + "," + pro + '}';
    }
}
