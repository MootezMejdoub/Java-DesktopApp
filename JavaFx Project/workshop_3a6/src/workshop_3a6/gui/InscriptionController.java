/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package workshop_3a6.gui;

import java.io.IOException;
import java.net.URL;
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
import workshop_3a6.entities.Personne;
import workshop_3a6.services.PersonneService;

/**
 * FXML Controller class
 *
 * @author remo
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private Button btnSubmit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void savePerson(ActionEvent event) {
        int id = Integer.parseInt(tfId.getText());
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        
        Personne p = new Personne(id, nom, prenom);
        PersonneService service = new PersonneService();
        service.ajouter(p);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsWindow.fxml"));
        
        try {
            Parent root = loader.load();
            DetailsWindowController detailsWindowController = loader.getController();
            detailsWindowController.setTfId(id+"");
            detailsWindowController.setTfNom(nom);
            detailsWindowController.setTfPrenom(prenom);
            
            tfNom.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
