/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import utils.JavaMailUtil;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import services.ReclamationService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Tox
 */
public class ReponseAdminController implements Initializable {
    String rec_referencee="";
    int id_rec=0;
    @FXML
    private ComboBox EtatButton;
    @FXML
    private Button EnvoyerRepButton;
    @FXML
    private Button AnnulerRepButton;
    @FXML
    private MediaView mediaViewRep;
    @FXML
    private TextField msgReply;
    LocalDate actuelle = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = actuelle.format(formatter);
        String dc = date;
        Connection connection;
        PreparedStatement pst;
    @FXML
    private StackPane rootPane;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Media media = new Media("file:///C:/Users/Tox/Desktop/tunisia.mp4");
        MediaPlayer player = new MediaPlayer(media);
        mediaViewRep.setMediaPlayer(player);
        player.setVolume(0);
        player.play();
     connection = MyDB.getInstance().getConnexion();
      ObservableList <String> list=FXCollections.observableArrayList("En attente","en cours de traitement","traité");
      EtatButton.setItems(list);
      //EtatButton.getSelectionModel().getSelectedItem().toString()
    }    

    @FXML
    private void etatReclamationchange(ActionEvent event) {
          
    }

    @FXML
    public void envoyerReponse(ActionEvent event ) {
        try {
            BoxBlur blur = new BoxBlur(3, 3, 3);
            
            String req = "INSERT INTO reponse (rec_id,rec_reference,datecreation,message) VALUES ( '"
                    + ReclamationAdminController.selected.getId()+ "', '" + ReclamationAdminController.selected.getReference() + "','"  + dc +"','"+msgReply.getText()+"') ";
            Statement stm = connection.createStatement();
            stm.executeUpdate(req); 
            System.out.println("Bien ajoutee");
           
           JFXDialogLayout dialogueLayout=new JFXDialogLayout();
           JFXDialog dialog= new JFXDialog(rootPane, dialogueLayout, JFXDialog.DialogTransition.TOP);
           dialogueLayout.setHeading(new Label("Votre Reponse a été envoyée avec succès, merci!"));
           dialog.show();
           dialog.setOnDialogClosed((JFXDialogEvent event1)->{
           msgReply.setEffect(new BoxBlur(0, 0, 0));
             });
           msgReply.setEffect(blur);

    
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        
          }
        PreparedStatement select;
        PreparedStatement update;
        ResultSet resultat;

        try {
            select = connection.prepareStatement("select etat from reclamation where rec_reference='" + ReclamationAdminController.selected.getReference() + "'");

            resultat = select.executeQuery();
            if (resultat.isBeforeFirst()) {
                update = connection.prepareStatement("update reclamation set  etat= ? where rec_reference = ?");

                
                
                update.setString(1, EtatButton.getSelectionModel().getSelectedItem().toString());
                update.setString(2, ReclamationAdminController.selected.getReference());
                update.executeUpdate();

                System.out.println("modification effectuée");

            } else {
                System.err.println("non effectuée");
            }

        } catch (SQLException ex) {
            System.err.println("erreur de modification");
        }

        
    }
public void setData(int id,String reference,String etat){
    EtatButton.setPromptText(etat);
    
 
    }
 
    @FXML
    private void annulerReponse(ActionEvent event) {
         
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationAdmin.fxml"));
        
            Parent root1= loader.load();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
             Scene scene=new Scene(root1);
             stage.setScene(scene);
             stage.show();  
        
        } catch (IOException ex) {
            Logger.getLogger(ReclamationVideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
