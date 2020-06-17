package com.velotn.Entite;

public class ListProduitsAchete {
    private int id;
    private int commande_id;
    private int produit;
    private int qte;
    private double prix;

    public ListProduitsAchete(int id, int commande_id, int produit, int qte, double prix) {
        this.id = id;
        this.commande_id = commande_id;
        this.produit = produit;
        this.qte = qte;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "ListProduitsAchete{" +
                "id=" + id +
                ", commande_id=" + commande_id +
                ", produit=" + produit +
                ", qte=" + qte +
                ", prix=" + prix +
                '}';
    }
}
