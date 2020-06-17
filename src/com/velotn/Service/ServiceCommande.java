package com.velotn.Service;

import com.velotn.Entite.Commande;
import com.velotn.IService.IService;
import com.velotn.Utils.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCommande implements IService<Commande> {

    private Connection con;
    private Statement ste;

    public ServiceCommande() {
        con = DataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Commande commande) throws SQLException {
        PreparedStatement pre = con.prepareStatement("INSERT INTO `commande` (`date`, `prix`, `user_id`) VALUES (?,?,?);");
        pre.setObject(1,commande.getDate());
        pre.setDouble(2,commande.getPrix());
        pre.setInt(3,commande.getUserid());
        pre.executeUpdate();
        //addingItems
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT LAST_INSERT_ID() as id from commande");
        int id = 0;
        while(rs.next()){
            id = rs.getInt(1);
        }
        PreparedStatement pre2 = con.prepareStatement("INSERT INTO `listprod` (`command_id`,`product`,`qte`,`prix`) VALUES (?,?,?,?);");
        while(!commande.getPaniers().isEmpty()){
            pre2.setInt(1,id);
            pre2.setInt(2,commande.getPaniers().get(0).getProduit_id());
            System.out.println(commande.getPaniers().get(0).getQte());
            System.out.println(commande.getPaniers().get(0).getPrix_total());
            pre2.setInt(3,commande.getPaniers().get(0).getQte());
            pre2.setDouble(4,commande.getPaniers().get(0).getPrix_total());
            commande.getPaniers().remove(0);
            pre2.executeUpdate();
        }
    }

    @Override
    public boolean delete(Commande commande) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `commande` WHERE id = ?");
        pre.setInt(1,commande.getId());
        pre.executeUpdate();
        return true;
    }

    @Override
    public boolean update(Commande commande) throws SQLException {
        /*PreparedStatement pre = con.prepareStatement("UPDATE `commande` SET insert changes here where id = ?");
        pre.setInt(2,commande.getId());
        pre.executeUpdate();*/
        return true;
    }

    @Override
    public List<Commande> readAll() throws SQLException {
        List<Commande> arr=new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM commande");
        while(rs.next()){
            int id = rs.getInt(1);
            Date date =(Date) rs.getObject(2);
            double prix = rs.getDouble(3);
            int userid = rs.getInt(4);
            Commande don = new Commande(id,date.toLocalDate(),prix,userid);
            arr.add(don);
        }
        return arr;
    }

    @Override
    public Commande search(Commande commande) throws SQLException {
        return null;
    }

    @Override
    public List<Commande> sortByPrice() throws SQLException {
        return null;
    }
}
