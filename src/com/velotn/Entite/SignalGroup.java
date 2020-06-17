/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velotn.Entite;

/**
 *
 * @author Farah GABSI
 */
public class SignalGroup {
    private Integer id;
    private String cause;
    private int IdUser;
    private int IdGroup;
    private String nomGroup;

    public String getNomGroup() {
        return nomGroup;
    }

    public void setNomGroup(String nomGroup) {
        this.nomGroup = nomGroup;
    }
     private boolean visible;
    private boolean valider;
  public boolean isValider() {
        return valider;
    }

    public void setValider(boolean valider) {
        this.valider = valider;
    }
     public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public SignalGroup(Integer id, String cause, int IdUser, int IdGroup) {
        this.id = id;
        this.cause = cause;
        this.IdUser = IdUser;
        this.IdGroup = IdGroup;
    }

    public SignalGroup() {
    }

    

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SignalGroup{" + "id=" + id + ", cause=" + cause + ", IdUser=" + IdUser + ", IdGroup=" + IdGroup + '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }

    public int getIdGroup() {
        return IdGroup;
    }

    public void setIdGroup(int IdGroup) {
        this.IdGroup = IdGroup;
    }

   
    
    
}
