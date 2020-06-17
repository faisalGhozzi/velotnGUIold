package com.velotn.Test;

import com.velotn.Entite.Commande;
import com.velotn.Entite.Don;
import com.velotn.Service.ServiceCommande;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestCommande {
    /*public static void main(String[] args) {
        ServiceCommande serviceCommande = new ServiceCommande();
        List<Integer> p = new ArrayList<Integer>();
        p.add(2);
        p.add(25);
        p.add(6);
        Commande c1 = new Commande("20/01/2020",123,250.0,p,2);
        Commande c2 = new Commande("02/01/2020",2,50.5,p,3);
        Commande c3 = new Commande("20/01/2020",13,250.0,p,2);
        Commande c4 = new Commande("02/01/2020",20,50.5,p,3);
        Commande sup = new Commande(15);
        Commande up = new Commande(15,500);
        try{
            //serviceCommande.ajouter(c1);
            //serviceCommande.ajouter(c2);
            //serviceCommande.ajouter(c3);
            //serviceCommande.ajouter(c4);
            //serviceCommande.update(up);
            serviceCommande.delete(sup);
            List<Commande> listCommande = serviceCommande.readAll();
            System.out.println(listCommande);
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }*/
}
