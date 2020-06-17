package com.velotn.Service;

import com.velotn.Entite.*;
import com.velotn.IService.IServiceProduit;
import com.velotn.Utils.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceProduit implements IServiceProduit {

    private Connection cnx;
    public Statement st;
    private PreparedStatement pst;
    private ResultSet rs;

    public ServiceProduit() {
        cnx=DataBase.getInstance().getConnection();
    }

    @Override
    public void insertVelo(Velo v) {

        String Produit ="insert into produits(nomProd,description,prix,quantite,img_url"+") values(?,?,?,?,?)";
        int id=0;
        try {
            pst=cnx.prepareStatement(Produit);
            pst.setString(1, v.getNomProduit());
            pst.setString(2, v.getDescription());
            pst.setFloat(3, v.getPrix());
            pst.setInt(4, v.getQuantite());
            pst.setString(5, "http://localhost/velotnproducts/"+v.getImg_url());
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
        }finally {

            String autoid = "select LAST_INSERT_ID() as id from produits";
            try {
                st=cnx.createStatement();
                rs=st.executeQuery(autoid);


                while(rs.next())
                    id=rs.getInt(1);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
            }finally {
                String Velo="insert into velos(id,marque,type) values(?,?,?)";
                try {
                    pst=cnx.prepareStatement(Velo);
                    pst.setInt(1, id);
                    pst.setString(2, v.getMarque());
                    pst.setString(3, v.getType());
                    pst.executeUpdate();

                } catch (SQLException e) {
                    Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
                }
            }
        }


    }

    @Override
    public void insetPieceR(Piece_Rechange p) {
        String Produit ="insert into produits(nomProd,description,prix,quantite,img_url) values(?,?,?,?,?)";
        int id=0;
        try {
            pst=cnx.prepareStatement(Produit);
            pst.setString(1, p.getNomProduit());
            pst.setString(2, p.getDescription());
            pst.setFloat(3, p.getPrix());
            pst.setInt(4, p.getQuantite());
            pst.setString(5, p.getImg_url());
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
        }finally {

            String autoid = "select LAST_INSERT_ID() as id from produits";
            try {
                st=cnx.createStatement();
                rs=st.executeQuery(autoid);


                while(rs.next())
                    id=rs.getInt(1);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
            }finally {
                String Velo="insert into piecesrechanges(id,marque,type) values(?,?,?)";
                try {
                    pst=cnx.prepareStatement(Velo);
                    pst.setInt(1, id);
                    pst.setString(2, p.getMarque());
                    pst.setString(3, p.getType());
                    pst.executeUpdate();

                } catch (SQLException e) {
                    Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
                }
            }
        }

    }

    @Override
    public void insertAccessoire(Accessoire a) {

        String Produit ="insert into produits(nomProd,description,prix,quantite,img_url) values(?,?,?,?,?)";
        int id=0;
        try {
            pst=cnx.prepareStatement(Produit);
            pst.setString(1, a.getNomProduit());
            pst.setString(2, a.getDescription());
            pst.setFloat(3, a.getPrix());
            pst.setInt(4, a.getQuantite());
            pst.setString(5, a.getImg_url());
            pst.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
        }finally {

            String autoid = "select LAST_INSERT_ID() as id from produits";
            try {
                st=cnx.createStatement();
                rs=st.executeQuery(autoid);


                while(rs.next())
                    id=rs.getInt(1);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
            }finally {
                String Velo="insert into accessoires(id,marque,type) values(?,?,?)";
                try {
                    pst=cnx.prepareStatement(Velo);
                    pst.setInt(1, id);
                    pst.setString(2, a.getMarque());
                    pst.setString(3, a.getType());
                    pst.executeUpdate();

                } catch (SQLException e) {
                    Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
                }
            }
        }


    }

    @Override
    public List<Produit> displayProducts() {

        List<Produit> list = new ArrayList<Produit>();
        list.addAll(displayVelos());
        list.addAll(displayPieces());
        list.addAll(displayAccesoires());

        return list;
    }

    @Override
    public List<Velo> displayVelos() {
        List<Velo> list = new ArrayList<Velo>();
        String req = "SELECT * from produits p INNER join velos v on p.id=v.id";
        try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
                list.add(new Velo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getInt(5),rs.getString(8),rs.getString(9),rs.getString(6)));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
        }

        return list;

    }

    @Override
    public List<Piece_Rechange> displayPieces() {
        List<Piece_Rechange> list = new ArrayList<Piece_Rechange>();
        String req = "SELECT * from produits p INNER join piecesrechanges v on p.id=v.id";
        try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
                list.add(new Piece_Rechange(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getInt(5),rs.getString(8),rs.getString(9),rs.getString(6)));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
        }

        return list;


    }

    @Override
    public List<Accessoire> displayAccesoires() {
        List<Accessoire> list = new ArrayList<Accessoire>();
        String req = "SELECT * from produits p INNER join accessoires v on p.id=v.id";
        try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while(rs.next())
            {
                list.add(new Accessoire(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getInt(5),rs.getString(8),rs.getString(9),rs.getString(6)));
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
        }

        return list;


    }

    // Recherche SELECT * from produits p INNER join velos v on p.id=[v.id]

    @Override
    public void update(Velo v) {
        String Produit="UPDATE produits set nomProd=?, description=?, prix=? where id=?";
        try {
            pst=cnx.prepareStatement(Produit);
            pst.setString(1, v.getNomProduit());
            pst.setString(2, v.getDescription());
            pst.setFloat(3, v.getPrix());
            pst.setInt(4, v.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
        }finally {
            String Velo="UPDATE velos set marque=?, type=? where id=?";
            try {
                pst=cnx.prepareStatement(Velo);
                pst.setString(1, v.getMarque());
                pst.setString(2, v.getType());
                pst.setInt(3, v.getId());
                pst.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
            }
        }

    }

    @Override
    public void update(Piece_Rechange p) {
        String Produit="UPDATE produits set nomProd=?, description=?, prix=? where id=?";
        try {
            pst=cnx.prepareStatement(Produit);
            pst.setString(1, p.getNomProduit());
            pst.setString(2, p.getDescription());
            pst.setFloat(3, p.getPrix());
            pst.setInt(4, p.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
        }finally {
            String PR="UPDATE piecesrechanges set marque=?, type=? where id=?";
            try {
                pst=cnx.prepareStatement(PR);
                pst.setString(1, p.getMarque());
                pst.setString(2, p.getType());
                pst.setInt(3, p.getId());
                pst.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
            }
        }


    }

    @Override
    public void update(Accessoire a) {
        String Produit="UPDATE produits set nomProd=?, description=?, prix=? where id=?";
        try {
            pst=cnx.prepareStatement(Produit);
            pst.setString(1, a.getNomProduit());
            pst.setString(2, a.getDescription());
            pst.setFloat(3, a.getPrix());
            pst.setInt(4, a.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
        }finally {
            String acc="UPDATE accessoire set marque=?, type=? where id=?";
            try {
                pst=cnx.prepareStatement(acc);
                pst.setString(1, a.getMarque());
                pst.setString(2, a.getType());
                pst.setInt(3, a.getId());
                pst.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
            }
        }



    }


    @Override
    public boolean RechercheProduit(Produit p) {
        if(p instanceof Velo)
        {
            System.out.println("V");

            return displayVelos().contains(p);
        }

        if(p instanceof Piece_Rechange)
        {
            System.out.println("P");
            return displayPieces().contains(p);
        }

        if(p instanceof Accessoire)
        {
            System.out.println("A");
            return displayAccesoires().contains(p);
        }
        return false;

    }

    @Override
    public void delete(Velo v) {
        String req="DELETE FROM produits where id=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1, v.getId());
            pst.executeUpdate();

        } catch (SQLException e) {

            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
        }



    }

    @Override
    public void delete(Piece_Rechange p) {
        String req="DELETE FROM produits where id=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1, p.getId());
            pst.executeUpdate();

        } catch (SQLException e) {

            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
        }


    }

    @Override
    public void delete(Accessoire a) {

        String req="DELETE FROM produits where id=?";
        try {
            pst=cnx.prepareStatement(req);
            pst.setInt(1, a.getId());
            pst.executeUpdate();

        } catch (SQLException e) {

            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE,null,e);
        }


    }



}
