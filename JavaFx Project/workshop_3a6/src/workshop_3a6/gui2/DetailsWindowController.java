/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package workshop_3a6.gui2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author remo
 */
public class DetailsWindowController implements Initializable {

    @FXML
    private TextField textFieldID;
    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldPrenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setTextFieldID(String value) {
        this.textFieldID.setText(value);
    }

    public void setTextFieldNom(String value) {
        this.textFieldNom.setText(value);
    }

    public void setTextFieldPrenom(String value) {
        this.textFieldPrenom.setText(value);
    }
    
    
    
    
    
    
    
}
