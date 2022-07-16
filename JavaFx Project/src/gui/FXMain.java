/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author mbell
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
      
        Parent root;
        
      
        try {
        root = FXMLLoader.load(getClass().getResource("Reservation.fxml"));
      
        Scene scene = new Scene(root);
       
//        Image icon =new Image("resicon.jpg"); 
//        primaryStage.getIcons().add(icon);
//          
               
            primaryStage.setTitle("Reservation");
        primaryStage.setScene(scene);
        primaryStage.show();
          } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /** 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
