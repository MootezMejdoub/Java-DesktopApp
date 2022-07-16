/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.CryptWithMD5;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import services.UserService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author eyaou
 */
public class ChangemdpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField adresse;
     @FXML
    private PasswordField txtPassword1;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button conf;
    PreparedStatement preparedStatement  ;
    Connection connexion;
    Statement stm;

    public ChangemdpController() {
        connexion = MyDB.getInstance().getConnexion();
    }
    public void function(String user)
        
        
{
   adresse.setText(user);
}

    @FXML
    void confirmer(ActionEvent event) throws IOException {
        
        if(txtPassword.getText().equals(txtPassword1.getText()))
        {
             String req = "update utilisateur set mdp = ?  where email = ?";
            
            try {
            preparedStatement = connexion.prepareStatement(req);
            
            String mdp=CryptWithMD5.cryptWithMD5(txtPassword.getText());
           
            preparedStatement.setString(1, mdp);
           
            preparedStatement.setString(2,adresse.getText() );
             
           
            
                System.out.println("modification effectuée");
            
            preparedStatement.executeUpdate();
             Notifications notificationBuilder= Notifications.create()
                    .title("Alert").text("Votre mot de passe a été modifié avec succès ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
        else
        {
             Notifications notificationBuilder= Notifications.create()
                    .title("Alert").text("Votre code est incorrect. veuillez verifier de nouveau ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
        }
        Stage stage= (Stage) conf.getScene().getWindow();
                    stage.close();
                   

    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
