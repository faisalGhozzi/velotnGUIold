package com.velotn.IService;

import com.velotn.Entite.Accessoire;
import com.velotn.Entite.Piece_Rechange;
import com.velotn.Entite.Produit;
import com.velotn.Entite.Velo;

import java.util.List;

public interface IServiceProduit {

    void insertVelo(Velo v);
    void insetPieceR(Piece_Rechange p);
    void insertAccessoire(Accessoire a);
    List<Produit> displayProducts();

    List<Velo> displayVelos();
    List<Piece_Rechange> displayPieces();
    List<Accessoire> displayAccesoires();

    boolean RechercheProduit(Produit p);


    void update(Velo v);
    void update(Piece_Rechange p);
    void update(Accessoire a);

    void delete(Velo v);
    void delete(Piece_Rechange p);
    void delete(Accessoire a);


}
