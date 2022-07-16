/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package workshop_3a6.gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import workshop_3a6.services.PersonneService;
import workshop_3a6.entities.Personne;
/**
 * FXML Controller class
 *
 * @author remo
 */
public class DetailsWindowController implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private Button btnList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setTfId(String value) {
        this.tfId.setText(value);
    }

    public void setTfNom(String value) {
        this.tfNom.setText(value);
    }

    public void setTfPrenom(String value) {
        this.tfPrenom.setText(value);
    }

    @FXML
    private void onClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowAll.fxml"));
             Parent root = loader.load();
            ShowAllController controller = loader.getController();
           
            PersonneService service = new PersonneService();
            List<Personne> personnes = service.afficher();
            controller.setLabel(personnes.toString());
            
            tfNom.getScene().setRoot(root);
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    
    
    
    
    
}
