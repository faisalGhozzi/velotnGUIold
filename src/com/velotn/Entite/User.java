package com.velotn.Entite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author khalil
 */
public class User {
    private int id ;
    private String username;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private Date date_naiss;
    private String tel ;
    private String interets;
    private String image;
    private String pays;
    private String region;
    private Integer code_postal;
    private String rue;

    public User( String username, String email, String password, String nom, String prenom, Date date_naiss, String tel, String interets, String image, String pays, String region, int code_postal,String rue) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naiss = date_naiss;
        this.tel = tel;
        this.image = image;
        this.pays= pays;
        this.region=region;
        this.code_postal=code_postal;
        this.rue=rue;

    }

    public User() {
    }

    public User(int id, String username, String email, String password, String nom, String prenom, Date date_naiss, String tel, String interets, String image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naiss = date_naiss;
        this.tel = tel;
        this.interets = interets;
        this.image = image;
    }
    public User(int id )
    {
        this.id= id ;
    }

    public User(int id_user, String username) {
        this.id = id_user;
        this.username = username;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDate_naiss(Date date_naiss) {
        this.date_naiss = date_naiss;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setInterets(String interets) {
        this.interets = interets;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDate_naiss() {
        return date_naiss;
    }

    public String getTel() {
        return tel;
    }

    public String getInterets() {
        return interets;
    }

    public String getImage() {
        return image;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(Integer code_postal) {
        this.code_postal = code_postal;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }


    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", date_naiss=" + date_naiss + ", tel=" + tel + ", interets=" + interets + ", image=" + image + " , pays=" + pays + " ,region=" + region + " ,code_postal=" + code_postal + " ,rue=" + rue + '}';
    }

    public static User createUser(ResultSet rs){
        User user = new User();
        try {
            user.setId(rs.getInt("id"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setImage(rs.getString("image"));
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
