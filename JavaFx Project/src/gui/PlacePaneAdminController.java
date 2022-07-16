/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Place;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.PlaceService;

/**
 *
 * @author bahe
 */
public class PlacePaneAdminController {
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
    private Button     deletePlace;
    @FXML
    private Button     deletePlaceZina;
    @FXML
    private Button     updatePlace;
    @FXML
    private Button     updatePlaceZina;
    @FXML
    private Label      id;
    public static int X=0;
    public void setPlace(String nom, String note, String address, String description, String type, String id) throws IOException{
        X+=1;
        System.out.print("PLAN:"+X);
        this.id.setText(Integer.toString(X));
        this.nom.setText(nom);
        this.note.setText(note);
        this.address.setText(address);
        this.description.setText(description);
        this.type.setText(type);
        this.id.setText(id);
    }
    
    public void delete() throws IOException, SQLException{
        
        PlaceService place_service = new PlaceService();
        place_service.delete(Integer.parseInt(this.id.getText()));
        
        BIGPANE.setStyle("-fx-background-color: #ff0000");
        BIGPANE.getChildren().remove(updatePlace);
        BIGPANE.getChildren().remove(updatePlaceZina);
        BIGPANE.getChildren().remove(deletePlace);
        
    }
    
    public void update() throws SQLException{
        PlaceService place_service = new PlaceService();
        //Place(String nom, double note, String address, String description, String type)

        Place place = new Place(Integer.parseInt(this.id.getText()),
                               this.nom.getText(),
                             Double.parseDouble(this.note.getText()),
                             this.address.getText(),
                             this.description.getText(),
                             this.type.getText());
        place_service.update(place);
        
        BIGPANE.setStyle("-fx-background-color: #00ff00");
        BIGPANE.getChildren().remove(deletePlace);
        BIGPANE.getChildren().remove(deletePlaceZina);
        BIGPANE.getChildren().remove(updatePlace);
    }
    
}
