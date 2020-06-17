package com.velotn.Ui;

import javafx.scene.web.WebEngine;
import com.jfoenix.controls.JFXButton;
import com.velotn.Entite.Location;
import com.velotn.Entite.Promotion;
import com.velotn.Service.LocationService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AffichageLocationUiController implements Initializable {


    @FXML
    private Pane paneLocation;

    @FXML
    private TextField recherche;

    @FXML
    private TableView<Location> tablocation;

    @FXML
    private TableColumn<Location, Number> idvelo;

    @FXML
    private TableColumn<Location, String> dd;

    @FXML
    private TableColumn<Location, String> df;

    @FXML
    private TableColumn<Location, Integer> ididVelo;

    @FXML
    private TableColumn<Location, Float> prixtotal;

    @FXML
    private TableColumn<Location, Number> iduser;

    @FXML
    private JFXButton suppBtn;

    @FXML
    private JFXButton ImprimerBtn;

    @FXML
    private JFXButton Localisationbtn;

    @FXML
    private WebView webViewGps;

    LocationService ls = new LocationService() ;
    List<Location> locations = new ArrayList<>() ;

    private final ObservableList<Location> data = FXCollections.observableArrayList() ;
    private final ObservableList<Integer> dataidv = FXCollections.observableArrayList(ls.get_id_velo());
    private final ObservableList<Integer> dataidu = FXCollections.observableArrayList(ls.get_id_user());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Aff();
        RechercheAV();
        webViewGps.setVisible(false);
    }

    public void Aff() {
        data.clear();
        locations =ls.displaAll() ;
        for (Location location : locations) {
            data.add(location);
        }
        dd.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        df.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        iduser.setCellValueFactory(e-> new SimpleIntegerProperty(e.getValue().getUser().getId()));
        idvelo.setCellValueFactory(e-> new SimpleIntegerProperty(e.getValue().getVelo().getId()));
        iduser.setCellValueFactory(e-> new SimpleIntegerProperty(e.getValue().getUser().getId()));
        idvelo.setCellValueFactory(e-> new SimpleIntegerProperty(e.getValue().getVelo().getId()));
        prixtotal.setCellValueFactory(new PropertyValueFactory<>("prixtotal"));
        tablocation.setItems(data);
        tablocation.setEditable(true);
        ididVelo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        dd.setCellFactory(TextFieldTableCell.forTableColumn());
        df.setCellFactory(TextFieldTableCell.forTableColumn());
        iduser.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter())) ;
        idvelo.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        prixtotal.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
    }

    @FXML
    void buttonSupprimer(ActionEvent event) {
        tablocation.setItems(data);
        ObservableList<Location> allDemandes,SingleDemandes ;
        allDemandes=tablocation.getItems();
        SingleDemandes=tablocation.getSelectionModel().getSelectedItems();
        Location A = SingleDemandes.get(0);
        LocationService STP = new LocationService();
        STP.DeleteUserLocation(A.getId());
        SingleDemandes.forEach(allDemandes::remove);
        Aff();
        RechercheAV();
    }

    @FXML
    private void change_dd(TableColumn.CellEditEvent bb) {
        Location tab_Promotionelected =tablocation.getSelectionModel().getSelectedItem();
        tab_Promotionelected.setDate_debut(bb.getNewValue().toString());
        ls.Update(tab_Promotionelected.getId(),tab_Promotionelected.getDate_debut(),tab_Promotionelected.getDate_fin());
        ls.UpdatePrix(tab_Promotionelected.getId(),ls.calculer(tab_Promotionelected.getVelo().getId()),tab_Promotionelected.getVelo().getId());
        Aff();
        RechercheAV();
    }

    @FXML
    private void change_df(TableColumn.CellEditEvent bb) {
        Location tab_Promotionelected =tablocation.getSelectionModel().getSelectedItem();
        tab_Promotionelected.setDate_fin(bb.getNewValue().toString());
        ls.Update(tab_Promotionelected.getId(),tab_Promotionelected.getDate_debut(),tab_Promotionelected.getDate_fin());
        ls.UpdatePrix(tab_Promotionelected.getId(),ls.calculer(tab_Promotionelected.getVelo().getId()),tab_Promotionelected.getVelo().getId());
        Aff();
        RechercheAV();
    }

    public void RechercheAV() {
        FilteredList<Location> filteredData = new FilteredList<>(data, b -> true);
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tab_location -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(tab_location.getPrixtotal()).indexOf(lowerCaseFilter) != -1 ) {
                    return true;
                }
                else if (String.valueOf(tab_location.getId()).indexOf(lowerCaseFilter)!=-1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<Location> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablocation.comparatorProperty());
        tablocation.setItems(sortedData);
    }
    @FXML
    private void telecharger(ActionEvent event) throws SQLException, FileNotFoundException {
        Location res = new Location();
        res = tablocation.getSelectionModel().getSelectedItem();
        ReservationPdf pdf = new ReservationPdf();
        pdf.Location(
                res.getId(),
                res.getDate_debut(),
                res.getDate_fin(),
                res.getPrixtotal(),
                res.getVelo()
        );
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Pdf Reservation ");
        alert.setContentText("votre pdf est telecharger avec succès");
        alert.showAndWait();
        if (Desktop.isDesktopSupported()) {
            File myFile = new File("C:\\Users\\user\\IdeaProjects\\VelotnGUI/Reservation" + res.getId()+ ".pdf");
            try {
               Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                Logger.getLogger(AffichageLocationUiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    @FXML
    void open_gps(ActionEvent event) {
        tablocation.setVisible(false);
        suppBtn.setVisible(false);
        recherche.setVisible(false);
        ImprimerBtn.setVisible(false);
        Localisationbtn.setVisible(false);
        webViewGps.setVisible(true);
        WebEngine webEngine = webViewGps.getEngine();
        webEngine.load("http://192.168.43.18:5500/index.html");
    }
}
