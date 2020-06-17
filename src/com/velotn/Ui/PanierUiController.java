package com.velotn.Ui;

import com.jfoenix.controls.JFXButton;
import com.velotn.Entite.Commande;
import com.velotn.Entite.Panier;
import com.velotn.Service.ServiceCommande;
import com.velotn.Service.ServicePanier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PanierUiController implements Initializable {
    @FXML
    private ScrollPane scrollPanePanier;

    @FXML
    private Pane panePanier;

    ServicePanier servicePanier = new ServicePanier();
    ServiceCommande serviceCommande = new ServiceCommande();
    List<Panier> paniers = new ArrayList<>();
    private final ObservableList<Panier> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            afficher();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void afficher() throws SQLException {
        data.clear();
        paniers = servicePanier.readAll();
        data.addAll(paniers);

        VBox vBox = new VBox();


        JFXButton jfxCommandeAddButton = new JFXButton();
        jfxCommandeAddButton.getStyleClass().add("buttonDonate");
        jfxCommandeAddButton.setText("Acheter");
        for (Panier panier: paniers) {
            HBox hbox = new HBox();
            Label prix_unitaire = new Label();
            Spinner<Integer> spinner = new Spinner<>();
            Label prix_total = new Label();
            JFXButton jfxItemRemoveButton = new JFXButton();
            jfxItemRemoveButton.getStyleClass().add("buttonDonate");
            jfxItemRemoveButton.setText("Enlever");

            /*Image image = new Image("http://"+panier.getUrl());
            ImageView imageView = new ImageView(image);*/
            prix_unitaire.setText(String.valueOf(panier.getPrix_unitaire()));
            prix_total.setText(String.valueOf(panier.getPrix_total()));
            spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,panier.getQte()));
            spinner.valueProperty().addListener(((observable, oldValue, newValue) -> {
                Panier updatedPanier = new Panier(panier.getId(),newValue);
                try {
                    servicePanier.update(updatedPanier);
                    prix_total.setText(String.valueOf(newValue*panier.getPrix_unitaire()));
                    paniers = servicePanier.readAll();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }));
            jfxItemRemoveButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Panier deletePanier = new Panier(panier.getId());
                    try {
                        servicePanier.delete(deletePanier);
                        paniers = servicePanier.readAll();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            //hbox.getChildren().add(imageView);
            hbox.setSpacing(50);
            hbox.getChildren().add(prix_unitaire);
            hbox.getChildren().add(spinner);
            hbox.getChildren().add(prix_total);
            hbox.getChildren().add(jfxItemRemoveButton);
            vBox.getChildren().add(hbox);
            vBox.setSpacing(20);
        }

        jfxCommandeAddButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Commande commande = new Commande(LocalDate.now().plusDays(1),paniers.stream().mapToDouble(Panier::getPrix_total).sum(),paniers,Controller.getUserId());
                try {
                    serviceCommande.ajouter(commande);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        if(!paniers.isEmpty())
            vBox.getChildren().add(jfxCommandeAddButton);

        panePanier.getChildren().add(vBox);
        scrollPanePanier.setContent(panePanier);
    }
}
