package com.velotn.Ui;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainUiController implements Initializable {
    Stage stage= new Stage();

    @FXML
    private Label panierImg;

    @FXML
    private HBox parent;

    @FXML
    private VBox contentArea;

    @FXML
    private JFXButton commande;

    @FXML
    private Pane displayArea;

    @FXML
    private JFXButton produits;

    @FXML
    private JFXButton don;

    @FXML
    private JFXButton connect;

    @FXML
    private JFXButton disconnect;

    @FXML
    private JFXButton profil;

    @FXML
    private JFXButton group;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if(Controller.getUserId()==0){
                connect.setVisible(true);
                disconnect.setVisible(false);
                profil.setVisible(false);
                group.setVisible(false);
                panierImg.setVisible(false);
            }
            else{
                connect.setVisible(false);
                disconnect.setVisible(true);
                profil.setVisible(true);
                group.setVisible(true);
                panierImg.setVisible(true);
            }
            Parent fxml = FXMLLoader.load(getClass().getResource("ProductsUI.fxml"));
            displayArea.getChildren().removeAll();
            displayArea.getChildren().setAll(fxml);
        }catch (IOException ex){
            Logger.getLogger(MainUiController.class.getName()).log(Level.SEVERE,null, ex);
        }
    }

    @FXML
    void close_app(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void minimize_app(MouseEvent event) {
        Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void close_connexion(ActionEvent event) throws IOException {
        Controller.setUserId(0);
        connect.setVisible(true);
        disconnect.setVisible(false);
        profil.setVisible(false);
        group.setVisible(false);
    }

    @FXML
    void open_don(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("DonUi.fxml"));
        displayArea.getChildren().removeAll();
        displayArea.getChildren().setAll(fxml);
    }

    @FXML
    void open_products(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("ProductsUI.fxml"));
        displayArea.getChildren().removeAll();
        displayArea.getChildren().setAll(fxml);
    }

    @FXML
    void open_list_don(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("AffichageDonUi.fxml"));
        displayArea.getChildren().removeAll();
        displayArea.getChildren().setAll(fxml);
    }

    @FXML
    void open_cart(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("PanierUi.fxml"));
        displayArea.getChildren().removeAll();
        displayArea.getChildren().setAll(fxml);
    }


    @FXML
    void open_commandes(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("CommandeUI.fxml"));
        displayArea.getChildren().removeAll();
        displayArea.getChildren().setAll(fxml);
    }

    @FXML
    void open_promotion(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("PromotionUi.fxml"));
        displayArea.getChildren().removeAll();
        displayArea.getChildren().setAll(fxml);
    }


    @FXML
    void open_events(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("EvenementUi.fxml"));
        displayArea.getChildren().removeAll();
        displayArea.getChildren().setAll(fxml);
    }

    @FXML
    void open_reclamations(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("ReclamationUi.fxml"));
        displayArea.getChildren().removeAll();
        displayArea.getChildren().setAll(fxml);
    }

    @FXML
    void open_connexion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

    @FXML
    void open_group(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("FrontGroupe.fxml"));
        displayArea.getChildren().removeAll();
        displayArea.getChildren().setAll(fxml);
    }

    @FXML
    void open_profil(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("Journal.fxml"));
        displayArea.getChildren().removeAll();
        displayArea.getChildren().setAll(fxml);
    }


    @FXML
    void add_group(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("AjouterGroupe.fxml"));
        displayArea.getChildren().removeAll();
        displayArea.getChildren().setAll(fxml);
    }


    @FXML
    void add_product(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("AjouterProduit.fxml"));
        displayArea.getChildren().removeAll();
        displayArea.getChildren().setAll(fxml);
    }

    @FXML
    void show_stat(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("stat.fxml"));
        displayArea.getChildren().removeAll();
        displayArea.getChildren().setAll(fxml);
    }
}
