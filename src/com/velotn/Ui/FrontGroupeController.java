/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velotn.Ui;

import static com.velotn.Ui.Controller.holderPane;
import com.velotn.Entite.Groups;
import com.velotn.Service.GroupeService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import controller.ProduitsFXMLController;
import static controller.ProduitsFXMLController.nombreproduits;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Farah GABSI
 */
public class FrontGroupeController implements Initializable {

    GroupeService service = new GroupeService();
    Groups p = new Groups();
    public String rechercher = "";

    @FXML
    private JFXListView<Pane> ListView_Produits;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private TextField recherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*AnchorPane box = FXMLLoader.load(getClass().getResource("drawer.fxml"));
        drawer.open();
        drawer.setSidePane(box);*/

            /*HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(1);*/
            /*hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * 1);
                transition.play();*/

                /*if (drawer.isOpened()) {
                    drawer.close();
                } else {
                    drawer.open();
                }*/

        //});

        ListView_Produits.setFocusTraversable(false);
        getShowPane();

        if (nombreproduits >= 1) {
            nombreproduits--;
            ListeActualites();
        }

    }

    public void ListeActualites() {
        ObservableList<Pane> refresh = FXCollections.observableArrayList();

    }

    public void getShowPane() {

        List<Groups> AllGroups = new ArrayList();
        if (rechercher.equals("")) {
            for (Groups p : service.AfficherAllGroups()) {
                AllGroups.add(p);
            }
        } else {
            for (Groups p : service.RechercheGroupeParNom(rechercher)) {
                AllGroups.add(p);

            }

        }
        int i = 0;
        int j = 0;
        ObservableList<Pane> Panes = FXCollections.observableArrayList();

        List<Groups> ThreeProducts = new ArrayList();
        for (Groups p : AllGroups) {
            if (i == 0) {
                ThreeProducts.add(p);
                i++;
                j++;

                if (j == AllGroups.size()) {
                    Panes.add(AddPane(ThreeProducts));

                    ThreeProducts.clear();
                }

            } else {
                ThreeProducts.add(p);
                i++;
                j++;
                if ((i % 3 == 0) || (j == AllGroups.size())) {
                    Panes.add(AddPane(ThreeProducts));

                    ThreeProducts.clear();

                }
            }
        }

        ObservableList<Pane> refresh = FXCollections.observableArrayList();
        ListView_Produits.setItems(refresh);
        ListView_Produits.setItems(Panes);
    }

    public Pane AddPane(List<Groups> ThreeProduct) {
        Pane pane = new Pane();
        pane.setStyle(" -fx-background-color: white");
        int k = 1;
        for (Groups p3 : ThreeProduct) {
            if (k == 1) {
                Pane pane2 = new Pane();
                pane2.setLayoutX(25);
                pane2.setLayoutY(50);
                pane2.setPrefWidth(pane2.getWidth() + 200);
                pane2.setPrefHeight(pane2.getHeight() + 200);

               
                pane2.setStyle(" -fx-border-radius: 10;-fx-border-color: #ff4f00 ;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0); ");
                JFXButton t = new JFXButton("Détails");
                    
                t.setStyle("-fx-font-weight: bold;");
                 

                HBox hb = new HBox(t);
                       

                hb.setLayoutX(0);
                hb.setLayoutY(170);
                hb.setPrefWidth(hb.getWidth() + 200);
                hb.setPrefHeight(hb.getHeight() + 35);
                hb.setStyle("-fx-background-color: #ff4f00; -fx-background-radius: 0 0 10 10;");


                pane2.getChildren().addAll(hb);

                String A = p3.getImage();
                A = "C:\\wamp64\\www\\velotnproducts\\" + A;
                File F1 = new File(A);
                Image image2 = new Image(F1.toURI().toString());

                ImageView image = new ImageView();
                image.setFitWidth(140);
                image.setFitHeight(130);
                image.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0);");

                image.setImage(image2);
                image.setLayoutX(30);
                image.setLayoutY(-45);
                pane2.getChildren().add(image);

                Text nomt = new Text("Nom : ");
                Label nom = new Label(p3.getNom());
                Text prixt = new Text("Description : ");
                Label prix = new Label(p3.getDescription());
                nomt.setLayoutX(50);
                nomt.setLayoutY(160);
                nom.setLayoutX(100);
                nom.setLayoutY(145);
                prixt.setLayoutX(50);
                prixt.setLayoutY(180);
                prix.setLayoutX(125);
                prix.setLayoutY(165);
                nomt.setStyle("-fx-font-weight: bold;-fx-fill : #ff4f00");
                prixt.setStyle("-fx-font-weight: bold;-fx-fill : #ff4f00");

                t.setOnMouseClicked((MouseEvent event) -> {
                   Groups.setGroupe_courant(p3.getId());

                   loadView("ProfilGroupe.fxml");
                });
                pane.getChildren().addAll(pane2, nomt, prixt, nom, prix);
            }          
            if (k == 2) {
                Pane pane2 = new Pane();
                pane2.setLayoutX(250);
                pane2.setLayoutY(50);
                pane2.setPrefWidth(pane2.getWidth() + 200);
                pane2.setPrefHeight(pane2.getHeight() + 200);
         
                pane2.setStyle(" -fx-border-radius: 10 ;-fx-border-color: #ff4f00 ;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0); ");

                JFXButton t = new JFXButton("Détails");
                         
                t.setStyle("-fx-font-weight: bold;");

                HBox hb = new HBox(t);


                hb.setLayoutX(0);
                hb.setLayoutY(170);
                hb.setPrefWidth(hb.getWidth() + 200);
                hb.setPrefHeight(hb.getHeight() + 35);
                hb.setStyle("-fx-background-color: #ff4f00; -fx-background-radius: 0 0 10 10;");
                pane2.getChildren().addAll(hb);

                String A = p3.getImage();
                A = "C:\\wamp64\\www\\velotnproducts\\" + A;
                File F1 = new File(A);
                Image image2 = new Image(F1.toURI().toString());

                ImageView image = new ImageView();
                image.setFitWidth(140);
                image.setFitHeight(130);
                image.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0);");

                image.setImage(image2);
                image.setLayoutX(30);
                image.setLayoutY(-45);
                pane2.getChildren().add(image);

                Text nomt = new Text("Nom : ");
                Label nom = new Label(p3.getNom());
                Text prixt = new Text("Description : ");
                Label prix = new Label(p3.getDescription());
                nomt.setLayoutX(275);
                nomt.setLayoutY(160);
                nom.setLayoutX(325);
                nom.setLayoutY(145);
                prixt.setLayoutX(275);
                prixt.setLayoutY(180);
                prix.setLayoutX(350);
                prix.setLayoutY(165);
                nomt.setStyle("-fx-font-weight: bold;-fx-fill : #ff4f00");
                prixt.setStyle("-fx-font-weight: bold;-fx-fill : #ff4f00");

                t.setOnMouseClicked((MouseEvent event) -> {
                    Groups.setGroupe_courant(p3.getId());

                    loadView("ProfilGroupe.fxml");
                });
                pane.getChildren().addAll(pane2, nomt, prixt, nom, prix);
            }

            if (k == 3) {
                Pane pane2 = new Pane();
                pane2.setLayoutX(475);
                pane2.setLayoutY(50);
                pane2.setPrefWidth(pane2.getWidth() + 200);
                pane2.setPrefHeight(pane2.getHeight() + 200);
        
                pane2.setStyle(" -fx-border-radius: 10;-fx-border-color: #ff4f00 ;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0); ");

                JFXButton t = new JFXButton("Détails");
                t.setStyle("-fx-font-weight: bold;");
                HBox hb = new HBox(t);

                hb.setLayoutX(0);
                hb.setLayoutY(170);
                hb.setPrefWidth(hb.getWidth() + 200);
                hb.setPrefHeight(hb.getHeight() + 35);
                hb.setStyle("-fx-background-color: #ff4f00; -fx-background-radius: 0 0 10 10;");
                pane2.getChildren().addAll(hb);

                String A = p3.getImage();
                A = "C:\\wamp64\\www\\velotnproducts\\" + A;
                File F1 = new File(A);
                Image image2 = new Image(F1.toURI().toString());

                ImageView image = new ImageView();
                image.setFitWidth(140);
                image.setFitHeight(130);
                image.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0);");

                image.setImage(image2);
                image.setLayoutX(30);
                image.setLayoutY(-45);

                pane2.getChildren().add(image);

                Text nomt = new Text("Nom : ");
                Label nom = new Label(p3.getNom());
                Text prixt = new Text("Description : ");
                Label prix = new Label(p3.getDescription());
                nomt.setLayoutX(500);
                nomt.setLayoutY(160);
                nom.setLayoutX(550);
                nom.setLayoutY(145);
                prixt.setLayoutX(500);
                prixt.setLayoutY(180);
                prix.setLayoutX(575);
                prix.setLayoutY(165);
                nomt.setStyle("-fx-font-weight: bold;-fx-fill : #ff4f00");
                prixt.setStyle("-fx-font-weight: bold;-fx-fill : #ff4f00");

                t.setOnMouseClicked((MouseEvent event) -> {
                    Groups.setGroupe_courant(p3.getId());
                    loadView("ProfilGroupe.fxml");
                });
                pane.getChildren().addAll(pane2, nomt, prixt, nom, prix);
 
            }
            k++;

        }
        return pane;
    }

    @FXML
    private void search(KeyEvent event) {

        rechercher = recherche.getText();
        getShowPane();

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
