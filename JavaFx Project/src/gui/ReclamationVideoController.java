/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.ReclamationAdminController.selected;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Tox
 */
public class ReclamationVideoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private MediaView mediaView;
    static   String id=String.valueOf(SigInController.id_client);
    static String nom;
    static String prenom;
    static String email;
    static String num_tel;
    @FXML
    private Button back;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Media media = new Media("file:///C:/Users/Tox/Desktop/tunisia.mp4");
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setVolume(10);
        player.play();
        getNomPrenomEmail();
    }    
private Stage stage;
private Scene scene;
    @FXML
    private void AjouterReclamationBtnAction(ActionEvent event)  {
        
        try {//bhy lahdha 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
        
            Parent root1= loader.load();
             stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
             scene=new Scene(root1);
             stage.setScene(scene);
             stage.show();  
             
             
                AjouterReclamationController ajouterReclamationController=loader.getController();
        
                ajouterReclamationController.setEmail("zzzzz@gmail.com");
                ajouterReclamationController.setId("10");
             
                
                
                
                
        } catch (IOException ex) {
            Logger.getLogger(ReclamationVideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void gererReclamationBtnAction(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GererReclamationClient.fxml"));
        
            Parent root1= loader.load();
             stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
             scene=new Scene(root1);
             stage.setScene(scene);
             stage.show();  
            GererReclamationClientController gererReclamationClientController=loader.getController();
            gererReclamationClientController.setId(id);
        
        } catch (IOException ex) {
            Logger.getLogger(ReclamationVideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void getNomPrenomEmail(){
          try {
        Connection connection =MyDB.getInstance().getConnexion();
        String query =  "select * from utilisateur where id="+id;
            PreparedStatement stm;
            stm = connection.prepareStatement(query);
            ResultSet rst=stm.executeQuery();

            while (rst.next()) {
               
                prenom=(rst.getString("prenom"));
                email= rst.getString("email");
                nom=(rst.getString("nom"));
                this.num_tel=(rst.getString("num_tel"));
    }
     } catch (SQLException ex) {
            Logger.getLogger(ReclamationVideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    @FXML
    private void RetourHome(ActionEvent event) {
       
             Stage stage= (Stage) back.getScene().getWindow();
                    stage.close();
        
    }
    
    
}
