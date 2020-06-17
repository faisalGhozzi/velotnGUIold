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
import com.velotn.Entite.PublicationGroup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Farah GABSI
 */
public class PublicationGroupService {
     Connection con = DataBase.getInstance().getConnection();
    public List<PublicationGroup> getPublicationByGroup(Groups u) {
        String req = "SELECT * FROM publication_group where groupid=? order by datePublication desc";
        ResultSet rs= null;
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, u.getId());
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<PublicationGroup> p = new ArrayList<>();
        try {
            while (rs.next()){
                Groups user = new Groups();
                user.setId(rs.getInt("groupid"));
                p.add(new PublicationGroup(rs.getInt("id"), user.getId(), rs.getString("contenu"), rs.getTimestamp("datePublication")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PublicationGroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

  
    public void ajouterPublication(PublicationGroup p) {
        Calendar c = Calendar.getInstance();
        Timestamp ts = new Timestamp(c.getTimeInMillis());
        try {
            String req = "INSERT INTO publication_group (groupid,contenu, datePublication,IdUser) VALUES (?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1,getGroupe_courant());
             pre.setString(2, p.getContenu());
            pre.setTimestamp(3, ts);
            
            pre.setInt(4, Controller.getUserId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PublicationGroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void modifierPublication(PublicationGroup p) {
        Calendar c = Calendar.getInstance();
        Timestamp ts = new Timestamp(c.getTimeInMillis());
        try {
            String req = "update publication_group set contenu=?, datePublication=? where id=? ";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, p.getContenu());
            
            pre.setTimestamp(2, ts);
            
            pre.setInt(3, p.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PublicationGroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public void supprimerPublication(PublicationGroup p) {
        try {
            String req = "delete from publication_group where id=? ";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, p.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PublicationGroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PublicationGroup getPublicationById(int id) {
        String req = "SELECT * FROM publication_group where id=?";
        ResultSet rs= null;
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(PublicationGroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
        PublicationGroup p = new PublicationGroup();
        try {
            while (rs.next()){
                p.setId(rs.getInt("id"));
                p.setContenu(rs.getString("contenu"));
                p.setDatePublication(rs.getTimestamp("datePublication"));
                p.setGroupid(rs.getInt("groupid"));
                p.setIdUser(rs.getInt("IdUser"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PublicationGroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

      public PublicationGroup getPublicationByIdUser(int id) {
        String req = "SELECT * FROM publication_group where IdUser=?";
        ResultSet rs= null;
        try {
            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(PublicationGroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
        PublicationGroup p = new PublicationGroup();
        try {
            while (rs.next()){
                p.setId(rs.getInt("id"));
                p.setContenu(rs.getString("contenu"));
                p.setDatePublication(rs.getTimestamp("datePublication"));
                p.setGroupid(rs.getInt("groupid"));
                p.setIdUser(rs.getInt("IdUser"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(PublicationGroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    
}
