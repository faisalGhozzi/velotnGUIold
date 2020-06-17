package com.velotn.Ui;

import com.jfoenix.controls.JFXDatePicker;
import com.velotn.Entite.Location;
import com.velotn.Service.LocationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LocationUiController implements Initializable {

    @FXML
    private Pane locationPane;

    @FXML
    private Label idLabelRent;

    @FXML
    private JFXDatePicker date_debut;

    @FXML
    private JFXDatePicker date_fin;

    @FXML
    private Label labelTypeProduit;

    @FXML
    private Label labelDescription;

    @FXML
    private Label labelPrix;

    LocationService ls = new LocationService() ;

    public void setIdLabelRent(String text){
        idLabelRent.setText(text);
    }

    @FXML
    void Ajouter(ActionEvent event) {
        if (date_debut.getValue() == null || date_debut.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir tout les champs");
            alert.showAndWait();
        } else {
            DatePicker tmpdated=(DatePicker) date_debut;
            String dated= (String) tmpdated.getValue().toString();
            DatePicker tmpdatef=(DatePicker) date_fin;
            String datef= (String) tmpdatef.getValue().toString();
            Location l = null;
            System.out.println(locationPane.getChildren().toString());
            /*try {
                //l = new Location(ls.rechercheu(1, dated, datef,ls.recherchev()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int id = ls.insertLocation(l);*/

            //ls.UpdatePrix(id,ls.calculer(Integer.parseInt(tmpcmbv.getValue().toString())),Integer.parseInt(tmpcmbv.getValue().toString()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
