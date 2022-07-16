/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import entities.Reponse;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
public class ReponseRecClientController implements Initializable {
private Stage stage;
private Scene scene;
    @FXML
    private MediaView mediaView;
    @FXML
    private Label EtatDeReclamation;
    @FXML
    private Label ReponseField;
    

    /**
     * Initializes the controller class.
     */
  Connection connection = MyDB.getInstance().getConnexion();
    PreparedStatement pst;
    @FXML
    private Button back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Media media = new Media("file:///C:/Users/Tox/Desktop/tunisia.mp4");
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setVolume(0);
        player.play();

        ReponseDeClient();
    }

    public void ReponseDeClient() {
        PreparedStatement sttm;
        ResultSet rst;
        try {
           String ss=GererReclamationClientController.selected.getReference();
            String req = "select * from reponse  where rec_reference='" + ss+ "'";
            sttm = connection.prepareStatement(req);
           rst=sttm.executeQuery();
            Reponse r = new Reponse();
            if (rst.next()) {
                r.setMessage(rst.getString("message"));
                ReponseField.setText(r.getMessage());
            }
            else ReponseField.setText("Votre reclamation n'est pas encore répondu ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
           
        }
    }

    public void setData(int id, String reference, String etat) {
        EtatDeReclamation.setText(etat);

    }

    @FXML
    private void backToListRec(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GererReclamationClient.fxml"));
        try{
            
            Parent root1= loader.load();
             stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
             scene=new Scene(root1);
             stage.setScene(scene);
             stage.show();  
             
        } catch (IOException ex) {
            Logger.getLogger(ReclamationVideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
