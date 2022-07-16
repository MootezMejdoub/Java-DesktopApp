/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.MyDB;


/**
 *
 * @author bahe
 */
public class MatchigoINTERFACES extends Application {
    Connection cnx;
    Statement stm;

    public MatchigoINTERFACES() {
               cnx =  MyDB.getInstance().getConnexion();

    }
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        //ADMIN  INTERFACE:   "/interfaces/admin/MainInterfaceAdmin.fxml"
        //CLIENT INTERFACE: "/interfaces/client/MainInterfaceClient.fxml"
        //GUIDE  INTERFACE:   "/interfaces/guide/MainInterfaceGuide.fxml"
        Parent root = FXMLLoader.load(getClass().getResource("/gui/MainInterfaceGuide.fxml"));
        Scene scene = new Scene(root, 1269.0, 571.0);
        primaryStage.setTitle("MATCHIGO");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
