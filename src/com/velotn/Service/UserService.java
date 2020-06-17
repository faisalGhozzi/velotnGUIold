/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velotn.Service;

import com.velotn.Entite.User;
import com.velotn.Utils.DataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Farah GABSI
 */
public class UserService {

    private Connection con = DataBase.getInstance().getConnection();
    ;
    private Statement ste;

    public UserService() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUserById(int id) {
        User u = null;
        try {
            String req = "select * from fos_user where id=?";

            PreparedStatement ps = con.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new User(rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("date_naiss"), rs.getString("tel"), rs.getString("interets"), rs.getString("image"), rs.getString("pays"), rs.getString("region"), rs.getInt("code_postal"), rs.getString("rue"));
                u.setId(rs.getInt("id"));
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void modifierUser(User u) {
        try {
            String req = "update fos_user set nom=?, prenom=?, date_naiss=?, pays=?, region=?, tel=?, code_postal=? , rue=? , username=? , password=? , interets=? , image=? , email=? where id=? ";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(5, u.getNom());
            pre.setString(6, u.getPrenom());
            pre.setDate(7, (Date) u.getDate_naiss());
            pre.setString(4, u.getPassword());
            pre.setString(11, u.getPays());
            pre.setString(12, u.getRegion());
            pre.setString(14, u.getRue());
            pre.setString(8, u.getTel());
            pre.setInt(13, u.getCode_postal());
            pre.setString(2, u.getUsername());
            pre.setString(3, u.getEmail());
            pre.setString(9, u.getInterets());
            pre.setString(10, u.getImage());

            //pre.setInt(15, u.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUserByuserName(String username) {
        try {
            String req = "select * from fos_user where username=?";
            User u = null;
            PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u = new User(rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("date_naiss"), rs.getString("tel"), rs.getString("interets"), rs.getString("image"), rs.getString("pays"), rs.getString("region"), rs.getInt("code_postal"), rs.getString("rue"));
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void modifierUserPhoto(User u) {
        try {
            String req = "update fos_user set image=?,nom=?,prenom=? where id=? ";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setString(1, u.getImage());
            pre.setString(2, u.getNom());
            pre.setString(3, u.getPrenom());

            pre.setInt(4, u.getId());
            pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerUser (int id) {
        try {
            PreparedStatement pt = con.prepareStatement("delete from fos_user where id=?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
