package com.velotn.Test;

import com.velotn.Entite.Panier;
import com.velotn.Service.ServicePanier;

import java.sql.SQLException;
import java.util.List;

public class TestPanier {
    public static void main(String[] args) throws SQLException {
        ServicePanier servicePanier = new ServicePanier();

        List<Panier> list = servicePanier.readAll();
        System.out.println(list);
    }
}
