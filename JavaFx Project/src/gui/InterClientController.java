/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import entities.Client;
import entities.CryptWithMD5;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

import services.UserService;
import utils.MyDB;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eyaou
 */
public class InterClientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection connexion;
    Statement stm;
    @FXML
    private PasswordField newPwd;

    @FXML
    private PasswordField confPwd;
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
    private TextField mdp;
    @FXML
    private TextField txtType;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtAdresse;

   

  
     
    
      
    
    PreparedStatement preparedStatement  ;
    
    
  
    @FXML
    private Button btnmodifier;
    @FXML
    private Button reclamation;
    @FXML
    private Button account;
    @FXML
    private Button plan;
    @FXML
    private Button places;
    @FXML
    private Button reservation;
    @FXML
    private Button facture;
    @FXML
    private Button facture1;
    @FXML
    private Button rating;

    public InterClientController() {
        connexion = MyDB.getInstance().getConnexion();
                                pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    }
       private static Pattern pattern;
private Matcher matcher;
        private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    public void setTxtNom(String value) {
        this.txtNom.setText(value);
    }

    public void setTxtPrenom(String value) {
                this.txtPrenom.setText(value);

    }
    
    private void ajouter(ActionEvent event)
    {
        String nom = txtNom.getText();
        String prenom = txtPrenom.getText();
      //  Date date_naissance = txtDN.getDate();
        String num_tel = txtTel.getText();
        String adresse = txtAdresse.getText();
        String type = txtType.getText();
        String email = txtEmail.getText();
        String mdp="";
        
        UserService service = new UserService();
        
        
        
        
        
      //  Client p = new Client( nom,  prenom,  date_naissance,  adresse, num_tel, email,"xxx",  type);

        
        
        
        
        
           // service.ajouterClient(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Person is added successfully!");
            alert.show();
            
              
            
        
        
    }
    
    public ObservableList<Client> getUsersList()
    {
        ObservableList<Client> UsersList=FXCollections.observableArrayList();
       
        try
        {
           String req = "select * from utilisateur ";
            
            stm = connexion.createStatement();
            //ensemble de resultat
            ResultSet rst = stm.executeQuery(req);
            while (rst.next()) {
                Client p;
               p = new Client(rst.getInt("id"),//or rst.getInt(1)
                       rst.getString("nom"),
                       rst.getString("prenom"),
                       rst.getDate("date_naissance"),
                       rst.getString("adresse"),
                       rst.getString("num_tel"),
                       rst.getString("email"),
                       rst.getString("mdp"),
                       rst.getString("type")
                       
               );
                 UsersList.add(p);
                 
                 }
            
        }catch(Exception ex)
        {
                        Logger.getLogger(TableUsersController.class.getName()).log(Level.SEVERE, null, ex);

        }
        return UsersList;
    }
    
     
    
     @FXML
   private void modifier(ActionEvent event)
    {
        String nom = txtNom.getText();
      
        String prenom = txtPrenom.getText();
            java.util.Date date=java.util.Date.from(txtDN.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date sqlDate= new Date(date.getTime());
            System.out.println(sqlDate);
        String num_tel = txtTel.getText();
        String adresse = txtAdresse.getText();
        String type = txtType.getText();
        String email = txtEmail.getText();
        
        int id=Integer.parseInt(search.getText());
        System.out.println(id);
        
        String mdpp = mdp.getText();
        
        
        UserService service = new UserService();
        if((newPwd.getText()).equals(""))
        {
            
            Client p = new Client( id,nom,  prenom, sqlDate ,  adresse, num_tel, email,mdpp,  type);

        
            service.modifierClientPst(p);
        }
        else if(!((newPwd.getText()).equals("")))
        {
         if((newPwd.getText()).equals(confPwd.getText()))
         {
            String nmdp= newPwd.getText();
            Client p = new Client( id,nom,  prenom, sqlDate ,  adresse, num_tel, email,nmdp,  type);

        
            service.modifierClientPst(p);
         }
         else
         {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("verifier votre mot de passe");
            alert.show();
            newPwd.setText("");
            confPwd.setText("");
         }
        }
        
         
           
          
        
        
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Modification effectuée");
            alert.show();
            
              
            
        
        
    }
     
    
         
     

   
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
       
          // TODO
        
    }  
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public void afficher(String password,int id,String nom,String prenom,String tel,String adresse,String email,String type,String mdpp,LocalDate DN)
    {
         
        String idd= String.valueOf(id);
        search.setText(idd);
        txtNom.setText(nom);
       txtPrenom.setText(prenom);
       txtAdresse.setText(adresse);
       txtEmail.setText(email);
       txtTel.setText(tel);
       mdp.setText(password);
       LocalDate date=DN;
        
       
        txtDN.setValue(date);
        txtType.setText(type);
        
    }
    
    private void delete(ActionEvent event) 
    {
         
       String nom = txtNom.getText();
      
        String prenom = txtPrenom.getText();
            java.util.Date date=java.util.Date.from(txtDN.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date sqlDate= new Date(date.getTime());
            System.out.println(sqlDate);
        String num_tel = txtTel.getText();
        String adresse = txtAdresse.getText();
        String type = txtType.getText();
        String email = txtEmail.getText();
        String mdpp = mdp.getText();
        int id=Integer.parseInt(search.getText());
        System.out.println(id);
        
        
        
        
        UserService service = new UserService();
        
        
         Client p = new Client( id,nom,  prenom, sqlDate ,  adresse, num_tel, email,mdpp,  type);

        
            service.suppClientPst(p);
            
        
        
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Person is added successfully!");
            alert.show();
            
              
    }
   public void client(String Nom,String Prenom ,String Tel,String Adresse,String Email,String Type,String mdpp)
   {
       
        txtNom.setText(Nom);
       txtPrenom.setText(Prenom);
       txtAdresse.setText(Adresse);
       txtEmail.setText(Email);
       txtTel.setText(Tel);
       mdp.setText(mdpp);
       /*Date date=DN;
        
       
        txtDN.setValue(date.toLocalDate());*/
        txtType.setText(Type);
   }
    private void search()
    {
        String sql= "select * from utilisateur where id= '"+search.getText()+"'";
        try {
            preparedStatement= connexion.prepareStatement(sql);
             ResultSet rst = preparedStatement.executeQuery();
             if(rst.next())
             {
                 mdp.setText(rst.getString("mdp"));
                 txtNom.setText(rst.getString("nom"));
       txtPrenom.setText(rst.getString("prenom"));
      
        txtAdresse.setText(rst.getString("adresse"));
       txtTel.setText(rst.getString("num_tel"));
       
      
       
        
       txtEmail.setText(rst.getString("email"));
       txtType.setText(rst.getString("type"));
       Date date=rst.getDate("date_naissance");
        txtDN.setValue(date.toLocalDate());
             }
        } catch (SQLException ex) {
            Logger.getLogger(TableUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
    }
     @FXML
    private void reclamer (ActionEvent event) throws IOException  {
       FXMLLoader loader5 = new FXMLLoader(getClass().getResource("ReclamationVideo.fxml"));
                                      Parent  roott=(Parent) loader5.load();
                                          Stage staget=new Stage();
                                        staget.setScene(new Scene(roott));
                                        staget.show();
                                       
    }

    @FXML
    private void toAccount(ActionEvent event) {
        
        
    }

    @FXML
    private void toPlan(ActionEvent event) throws IOException {
         FXMLLoader loader5 = new FXMLLoader(getClass().getResource("MainInterfaceClient.fxml"));
        Parent roott = (Parent) loader5.load();
        Stage staget = new Stage();

        staget.setScene(new Scene(roott));
        staget.show();
    }

    @FXML
    private void toPlace(ActionEvent event) {
    }

    @FXML
    private void toRes(ActionEvent event) {
    }

    @FXML
    private void toFac(ActionEvent event) {
    }

    @FXML
    private void toRate(ActionEvent event) throws IOException {
        FXMLLoader loader5 = new FXMLLoader(getClass().getResource("rate.fxml"));
                                      Parent  roott=(Parent) loader5.load();
                                          Stage staget=new Stage();
                                        staget.setScene(new Scene(roott));
                                        staget.show();
    }
    
    
    
    
    
}
