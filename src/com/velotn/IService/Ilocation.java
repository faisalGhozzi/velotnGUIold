package com.velotn.IService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;

/**
 *
 * @author khalil
 */
public interface Ilocation<T> {
    int insertLocation(T t) ;
    List<T> displaAll() ;
    void DeleteUserLocation(int id) ;
    void Update(int id , String date_debut, String date_fin );
    List<T> rechercherParid(int id ) ;
    float  calculer(int id ) ;
    void DeleteProduitLocation(int id) ;
    void UpdatePrix(int id  ,float prixtotal,int id_v) ;
    List<T> TrierParPrix();
    List<Integer> get_id_velo()  ;
    List<Integer> get_id_user()  ;


}

