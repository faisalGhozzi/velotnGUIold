/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velotn.Ui;

import static com.velotn.Ui.Controller.getUserId;
import static com.velotn.Ui.Controller.holderPane;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import com.velotn.Entite.Groups;
import static com.velotn.Entite.Groups.getGroupe_courant;
import com.velotn.Entite.GroupsMembers;
import com.velotn.Entite.PublicationGroup;
import com.velotn.Entite.SignalGroup;
import com.velotn.Entite.User;
import com.velotn.Service.GroupeService;
import com.velotn.Service.GroupsMembersService;
import com.velotn.Service.PublicationGroupService;
import com.velotn.Service.SignalGroupService;
import com.velotn.Service.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Farah GABSI
 */
public class profilGroupeController implements Initializable {

    GroupeService service = new GroupeService();
    final GroupsMembersService p = new GroupsMembersService();

    UserService sd = new UserService();
    PublicationGroupService ser = new PublicationGroupService();
    SignalGroupService sig = new SignalGroupService();
    @FXML
    private ImageView p_image;

    @FXML
    private Label p_desc;
    @FXML
    private Text date2;
    @FXML
    private TextField reclamaer;
    @FXML
    private Button reclamation;
    @FXML
    private VBox vboxStatus;
    @FXML
    private MenuItem modifierStatus;
    @FXML
    private MenuItem supprimerStatus;

    /**
     * Initializes the controller class.
     */
    public Groups EvenementCourant = new Groups();

    @FXML
    private VBox part1;
    @FXML
    private VBox annu1;
    @FXML
    private VBox nom1;
    @FXML
    private VBox nomG;
    @FXML
    private Label nomg;
    @FXML
    private VBox modif;
    @FXML
    private VBox supp;
    Label nbr;
    @FXML
    private Button signaler;
    @FXML
    private MenuButton action;
    @FXML
    private Label lieu;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EvenementCourant = service.getGroupeById(EvenementCourant.getGroupe_courant());

        System.out.println("a3333333333" + EvenementCourant.getGroupe_courant());
        nomg.setText(EvenementCourant.getNom());
        lieu.setText(EvenementCourant.getLieu());
        date2.setText(String.valueOf(EvenementCourant.getDate_de_creation()));
        p_desc.setText(EvenementCourant.getDescription());
        String A = EvenementCourant.getImage();
        A = "C:\\wamp64\\www\\velo\\src\\Images\\" + A;
        File F1 = new File(A);
        Image image2 = new Image(F1.toURI().toString());
        p_image.setImage(image2);

        FontAwesomeIconView fb = new FontAwesomeIconView(FontAwesomeIcon.FACEBOOK_SQUARE);
        fb.setFill(javafx.scene.paint.Color.BLUE);
        fb.setSize("50");

        fb.setCursor(Cursor.HAND);

        fb.setLayoutX(150);
        fb.setLayoutY(240);
        fb.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("te5demmm ");

            Groups EvenementCourantt = new Groups();

            EvenementCourantt = service.getGroupeById(EvenementCourantt.getGroupe_courant());

