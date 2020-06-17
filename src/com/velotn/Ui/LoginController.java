/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.velotn.Ui;


import com.velotn.Service.LoginService;
import com.velotn.Service.UserService;
import com.velotn.Utils.DataBase;
import com.jfoenix.controls.JFXCheckBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author Farah GABSI
 */
public class LoginController extends Controller implements Initializable {

    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private Button button;
    @FXML
    private Label msg;
    
    private DataBase cnx;
    Stage stage= new Stage();
    Scene scene;
    @FXML
    private Button signup;
    @FXML
    private Hyperlink mail;
    int x;
    @FXML
    private JFXCheckBox remember;
    private final String path="src\\LoginData.ini";
    LoginService loginService = new LoginService();
    UserService ser = new UserService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loginService.readinifile(path,username,password,remember);
    }
    
    private void init()
    {
    }

    @FXML
    private void LoginAction(ActionEvent event) throws SQLException, IOException {
       
        Connection conn = DataBase.getInstance().getConnection();
        String req= "Select * from fos_user where (username=? or email=?)";
        PreparedStatement prs= conn.prepareStatement(req);
        prs.setString(1, username.getText());
        prs.setString(2, username.getText());
        ResultSet rs = prs.executeQuery();
        if (!rs.next()){
            msg.setText("Username incorrect");
        } 
        else {
            if(BCrypt.checkpw(password.getText(),rs.getString("password").substring(0,2)+"a"+rs.getString("password").substring(3)))
            {
            if (!remember.isSelected()){
               
                prs.setString(1, username.getText());
                ResultSet res= prs.executeQuery();
                while (res.next()){
                 x= res.getInt("id");       
                }
                
                Controller.setUserId(x);
              
                init();
                Node node =(Node)event.getSource();
                stage = (Stage)node.getScene().getWindow();
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("MainUi.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                stage.setResizable(false);          

            }
            else {
                loginService.createiniFile(path,username.getText(),password.getText());
                System.out.println("success");           
                
                prs.setString(1, username.getText());
                ResultSet res= prs.executeQuery();
                while (res.next()){
                    x= res.getInt("id");
                } 
                Controller.setUserId(x);
            
                init();
                Node node =(Node)event.getSource();
                stage = (Stage)node.getScene().getWindow();
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("MainUi.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                stage.setResizable(false);
                
            }
            }
            else
            {
                msg.setText("Mot de passe incorrecte!");
            }
        }     
    }
    
    @FXML
    public void SignUp(ActionEvent event) throws IOException{
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("SignUp.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    


    private void exit(MouseEvent event) {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void sendmail(ActionEvent event) throws IOException {
        Node node =(Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.close();
        scene = new Scene(FXMLLoader.load(getClass().getResource("ForgotPass.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    

    
    
    

}
