package com.velotn.Service;

import com.velotn.Entite.Evenement;
import com.velotn.IService.IService;
import com.velotn.Utils.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceEvenement implements IService<Evenement> {

    private Connection con;
    private Statement ste;

    public ServiceEvenement() {
        con = DataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Evenement t) throws SQLException {

        PreparedStatement pre=con.prepareStatement ("INSERT INTO evenement (nom,heure,date,description) VALUES (?,?,?,?) ");
        pre.setString(1, t.getNom());

        pre.setString(2, t.getHeure().toString());
        pre.setDate(3, t.getDate());
        pre.setString(4, t.getDescription());
        pre.executeUpdate();


    }

    @Override
    public boolean delete(Evenement t) throws SQLException {
        PreparedStatement pre=con.prepareStatement ("DELETE FROM evenement WHERE id=?");
        pre.setInt(1,t.getId());
        pre.executeUpdate();
        return true;

    }

    @Override
    public boolean update(Evenement t) throws SQLException {
        PreparedStatement pre=con.prepareStatement ("UPDATE evenement SET nom=?,heure=?,date=?,description=? WHERE  id=? ");
        pre.setString(1, t.getNom());
        pre.setString(2, t.getHeure().toString());
        pre.setDate(3, t.getDate());
        pre.setString(4, t.getDescription());
        pre.setInt(5,t.getId());
        pre.executeUpdate();
        return true;
    }

    @Override
    public List<Evenement> readAll() throws SQLException {
        List<Evenement> arr=new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from evenement");
        while (rs.next()) {
            int id=rs.getInt(1);
            String nom=rs.getString("nom");
            Time heure=rs.getTime(3);
            Date  date=rs.getDate(4);
            String description=rs.getString(5);

            Evenement e=new Evenement(id,nom, heure, date, description);
            arr.add(e);
        }
        return arr;
    }

    @Override
    public Evenement search(Evenement evenement) throws SQLException {
        return null;
    }

    @Override
    public List<Evenement> sortByPrice() throws SQLException {
        return null;
    }

    public void rechercher(int a){
        try {
            String req = "Select* from evenement where id='"+a+"'" ;
            Statement st = con.prepareStatement(req);
            ResultSet rst;
            st=con.createStatement();
            rst=st.executeQuery(req);
            rst.last();
            int nbrRow=rst.getRow();
            if (nbrRow!=0){
                System.out.println("evenement trouvé");
            }else{
                System.out.println("evenement introuvable");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void rechercherParNom(String a){
        try {
            String req = "Select* from evenement where nom='"+a+"'";
            Statement st = con.prepareStatement(req);
            ResultSet rst;
            st=con.createStatement();
            rst=st.executeQuery(req);
            rst.last();
            int nbrRow=rst.getRow();
            if (nbrRow!=0){
                System.out.println("evenement trouvé");
            }else{
                System.out.println("evenement introuvable");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
