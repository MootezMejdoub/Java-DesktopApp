/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package workshop_3a6.gui2;

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
public class PersonneInscriptionController implements Initializable {

    @FXML
    private TextField textFieldID;
    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldPrenom;
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
    private void onCreate(ActionEvent event) {
        // try{
        int id = Integer.parseInt(this.textFieldID.getText());
        String nom = textFieldNom.getText();
        String prenom = textFieldPrenom.getText();

        Personne p = new Personne(id, nom, prenom);
        PersonneService service = new PersonneService();
        service.ajouter(p);

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsWindow.fxml"));
            Parent root = loader.load();
            DetailsWindowController controller =  loader.getController();
            controller.setTextFieldID(p.getId()+"");
            controller.setTextFieldNom(p.getNom());
            controller.setTextFieldPrenom(p.getPrenom());
            
            textFieldID.getScene().setRoot(root);
            
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
