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
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Farah GABSI
 */
public class AjoutGroupeController implements Initializable {
GroupeService service = new GroupeService();
    @FXML
    private JFXTextField titre;
    @FXML
    private JFXTextArea description;
    @FXML
    private Button ajouter;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXButton fichier;
    @FXML
    private ImageView image_p;
     final FileChooser fileChooser = new FileChooser();
    private Desktop desktop = Desktop.getDesktop();
    private String file_image;
    private Path pathfrom;
    private Path pathto;
    private File Current_file;
    private FileInputStream fis;
    @FXML
    private JFXTextField lieu;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
          

            AnchorPane box = FXMLLoader.load(getClass().getResource("drawer.fxml"));
            drawer.open();
            drawer.setSidePane(box);

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * 1);
                transition.play();

                if (drawer.isOpened()) {
                    drawer.close();
                } else {
                    drawer.open();
                }

            });

        } catch (IOException ex) {
            Logger.getLogger(AjoutGroupeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
         if (!checkText(titre.getText())) {
            Alert a = new Alert(null, "Vérifier le nom du groupe !! ", ButtonType.CLOSE);
            a.showAndWait();
        } else if (!checkText(description.getText())) {
            Alert a = new Alert(null, "Vérifier votre description !!", ButtonType.CLOSE);
            a.showAndWait();
            System.out.println("Incorrect");
        } else if (!checkText(lieu.getText())) {
            Alert a = new Alert(null, "Vérifier votre lieu !!", ButtonType.CLOSE);
            a.showAndWait();
            System.out.println("Incorrect");
        } 
         else {
            Groups events = new Groups();
            
            
            events.setNom(titre.getText());
        
            events.setDescription(description.getText());
            events.setIdUser(Controller.getUserId());
            events.setNbrMembre(1);
            events.setNbr_signal(0);
            events.setLieu(lieu.getText());
           
            
            events.setImage(file_image);
            pathfrom = FileSystems.getDefault().getPath(Current_file.getPath());
            pathto = FileSystems.getDefault().getPath("C:\\wamp64\\www\\velotnproducts\\" + Current_file.getName());
            Path targetDir = FileSystems.getDefault().getPath("C:\\wamp64\\www\\velotnproducts\\");
            Files.copy(pathfrom, pathto, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("ayyya 3ad"+Controller.getUserId());

            ///////////////
            service.ajouterGroupe(events);


             loadView("FrontGroupe.fxml");
        }
    }

    @FXML
   
     private void fichier_image(ActionEvent event) throws MalformedURLException, IOException {

        FileChooser fc = new FileChooser();
        Current_file = fc.showOpenDialog(null);
        if (Current_file != null) {
        Image images = new Image(Current_file.toURI().toString(),100,100,true,true);
        image_p.setImage(images);
            try {
                fis = new FileInputStream(Current_file);
                file_image = Current_file.getName();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AjoutGroupeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

        final GridPane inputGridPane = new GridPane();
        GridPane.setConstraints(fichier, 0, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {

            Logger.getLogger(
                    AjoutGroupeController.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }
    

    private boolean checkText(String b) {

        if (this.testTextInput(b) || (this.testTextInput(b)) || (this.testTextInput(b)) || (this.testTextInput(b))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean testTextInput(String a) {

        boolean b = true;
        if (a.length() == 0 || testNumberInput(a)) {
            b = false;
        }

        return b;

    }

    private boolean testNumberInput(String a) {
        boolean b = false;
        if (a.matches("^[0-9]*")) {
            b = true;
        }
        return b;
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
