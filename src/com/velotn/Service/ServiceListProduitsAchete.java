package com.velotn.Service;

import com.velotn.Entite.Don;
import com.velotn.Entite.ListProduitsAchete;
import com.velotn.IService.IService;
import com.velotn.Utils.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceListProduitsAchete implements IService<ListProduitsAchete> {

    private Connection con;
    private Statement ste;

    public ServiceListProduitsAchete() {
        con = DataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(ListProduitsAchete listProduitsAchete) throws SQLException {

    }

    @Override
    public boolean delete(ListProduitsAchete listProduitsAchete) throws SQLException {
        return false;
    }

    @Override
    public boolean update(ListProduitsAchete listProduitsAchete) throws SQLException {
        return false;
    }

    @Override
    public List<ListProduitsAchete> readAll() throws SQLException {
        List<ListProduitsAchete> arr=new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM listprod");
        while(rs.next()){
            int id = rs.getInt(1);
            int comande_id = rs.getInt(2);
            int product = rs.getInt(3);
            int qte = rs.getInt(4);
            Double prixTotal = rs.getDouble(5);
            ListProduitsAchete listProduitsAchete = new ListProduitsAchete(id,comande_id,product,qte,prixTotal);
            arr.add(listProduitsAchete);
        }
        return arr;
    }

    public List<ListProduitsAchete> readCertain(int command_id)throws  SQLException{
        List<ListProduitsAchete> arr=new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM listprod where command_id ="+command_id);
        while(rs.next()){
            int id = rs.getInt(1);
            int command_idd = rs.getInt(2);
            int product = rs.getInt(3);
            int qte = rs.getInt(4);
            double prixTotal = rs.getDouble(5);
            ListProduitsAchete listProduitsAchete = new ListProduitsAchete(id,command_idd,product,qte,prixTotal);
            arr.add(listProduitsAchete);
        }
        return arr;
    }

    @Override
    public ListProduitsAchete search(ListProduitsAchete listProduitsAchete) throws SQLException {
        return null;
    }

    @Override
    public List<ListProduitsAchete> sortByPrice() throws SQLException {
        return null;
    }
}
