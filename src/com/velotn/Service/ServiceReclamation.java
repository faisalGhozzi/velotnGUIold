package com.velotn.Service;

import com.velotn.Entite.Reclamation;
import com.velotn.IService.IService;
import com.velotn.Utils.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceReclamation implements IService<Reclamation> {
    private Connection con;
    private Statement ste;
    public ServiceReclamation() {
        con = DataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Reclamation t) throws SQLException {
        PreparedStatement pre=con.prepareStatement ("INSERT INTO reclamation (idUser,descriptionR,etat) VALUES (?,?,?) ");
        pre.setInt(1, t.getIdUser());
        pre.setString(2, t.getDescriptionR());
        pre.setInt(3, t.getEtat());
        pre.executeUpdate();
    }



    @Override
    public boolean delete(Reclamation t) throws SQLException {
        PreparedStatement pre=con.prepareStatement ("DELETE FROM reclamation WHERE id=?");
        pre.setInt(1,t.getId());
        pre.executeUpdate();
        return true;
    }

    @Override
    public boolean update(Reclamation t) throws SQLException {
        PreparedStatement pre=con.prepareStatement ("UPDATE reclamation SET idUser=?,descriptionR=?,etat=? WHERE  id=? ");
        pre.setInt(1, t.getIdUser());

        pre.setString(2, t.getDescriptionR());
        pre.setInt(3, t.getEtat());
        pre.setInt(4,t.getId());
        pre.executeUpdate();
        return true;
    }

    @Override
    public List<Reclamation> readAll() throws SQLException {
        List<Reclamation> arr=new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from reclamation");
        while (rs.next()) {
            int id=rs.getInt(1);
            int idUser=rs.getInt(2);
            String descriptionR=rs.getString(3);
            int etat=rs.getInt(4);


            Reclamation r=new Reclamation(id,idUser,descriptionR,etat);
            arr.add(r);
        }
        return arr;
    }

    @Override
    public Reclamation search(Reclamation reclamation) throws SQLException {
        return null;
    }

    @Override
    public List<Reclamation> sortByPrice() throws SQLException {
        return null;
    }

    public void rechercher(int a){
        try {
            String req = "Select* from reclamation where id='"+a+"'" ;
            Statement st = con.prepareStatement(req);
            ResultSet rst;
            st=con.createStatement();
            rst=st.executeQuery(req);
            rst.last();
            int nbrRow=rst.getRow();
            if (nbrRow!=0){
                System.out.println("reclamation trouvé");
            }else{
                System.out.println("reclamation introuvable");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void rechercherParEtat(String a){
        try {
            String req = "Select* from reclamation where etat='"+a+"'";
            Statement st = con.prepareStatement(req);
            ResultSet rst;
            st=con.createStatement();
            rst=st.executeQuery(req);
            rst.last();
            int nbrRow=rst.getRow();
            if (nbrRow!=0){
                System.out.println("reclamation trouvé");
            }else{
                System.out.println("reclamation introuvable");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<Reclamation> readRec(int id) throws SQLException {
        List<Reclamation> arr=new ArrayList<>();
        ste=con.createStatement();
        ResultSet rs=ste.executeQuery("select * from reclamation where idUser="+id);
        while (rs.next()) {
            int idR=rs.getInt(1);
            int idUser=rs.getInt(2);
            String descriptionR=rs.getString(3);
            int etat=rs.getInt(4);


            Reclamation r=new Reclamation(idR,idUser,descriptionR,etat);
            arr.add(r);
        }
        return arr;

    }
}
