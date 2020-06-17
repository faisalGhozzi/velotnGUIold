package com.velotn.Ui;

import com.jfoenix.controls.JFXTextField;
import com.velotn.Entite.Reclamation;
import com.velotn.Service.ServiceReclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReclamationUiController implements Initializable {
    ServiceReclamation sr = new ServiceReclamation();
    @FXML
    private JFXTextField idUser;

    @FXML
    private Pane PaneEtat;

    @FXML
    private JFXTextField descriptionR;

    @FXML
    private JFXTextField etat;

    @FXML
    private TableView<Reclamation> listeReclamations;

    @FXML
    private TableColumn<Reclamation, Integer> id;

    @FXML
    private TableColumn<Reclamation, Integer> idUserR;

    @FXML
    private TableColumn<Reclamation, String> description;

    @FXML
    private TableColumn<Reclamation, Integer> etatR;

    private final ObservableList<Reclamation> data = FXCollections.observableArrayList();
    private List<Reclamation> reclamations = new ArrayList<>();


        private void listeReclamations() throws SQLException
        {
            listeReclamations.setItems(null);
            data.clear();
            reclamations = sr.readRec(Controller.getUserId());
            for(Reclamation reclamation:reclamations)
            {
                data.add(reclamation);
            }

            idUserR.setCellValueFactory(new PropertyValueFactory<>("idUser"));
            description.setCellValueFactory(new PropertyValueFactory<>("descriptionR"));
            etatR.setCellValueFactory(new PropertyValueFactory<>("etat"));

            listeReclamations.setItems(data);
            listeReclamations.setEditable(true);

            idUserR.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            description.setCellFactory(TextFieldTableCell.forTableColumn());
            etatR.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

            listeReclamations.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    if(listeReclamations.getSelectionModel().getSelectedItem().getEtat()==1)
                    {
                        Label con = new Label("Reclamation Traitée");
                        con.setStyle("-fx-text-fill: green;");
                        PaneEtat.getChildren().clear();
                        PaneEtat.getChildren().add(con);
                    }
                    if(listeReclamations.getSelectionModel().getSelectedItem().getEtat()==0)
                    {

                        Label con = new Label("Reclamation Non Traitée");
                        con.setStyle("-fx-text-fill: red;");
                        PaneEtat.getChildren().clear();
                        PaneEtat.getChildren().add(con);
                    }

                }



            });
        }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            listeReclamations();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationUiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void ajouterReclamation(ActionEvent event) throws SQLException {
        sr.ajouter(new Reclamation(Controller.getUserId(), descriptionR.getText(), 0));
        listeReclamations();
    }

    @FXML
    void SupprimerReclamation(ActionEvent event) throws SQLException
    {
        ObservableList<Reclamation> tous,Single ;
        tous=listeReclamations.getItems();
        Single=listeReclamations.getSelectionModel().getSelectedItems();
        Reclamation r = Single.get(0);
        sr.delete(r);
        Single.forEach(tous::remove);
    }
    public void Change_idUser(TableColumn.CellEditEvent bb) throws SQLException{
        Reclamation Reclamationselected = listeReclamations.getSelectionModel().getSelectedItem();
        Reclamationselected.setIdUser(Integer.parseInt(bb.getNewValue().toString()));
        sr.update(Reclamationselected);
    }
    public void Change_descriptionR(TableColumn.CellEditEvent bb) throws SQLException{
        Reclamation Reclamationselected = listeReclamations.getSelectionModel().getSelectedItem();
        Reclamationselected.setDescriptionR(bb.getNewValue().toString());
        sr.update(Reclamationselected);
    }
    public void Change_etat(TableColumn.CellEditEvent bb) throws SQLException{
        Reclamation Reclamationselected = listeReclamations.getSelectionModel().getSelectedItem();
        Reclamationselected.setEtat(Integer.parseInt(bb.getNewValue().toString()));
        sr.update(Reclamationselected);
    }
}
