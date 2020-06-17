package com.velotn.Entite;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author khalil
 */
public class Location {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy",Locale.ENGLISH) ;
    private int id ;
    private String date_debut  ;
    private String date_fin  ;
    private float  prixtotal ;
    private Velo velo ;
    private User user ;
    public Location(User u , String date_debut , String date_fin , float prixtotal, Velo v ) {
        this.user= u ;
        this.prixtotal = prixtotal;
        this.date_debut = date_debut;
        this.date_fin = date_fin ;
        this.velo = v;

    }
    public Location(User u , String date_debut , String date_fin , Velo v ) {
        this.user= u ;

        this.date_debut = date_debut;
        this.date_fin = date_fin ;
        this.velo = v;

    }

    public Location()
    {

    }

    public Location(int id ,String date_debut , String date_fin , float prixtotal, Velo v ) {
        this.id = id ;
        this.prixtotal = prixtotal;
        this.date_debut = date_debut;
        this.date_fin = date_fin ;
        this.velo = v;

    }

    public Location(User u , int id ,String date_debut , String date_fin , float prixtotal, Velo v ) {
        this.id = id ;
        this.prixtotal = prixtotal;
        this.date_debut = date_debut;
        this.date_fin = date_fin ;
        this.velo = v;
        this.user=u ;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Velo getVelo() {
        return velo;
    }

    public void setVelo(Velo velo) {
        this.velo = velo;
    }

    public Location(String date_debut , String date_fin , float prixtotal) {

        this.prixtotal = prixtotal;
        this.date_debut = date_debut;
        this.date_fin = date_fin ;
    }

    @Override
    public String toString() {
        return "Location{" + "sdf=" + sdf + ", id=" + id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", prixtotal=" + prixtotal + ", velo=" + velo + ", user=" + user + '}';
    }





    public void setId(int id) {
        this.id = id;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setPrixtotal(float prixtotal) {
        this.prixtotal = prixtotal;
    }

    public int getId() {
        return id;
    }

    public float getPrixtotal() {
        return prixtotal;
    }
}
