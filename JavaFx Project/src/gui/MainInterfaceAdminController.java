/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import gui.PlanInterfaceAdminController;
import java.sql.SQLException;
import javafx.scene.Parent;

/**
 *
 * @author bahe
 */
public class MainInterfaceAdminController {
    @FXML
    private BorderPane MainBorderPaneAdmin;
    
    public void LesPlansBtn_OnAction( ActionEvent event ) throws IOException, SQLException{
        
        FXMLLoader load = new FXMLLoader(getClass().getResource("PlanInterfaceAdmin.fxml"));
        Pane PlanPage= load.load();
        PlanInterfaceAdminController PIAC = load.getController();
        PIAC.setPlansList(event);
        MainBorderPaneAdmin.setCenter(PlanPage);
        
    }
    
    public void LesPlacesBtn_OnAction( ActionEvent event ) throws IOException, SQLException{
        
        FXMLLoader loadd = new FXMLLoader(getClass().getResource("PlaceInterfaceAdmin.fxml"));
        Pane PlacePage= loadd.load();
        PlaceInterfaceAdminController PpIAC = loadd.getController();
        PpIAC.setPlacesList(event);
        MainBorderPaneAdmin.setCenter(PlacePage);
        
    }
}

