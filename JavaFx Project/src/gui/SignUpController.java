/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Arrays;
import java.util.List;

import com.squareup.okhttp.*;
import com.squareup.okhttp.internal.http.HttpMethod;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.squareup.okhttp.logging.HttpLoggingInterceptor.Level;
import okio.BufferedSink;
import okio.Okio;
import entities.Client;
import entities.Guide;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.UserService;
import java.util.Arrays;
import java.util.List;
import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
//import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author eyaou
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtTel;
    @FXML
    private TextField txtEmail;
    @FXML
    private DatePicker txtDN;
    @FXML
    private TextField txtType;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextArea txtdescription;
    @FXML
    private Button btnvalider;

    String gr=rnd();
    public SignUpController() {
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    }

    /**
     * Initializes the controller class.
     */
    private static Pattern pattern;

    private Matcher matcher;
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    UserService us = new UserService();

    
    

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
          
       
        

// TODO
        txtTel.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtTel.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        txtNom.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("[^\\d]")) {
                txtNom.setText(newValue.replaceAll("\\d*", ""));
            }
        });
        txtPrenom.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("[^\\d]")) {
                txtPrenom.setText(newValue.replaceAll("\\d*", ""));
            }
        });
        txtType.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("[^\\d]")) {
                txtType.setText(newValue.replaceAll("\\d*", ""));
            }
        });
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @FXML
    private void valider(ActionEvent event) throws SQLException, IOException, ClickSend.ApiException{
        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();

        String num_tel = txtTel.getText();
        String adresse = txtAdresse.getText();
        String type = txtType.getText();
        String email = txtEmail.getText();
        String description = "";
        String mdp = txtPassword.getText();

        System.out.println(num_tel);
        System.out.println(gr);
        if (us.readAllLogins().contains(email) == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Email already exists");
            alert.showAndWait();
            txtEmail.clear();
            txtPassword.clear();

            return;
        }
        if (us.readAllTels().contains(num_tel) == true) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Telephone number already exists");
            alert.showAndWait();
            txtTel.clear();
            return;
        }
if (txtTel.getText().length() < 8) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Numero de télephone invalide ,8 chiffres");
                alert.showAndWait();
                txtTel.clear();
                
                return;
            }
if (txtPassword.getText().length() < 5) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Password too short, minimum length 5 characters");
                alert.showAndWait();
                txtPassword.clear();
                
                return;
            }
        if (!validateEmail(txtEmail.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Invalid email pattern");
            alert.showAndWait();
            txtEmail.clear();
            txtPassword.clear();

            return;

        }
        if (("".equals(nom)) || ("".equals(type)) || ("".equals(prenom)) || ("".equals(email)) || ("".equals(adresse)) || ("".equals(num_tel)) || ("".equals(mdp))) {
            System.out.println("oui");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("les champs sont vides ");
            alert.showAndWait();
            return;
        }

        java.util.Date date = java.util.Date.from(txtDN.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        Client p = new Client(nom, prenom, sqlDate, adresse, num_tel, email, mdp, type);

        Guide g = new Guide(nom, prenom, sqlDate, adresse, num_tel, email, mdp, type, description);

        if (type.equals("client") || type.equals("admin") || type.equals("simpleUser")) {

            us.ajouterClient(p);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
            Parent root = (Parent) loader.load();
            

        } else if (type.equals("guide")) {

            us.ajouterGuide(g);

        }

        /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Person is added successfully!");
            alert.show();*/
        ApiClient defaultClient = new ApiClient();
        defaultClient.setUsername("mootez.mejdoub@esprit.tn");
        defaultClient.setPassword("47CE09C9-E071-4C6C-7268-9EC4CB13E51D");
        SmsApi apiInstance = new SmsApi(defaultClient);

        SmsMessage smsMessage = new SmsMessage();
        smsMessage.body("Registered successfully.Please verify your account using this code:" +gr);
        smsMessage.to("+216"+num_tel);
        smsMessage.source("sign up");
        

        List<SmsMessage> smsMessageList = Arrays.asList(smsMessage);
        // SmsMessageCollection | SmsMessageCollection model
        SmsMessageCollection smsMessages = new SmsMessageCollection();
        smsMessages.messages(smsMessageList);
        try {
            String result = apiInstance.smsSendPost(smsMessages);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SmsApi#smsSendPost");
            e.printStackTrace();
        }
     
      
    
                                         FXMLLoader loaderv = new FXMLLoader(getClass().getResource("verifSignUp.fxml"));
                                      Parent  rootv=(Parent) loaderv.load();
                                         Stage  stagev=new Stage();
                                        stagev.setScene(new Scene(rootv));
                                        
                                        VerifSignUpController v=loaderv.getController();
                                        
                                        v.function(mdp,email, gr);
                    /* v.sens_sms(num_tel, gr);
                    */
                     System.out.println(num_tel);
                     System.out.println(gr);
                    stagev.show();
                     
                                         FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
                                         Parent roott = (Parent) loader.load();
                                          Stage stage2=new Stage();
                                        stage2.setScene(new Scene(roott));
                                   /*     Stage stage= (Stage) btnvalider.getScene().getWindow();
                    stage.close();*/
                                        
                                        ////////////////
    }
    

    public String rnd(){
     
        Random rnd= new Random();
        int number=rnd.nextInt(9999999);
        return String.format("%06d", number);
        
    } 
}
