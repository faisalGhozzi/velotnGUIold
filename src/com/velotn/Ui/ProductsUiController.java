package com.velotn.Ui;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.velotn.Entite.*;
import com.velotn.Service.LocationService;
import com.velotn.Service.ServicePanier;
import com.velotn.Service.ServiceProduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductsUiController implements Initializable {

    @FXML
    private ScrollPane productScrollPane;

    @FXML
    private Label labelIdRent;

    @FXML
    private JFXDatePicker date_debut;

    @FXML
    private JFXDatePicker date_fin;

    @FXML
    private Pane rentDetailsPane;


    @FXML
    private Pane productPane;

    LocationService ls = new LocationService() ;
    ServiceProduit serviceProduit = new ServiceProduit();
    ServicePanier servicePanier = new ServicePanier();
    private final ObservableList<Velo> dataV = FXCollections.observableArrayList();
    private final ObservableList<Piece_Rechange> dataP = FXCollections.observableArrayList();
    private final ObservableList<Accessoire> dataA = FXCollections.observableArrayList();
    List<Produit> produits = new ArrayList<>();
    private List<Velo> velos = new ArrayList<>();
    private List<Piece_Rechange> pieces = new ArrayList<>();
    private List<Accessoire> accessoires = new ArrayList<>();
    private final ObservableList<Produit> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            afficherProduits();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void afficherProduits() throws SQLException {
        rentDetailsPane.setVisible(false);
        new FadeOut(rentDetailsPane).play();
        productPane.getChildren().clear();
        data.clear();
        dataA.clear();
        dataP.clear();
        dataV.clear();
        produits = serviceProduit.displayProducts();
        data.addAll(produits);

        VBox vBox = new VBox();
        for (Produit produit: produits) {
            HBox hBox = new HBox();
            Image image = new Image(produit.getImg_url());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            Label detailsLabel = new Label();
            detailsLabel.setText(produit.getDescription());
            Label prixLabel = new Label();
            prixLabel.setText(String.valueOf(produit.getPrix()));
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().add(imageView);
            hBox.getChildren().add(detailsLabel);
            hBox.getChildren().add(prixLabel);

            if(Controller.getUserId()!=0) {
                JFXButton jfxAddProductButton = new JFXButton();
                jfxAddProductButton.getStyleClass().add("buttonDonate");
                jfxAddProductButton.setText("Acheter");
                jfxAddProductButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Panier panier = new Panier(produit.getId(), 1, produit.getPrix(), 1);
                        try {
                            servicePanier.ajouter(panier);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                hBox.getChildren().add(jfxAddProductButton);
            }

            hBox.setSpacing(50);
            vBox.getChildren().add(hBox);
            vBox.setSpacing(50);
        }
        productPane.getChildren().add(vBox);
        productScrollPane.setContent(productPane);
    }

    @FXML
    void afficher_velos(ActionEvent event) {
        rentDetailsPane.setVisible(false);
        new FadeOut(rentDetailsPane).play();
        productPane.getChildren().clear();
        data.clear();
        dataA.clear();
        dataP.clear();
        dataV.clear();
        velos = serviceProduit.displayVelos();
        dataV.addAll(velos);

        VBox vBox = new VBox();
            for (Velo produit: velos) {
            HBox hBox = new HBox();
            Image image = new Image(produit.getImg_url());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            Label detailsLabel = new Label();
            detailsLabel.setText(produit.getDescription());
            Label prixLabel = new Label();
            prixLabel.setText(String.valueOf(produit.getPrix()));
                hBox.setAlignment(Pos.CENTER);
                hBox.getChildren().add(imageView);
                hBox.getChildren().add(detailsLabel);
                hBox.getChildren().add(prixLabel);

                if(Controller.getUserId()!=0) {
                    JFXButton jfxAddProductButton = new JFXButton();
                    jfxAddProductButton.getStyleClass().add("buttonDonate");
                    jfxAddProductButton.setText("Acheter");
                    jfxAddProductButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Panier panier = new Panier(produit.getId(), 1, produit.getPrix(), Controller.getUserId());
                            try {
                                servicePanier.ajouter(panier);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    hBox.getChildren().add(jfxAddProductButton);
                }
            hBox.setSpacing(50);
            vBox.getChildren().add(hBox);
            vBox.setSpacing(50);
        }
        productPane.getChildren().add(vBox);
        productScrollPane.setContent(productPane);
    }

    @FXML
    void afficher_accessoires(ActionEvent event) {
        rentDetailsPane.setVisible(false);
        new FadeOut(rentDetailsPane).play();
        productPane.getChildren().clear();
        data.clear();
        dataA.clear();
        dataP.clear();
        dataV.clear();
        accessoires = serviceProduit.displayAccesoires();
        dataA.addAll(accessoires);

        VBox vBox = new VBox();
        for (Accessoire produit: accessoires) {
            HBox hBox = new HBox();
            Image image = new Image(produit.getImg_url());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            Label detailsLabel = new Label();
            detailsLabel.setText(produit.getDescription());
            Label prixLabel = new Label();
            prixLabel.setText(String.valueOf(produit.getPrix()));
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().add(imageView);
            hBox.getChildren().add(detailsLabel);
            hBox.getChildren().add(prixLabel);

            if(Controller.getUserId()!=0) {
                JFXButton jfxAddProductButton = new JFXButton();
                jfxAddProductButton.getStyleClass().add("buttonDonate");
                jfxAddProductButton.setText("Acheter");
                jfxAddProductButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Panier panier = new Panier(produit.getId(), 1, produit.getPrix(), Controller.getUserId());
                        try {
                            servicePanier.ajouter(panier);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                hBox.getChildren().add(jfxAddProductButton);

            }
            hBox.setSpacing(50);
            vBox.getChildren().add(hBox);
            vBox.setSpacing(50);
        }
        productPane.getChildren().add(vBox);
        productScrollPane.setContent(productPane);
    }

    @FXML
    void afficher_location(ActionEvent event) {
        rentDetailsPane.setVisible(false);
        new FadeOut(rentDetailsPane).play();
        productPane.getChildren().clear();
        data.clear();
        dataA.clear();
        dataP.clear();
        dataV.clear();
        velos = serviceProduit.displayVelos();
        dataV.addAll(velos);

        VBox vBox = new VBox();
        for (Velo produit: velos) {
            HBox hBox = new HBox();
            Image image = new Image(produit.getImg_url());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            Label detailsLabel = new Label();
            detailsLabel.setText(produit.getDescription());
            Label prixLabel = new Label();
            prixLabel.setText(String.valueOf(produit.getPrix()));
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().add(imageView);
            hBox.getChildren().add(detailsLabel);
            hBox.getChildren().add(prixLabel);

            if(Controller.getUserId()!=0) {
                JFXButton jfxAddProductButton = new JFXButton();
                jfxAddProductButton.getStyleClass().add("buttonDonate");
                jfxAddProductButton.setText("Louer");
                jfxAddProductButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        rentDetailsPane.setVisible(true);
                        new FadeIn(rentDetailsPane).play();
                        labelIdRent.setText(String.valueOf(produit.getId()));

                    /*Parent fxml = null;
                    try {
                        fxml = FXMLLoader.load(getClass().getResource("LocationUi.fxml"));

                        LocationUiController locationUiController = FXMLLoader.load(getClass().getResource("LocationUiController.java"));
                        locationUiController.setIdLabelRent(String.valueOf(produit.getId()));

                        productPane.getChildren().removeAll();
                        productPane.getChildren().setAll(fxml);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    }
                });
                hBox.getChildren().add(jfxAddProductButton);
            }

            hBox.setSpacing(50);
            vBox.getChildren().add(hBox);
            vBox.setSpacing(50);
        }
        productPane.getChildren().add(vBox);
        productScrollPane.setContent(productPane);
    }

    @FXML
    void afficher_pieces(ActionEvent event) {
        rentDetailsPane.setVisible(false);
        new FadeOut(rentDetailsPane).play();
        productPane.getChildren().clear();
        data.clear();
        dataA.clear();
        dataP.clear();
        dataV.clear();
        pieces = serviceProduit.displayPieces();
        dataP.addAll(pieces);

        VBox vBox = new VBox();
        for (Piece_Rechange produit: pieces) {
            HBox hBox = new HBox();
            Image image = new Image(produit.getImg_url());
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setFitHeight(150);
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
            imageView.setCache(true);
            Label detailsLabel = new Label();
            detailsLabel.setText(produit.getDescription());
            Label prixLabel = new Label();
            prixLabel.setText(String.valueOf(produit.getPrix()));
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().add(imageView);
            hBox.getChildren().add(detailsLabel);
            hBox.getChildren().add(prixLabel);

            if(Controller.getUserId()!=0) {
                JFXButton jfxAddProductButton = new JFXButton();
                jfxAddProductButton.getStyleClass().add("buttonDonate");
                jfxAddProductButton.setText("Acheter");
                jfxAddProductButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Panier panier = new Panier(produit.getId(), 1, produit.getPrix(), Controller.getUserId());
                        try {
                            servicePanier.ajouter(panier);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                hBox.getChildren().add(jfxAddProductButton);

            }
            hBox.setSpacing(50);
            vBox.getChildren().add(hBox);
            vBox.setSpacing(50);
        }
        productPane.getChildren().add(vBox);
        productScrollPane.setContent(productPane);
    }

    @FXML
    void Ajouter(ActionEvent event) throws SQLException {
        if (date_debut.getValue() == null || date_debut.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tout les champs");
            alert.showAndWait();
        } else {
            DatePicker tmpdated= date_debut;
            String dated=  tmpdated.getValue().toString();
            DatePicker tmpdatef= date_fin;
            String datef=  tmpdatef.getValue().toString();
            Location l = new Location(ls.rechercheu(Controller.getUserId()), dated, datef,ls.recherchev(Integer.parseInt(labelIdRent.getText())));
            int id = ls.insertLocation(l);
            ls.UpdatePrix(id,ls.calculer(Integer.parseInt(labelIdRent.getText())),Integer.parseInt(labelIdRent.getText()));
        }
    }

    @FXML
    void Annuler(ActionEvent event) {
        rentDetailsPane.setVisible(false);
        new FadeOut(rentDetailsPane).play();
    }

    @FXML
    void afficher_reservations(ActionEvent event) throws IOException {
        rentDetailsPane.setVisible(false);
        new FadeOut(rentDetailsPane).play();
        Parent fxml = FXMLLoader.load(getClass().getResource("AffichageLocationUi.fxml"));
        productPane.getChildren().clear();
        productPane.getChildren().add(fxml);
    }


}
