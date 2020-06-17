package com.velotn.Ui;

import com.velotn.Entite.Accessoire;
import com.velotn.Entite.Piece_Rechange;
import com.velotn.Entite.Produit;
import com.velotn.Entite.Velo;
import com.velotn.Service.ServiceProduit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class AjouterProduitController implements Initializable {
    @FXML
    private TableView<Produit> listeProduits;

    @FXML
    private TableView<Velo> listeVelos;

    @FXML
    private TableView<Piece_Rechange> listePieces;

    @FXML
    private TableView<Accessoire> listeAccessoires;


    @FXML
    private TableColumn<Produit, String> Nom_Produit ;

    @FXML
    private TableColumn<Produit, String> Description ;

    @FXML
    private TableColumn<Produit, Float> Prix;

    @FXML
    private TableColumn<Produit, String> Marque ;

    @FXML
    private TableColumn<Produit, String> Type ;

    @FXML
    private TableColumn<Produit, Integer> Quantite ;

    @FXML
    private TableColumn<Velo, String> nomV;

    @FXML
    private TableColumn<Velo, String> DescriptionV;

    @FXML
    private TableColumn<Velo, Float> PrixV;

    @FXML
    private TableColumn<Velo, String> MarqueV;

    @FXML
    private TableColumn<Velo, String> TypeV;

    @FXML
    private TableColumn<Velo, Integer> QuantiteV;

    @FXML
    private TableColumn<Piece_Rechange, String> nomP;

    @FXML
    private TableColumn<Piece_Rechange, String> descriptionP;

    @FXML
    private TableColumn<Piece_Rechange, Float> prixP;

    @FXML
    private TableColumn<Piece_Rechange, String> marqueP;

    @FXML
    private TableColumn<Piece_Rechange, String> typeP;

    @FXML
    private TableColumn<Piece_Rechange, Integer> quantiteP;

    @FXML
    private TableColumn<Accessoire, String> nomA;

    @FXML
    private TableColumn<Accessoire, String> descriptionA;

    @FXML
    private TableColumn<Accessoire, Float> prixA;

    @FXML
    private TableColumn<Accessoire, String> marqueA;

    @FXML
    private TableColumn<Accessoire, String> typeA;

    @FXML
    private TableColumn<Accessoire, Integer> quantiteA;

    @FXML
    private TextField TF_nomProd;

    @FXML
    private TextField TF_description;

    @FXML
    private TextField TF_prix;

    @FXML
    private TextField TF_quantite;

    @FXML
    private TextField TF_marque;

    @FXML
    private TextField TF_type;

    @FXML
    private TextField recherche;

    @FXML
    private Button ImageProduit;

    @FXML
    private TextField NomImage;

    Pattern floatPattern = Pattern.compile("-?\\d*");
    UnaryOperator<TextFormatter.Change> filter = change -> {
        if(floatPattern.matcher(change.getControlNewText()).matches()){
            return change;
        }
        return null;
    };

    TextFormatter textFormatter = new TextFormatter(filter);


    private final ObservableList<Produit> data = FXCollections.observableArrayList();
    private final ObservableList<Velo> dataV = FXCollections.observableArrayList();
    private final ObservableList<Piece_Rechange> dataP = FXCollections.observableArrayList();
    private final ObservableList<Accessoire> dataA = FXCollections.observableArrayList();





    private List<Produit> produits = new ArrayList<>();
    private List<Velo> velos = new ArrayList<>();
    private List<Piece_Rechange> Pieces = new ArrayList<>();
    private List<Accessoire> accessoires = new ArrayList<>();
    ServiceProduit sp = new ServiceProduit();

    /*public Velo(int id, String nomProduit, String description, float prix,int quantite, String marque, String type,String img_url) {
        super(id, nomProduit, description, prix,quantite,img_url);
        this.setMarque(marque);
        this.setType(type);
    }*/

    private void listProducts()
    {
        produits= sp.displayProducts();
        data.clear();


        for (Produit produit : produits) {
            data.add(produit);
        }
        //identifiant.setCellValueFactory(new PropertyValueFactory<>("id"));
        Nom_Produit.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        Marque.setCellValueFactory(new PropertyValueFactory<>("marque"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        listeProduits.setItems(data);

        //identifiant.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Nom_Produit.setCellFactory(TextFieldTableCell.forTableColumn());
        Description.setCellFactory(TextFieldTableCell.forTableColumn());
        Prix.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        Marque.setCellFactory(TextFieldTableCell.forTableColumn());
        Type.setCellFactory(TextFieldTableCell.forTableColumn());
        Quantite.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

    }

    private void listVelos() {

        velos= sp.displayVelos();
        dataV.clear();


        for (Velo velo : velos) {
            dataV.add(velo);

        }

        nomV.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        DescriptionV.setCellValueFactory(new PropertyValueFactory<>("description"));
        PrixV.setCellValueFactory(new PropertyValueFactory<>("prix"));
        MarqueV.setCellValueFactory(new PropertyValueFactory<>("marque"));
        TypeV.setCellValueFactory(new PropertyValueFactory<>("type"));
        QuantiteV.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        listeVelos.setItems(dataV);
        listeVelos.setEditable(true);

        nomV.setCellFactory(TextFieldTableCell.forTableColumn());
        DescriptionV.setCellFactory(TextFieldTableCell.forTableColumn());
        PrixV.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        MarqueV.setCellFactory(TextFieldTableCell.forTableColumn());
        TypeV.setCellFactory(TextFieldTableCell.forTableColumn());
        QuantiteV.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));



    }

    private void listPieces() {
        Pieces= sp.displayPieces();
        dataP.clear();


        for (Piece_Rechange piece : Pieces) {
            dataP.add(piece);

        }

        nomP.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        descriptionP.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixP.setCellValueFactory(new PropertyValueFactory<>("prix"));
        marqueP.setCellValueFactory(new PropertyValueFactory<>("marque"));
        typeP.setCellValueFactory(new PropertyValueFactory<>("type"));
        quantiteP.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        listePieces.setItems(dataP);
        listePieces.setEditable(true);


        nomP.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionP.setCellFactory(TextFieldTableCell.forTableColumn());
        prixP.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        marqueP.setCellFactory(TextFieldTableCell.forTableColumn());
        typeP.setCellFactory(TextFieldTableCell.forTableColumn());
        quantiteP.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

    }
    private void listAccessoires() {
        accessoires= sp.displayAccesoires();
        dataA.clear();


        for (Accessoire accessoire : accessoires) {
            dataA.add(accessoire);

        }
        nomA.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
        descriptionA.setCellValueFactory(new PropertyValueFactory<>("description"));
        prixA.setCellValueFactory(new PropertyValueFactory<>("prix"));
        marqueA.setCellValueFactory(new PropertyValueFactory<>("marque"));
        typeA.setCellValueFactory(new PropertyValueFactory<>("type"));
        quantiteA.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        listeAccessoires.setItems(dataA);
        listeAccessoires.setEditable(true);

        nomA.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionA.setCellFactory(TextFieldTableCell.forTableColumn());
        prixA.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        marqueA.setCellFactory(TextFieldTableCell.forTableColumn());
        typeA.setCellFactory(TextFieldTableCell.forTableColumn());
        quantiteA.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

    }


    @FXML
    private void AjouterVelo()
    {
        sp.insertVelo(new Velo(TF_nomProd.getText(), TF_description.getText(), Float.parseFloat(TF_prix.getText()), Integer.parseInt(TF_quantite.getText()), TF_marque.getText(), TF_type.getText(),NomImage.getText()));
        listVelos();
        listProducts();

    }

    @FXML
    private void AjouterPiece()
    {
        sp.insetPieceR(new Piece_Rechange(TF_nomProd.getText(), TF_description.getText(), Float.parseFloat(TF_prix.getText()), Integer.parseInt(TF_quantite.getText()), TF_marque.getText(), TF_type.getText(),NomImage.getText()));
        listPieces();
        listProducts();

    }

    @FXML
    private void AjouterAccessoire()
    {
        sp.insertAccessoire(new Accessoire(TF_nomProd.getText(), TF_description.getText(), Float.parseFloat(TF_prix.getText()), Integer.parseInt(TF_quantite.getText()), TF_marque.getText(), TF_type.getText(),NomImage.getText()));
        listAccessoires();
        listProducts();

    }

    @FXML
    private void SupprimerVelo()
    {
        listeVelos.setItems(dataV);

        ObservableList<Velo> allDemandes,SingleDemandes ;
        allDemandes=listeVelos.getItems();
        SingleDemandes=listeVelos.getSelectionModel().getSelectedItems();
        Velo v = SingleDemandes.get(0);
        sp.delete(v);

        SingleDemandes.forEach(allDemandes::remove);
        listProducts();

    }

    @FXML
    private String selectImage()
    {
        final FileChooser image = new FileChooser();
        image.setTitle("Choisir une image ");
        String path = image.showOpenDialog(ImageProduit.getScene().getWindow()).getName();
        NomImage.setText(path);
        return "http://localhost/velotnproducts/"+path;
    }

    @FXML
    private void SupprimerPiece()
    {
        listePieces.setItems(dataP);

        ObservableList<Piece_Rechange> allDemandes,SingleDemandes ;
        allDemandes=listePieces.getItems();
        SingleDemandes=listePieces.getSelectionModel().getSelectedItems();
        Piece_Rechange p = SingleDemandes.get(0);
        sp.delete(p);

        SingleDemandes.forEach(allDemandes::remove);
        listProducts();
    }

    @FXML
    private void SupprimerAccessoire()
    {
        listeAccessoires.setItems(dataA);

        ObservableList<Accessoire> allDemandes,SingleDemandes ;
        allDemandes=listeAccessoires.getItems();
        SingleDemandes=listeAccessoires.getSelectionModel().getSelectedItems();
        Accessoire a = SingleDemandes.get(0);
        sp.delete(a);
        SingleDemandes.forEach(allDemandes::remove);
        listProducts();
    }


    @FXML
    public void Changer_NomProduitVelo(TableColumn.CellEditEvent evt) {
        Velo selectedVelo = listeVelos.getSelectionModel().getSelectedItem();
        selectedVelo.setNomProduit(evt.getNewValue().toString());
        sp.update(selectedVelo);
        listProducts();
    }
    @FXML
    public void Changer_DescriptionVelo(TableColumn.CellEditEvent evt) {
        Velo selectedVelo = listeVelos.getSelectionModel().getSelectedItem();
        selectedVelo.setDescription(evt.getNewValue().toString());
        sp.update(selectedVelo);
        listProducts();
    }
    @FXML
    public void Changer_PrixVelo(TableColumn.CellEditEvent evt) {
        Velo selectedVelo = listeVelos.getSelectionModel().getSelectedItem();
        selectedVelo.setPrix(Float.parseFloat(evt.getNewValue().toString()));
        sp.update(selectedVelo);
        listProducts();
    }
    @FXML
    public void Changer_QuantiteVelo(TableColumn.CellEditEvent evt) {
        Velo selectedVelo = listeVelos.getSelectionModel().getSelectedItem();
        selectedVelo.setQuantite(Integer.parseInt(evt.getNewValue().toString()));
        sp.update(selectedVelo);
        listProducts();
    }

    @FXML
    public void Changer_NomProduitPiece(TableColumn.CellEditEvent evt) {
        Piece_Rechange selectedPiece = listePieces.getSelectionModel().getSelectedItem();
        selectedPiece.setNomProduit(evt.getNewValue().toString());
        sp.update(selectedPiece);
        listProducts();
    }
    @FXML
    public void Changer_DescriptionPiece(TableColumn.CellEditEvent evt) {
        Piece_Rechange selectedPiece = listePieces.getSelectionModel().getSelectedItem();
        selectedPiece.setDescription(evt.getNewValue().toString());
        sp.update(selectedPiece);
        listProducts();
    }
    @FXML
    public void Changer_PrixPiece(TableColumn.CellEditEvent evt) {
        Piece_Rechange selectedPiece = listePieces.getSelectionModel().getSelectedItem();
        selectedPiece.setPrix(Float.parseFloat(evt.getNewValue().toString()));
        sp.update(selectedPiece);
        listProducts();
    }
    @FXML
    public void Changer_QuantitePiece(TableColumn.CellEditEvent evt) {
        Piece_Rechange selectedPiece = listePieces.getSelectionModel().getSelectedItem();
        selectedPiece.setQuantite(Integer.parseInt(evt.getNewValue().toString()));
        sp.update(selectedPiece);
        listProducts();
    }
    /**/
    @FXML
    public void Changer_NomProduitAcc(TableColumn.CellEditEvent evt) {
        Accessoire selectedAccessoire = listeAccessoires.getSelectionModel().getSelectedItem();
        selectedAccessoire.setNomProduit(evt.getNewValue().toString());
        sp.update(selectedAccessoire);
        listProducts();
    }
    @FXML
    public void Changer_DescriptionAcc(TableColumn.CellEditEvent evt) {
        Accessoire selectedAccessoire = listeAccessoires.getSelectionModel().getSelectedItem();
        selectedAccessoire.setDescription(evt.getNewValue().toString());
        sp.update(selectedAccessoire);
        listProducts();
    }
    @FXML
    public void Changer_PrixAcc(TableColumn.CellEditEvent evt) {
        Accessoire selectedAccessoire = listeAccessoires.getSelectionModel().getSelectedItem();
        selectedAccessoire.setPrix(Float.parseFloat(evt.getNewValue().toString()));
        sp.update(selectedAccessoire);
        listProducts();
    }
    @FXML
    public void Changer_QuantiteAcc(TableColumn.CellEditEvent evt) {
        Accessoire selectedAccessoire = listeAccessoires.getSelectionModel().getSelectedItem();
        selectedAccessoire.setQuantite(Integer.parseInt(evt.getNewValue().toString()));
        sp.update(selectedAccessoire);
        listProducts();
    }

    @FXML
    public void RechercheAV(){
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Produit> filteredData = new FilteredList<>(data, b -> true);

// 2. Set the filter Predicate whenever the filter changes.
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(produit -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (produit.getNomProduit().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (produit.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (String.valueOf(produit.getId()).indexOf(lowerCaseFilter)!=-1)
                    return true;
                else
                    return false; // Does not match.
            });
        });

// 3. Wrap the FilteredList in a SortedList.
        SortedList<Produit> sortedData = new SortedList<>(filteredData);

// 4. Bind the SortedList comparator to the TableView comparator.
// 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(listeProduits.comparatorProperty());

// 5. Add sorted (and filtered) data to the table.
        listeProduits.setItems(sortedData);
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TF_prix.setTextFormatter(textFormatter);
        //TF_quantite.setTextFormatter(textFormatter);
        listProducts();
        listVelos();
        listPieces();
        listAccessoires();



    }
}
