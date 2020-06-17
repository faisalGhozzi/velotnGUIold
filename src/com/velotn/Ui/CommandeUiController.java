package com.velotn.Ui;

import com.velotn.Entite.Commande;
import com.velotn.Entite.Don;
import com.velotn.Entite.ListProduitsAchete;
import com.velotn.Service.ServiceCommande;
import com.velotn.Service.ServiceDon;
import com.velotn.Service.ServiceListProduitsAchete;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.ResourceBundle;

public class CommandeUiController implements Initializable {
    @FXML
    private TableView<Commande> tableCommande;

    @FXML
    private TableColumn<Commande, LocalDate> dateCommande;

    @FXML
    private TableColumn<Commande, Double> prixCommande;

    @FXML
    private TableView<ListProduitsAchete> tableProduits;

    @FXML
    private TableColumn<ListProduitsAchete, Integer> produitProduit;

    @FXML
    private TableColumn<ListProduitsAchete, Double> puProduit;

    @FXML
    private TableColumn<ListProduitsAchete, Integer> qteProduit;

    @FXML
    private TableColumn<ListProduitsAchete, Double> ptProduit;

    ServiceCommande serviceCommande = new ServiceCommande();
    ServiceListProduitsAchete serviceListProduitsAchete = new ServiceListProduitsAchete();
    List<ListProduitsAchete> listProduitsAchetes = new ArrayList<>();

    List<Commande> commandes = new ArrayList<>();

    private final ObservableList<Commande> data = FXCollections.observableArrayList();

    private final ObservableList<ListProduitsAchete> dataProduitAchete = FXCollections.observableArrayList();

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
        commandes =serviceCommande.readAll() ;
        data.addAll(commandes);


        dateCommande.setCellValueFactory(new PropertyValueFactory<>("date"));
        prixCommande.setCellValueFactory(new PropertyValueFactory<>("prix"));

        tableCommande.setItems(data);
        tableCommande.setEditable(true);

        dateCommande.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        prixCommande.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        tableCommande.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int commande_id = tableCommande.getSelectionModel().getSelectedItem().getId();
                try {
                    dataProduitAchete.clear();
                    listProduitsAchetes = serviceListProduitsAchete.readCertain(commande_id);
                    dataProduitAchete.addAll(listProduitsAchetes);

                    qteProduit.setCellValueFactory(new PropertyValueFactory<>("qte"));
                    tableProduits.setItems(dataProduitAchete);
                    qteProduit.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));



                    //afficherProduitAcheter(commande_id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void afficherProduitAcheter(int commande_id) throws SQLException {
        dataProduitAchete.clear();
        listProduitsAchetes = serviceListProduitsAchete.readCertain(commande_id);
        for(ListProduitsAchete list : listProduitsAchetes) {
            dataProduitAchete.add(list);
        }
       // System.out.println(listProduitsAchetes);
        produitProduit.setCellValueFactory(new PropertyValueFactory<>("produit"));
        //puProduit.setCellValueFactory(new PropertyValueFactory<>("prix"));
        //qteProduit.setCellValueFactory(new PropertyValueFactory<>("qte"));
        //ptProduit.setCellValueFactory(new PropertyValueFactory<>("prix"));

        tableProduits.setItems(dataProduitAchete);
        //tableProduits.setEditable(true);

        produitProduit.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        //puProduit.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        //qteProduit.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        //ptProduit.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
    }
}