            System.out.println("FB succes!");

        });

        List<PublicationGroup> pulications = ser.getPublicationByGroup(EvenementCourant);
        vboxStatus.getChildren().clear();

        for (PublicationGroup p : pulications) {

            vboxStatus.getChildren().add(publicationItem(sd.getUserById(p.getIdUser()), p));

        }

        rejoindre();

        modifier();
        nbr = new Label();
        nbr.setLayoutX(450);
        nbr.setLayoutY(280);
        nbr.setText(String.valueOf(EvenementCourant.getNbrMembre()));
        nom1.getChildren().add(nbr);
        JFXButton aff = new JFXButton();
        aff.setText("Membres");
        aff.setPrefHeight(25.0);
        aff.setPrefWidth(88.0);
        aff.setLayoutX(250);
        aff.setLayoutY(280);
        aff.setFont(new Font("Cambria", 15));
        aff.setStyle("-fx-background-color: 	#FF4500; ");
        aff.setTextFill(Color.web("#e8f8ff"));
        JFXButton demande = new JFXButton();
        demande.setText("Demandes");
        demande.setPrefHeight(25.0);
        demande.setPrefWidth(88.0);
        demande.setLayoutX(250);
        demande.setLayoutY(280);
        demande.setFont(new Font("Cambria", 15));
        demande.setStyle("-fx-background-color: 	#FF4500; ");
        demande.setTextFill(Color.web("#e8f8ff"));
        if (service.getGroupeById(Groups.getGroupe_courant()).getIdUser() == getUserId()) {
            nom1.getChildren().add(aff);
            nom1.getChildren().add(demande);
        }

        if (service.getGroupeById(Groups.getGroupe_courant()).getIdUser() == getUserId() || sig.getSignal(getUserId(), getGroupe_courant())) {
            signaler.setDisable(true);
        }

        aff.setOnMouseClicked(e -> {

            Alert dialog = new Alert(AlertType.INFORMATION);
            dialog.setTitle("Membres de  Groupe");
            dialog.setResizable(true);
            dialog.getDialogPane().setPrefSize(700, 320);

            dialog.setHeaderText(null);
            dialog.setGraphic(null);

            dialog.getDialogPane().setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(2), new Insets(2))));
            dialog.getDialogPane().setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            VBox l = new VBox();
            l.setPrefWidth(400);
            l.setPrefHeight(500);
            l.getChildren().add(userItem1(sd.getUserById(service.getGroupeById(getGroupe_courant()).getIdUser())));
            List<GroupsMembers> y = p.getMemberByIdGroup(getGroupe_courant());
            for (GroupsMembers g : y) {
                System.out.println("hhhhhhhhhhh" + g.getId());
                l.getChildren().add(userItem(sd.getUserById(g.getIdauthor())));

            }

            grid.add(l, 1, 0);

            dialog.getDialogPane().setContent(grid);
            dialog.showAndWait();

        });
        demande.setOnMouseClicked(e -> {
            Alert dialog = new Alert(AlertType.INFORMATION);
            dialog.setTitle("Demande d'inscription de  Groupe");
            dialog.setResizable(true);
            dialog.getDialogPane().setPrefSize(700, 320);
            dialog.setHeaderText(null);
            dialog.setGraphic(null);
            dialog.getDialogPane().setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(2), new Insets(2))));
            dialog.getDialogPane().setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            VBox l = new VBox();
            l.setPrefWidth(400);
            l.setPrefHeight(500);
            List<GroupsMembers> y = p.getMemberByIdGroupNon(getGroupe_courant());
            for (GroupsMembers g : y) {
                System.out.println("hhhhhhhhhhh" + g.getId());
                l.getChildren().add(userItem2(sd.getUserById(g.getIdauthor())));

            }

            grid.add(l, 1, 0);

            dialog.getDialogPane().setContent(grid);

            dialog.showAndWait();

        });

    }

    private HBox userItem(User user) {
        Font prefFont = new Font("System Bold", 12);
        HBox hBox = new HBox();
        hBox.setPrefSize(150, 60);
        ImageView userImage = new ImageView(getClass().getResource("../Images/" + user.getImage()).toExternalForm());
        userImage.setFitWidth(60);
        userImage.setFitHeight(60);
        userImage.setPickOnBounds(true);
        userImage.setPreserveRatio(true);
        userImage.setClip(new Circle(30, 30, 30));
        Label userName = new Label(user.getNom() + " " + user.getPrenom());
        userName.setPrefSize(200, 60);
        userName.setFont(prefFont);
        JFXButton sup = new JFXButton("Supprimer Membre");
        sup.setFont(new Font("Cambria", 15));
        sup.setStyle("-fx-background-color: #494c62; ");
        sup.setTextFill(Color.web("#e8f8ff"));

        HBox.setMargin(userName, new Insets(0, 0, 0, 5));
        HBox.setMargin(userImage, new Insets(5, 0, 0, 5));
        HBox.setMargin(sup, new Insets(10, 0, 0, 5));
        ObservableList<Node> childs = hBox.getChildren();
        childs.add(userImage);
        childs.add(userName);
        sup.setOnMouseClicked(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sure de vouloir supprimer le membre ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {

                participer.setText("Participer");

                p.decrement(service.getGroupeById(getGroupe_courant()), user);
                hBox.getChildren().clear();
                try {
                    //---
                    sleep(1500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(profilGroupeController.class.getName()).log(Level.SEVERE, null, ex);
                }

                //---
                loadView("/gui/ProfilGroupe.fxml");
            }

        });
        childs.add(sup);

        return hBox;
    }

    private HBox userItem2(User user) {
        Font prefFont = new Font("System Bold", 12);
        HBox hBox = new HBox();

        hBox.setPrefSize(150, 60);
        ImageView userImage = new ImageView(getClass().getResource("../Images/" + user.getImage()).toExternalForm());
        userImage.setFitWidth(60);
        userImage.setFitHeight(60);
        userImage.setPickOnBounds(true);
        userImage.setPreserveRatio(true);
        userImage.setClip(new Circle(30, 30, 30));
        Label userName = new Label(user.getNom() + " " + user.getPrenom());

        userName.setPrefSize(200, 60);
        userName.setFont(prefFont);
        JFXButton sup = new JFXButton("Refuser Membre");
        sup.setFont(new Font("Cambria", 15));
        sup.setStyle("-fx-background-color: #494c62; ");
        sup.setTextFill(Color.web("#e8f8ff"));
        JFXButton accepter = new JFXButton("Accepter Membre");
        accepter.setFont(new Font("Cambria", 15));
        accepter.setStyle("-fx-background-color: #494c62; ");
        accepter.setTextFill(Color.web("#e8f8ff"));

        HBox.setMargin(userName, new Insets(0, 0, 0, 5));
        HBox.setMargin(userImage, new Insets(5, 0, 0, 5));
        HBox.setMargin(accepter, new Insets(10, 0, 0, 5));

        HBox.setMargin(sup, new Insets(15, 0, 0, 5));

        ObservableList<Node> childs = hBox.getChildren();
        childs.add(userImage);
        childs.add(userName);
        childs.add(accepter);
        accepter.setOnMouseClicked(e -> {
            participer.setText("Deja Membre");
            annuler.setVisible(true);

            nbr.setText(String.valueOf(Integer.parseInt(nbr.getText()) + 1));
            service.getGroupeById(getGroupe_courant()).setNbrMembre(Integer.parseInt(nbr.getText()) - 1);
            p.increment1(service.getGroupeById(getGroupe_courant()), user);
            System.out.println("gggg" + user.getTel());
            hBox.getChildren().clear();

            loadView("/gui/ProfilGroupe.fxml");

        });

        sup.setOnMouseClicked(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sure de vouloir refuser l'inscription ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                nbr.setText(String.valueOf(Integer.parseInt(nbr.getText()) - 1));
                participer.setText("Participer");
                p.decrement1(service.getGroupeById(getGroupe_courant()), user);
                hBox.getChildren().clear();        //-------
                try {
                    sleep(1500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(profilGroupeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                loadView("/gui/ProfilGroupe.fxml");
            }

        });
        childs.add(sup);

        return hBox;
    }

    private HBox userItem1(User user) {
        Font prefFont = new Font("System Bold", 12);
        HBox hBox = new HBox();
        hBox.setPrefSize(150, 60);
        ImageView userImage = new ImageView(getClass().getResource("../Images/" + user.getImage()).toExternalForm());
        userImage.setFitWidth(60);
        userImage.setFitHeight(60);
        userImage.setPickOnBounds(true);
        userImage.setPreserveRatio(true);
        userImage.setClip(new Circle(30, 30, 30));
        Label userName = new Label(user.getNom() + " " + user.getPrenom());
        userName.setPrefSize(200, 60);
        userName.setFont(prefFont);

        HBox.setMargin(userName, new Insets(0, 0, 0, 5));
        HBox.setMargin(userImage, new Insets(5, 0, 0, 5));
        ObservableList<Node> childs = hBox.getChildren();
        childs.add(userImage);
        childs.add(userName);

        return hBox;
    }
    JFXButton participer;
    JFXButton annuler;

    private void rejoindre() {
        participer = new JFXButton();
        participer.setText("Participer");
        participer.setPrefHeight(25.0);
        participer.setPrefWidth(88.0);
        participer.setLayoutX(250);
        participer.setLayoutY(280);
        annuler = new JFXButton();
        annuler.setText("Annuler");
        annuler.setPrefHeight(25.0);
        annuler.setPrefWidth(88.0);
        annuler.setLayoutX(350);
        annuler.setLayoutY(50);
        participer.setFont(new Font("Cambria", 15));
        participer.setStyle("-fx-background-color: #494c62; ");
        participer.setTextFill(Color.web("#e8f8ff"));
        if (p.checkAcceptation(EvenementCourant, sd.getUserById(getUserId()))) {
            participer.setText("En attente");

            participer.setDisable(true);
            annuler.setVisible(false);
        } else if (p.checkParticipation(EvenementCourant, sd.getUserById(getUserId()))) {
            participer.setText("Deja Membre");
            participer.setDisable(true);
            annuler.setDisable(false);
        } else {
            participer.setDisable(false);
            annuler.setDisable(true);
        }
        participer.setOnMouseClicked(s
                -> {
            participer.setText("En attente");
            annuler.setVisible(false);

            p.increment(service.getGroupeById(getGroupe_courant()), sd.getUserById(getUserId()));
            participer.setDisable(true);
        });

        annuler.setFont(new Font("Cambria", 15));
        annuler.setStyle("-fx-background-color: #494c62; ");
        annuler.setTextFill(Color.web("#e8f8ff"));
        annuler.setOnMouseClicked(k
                -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sure de vouloir annuler l'inscription ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                nbr.setText(String.valueOf(Integer.parseInt(nbr.getText()) - 1));
                p.decrement(service.getGroupeById(getGroupe_courant()), sd.getUserById(getUserId()));
                participer.setDisable(false);
                annuler.setDisable(true);            //-------
                try {

                    sleep(1500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(profilGroupeController.class.getName()).log(Level.SEVERE, null, ex);
                }

                //---
                loadView("/gui/ProfilGroupe.fxml");
            }

        });
        if (service.getGroupeById(Groups.getGroupe_courant()).getIdUser() == getUserId()) {
            participer.setDisable(true);
            annuler.setDisable(true);
        }

        part1.getChildren().add(participer);
        annu1.getChildren().add(annuler);

    }

    private AnchorPane publicationItem(User user, PublicationGroup publication) {
        Font prefFont = new Font("System Bold", 12);
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(435, 128);
        ImageView userImage = new ImageView(getClass().getResource("C:\\wamp64\\www\\velotnproducts\\" + sd.getUserById(ser.getPublicationById(publication.getId()).getIdUser()).getImage()).toExternalForm());

        userImage.setFitWidth(60);
        userImage.setFitHeight(60);
        userImage.setPickOnBounds(true);
        userImage.setPreserveRatio(true);
        userImage.setClip(new Circle(30, 30, 30));
        Label userName = new Label(sd.getUserById(ser.getPublicationById(publication.getId()).getIdUser()).getPrenom()
                + " " + sd.getUserById(ser.getPublicationById(publication.getId()).getIdUser()).getNom());
        userName.setLayoutX(62);
        userName.setLayoutY(3);
        userName.setFont(prefFont);

        SimpleDateFormat formater = new SimpleDateFormat("EEEE, dd-MM-yyyy HH:mm");
        Label pubDate = new Label(formater.format(publication.getDatePublication()));
        pubDate.setLayoutX(63);
        pubDate.setLayoutY(20);
        pubDate.setFont(prefFont);
        Text contenu = new Text(publication.getContenu());
        contenu.setLayoutX(13);
        contenu.setLayoutY(70);
        contenu.setStrokeType(StrokeType.OUTSIDE);
        contenu.setStrokeWidth(0);
        contenu.setWrappingWidth(350);

        MenuButton menuButton;
        menuButton = new MenuButton("Action");
        menuButton.setLayoutX(358);
        menuButton.setLayoutY(9);
        menuButton.setMnemonicParsing(false);

        MenuItem updateItem = new MenuItem("Modifier");
        updateItem.setMnemonicParsing(false);

        updateItem.setId(publication.getId().toString());
        updateItem.setOnAction(this::modifierPublicationAction);

        MenuItem deleteItem = new MenuItem("Supprimer");
        deleteItem.setMnemonicParsing(false);

        deleteItem.setId(publication.getId().toString());
        deleteItem.setOnAction(this::supprimerPublicationAction);
        menuButton.getItems().addAll(updateItem, deleteItem);
        if (ser.getPublicationById(publication.getId()).getIdUser() != getUserId()) {
            menuButton.setDisable(true);
        }
        anchorPane.getChildren().addAll(userName, pubDate, contenu, menuButton, userImage);
        VBox.setMargin(anchorPane, new Insets(0, 0, 30, 0));

        return anchorPane;
    }

    @FXML
    private void voidReclamation(ActionEvent event) {

        PublicationGroup p = new PublicationGroup(reclamaer.getText());
        ser.ajouterPublication(p);
        //---
        loadView("ProfilGroupe.fxml");

    }

    @FXML
    private void supprimerPublicationAction(ActionEvent event) {
        MenuItem x = (MenuItem) event.getSource();
        PublicationGroup p = new PublicationGroup();
        p.setId(Integer.parseInt(x.getId()));
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sure de vouloir supprimer ce statut ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            ser.supprimerPublication(p);
            //-------
            try {
                //---
                sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(profilGroupeController.class.getName()).log(Level.SEVERE, null, ex);
            }

            //---
            loadView("ProfilGroupe.fxml");
        }
    }

    @FXML
    private void modifierPublicationAction(ActionEvent event) {
        MenuItem x = (MenuItem) event.getSource();
        PublicationGroup p = new PublicationGroup();
        p = ser.getPublicationById(Integer.parseInt(x.getId()));
        p.setGroupid(p.getGroupid());
        TextInputDialog dialog = new TextInputDialog(p.getContenu());
        dialog.setTitle("Modifier publication");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.getDialogPane().setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(2), new Insets(2))));
        dialog.getDialogPane().setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextArea contenuModifie = new TextArea();
        contenuModifie.setPrefWidth(300);
        contenuModifie.setPrefHeight(100);
        contenuModifie.setText(p.getContenu());

        grid.add(new Label("Contenu: "), 0, 0);
        grid.add(contenuModifie, 1, 0);

        dialog.getDialogPane().setContent(grid);
        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            p.setContenu(contenuModifie.getText());
            ser.modifierPublication(p);
            loadView("ProfilGroupe.fxml");

        }
    }

    @FXML
    private void signalerProfilAction(ActionEvent event) {
        SignalGroup s = new SignalGroup();
        Groups g = new Groups();
        g.setId(g.getGroupe_courant());
        g.setNbr_signal(service.getGroupeById(g.getGroupe_courant()).getNbr_signal());

        //------------------
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Signaler profil");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.getDialogPane().setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(2), new Insets(2))));
        dialog.getDialogPane().setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        dialog.setResizable(true);
        dialog.getDialogPane().setPrefSize(600, 320);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(new Label("Raison de signal: "), 0, 0);
        JFXRadioButton r1 = new JFXRadioButton();
        JFXRadioButton r2 = new JFXRadioButton();

        JFXRadioButton r3 = new JFXRadioButton();

        JFXRadioButton r4 = new JFXRadioButton();

        r1.setText("Usurpation d'identité ");
        r2.setText(" Faux compte ");
        r3.setText(" Faux nom ");
        r4.setText(" Publication de contenus inappropriés ");
        grid.add(r1, 1, 0);
        grid.add(r2, 1, 1);
        grid.add(r3, 1, 2);
        grid.add(r4, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            if (r1.isSelected()) {
                r3.setSelected(false);
                r4.setSelected(false);
                r2.setSelected(false);
                s.setCause(r1.getText());
                sig.ajouterSignal(s);
                try {
                    service.UpdateGroupe1(g);
                } catch (SQLException ex) {
                    Logger.getLogger(profilGroupeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Notifications n = Notifications.create().title("Notification")
                        .text("Le groupe est signalé avec succées")
                        .graphic(null)
                        .position(Pos.BASELINE_LEFT)
                        .onAction((ActionEvent event1) -> {
                            System.out.println("notification");
                        });
                n.showConfirm();
                loadView("ProfilGroupe.fxml");

            } else if (r2.isSelected()) {

                r3.setSelected(false);
                r4.setSelected(false);
                r1.setSelected(false);
                s.setCause(r2.getText());
                sig.ajouterSignal(s);
                try {
                    service.UpdateGroupe1(g);
                } catch (SQLException ex) {
                    Logger.getLogger(profilGroupeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Notifications n = Notifications.create().title("Notification")
                        .text("Le groupe est signalé avec succées")
                        .graphic(null)
                        .position(Pos.BASELINE_LEFT)
                        .onAction((ActionEvent event1) -> {
                            System.out.println("notification");
                        });
                n.showConfirm();
                loadView("ProfilGroupe.fxml");

            } else if (r3.isSelected()) {

                r2.setSelected(false);
                r4.setSelected(false);
                r1.setSelected(false);

                s.setCause(r3.getText());
                sig.ajouterSignal(s);
                try {
                    service.UpdateGroupe1(g);
                } catch (SQLException ex) {
                    Logger.getLogger(profilGroupeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Notifications n = Notifications.create().title("Notification")
                        .text("Le groupe est signalé avec succées")
                        .graphic(null)
                        .position(Pos.BASELINE_LEFT)
                        .onAction((ActionEvent event1) -> {
                            System.out.println("notification");
                        });
                n.showConfirm();
                loadView("ProfilGroupe.fxml");

            } else if (r4.isSelected()) {

                r3.setSelected(false);
                r2.setSelected(false);
                r1.setSelected(false);
                s.setCause(r4.getText());
                sig.ajouterSignal(s);
                try {
                    service.UpdateGroupe1(g);
                } catch (SQLException ex) {
                    Logger.getLogger(profilGroupeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Notifications n = Notifications.create().title("Notification")
                        .text("Le groupe est signalé avec succées")
                        .graphic(null)
                        .position(Pos.BASELINE_LEFT)
                        .onAction((ActionEvent event1) -> {
                            System.out.println("notification");
                        });
                n.showConfirm();
                loadView("ProfilGroupe.fxml");

            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Alert !!!");
                alert.setHeaderText("Attention !!!");
                alert.setContentText("Selectionner une raison !");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    //remove selected item from the table list
                    dialog.showAndWait();
                }

            }

        }
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

    private Object loadView(String path) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource((path)));
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
    final FileChooser fileChooser = new FileChooser();
    private Desktop desktop = Desktop.getDesktop();
    private String file_image;
    private Path pathfrom;
    private Path pathto;
    private File Current_file;
    private FileInputStream fis;

    private void modifier() {
        JFXButton modifier1 = new JFXButton();
        modifier1.setText("Modifier");
        modifier1.setPrefHeight(25.0);
        modifier1.setPrefWidth(88.0);
        modifier1.setLayoutX(600);
        modifier1.setLayoutY(280);
        JFXButton supprimer1 = new JFXButton();
        supprimer1.setText("Supprimer");
        supprimer1.setPrefHeight(25.0);
        supprimer1.setPrefWidth(88.0);
        supprimer1.setLayoutX(350);
        supprimer1.setLayoutY(50);

        modifier1.setFont(new Font("Cambria", 15));
        modifier1.setStyle("-fx-background-color: #494c62; ");
        modifier1.setTextFill(Color.web("#e8f8ff"));
        supprimer1.setOnMouseClicked(x -> {

            // get Selected Item
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes vous sure de vouloir supprimer ce groupe ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
//                Groups associationCourant = service.getGroupeById(Groups.getGroupe_courant());
                //remove selected item from the table list
                service.supprimerGroupe(Groups.getGroupe_courant());
                Notifications n = Notifications.create().title("Notification")
                        .text("Le groupe est supprimé avec succées")
                        .graphic(null)
                        .position(Pos.BASELINE_LEFT)
                        .onAction((ActionEvent event1) -> {
                            System.out.println("notification");
                        });
                n.showConfirm();
                loadView("FrontGroupe.fxml");
            }

        });

        modifier1.setOnMouseClicked(s
                -> {
            Groups a = new Groups();
            a = service.getGroupeById(Groups.getGroupe_courant());

            //------------------
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Modifier Groupe");
            dialog.setResizable(true);
            dialog.getDialogPane().setPrefSize(700, 320);
            dialog.setHeaderText(null);
            dialog.setGraphic(null);
            dialog.getDialogPane().setBackground(new Background(new BackgroundFill(Color.GREY, new CornerRadii(2), new Insets(2))));
            dialog.getDialogPane().setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField textNom = new TextField();
            textNom.setPrefWidth(300);
            textNom.setPrefHeight(40);
            textNom.setText(a.getNom());
            TextField textBut = new TextField();
            textBut.setPrefWidth(300);
            textBut.setPrefHeight(40);
            textBut.setText(a.getDescription());

            JFXButton upload = new JFXButton("Télécharger votre image");
            ImageView image_p = new ImageView();
            upload.setStyle("-fx-background-color: #724848; ");
            upload.setTextFill(Color.web("#e8f8ff"));
            upload.setOnMouseClicked(d -> {
                FileChooser fc = new FileChooser();
                Current_file = fc.showOpenDialog(null);
                if (Current_file != null) {
                    Image images = new Image(Current_file.toURI().toString(), 100, 100, true, true);
                    image_p.setImage(images);
                    try {
                        fis = new FileInputStream(Current_file);
                        file_image = Current_file.getName();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(profilGroupeController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            });
            grid.add(new Label("Nom: "), 0, 0);
            grid.add(new Label("Description: "), 0, 1);

            grid.add(textNom, 1, 0);
            grid.add(textBut, 1, 1);

            grid.add(upload, 0, 2);
            grid.add(image_p, 1, 2);

            dialog.getDialogPane().setContent(grid);
            System.out.println("ahlan   " + a.getNom());
            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            Groups gh = service.getGroupeById(Groups.getGroupe_courant());
            if (result.isPresent()) {

                Groups ass = new Groups();
                ass.setId(Groups.getGroupe_courant());
                ass.setNom(textNom.getText());
                ass.setDescription(textBut.getText());
                ass.setIdUser(getUserId());
                ass.setDate_de_creation(gh.getDate_de_creation());
                ass.setNbrMembre(gh.getNbrMembre());
                ass.setNbr_signal(gh.getNbr_signal());

                //// upload image ///////
                try {
                    ass.setImage(file_image);
                    pathfrom = FileSystems.getDefault().getPath(Current_file.getPath());
                    pathto = FileSystems.getDefault().getPath("C:\\Users\\Farah GABSI\\Documents\\NetBeansProjects\\velo\\src\\Images\\" + Current_file.getName());
                    Path targetDir = FileSystems.getDefault().getPath("C:\\Users\\Farah GABSI\\Documents\\NetBeansProjects\\velo\\src\\Images\\");

                    Files.copy(pathfrom, pathto, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    Logger.getLogger(profilGroupeController.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    ///////////////
                    service.UpdateGroupe(ass);
                } catch (SQLException ex) {
                    Logger.getLogger(profilGroupeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Notifications n = Notifications.create().title("Notification")
                        .text("Le groupe est modifié avec succées")
                        .graphic(null)
                        .position(Pos.BASELINE_LEFT)
                        .onAction((ActionEvent event1) -> {
                            System.out.println("notification");
                        });
                n.showConfirm();
                loadView("ProfilGroupe.fxml");

            }

        });

        supprimer1.setFont(new Font("Cambria", 15));
        supprimer1.setStyle("-fx-background-color: #494c62; ");
        supprimer1.setTextFill(Color.web("#e8f8ff"));
        if (service.getGroupeById(Groups.getGroupe_courant()).getIdUser() != getUserId()) {
            supprimer1.setDisable(true);
            modifier1.setDisable(true);
        }

        modif.getChildren().add(modifier1);
        supp.getChildren().add(supprimer1);
    }

    @FXML
    private void retour(MouseEvent event) {
        loadView("FrontGroupe.fxml");
    }

}
