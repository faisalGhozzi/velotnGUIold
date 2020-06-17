package com.velotn.Service;

import com.velotn.Entite.Panier;
import com.velotn.IService.IService;
import com.velotn.Utils.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePanier implements IService<Panier> {

    private Connection con;
    private Statement ste;

    public ServicePanier() {
        con = DataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Panier panier) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT into `panier` (`produit_id`,`qte`,`prix_unitaire`,`prix_total`,`user_id`) VALUES (?,?,?,?,?);");
        pre.setInt(1,panier.getProduit_id());
        pre.setInt(2,panier.getQte());
        pre.setDouble(3,panier.getPrix_unitaire());
        pre.setDouble(4,panier.getQte()*panier.getPrix_unitaire());
        pre.setInt(5,panier.getUser_id());
        pre.executeUpdate();
    }

    @Override
    public boolean delete(Panier panier) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `panier` WHERE id = ?");
        pre.setInt(1,panier.getId());
        pre.executeUpdate();
        return true;
    }

    @Override
    public boolean update(Panier panier) throws SQLException {
        PreparedStatement pre = con.prepareStatement("UPDATE `panier` SET qte = ?, prix_total = qte*prix_unitaire where id = ?");
        pre.setInt(1,panier.getQte());
        pre.setInt(2,panier.getId());
        pre.executeUpdate();
        return true;
    }

    @Override
    public List readAll() throws SQLException {
        List<Panier> arr=new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM panier inner join produits on panier.produit_id = produits.id");
        while(rs.next()){
            int id = rs.getInt(1);
            int produit_id = rs.getInt(2);
            int qte = rs.getInt(3);
            Double prix_unitaire = rs.getDouble(4);
            Double prix_total = rs.getDouble(5);
            int user_id = rs.getInt(6);
            String url = rs.getString(8);
            Panier panier = new Panier(id,produit_id,qte,prix_unitaire,prix_total,user_id,url);
            arr.add(panier);
        }
        return arr;
    }

    @Override
    public Panier search(Panier panier) throws SQLException {
        return null;
    }

    @Override
    public List sortByPrice() throws SQLException {
        return null;
    }
}
