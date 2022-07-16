/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.PlaceInterfaceClientController;
import gui.PlanInterfaceClientController;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author bahe
 */
public class MainInterfaceClientController {
    @FXML
    private BorderPane MainBorderPaneClient;
    
    public void LesPlansBtn_OnAction( ActionEvent event ) throws IOException, SQLException{
        
        FXMLLoader load = new FXMLLoader(getClass().getResource("PlanInterfaceClient.fxml"));
        Pane PlanPage= load.load();
        PlanInterfaceClientController PICC = load.getController();
        PICC.setPlansList(event);
        MainBorderPaneClient.setCenter(PlanPage);
        
    }
    
    public void LesPlacesBtn_OnAction( ActionEvent event ) throws IOException, SQLException{
        
        FXMLLoader loadd = new FXMLLoader(getClass().getResource("PlaceInterfaceClient.fxml"));
        Pane PlacePage= loadd.load();
        PlaceInterfaceClientController PICC = loadd.getController();
        PICC.setPlacesList(event);
        MainBorderPaneClient.setCenter(PlacePage);
        
    }
}
