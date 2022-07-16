/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.PlacePaneClientController;
import entities.Place;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.PlaceService;

/**
 *
 * @author bahe
 */
public class PlaceInterfaceClientController {
    @FXML
    private VBox PlaceVboxOfClient;
    @FXML
    private Pane      BIGPANE;
    @FXML
    private TextField nom;
    @FXML
    private TextField address;
    @FXML
    private TextField description;
    @FXML
    private TextField type;
    @FXML
    private TextField note;

    @FXML
    private Button     createPlace;
    @FXML
    private Button     createPlaceZina;
    public void setPlacesList( ActionEvent event ) throws IOException, SQLException{

        PlaceService place_service = new PlaceService();
        for (final Place place : place_service.readAll()) {
            FXMLLoader load = new FXMLLoader(getClass().getResource("PlacePaneClient.fxml"));
            Pane PlacePane= load.load();
            PlacePaneClientController PPCC = load.getController();
//Place(String nom, double note, String address, String description, String type)
            PPCC.setPlace(place.getNom(),
                         Double.toString(place.getNote()),
                         place.getAddress(),
                         place.getDescription(),
                         place.getType(),
                         Integer.toString(place.getId()));
            PlaceVboxOfClient.getChildren().add(PlacePane);
            System.out.print("hi");
        }
    }
    
    public void create() throws SQLException{
        PlaceService place_service = new PlaceService();
        
        Place place = new Place(this.nom.getText(),
                             Double.parseDouble(this.note.getText()),
                             this.address.getText(),
                             this.description.getText(),
                             this.type.getText());
        place_service.create(place);
        
        BIGPANE.getChildren().remove(createPlace);
    }
    
}
