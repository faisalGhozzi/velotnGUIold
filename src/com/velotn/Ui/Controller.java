/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velotn.Ui;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Farah GABSI
 */
public class Controller {
    public static AnchorPane holderPane;
    private static int userId;
   
    public static void setUserId(int id)
    {
        if(id == 0) return ;
        Controller.userId = id ;
    }
    public static int getUserId()
    {
        return Controller.userId;
    }
    
   
    //----------
   
   
    
}
