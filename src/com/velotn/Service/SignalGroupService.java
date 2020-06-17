/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velotn.Service;

import com.velotn.Utils.DataBase;
import com.velotn.Ui.Controller;
import com.velotn.Entite.Groups;
import static com.velotn.Entite.Groups.getGroupe_courant;
import com.velotn.Entite.SignalGroup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Farah GABSI
 */
public class SignalGroupService {

    GroupeService service = new GroupeService();
    Connection con = DataBase.getInstance().getConnection();

    public void ajouterSignal(SignalGroup s) {
        try {
            String req = "INSERT INTO signalgroup (cause, IdUser,IdGroup,`visible`,  `valider`,`nomGroup`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, s.getCause());
            pre.setInt(2, Controller.getUserId());
            pre.setInt(3, getGroupe_courant());
            pre.setBoolean(4, s.isVisible());
            pre.setBoolean(5, s.isValider());
            pre.setString(6, service.getGroupeById(getGroupe_courant()).getNom());

            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SignalGroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<SignalGroup> getAllSignalsByUser(Groups u) {
        String req = "SELECT * FROM signalgroup where IdGroup=?";
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, u.getId());
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(SignalGroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<SignalGroup> s = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                s.add(new SignalGroup(rs.getInt("id"), rs.getString("cause"), rs.getInt("IdUser"), u.getId()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignalGroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public void UpdateValiderRec(SignalGroup rec) throws SQLException {

        String sql = "update signalgroup set valider=? where id=?";

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setBoolean(1, true);
        pstmt.setInt(2, rec.getId());

        pstmt.executeUpdate();
    }

    public ObservableList<SignalGroup> selectAllReclamation() {
        ObservableList<SignalGroup> recList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM `signalgroup` where valider = false";
            Statement pstmt;
            pstmt = con.createStatement();
            ResultSet rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                SignalGroup fav = new SignalGroup();
                fav.setId(rs.getInt("id"));
                fav.setIdUser(rs.getInt("IdUser"));
                fav.setIdGroup(rs.getInt("idGroup"));

                fav.setVisible(false);
                recList.add(fav);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SignalGroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recList;
    }

    public ObservableList<SignalGroup> selectReclamationNotChecked() {
        ObservableList<SignalGroup> recList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM `signalgroup` where visible = false and valider = false";
            Statement pstmt;

            pstmt = con.createStatement();
            ResultSet rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                SignalGroup fav = new SignalGroup();
                fav.setId(rs.getInt("id"));
                fav.setIdUser(rs.getInt("IdUser"));
                fav.setIdGroup(rs.getInt("idGroup"));
                fav.setCause(rs.getString("cause"));

                fav.setVisible(false);
                recList.add(fav);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SignalGroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recList;
    }

    public ObservableList<SignalGroup> selectReclamationChecked() {
        ObservableList<SignalGroup> recList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * FROM `signalgroup` where visible = true and valider = false";
            Statement pstmt;

            pstmt = con.createStatement();
            ResultSet rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                SignalGroup fav = new SignalGroup();
                fav.setId(rs.getInt("id"));
                fav.setIdUser(rs.getInt("IdUser"));
                fav.setIdGroup(rs.getInt("idGroup"));
                fav.setCause(rs.getString("cause"));

                fav.setVisible(false);
                recList.add(fav);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SignalGroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return recList;
    }

    public void sendMailToUser(SignalGroup r) {
        GroupeService service = new GroupeService();
        UserService service1 = new UserService();
        Runnable runnable
                = new Runnable() {
            public void run() {
                String emailAdress = service1.getUserById(service.getGroupeById(r.getIdGroup()).getIdUser()).getEmail();
                String emailSubject = "Reclamation";
                String emailBody = "Votre groupe nommé " + service.getGroupeById(r.getIdGroup()).getNom() + " a eté signalé à la cause suivante : "
                        + r.getCause() + "  :(";
//                sendMail(emailAdress, emailSubject, emailBody);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void UpdateReclamation(SignalGroup rec) throws SQLException {

        String sql = "update signalGroup set visible=? where id=?";

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setBoolean(1, true);
        pstmt.setInt(2, rec.getId());

        pstmt.executeUpdate();
    }

    public boolean getSignal(int idU, int idG) {
        String req = "SELECT * FROM signalgroup where IdUser=? and IdGroup=?";
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, idU);
            ps.setInt(2, idG);

            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(PublicationGroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
        SignalGroup p = new SignalGroup();
        try {
            while (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setCause(rs.getString("cause"));
                p.setIdGroup(rs.getInt("IdGroup"));
                p.setIdUser(rs.getInt("IdUser"));
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

}
