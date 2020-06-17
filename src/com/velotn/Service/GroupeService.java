 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package com.velotn.Service;

import static com.velotn.Ui.Controller.getUserId;
import com.velotn.Entite.Groups;
import com.velotn.Utils.DataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.velotn.Utils.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Farah GABSI
 */
public class GroupeService {

    Connection c = DataBase.getInstance().getConnection();

    public GroupeService() {

    }

    public void ajouterGroupe(Groups p1) {
        String req = "INSERT INTO `groups`(`id`, `nom`,`nbrMembre`, `description`,  `date_de_creation`,  image , `nbr_signal`,`IdUser`,lieux) VALUES (?,?,?,?,?,?,?,?,?)";
        Calendar c1 = Calendar.getInstance();
        Timestamp ts = new Timestamp(c1.getTimeInMillis());
        try {

            PreparedStatement ps = c.prepareStatement(req);
            ps.setInt(1, p1.getId());
            ps.setString(2, p1.getNom());
            ps.setInt(3, 1);
            ps.setString(4, p1.getDescription());

            ps.setTimestamp(5, ts);
            ps.setString(6, p1.getImage());
            ps.setInt(7, 0);
            ps.setInt(8, getUserId());
            ps.setString(9, p1.getLieu());

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Groups> AfficherAllGroups() {
        ObservableList<Groups> ALLgroups = FXCollections.observableArrayList();
        try {
            String query = "select * from groups";
            Statement st = c.createStatement();
            ResultSet rest = st.executeQuery(query);
            while (rest.next()) {
                Groups g = new Groups();
                g.setId(rest.getInt("id"));
                g.setNom(rest.getString("nom"));
                g.setNbrMembre(rest.getInt("nbrMembre"));
                g.setDate_de_creation(rest.getDate("date_de_creation"));
                g.setNbr_signal(rest.getInt("nbr_signal"));
                g.setImage(rest.getString("image"));
                g.setIdUser(rest.getInt("IdUser"));
                g.setDescription(rest.getString("description"));

                System.out.println(g);

                ALLgroups.add(g);

            }

        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ALLgroups;

    }

    public Groups getGroupeById(int idCourant) {

        Groups g = new Groups();
        try {
            String query = "select * from groups where id = ?";
            PreparedStatement ps;

            ps = c.prepareStatement(query);
            ps.setInt(1, idCourant);
            ResultSet rest = ps.executeQuery();

            while (rest.next()) {
                g.setId(rest.getInt("id"));
                g.setNom(rest.getString("nom"));
                g.setNbrMembre(rest.getInt("nbrMembre"));
                g.setDate_de_creation(rest.getDate("date_de_creation"));
                g.setNbr_signal(rest.getInt("nbr_signal"));
                g.setImage(rest.getString("image"));
                g.setIdUser(rest.getInt("IdUser"));
                g.setDescription(rest.getString("description"));
                g.setLieu(rest.getString("lieux"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return g;

    }

    public void supprimerGroupe(int id) {
        String query = "delete from groups where id=?";
        PreparedStatement ps;
        try {
            ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdateGroupe(Groups b) throws SQLException  {

        String reqUp = "UPDATE groups SET nom=? ,description=?,image=? where id=?";
        PreparedStatement pss = c.prepareStatement(reqUp);

        System.out.println(b);
        pss.setString(1, b.getNom());
        pss.setString(2, b.getDescription());
        pss.setString(3, b.getImage());
        pss.setInt(4, b.getId());

        pss.executeUpdate();

    }
     public void UpdateGroupe2(Groups b) throws SQLException  {

        String reqUp = "UPDATE groups SET nom=?, nbrMembre = ? ,description=?, date_de_creation = ? ,image=?, nbr_signal where id=?";
        PreparedStatement pss = c.prepareStatement(reqUp);

        System.out.println(b);
        pss.setString(1, b.getNom());
        pss.setInt(2, b.getNbrMembre());
        pss.setString(3, b.getDescription());
        pss.setDate(4, (Date) b.getDate_de_creation());
        pss.setString(5, b.getImage());
        pss.setInt(6, b.getNbr_signal());
        pss.setInt(7, b.getId());

        pss.executeUpdate();

    }

    public void UpdateGroupe1(Groups b) throws SQLException {
        String reqUp = "UPDATE groups SET nbr_signal=? where id=?";
        PreparedStatement pss = c.prepareStatement(reqUp);
        pss.setInt(1, b.getNbr_signal() + 1);
        pss.setInt(2, b.getGroupe_courant());
        pss.executeUpdate();
        System.out.println(b);
    }

    public ObservableList<Groups> RechercheGroupeParNom(String recherche) {

        ObservableList<Groups> ALLgroups = FXCollections.observableArrayList();
        try {
            String query = "select * from groups WHERE nom LIKE '%" + recherche + "%' ;";
            Statement st = c.createStatement();
            ResultSet rest = st.executeQuery(query);
            while (rest.next()) {
                Groups g = new Groups();

                g.setId(rest.getInt("id"));
                g.setNom(rest.getString("nom"));
                g.setNbrMembre(rest.getInt("nbrMembre"));
                g.setDate_de_creation(rest.getDate("date_de_creation"));
                g.setNbr_signal(rest.getInt("nbr_signal"));
                g.setImage(rest.getString("image"));
                g.setIdUser(rest.getInt("IdUser"));
                g.setDescription(rest.getString("description"));
                ALLgroups.add(g);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ALLgroups;

    }

}
