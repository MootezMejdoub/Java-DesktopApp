/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.email.durgesh.Email;
import doryan.windowsnotificationapi.fr.Notification;
import entities.Mail;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author eyaou
 */
public class VerifController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField code;
    @FXML
    private Button verifier;
      @FXML
    private TextField username;

    @FXML
    private TextField gr;
     @FXML
    private Button envCode;

    
    
   
public void function(String user,String grr)
        
        
{
   gr.setText(grr);
   username.setText(user);
    System.out.println(grr);
    System.out.println(user);
}

   
@FXML
    void envoyer(ActionEvent event) {
 try
        {

            
        Email email= new Email("eyawarteni68@gmail.com","191JFT1082");
       email.setFrom("eyawarteni68@gmail.com", "verification code");
    email.setSubject("User Email Verification");
    email.setContent("Registered successfully.Please verify your account using this code: " +gr.getText(),"text/html");
    email.addRecipient(username.getText());
    email.send();
    
            Notifications notificationBuilder= Notifications.create()
                    .title("Alert").text("Votre Mail a été envoyé avec succès").graphic(null).hideAfter(javafx.util.Duration.seconds(10))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
                    
                    
    
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    
        
    }
    
   
   /* void forgot(String username,String password) throws Exception {

        try
        {

            System.out.println(gr);
        Email email= new Email("eyawarteni68@gmail.com","191JFT1082");
       email.setFrom("eyawarteni68@gmail.com", "verification code");
    email.setSubject("User Email Verification");
    email.setContent("Registered successfully.Please verify your account using this code: " +gr,"text/html");
    email.addRecipient(username);
    email.send();
     
    
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    
    }
    */
      
    
    
    @FXML
    void verifier(ActionEvent event) {
        
           
            
            System.out.println(gr.getText());
            
            if(code.getText().equals(gr.getText()))
            {
                try {
                    System.out.println("eyyyy");
                     Stage stage= (Stage) envCode.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("TableUsers.fxml"));
                                      Parent  roota=(Parent) loader2.load();
                                         Stage  stage2=new Stage();
                                        stage2.setScene(new Scene(roota));
                                        stage2.show();
                    
                } catch (IOException ex) {
                    Logger.getLogger(VerifController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                
            Notifications notificationBuilder= Notifications.create()
                    .title("Alert").text("Votre code est incorrect. veuillez verifier de nouveau ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
                System.out.println("leeeee");
            }
        
    }
    
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
      
    
        
        
    }    
    
}
