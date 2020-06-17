/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velotn.Ui;

import static com.velotn.Ui.Controller.holderPane;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Farah GABSI
 */
public class DrawerController implements Initializable {

    @FXML
    private AnchorPane box;
    @FXML
    private ImageView imgUser;
    @FXML
    private Label labelUser;
    @FXML
    private JFXButton notification;
    @FXML
    private JFXButton listeDesAnnonce;
    @FXML
    private JFXButton AjoutAnnonce;
    @FXML
    private JFXButton statRec;
    @FXML
    private JFXButton deconnexion;
    private int userId;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void notification(ActionEvent event) {
                loadView("/gui/stat.fxml");

    }

    @FXML
    private void listeDesAnnonce(ActionEvent event) throws IOException {

        loadView("/gui/FrontGroupe.fxml");
    }

    @FXML
    private void AjoutAnnonce(ActionEvent event) throws IOException {
    loadView("/gui/ajouterGroupe.fxml");
    }

    private void chatRoom(ActionEvent event) {
         loadView("/gui/SignalGroup.fxml");
    }

    @FXML
    private void makeStat(ActionEvent event) {
                 loadView("/gui/affichafeGroupe.fxml");

    }

    @FXML
    private void makeDisconnect(ActionEvent event) {
    }
     private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    
    private Object loadView(String path)
    {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource((path)));
        AnchorPane parentContent = null;
        try {
            parentContent = fxmlLoader.load();
        } catch (IOException ex) {
            Logger.getLogger(LayoutFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setNode(parentContent);
        System.gc();
        return fxmlLoader.getController();
    }
    
}
