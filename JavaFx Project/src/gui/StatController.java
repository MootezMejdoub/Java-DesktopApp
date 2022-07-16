/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author eyaou
 */


public class StatController implements Initializable {
     private Connection cnx;
    private Statement stm;
    private PreparedStatement pst;
    private PreparedStatement pst1;
    private ResultSet rs;
       @FXML
    private LineChart<String, Integer> stat;
    @FXML
    private Button statics;
    @FXML
    private Button reclamation;
    @FXML
    private Button statics11;
    @FXML
    private Button statics111;
    @FXML
    private Button statics1111;
    @FXML
    private Button account;
       



    /**
     * Initializes the controller class.
     */
    
      



    public StatController() {
        cnx = MyDB.getInstance().getConnexion();
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String req="select type,COUNT(type) from utilisateur GROUP by type";
        XYChart.Series<String,Integer> series=new XYChart.Series<>();
        try{
            rs=cnx.createStatement().executeQuery(req);
            while(rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
            }
            stat.getData().add(series);
            
            
            
        }catch(Exception e){
            System.out.println("erreur stat");
            
        }
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
    private void toAccount(ActionEvent event) throws IOException {
        
         FXMLLoader loader5 = new FXMLLoader(getClass().getResource("TableUsers.fxml"));
                                      Parent  roott=(Parent) loader5.load();
                                          Stage staget=new Stage();
                                        staget.setScene(new Scene(roott));
      TableUsersController cl =loader5.getController();
                    cl.afficher(SigInController.password,SigInController.id,SigInController.nom,SigInController.prenom,SigInController.num_tel,SigInController.adresse,SigInController.email,SigInController.type,SigInController.mdp,SigInController.dn);
                   staget.show();
    }

    @FXML
    private void toStat(ActionEvent event) throws IOException {
         FXMLLoader loader5 = new FXMLLoader(getClass().getResource("stat.fxml"));
                                      Parent  roott=(Parent) loader5.load();
                                          Stage staget=new Stage();
                                        staget.setScene(new Scene(roott));
                                        staget.show();
    }
   

    
    
    
}
