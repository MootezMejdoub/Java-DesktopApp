/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.PlaceInterfaceGuideController;
import gui.PlanInterfaceGuideController;
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
public class MainInterfaceGuideController {
    @FXML
    private BorderPane MainBorderPaneGuide;
    
    public void LesPlansBtn_OnAction( ActionEvent event ) throws IOException, SQLException{
        
        FXMLLoader load = new FXMLLoader(getClass().getResource("PlanInterfaceGuide.fxml"));
        Pane PlanPage= load.load();
        PlanInterfaceGuideController PIGC = load.getController();
        PIGC.setPlansList(event);
        MainBorderPaneGuide.setCenter(PlanPage);
        
    }
    
    public void LesPlacesBtn_OnAction( ActionEvent event ) throws IOException, SQLException{
        
        FXMLLoader load = new FXMLLoader(getClass().getResource("PlaceInterfaceGuide.fxml"));
        Pane PlacePage= load.load();
        PlaceInterfaceGuideController PIGC = load.getController();
        PIGC.setPlacesList(event);
        MainBorderPaneGuide.setCenter(PlacePage);
        
    }
}
