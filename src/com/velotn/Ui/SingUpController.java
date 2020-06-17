/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velotn.Ui;

import com.velotn.Utils.DataBase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author Farah GABSI
 */
public class SingUpController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private PasswordField repeat_password;
    @FXML
    private PasswordField password;
    @FXML
    private DatePicker date_naissance;
    @FXML
    private Button ajouter;

    private DataBase cnx;
    Stage stage = new Stage();
    Scene scene;
    @FXML
    private Label exit;
    @FXML
    private Button reset;
    @FXML
    private Label info;
    @FXML
    private ImageView back;
    @FXML
    private JFXCheckBox terms;
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
    private TextField pays;
    @FXML
    private TextField region;
    @FXML
    private TextField code;
    @FXML
    private TextField rue;
 public static final String ACCOUNT_SID = "AC873d2cad40a075f11e135bad0f9792b4";
    public static final String AUTH_TOKEN = "c4c06eda1277ed994504be6047b43459";
        public String username1,pass1,mesg1,email1,code1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouter_utilisateur(ActionEvent event) throws SQLException, IOException, MessagingException {
        Connection conn = DataBase.getInstance().getConnection();
        String req = "INSERT INTO fos_user (username,email,password,nom,prenom,date_naiss,tel,interets,image,pays,region,rue,code_postal,claire) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        String req1 = "SELECT * FROM fos_user WHERE email=?";
        PreparedStatement prs = conn.prepareStatement(req);
        PreparedStatement prs1 = conn.prepareStatement(req1);
        prs1.setString(1, email.getText());
        ResultSet rs = prs1.executeQuery();
        prs.setString(1, username.getText());
        prs.setString(5, prenom.getText());
        prs.setString(4, nom.getText());
        prs.setString(2, email.getText());
        String pwd = BCrypt.hashpw(password.getText(), BCrypt.gensalt(13));
        prs.setString(3, pwd.substring(0, 2) + "y" + pwd.substring(3));
        prs.setDate(6, new java.sql.Date(new Date().getTime()));
        prs.setString(7, username.getText());
        prs.setString(8, email.getText());
        prs.setString(9, file_image);
        prs.setString(10, pays.getText());
        prs.setString(11, region.getText());
        prs.setString(12, rue.getText());
        prs.setInt(13, Integer.parseInt(code.getText()));
        prs.setString(14, password.getText());
        pathfrom = FileSystems.getDefault().getPath(Current_file.getPath());
        pathto = FileSystems.getDefault().getPath("C:\\wamp64\\www\\velotnproducts\\" + Current_file.getName());
        Path targetDir = FileSystems.getDefault().getPath("C:\\wamp64\\www\\velotnproducts\\");
        Files.copy(pathfrom, pathto, StandardCopyOption.REPLACE_EXISTING);

        if (username.getText().equals("")) {
            info.setText("Username is empty");
        } else if (rs.next()) {
            info.setText("Email deja existant");
        } else if (email.getText().isEmpty()) {
            info.setText("Please insert your Email");
        } else if (!email.getText().matches("[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\-\\_\\.]+\\.[a-zA-Z0-9]{2}")) {
            info.setText("Incorrect Email");
        } else if (nom.getText().equals("")) {
            info.setText("Name is empty");
        } else if (prenom.getText().equals("")) {
            info.setText("Prenom is empty");
        } else if (date_naissance.toString().equals("")) {
            info.setText("Date de naissance est vide");
        } else if (password.getText().equals("")) {
            info.setText("Mot de passe est vide");
        } else if (repeat_password.getText().equals("")) {
            info.setText("Repeter mot de passe est vide");
        } else if (!repeat_password.getText().equals(password.getText())) {
            info.setText("Mot de passe n'est pas compatible");
        } else if (pays.getText().equals("")) {
            info.setText("Pays est vide");
        } else if (region.getText().equals("")) {
            info.setText("Region est vide");
        } else if (code.getText().equals("")) {
            info.setText("Code postal est vide");
        } else if (rue.getText().equals("")) {
            info.setText("Rue est vide");
        } else if (!terms.isSelected()) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Term.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(sc);
            stage.show();
            terms.setSelected(true);

        } else {

            int executeUpdate = prs.executeUpdate();
             mesg1=" Félicitation " + username.getText() + " votre compte est crée avec succés ";
          
          
       String from ="velotn.velotn@gmail.com";
       String pass="93751038O";
       String [] to={email.getText()};
       String host="mail.javatpoint.com";
       String sub="Welcome to Velo.tn";
       
        Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",      
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(from,pass);
    }
}); 
          //compose message                
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(email.getText()));    
           message.setSubject(sub);    
           message.setText(mesg1);    
           //send message  
           Transport.send(message);    
          
            
            Node node = (Node) event.getSource();
            stage = (Stage) node.getScene().getWindow();
            stage.close();
            scene = new Scene(FXMLLoader.load(getClass().getResource("../GUI/Login.fxml")));
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    private void exit(MouseEvent event) {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ResetAll(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void back(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void fichier_image(ActionEvent event) {
        FileChooser fc = new FileChooser();
        Current_file = fc.showOpenDialog(null);
        if (Current_file != null) {
            Image images = new Image(Current_file.toURI().toString(), 100, 100, true, true);
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

}
