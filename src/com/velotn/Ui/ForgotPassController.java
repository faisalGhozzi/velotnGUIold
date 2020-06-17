package com.velotn.Ui;

import com.velotn.Utils.DataBase;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.Status.PLAYING;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Farah GABSI
 */
public class ForgotPassController implements Initializable {

    @FXML
    private TextField mailid;
    @FXML
    private Button btnid;

    private DataBase cnx;
    public ResultSet rs;
    public int x;
    public String y, z;
    public String username, pass, mesg;
    Scene scene;
    Stage stage = new Stage();
    @FXML
    private Label label;
    @FXML
    private Label exit;
    MediaPlayer mediaplayer;
    @FXML
    private MediaView mv;
    @FXML
    private JFXRadioButton btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String VUrl = getClass().getResource("../Images/Evolution of the Bicycle.mp4").toExternalForm();
        Media media = new Media(VUrl);
        mediaplayer = new MediaPlayer(media);
        mv.setMediaPlayer(mediaplayer);
        mediaplayer.play();
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    @FXML
    private void SendMail(ActionEvent event) throws AddressException, MessagingException, SQLException, IOException {
        if (mailid.getText().isEmpty()) {
            label.setText("Remarque : email vide");
        } else if (!mailid.getText().matches("[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\-\\_\\.]+\\.[a-zA-Z0-9]{2}")) {
            label.setText("Remarque : email non valide");
        } else {
            Connection conn = DataBase.getInstance().getConnection();
            String req = "Select username,password from fos_user where email=? ";
            PreparedStatement prs = conn.prepareStatement(req);
            prs.setString(1, mailid.getText());
            rs = prs.executeQuery();
            while (rs.next()) {
                username = rs.getString("username");
                pass = rs.getString("password");
            }
            y = getSaltString();
            z = mailid.getText();
            mesg = "votre code est : " + y;

               //String from ="world.friendship2019@gmail.com";
       String from ="velotn.velotn@gmail.com";
       //String pass="21626747";
       String pass="93751038O";
           
            String[] to = {mailid.getText()};
            String host = "mail.javatpoint.com";
            String sub = "Password Recovery";

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
                    return new PasswordAuthentication(from, pass);
                }
            });
            //compose message                
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailid.getText()));
            message.setSubject(sub);
            message.setText(mesg);
            //send message  
            Transport.send(message);
            System.out.println("Message sent successfully");
            mediaplayer.stop();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Code.fxml"));
            Parent root = loader.load();
            CodeController ccc = loader.getController();
            ccc.setEmail(z);
            ccc.setCode(y);
            mailid.getScene().setRoot(root);

        }
    }

    @FXML
    private void exit(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
        mediaplayer.stop();

        scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void OnClick_btn_play() {
        if (mediaplayer.getStatus() == PLAYING) {
            mediaplayer.pause();
        } else {
            mediaplayer.play();

        }

    }

}
