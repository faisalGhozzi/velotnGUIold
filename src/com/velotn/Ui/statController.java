/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velotn.Ui;

import com.velotn.Utils.DataBase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Farah GABSI
 */
public class statController implements Initializable {

    @FXML
    private JFXDrawer drawer2;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXButton statReclamationBtn;
    @FXML
    private BarChart<String, Integer> barChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*try {
            AnchorPane box = FXMLLoader.load(getClass().getResource("/gui/drawer.fxml"));
            drawer2.open();
            drawer2.setSidePane(box);

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * 1);
                transition.play();
                 if (drawer2.isOpened()){
                    drawer2.close();
                } else {
                    drawer2.open();
                }

               

            });
        } catch (IOException ex) {
            Logger.getLogger(statController.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        
       
    }    

    @FXML
    private void AfficherStatReclamation(ActionEvent event) {
        String req = "select nomGroup, count(*) as nbrmembre from groups_members group by nomGroup";
    
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
        try{       
       
            Statement stm = DataBase.getInstance().getConnection().createStatement();
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()){
                
                series.getData().add(new XYChart.Data<>(rs.getString(1)
                        ,rs.getInt(2)));
            }                    

            barChart.getData().add(series);

        }catch(Exception e){
            
        }
    }
    
}
