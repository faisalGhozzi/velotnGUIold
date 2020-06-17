package com.velotn.Ui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.velotn.Entite.Produit;
import com.velotn.Entite.Promotion;
import com.velotn.Service.PromotionService;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class PromotionUiController implements Initializable {
    @FXML
    private JFXTextField tftype;

    @FXML
    private JFXTextField tftaux;

    @FXML
    private JFXComboBox<Integer> combo;

    @FXML
    private TableView<Promotion> tablePromotion;

    @FXML
    private TableColumn<Promotion, String> typepromotion;

    @FXML
    private TableColumn<Promotion, Number> idproduit;

    @FXML
    private TableColumn<Promotion, Number> Prix;

    @FXML
    private TableColumn<Promotion, Float> tauxpromotion;

    @FXML
    private JFXTextField recherche;


    Pattern floatPattern = Pattern.compile("-?\\d*");
    UnaryOperator<TextFormatter.Change> filter = change -> {
        if(floatPattern.matcher(change.getControlNewText()).matches()){
            return change;
        }
        return null;
    };

    TextFormatter textFormatter = new TextFormatter(filter);


    PromotionService ps = new PromotionService();

    List<Promotion> promotions = new ArrayList<>() ;
    private final ObservableList<Promotion> data = FXCollections.observableArrayList() ;
    private final ObservableList<Integer> dataid = FXCollections.observableArrayList(ps.get_id_product());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tftaux.setTextFormatter(textFormatter);
        combo.setItems(dataid);
        afficher();
        RechercheAV();
    }

    void afficher(){
        data.clear();
        promotions =ps.displaAll() ;
        for (Promotion promotion : promotions) {
            data.add(promotion);
        }
        //idpromotion.setCellValueFactory(new PropertyValueFactory<>("id")) ;
        typepromotion.setCellValueFactory(new PropertyValueFactory<>("type"));
        tauxpromotion.setCellValueFactory(new PropertyValueFactory<>("taux"));
        idproduit.setCellValueFactory(e-> new SimpleIntegerProperty(e.getValue().getPro().getId()));
        Prix.setCellValueFactory(e-> new SimpleFloatProperty(e.getValue().getPro().getPrix()));

        tablePromotion.setItems(data);
        tablePromotion.setEditable(true);

        //idpromotion.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter())) ;
        typepromotion.setCellFactory(TextFieldTableCell.forTableColumn());
        tauxpromotion.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        idproduit.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter())) ;
        Prix.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));



    }

    @FXML
    void ajouter(ActionEvent event) throws SQLException {
        List<String> mails = ps.getUsersMails()  ;
        String typeP = tftype.getText();
        float tauxp =Float.parseFloat(tftaux.getText()) ;
        JFXComboBox tmpcmb = combo;
        Produit pr = ps.rechercheP(Integer.parseInt(tmpcmb.getValue().toString())) ;
        Promotion p = new Promotion(typeP,tauxp,pr);
        int id =ps.insertPromotion(p);
        ps.Updateprix(id,Integer.parseInt(tmpcmb.getValue().toString()));
        afficher();
        RechercheAV();
        float promo = pr.getPrix() - (pr.getPrix()*Integer.parseInt(tftaux.getText()))/100;
        String bd = " Voici Notre Nouvlle Prommtion Pour le Produit  : "+pr.getNomProduit()+"\n Description : "+pr.getDescription()+"\n Ancien Prix : "+pr.getPrix()+"\nNouveau Prix"+promo+"\n Type de Produit : "+typeP+"\n Taux de Promotion : "+tauxp+"%";
        for(String mail : mails)
        {
            SendMail.sendMail(mail,"Promotion",bd);
        }
        tftaux.setText("");
        tftype.setText("");
    }

    @FXML
    void supprimer(ActionEvent event) {
        tablePromotion.setItems(data);

        ObservableList<Promotion> allDemandes,SingleDemandes ;
        allDemandes=tablePromotion.getItems();
        SingleDemandes=tablePromotion.getSelectionModel().getSelectedItems();
        Promotion A = SingleDemandes.get(0);
        PromotionService STP = new PromotionService();
        STP.Delete(A.getId());
        SingleDemandes.forEach(allDemandes::remove);
        afficher();
        RechercheAV();
    }

    public void RechercheAV(){

        FilteredList<Promotion> filteredData = new FilteredList<>(data, b -> true);

        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tab_promotion -> {


                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }


                String lowerCaseFilter = newValue.toLowerCase();

                if (tab_promotion.getType().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true;
                }
                else if (String.valueOf(tab_promotion.getId()).indexOf(lowerCaseFilter)!=-1)
                    return true;
                else
                    return false;
            });
        });

        SortedList<Promotion> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tablePromotion.comparatorProperty());


        tablePromotion.setItems(sortedData);

    }
    @FXML
    private void Change_Type(TableColumn.CellEditEvent bb ) {
        Promotion tab_Promotionelected =tablePromotion.getSelectionModel().getSelectedItem();
        tab_Promotionelected.setType(bb.getNewValue().toString());
        ps.Update(tab_Promotionelected.getId(),tab_Promotionelected.getType(),tab_Promotionelected.getTaux());
    }


}
