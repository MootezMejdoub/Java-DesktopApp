/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
//import Event.crud.rating;
import java.io.IOException;
import utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author ideapadGAMING
 */
public class RateController implements Initializable {

    @FXML
    private ComboBox<String> cbrate;
    @FXML
    private Rating rateev;
    @FXML
    private Button btnrate;

    /**
     * Initializes the controller class.
     */
 PreparedStatement ste;
            Statement st;
            Connection cnx;
    @FXML
    private Button statics;
    @FXML
    private Button reclamation;
    @FXML
    private Button plan;
    @FXML
    private Button place;
    @FXML
    private Button rating;
    @FXML
    private Button account;
            
    @FXML
    private void remplirevent(MouseEvent event) {
        
        
        
        
        try {
            String sql="select nom from utilisateur ";
            
            
            List<String> nm =new ArrayList<String>();
           
            
            st = cnx.prepareStatement(sql);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                
                nm.add(rs.getString("nom"));
                cbrate.setItems(FXCollections.observableArrayList(nm));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public RateController() {
        cnx = MyDB.getInstance().getConnexion();
        
    }

 

    @FXML
    private void rateev(MouseEvent event) {
              
                  
                   
                   int rate=(int) rateev.getRating();
                     System.out.println("rating is :"+rateev.getRating());
                   
                   String nomev =  cbrate.getValue();
                   
                  
                   
                  
                   String sql2= "update utilisateur set evaluation = ?  where nom = ?";
                    
                     try {
                 
                   ste = cnx.prepareStatement(sql2);
                   
                  
                   
                   ste.setInt(1, rate);
                   ste.setString(2, nomev);
                   
                   
                   ste.executeUpdate();
                   Notifications notificationBuilder= Notifications.create()
                    .title("Alert").text("Votre évaluation a été effectué. ").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT);
            notificationBuilder.darkStyle();
            notificationBuilder.show();
               } catch (SQLException ex) {
            Logger.getLogger(RateController.class.getName()).log(Level.SEVERE, null, ex);
        }
                     
                 
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void toStat(ActionEvent event) {
    }

    @FXML
    private void toRec(ActionEvent event) throws IOException {
        FXMLLoader loader5 = new FXMLLoader(getClass().getResource("ReclamationAdmin.fxml"));
                                      Parent  roott=(Parent) loader5.load();
                                          Stage staget=new Stage();
                                        staget.setScene(new Scene(roott));
                                        staget.show();
    }

    @FXML
    private void toPlan(ActionEvent event) throws IOException {
        FXMLLoader loader5 = new FXMLLoader(getClass().getResource("MainInterfaceClient.fxml"));
                                      Parent  roott=(Parent) loader5.load();
                                          Stage staget=new Stage();
                                        staget.setScene(new Scene(roott));
                                        staget.show();
    }

    @FXML
    private void toPlace(ActionEvent event) {
    }

    @FXML
    private void toRate(ActionEvent event) {
    }

    @FXML
    private void toAccount(ActionEvent event) throws IOException {
         FXMLLoader loader5 = new FXMLLoader(getClass().getResource("TableUsers.fxml"));
                                      Parent  roott=(Parent) loader5.load();
                                          Stage staget=new Stage();
                                        staget.setScene(new Scene(roott));
      TableUsersController cl =loader5.getController();
                    cl.afficher(SigInController.password,SigInController.id,SigInController.nom,SigInController.prenom,SigInController.num_tel,SigInController.adresse,SigInController.email,SigInController.type,SigInController.mdp,SigInController.dn);
                   staget.show();
    }
    
}