/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.email.durgesh.Email;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
public class MdpOubController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private Button verifier;
     @FXML
    private TextField add;

    @FXML
    private TextField code;
String gr=getRandom();

    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public String getRandom(){
     
        Random rnd= new Random();
        int number=rnd.nextInt(9999999);
        return String.format("%06d", number);
        
    } 
    @FXML
    void envoyer(ActionEvent event) {
        try
        {

            
        Email email= new Email("eyawarteni68@gmail.com","191JFT1082");
       email.setFrom("eyawarteni68@gmail.com", "verification code");
    email.setSubject("User Email Verification");
    email.setContent("You forgot your password? Please verify your account using this code: " +gr,"text/html");
    email.addRecipient(add.getText());
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
    
    @FXML
    void verifier(ActionEvent event) throws IOException
    {
        if(code.getText().equals(gr))
        {
             
             Stage stage= (Stage) verifier.getScene().getWindow();
                    stage.close();
                    
            FXMLLoader loaderzz = new FXMLLoader(getClass().getResource("changemdp.fxml"));
                                         Parent rootzz=(Parent) loaderzz.load();
                                         ChangemdpController chmdp=loaderzz.getController();
                                         chmdp.function(add.getText());
                                          Stage stagezz=new Stage();
                                        stagezz.setScene(new Scene(rootzz));
                                        stagezz.show();
        }
    }
    
    
    
}
