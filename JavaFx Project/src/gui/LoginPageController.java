/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author eyaou
 */
public class LoginPageController implements Initializable {

    @FXML
    private TextField  txtUsername;
    @FXML
    private Label exit;
    @FXML
    private PasswordField txtPassword;
   @FXML
    private Button btnSignin;
   @FXML
    private VBox scroll;
     @FXML
    private TextArea txtdescription;

      @FXML
    private TextField txtNom;
 @FXML
    private PasswordField txttPassword;
    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtEmail;

    @FXML
    private DatePicker txtDN;

    @FXML
    private Button btnvalider;

    @FXML
    private ComboBox<?> txtType;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtAdresse;

    @FXML
    private Button btnafficher;

   
   public void handleButtonAction(MouseEvent event)
   {
       if(event.getSource()==exit)
       {
           System.exit(0);
       }
   }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    public void setTxtUsername(String value) {
        this.txtUsername.setText(value);
    }

    public void setTxtPassword(String value) {
                this.txtUsername.setText(value);

    }
    public void setbtnSignin(String value) {
                this.btnSignin.setText(value);

    }
   
   

    @FXML
    private void signup (ActionEvent event)
    {
   
   
    }        
   
    @FXML
    private void signin (ActionEvent event)
    {
        
        try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));

            Parent root = loader.load();
                        LoginPageController controller = loader.getController();
            UserService us= new UserService();
               String username= txtUsername.getText();    
                    String password= txtPassword.getText();
                    boolean rs=us.login(username,password);
                  // System.out.println(sp.afficherMdp(username));
                    if(rs==true)
                    {
                         JOptionPane.showMessageDialog(null, "mdp et username corrects");
                          Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                          Scene scene = new Scene(FXMLLoader.load(getClass().getResource("TableUsers.fxml")));
                    stage.setScene(scene);
                    stage.show();
                    }
                    else
                    {
                       JOptionPane.showMessageDialog(null, "mdp et username incorrects");
                       
                    }
                    txtUsername.setText("");
                    txtPassword.setText("");
                    txtUsername.getScene().setRoot(root);
                    
            
        } catch (IOException ex) {
            Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
}
