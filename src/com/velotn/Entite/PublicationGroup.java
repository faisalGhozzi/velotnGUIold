/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velotn.Entite;

import java.util.Date;

/**
 *
 * @author Farah GABSI
 */
public class PublicationGroup {
    private Integer id;
    private int groupid;
    private String contenu;
    private Date datePublication;
    private int IdUser;

    public PublicationGroup(String contenu) {
        this.contenu = contenu;
    }

    public PublicationGroup(Integer id, int groupid, String contenu, Date datePublication, int IdUser) {
        this.id = id;
        this.groupid = groupid;
        this.contenu = contenu;
        this.datePublication = datePublication;
        this.IdUser = IdUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    @Override
    public String toString() {
        return "PublicationGroup{" + "id=" + id + ", groupid=" + groupid + ", contenu=" + contenu + ", datePublication=" + datePublication + ", IdUser=" + IdUser + '}';
    }

    public PublicationGroup(Integer id, String contenu, Date datePublication) {
        this.id = id;
        this.contenu = contenu;
        this.datePublication = datePublication;
    }

    public PublicationGroup(Integer id, int groupid, String contenu, Date datePublication) {
        this.id = id;
        this.groupid = groupid;
        this.contenu = contenu;
        this.datePublication = datePublication;
    }

    public PublicationGroup() {
    }
    
    
}
