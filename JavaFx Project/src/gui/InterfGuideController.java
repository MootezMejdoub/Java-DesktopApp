/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Client;
import entities.Guide;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author eyaou
 */
public class InterfGuideController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField search;

    @FXML
    private DatePicker txtDN;

    @FXML
    private TextArea description;
    @FXML
    private TextField mdp;
    @FXML
    private TextField txtType;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtAdresse;
    @FXML
    private PasswordField newPwd;

    @FXML
    private PasswordField confPwd;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button Plans;
    @FXML
    private Button place;
    @FXML
    private Button statics11;
    @FXML
    private Button statics111;
    @FXML
    private Button statics1111;
    @FXML
    private Button statics2;

    public InterfGuideController() {
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    }
    UserService us = new UserService();
    private static Pattern pattern;

    private Matcher matcher;
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

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
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    void delete(ActionEvent event) {

    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        String nom = txtNom.getText();

        String prenom = txtPrenom.getText();
        java.util.Date date = java.util.Date.from(txtDN.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date sqlDate = new Date(date.getTime());
        System.out.println(sqlDate);
        String num_tel = txtTel.getText();
        String adresse = txtAdresse.getText();
        String type = txtType.getText();
        String email = txtEmail.getText();
        String desc = description.getText();

        int id = Integer.parseInt(search.getText());
        System.out.println(id);

        String mdpp = mdp.getText();
        int ev = 0;
        /*   if (us.readAllLogins().contains(email) == true) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Cette adresse est utilisée par un autre compte");
                alert.showAndWait();
                txtEmail.clear();
               // txtPassword.clear();
                
                return;
            }
         */

        UserService service = new UserService();
        if ((newPwd.getText()).equals("")) {

            Guide p = new Guide(id, nom, prenom, sqlDate, adresse, num_tel, email, mdpp, type, desc, ev);

            service.modifierGuidePst(p);;
        } else if (!((newPwd.getText()).equals(""))) {
            if ((newPwd.getText()).equals(confPwd.getText())) {
                String nmdp = newPwd.getText();
                Guide p = new Guide(id, nom, prenom, sqlDate, adresse, num_tel, email, nmdp, type, desc, ev);

                service.modifierGuidePst(p);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("verifier votre mot de passe");
                alert.show();
                newPwd.setText("");
                confPwd.setText("");
            }
        }
    }

    public void afficher(String des, String password, int id, String nom, String prenom, String tel, String adresse, String email, String type, String mdpp, LocalDate DN) {

        String idd = String.valueOf(id);
        search.setText(idd);
        txtNom.setText(nom);
        txtPrenom.setText(prenom);
        txtAdresse.setText(adresse);
        txtEmail.setText(email);
        txtTel.setText(tel);
        mdp.setText(password);
        LocalDate date = DN;

        description.setText(des);

        txtDN.setValue(date);
        txtType.setText(type);

    }

    @FXML
    private void toPlan(ActionEvent event) throws IOException {

        FXMLLoader loader5 = new FXMLLoader(getClass().getResource("MainInterfaceGuide.fxml"));
        Parent roott = (Parent) loader5.load();
        Stage staget = new Stage();

        staget.setScene(new Scene(roott));
        staget.show();
    }

    @FXML
    private void toPlace(ActionEvent event) {
    }
}
