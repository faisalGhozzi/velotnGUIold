package com.velotn.Test;

import com.velotn.Entite.Don;
import com.velotn.Service.ServiceDon;

import java.sql.SQLException;
import java.util.List;

public class TestDon {
    public static void main(String[] args) {
        ServiceDon serviceDon = new ServiceDon();

        Don d1 = new Don(200.0,1);
        Don d2 = new Don(350.0,2);
        Don sup = new Don(5);
        Don up = new Don(3,500.0);
        try{
            //serviceDon.ajouter(d1);
            //serviceDon.ajouter(d2);
            //serviceDon.update(up);
            //serviceDon.delete(sup);
            List<Don> listDon = serviceDon.readAll();
            System.out.println(listDon);
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }
}
