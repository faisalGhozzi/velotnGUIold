/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velotn.Service;

import com.velotn.Entite.Groups;
import static com.velotn.Entite.Groups.getGroupe_courant;
import com.velotn.Entite.GroupsMembers;
import com.velotn.Entite.User;
import com.velotn.Utils.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Farah GABSI
 */
public class GroupsMembersService {

    private Connection con = DataBase.getInstance().getConnection();
    GroupeService service = new GroupeService();
     public List<GroupsMembers> getMemberByIdGroup(int idCourant) {

       
        List<GroupsMembers> l=new ArrayList<>();
        try {
            String query = "select * from groups_members where groups_id = ? and confirmation =?";
            PreparedStatement ps;

            ps = con.prepareStatement(query);
            ps.setInt(1, idCourant);
                        ps.setBoolean(2, true);

            ResultSet rest = ps.executeQuery();
            
            while (rest.next()) {
                GroupsMembers g = new GroupsMembers();
                g.setId(rest.getInt("id"));
               g.setGroups_id(rest.getInt("groups_id"));
               g.setIdauthor(rest.getInt("Idauthor"));
               l.add(g);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;

    }
 public List<GroupsMembers> getMemberByIdGroupNon(int idCourant) {

       
        List<GroupsMembers> l=new ArrayList<>();
        try {
            String query = "select * from groups_members where groups_id = ? and confirmation =?";
            PreparedStatement ps;

            ps = con.prepareStatement(query);
            ps.setInt(1, idCourant);
                        ps.setBoolean(2, false);

            ResultSet rest = ps.executeQuery();
            
            while (rest.next()) {
                GroupsMembers g = new GroupsMembers();
                g.setId(rest.getInt("id"));
               g.setGroups_id(rest.getInt("groups_id"));
               g.setIdauthor(rest.getInt("Idauthor"));
               l.add(g);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GroupeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;

    }

    public void increment(Groups ev, User user) {
        try {
           
           
            
           String req = "INSERT INTO groups_members (groups_id,Idauthor,nomGroup,confirmation) VALUES (?,?,?,?)";
            PreparedStatement prs = con.prepareStatement(req);
            prs = con.prepareStatement(req);
            prs.setInt(1, ev.getId());
            prs.setInt(2, user.getId());
            prs.setString(3, service.getGroupeById(getGroupe_courant()).getNom());
            prs.setBoolean(4, false);

            prs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("404");
        }

    }public void increment1(Groups ev, User user) {
        try {
            String req = "UPDATE `groups` SET `nbrMembre`=? WHERE `id`=?";
            PreparedStatement prs = con.prepareStatement(req);
            prs.setInt(1, (ev.getNbrMembre()) + 1);
            prs.setInt(2, ev.getId());
            prs.executeUpdate();
            req = "update groups_members SET confirmation=? where groups_id = ? and Idauthor=?";
            prs = con.prepareStatement(req);
            prs.setInt(2, ev.getId());
            prs.setInt(3, user.getId());
            prs.setBoolean(1, true);

            prs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("404");
        }

    }

    public void decrement(Groups ev, User user) {
        try {
            String req = "UPDATE `groups` SET `nbrMembre`=? WHERE `id`=?";
            PreparedStatement prs = con.prepareStatement(req);
            prs.setInt(1, (ev.getNbrMembre()) - 1);
            prs.setInt(2, ev.getId());
            prs.executeUpdate();
            req = "DELETE FROM groups_members WHERE groups_id = ? and Idauthor=?";
            prs = con.prepareStatement(req);
            prs.setInt(1, ev.getId());
            prs.setInt(2, user.getId());
            prs.executeUpdate();

        } catch (SQLException e) {
            System.out.println("404");
        }

    }
     public void decrement1(Groups ev, User user) {
        try {
           
           
          
            String req = "DELETE FROM groups_members WHERE groups_id = ? and Idauthor=?";
             PreparedStatement prs = con.prepareStatement(req);
            prs = con.prepareStatement(req);
            prs.setInt(1, ev.getId());
            prs.setInt(2, user.getId());
            prs.executeUpdate();

        } catch (SQLException e) {
            System.out.println("404");
        }

    }
     public boolean checkAcceptation(Groups evenement, User user) {
        String query = "select * from groups_members where groups_id = ? and Idauthor = ? and confirmation=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, evenement.getId());
            ps.setInt(2, user.getId());
            ps.setBoolean(3, false);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupsMembersService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }



    public boolean checkParticipation(Groups evenement, User user) {
        String query = "select * from groups_members where groups_id = ? and Idauthor = ? and confirmation=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, evenement.getId());
            ps.setInt(2, user.getId());
            ps.setBoolean(3, true);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupsMembersService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
