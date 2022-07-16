/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Client;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;

import services.UserService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author eyaou
 */
public class TableUsersController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private TextField noma;

    @FXML
    private TextField emaila;

    @FXML
    private TextField typea;
    
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
    private Button btnsearch;
 
    @FXML
    private TextField mdp;
    @FXML
    private TextField txtType;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtAdresse;

   
    @FXML
    private TableView <Client> tblUsers;
    @FXML
    private TableColumn<Client, String> nomCol;
    @FXML
    private TableColumn<Client, String> prenomCol;
    @FXML
    private TableColumn<Client, String> emailCol;
    @FXML
    private TableColumn<Client, String> typeCol;
     
    
    
    
    ObservableList<Client>  UsersList = FXCollections.observableArrayList();
    @FXML
    private Button btnmodifier;

      Connection connexion;
    Statement stm;
    
    PreparedStatement preparedStatement  ;
    @FXML
    private Button statics;
    @FXML
    private Button reclamation;
    @FXML
    private Button statics1111;
    @FXML
    private Button statics2;
    @FXML
    private Button ajouteradmin;
    @FXML
    private Button plan;
    @FXML
    private Button place;
    
    public TableUsersController() {
        connexion = MyDB.getInstance().getConnexion();
    }
    public void setTxtNom(String value) {
        this.txtNom.setText(value);
        
    }

    public void setTxtPrenom(String value) {
                this.txtPrenom.setText(value);

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
           String req = "select * from utilisateur  ";
            
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
    
     public void afficher()
    {
        
        
                ObservableList<Client> UsersList=getUsersList();
       
         
      //   idCol.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id"));

         nomCol.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<Client,String>("prenom"));
       // dnCol.setCellValueFactory(new PropertyValueFactory<Client,Date>("date_naissance"));
      //  adresseCol.setCellValueFactory(new PropertyValueFactory<Client,String>("adresse"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Client,String>("email"));
         typeCol.setCellValueFactory(new PropertyValueFactory<Client,String>("type"));
       // telCol.setCellValueFactory(new PropertyValueFactory<Client,String>("num_tel"));
       // mdpCol.setCellValueFactory(new PropertyValueFactory<Client,String>("mdp"));
        tblUsers.setItems(UsersList);
        nomCol.setCellFactory(TextFieldTableCell.forTableColumn());
       
        
        
        
        
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
        String mdpp = mdp.getText();
        int id=Integer.parseInt(search.getText());
        System.out.println(id);
        
        
        
        
        UserService service = new UserService();
        
        
         Client p = new Client( id,nom,  prenom, sqlDate ,  adresse, num_tel, email,mdpp,  type);

        
            service.modifierClientPst(p);
           
            afficher();
        
        
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Person is added successfully!");
            alert.show();
            
              
            
        
        
    }
     
     void tableEvent()
     {
         Client c=tblUsers.getSelectionModel().getSelectedItem();
         String sql="select * from utilisateur where nom ='"+c.getNom()+"'";
        try {
            stm = connexion.prepareStatement(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(TableUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
     }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      afficher();
       
          // TODO
        
    }    
    
    @FXML
    private void toStat(ActionEvent event) throws IOException  {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("stat.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
     @FXML
    void ajouteradmin(ActionEvent event) {
         String req = "update utilisateur set type= ?  where email = ?";
            
            try {
            preparedStatement = connexion.prepareStatement(req);
            
            
           
            preparedStatement.setString(1, typea.getText());
           
            preparedStatement.setString(2,emaila.getText() );
             
           
            
                System.out.println("modification effectuée");
            
            preparedStatement.executeUpdate();
            afficher();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

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
            afficher();
        
        
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Person is added successfully!");
            alert.show();
            
              
    }
    @FXML
    private void handleMouseAction(MouseEvent event)
    {
        UserService u=new UserService();
        Client c=tblUsers.getSelectionModel().getSelectedItem();
       /* System.out.println("id="+c.getId());
        System.out.println("id="+c.getNom());*/
       noma.setText(c.getNom());
       emaila.setText(c.getEmail());
     //  txtAdresse.setText(c.getAdresse());
     //  txtEmail.setText(c.getEmail());
     //  txtTel.setText(c.getNum_tel());
     //  mdp.setText(c.getMdp());
    //   Date date=c.getDate_naissance();
       // txtDN.setValue(date.toLocalDate());
        typea.setText(c.getType());
      //  search.setText(String.valueOf(c.getId()));
       //txtId.setText(c.getId());
       
   
    }
    @FXML
    private void search(ActionEvent event)
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
    private void toRec(ActionEvent event) throws IOException {
         FXMLLoader loader5 = new FXMLLoader(getClass().getResource("ReclamationAdmin.fxml"));
                                      Parent  roott=(Parent) loader5.load();
                                          Stage staget=new Stage();
                                        staget.setScene(new Scene(roott));
                                        staget.show();
    }

    @FXML
    private void toPlan(ActionEvent event) throws IOException {
         FXMLLoader loader5 = new FXMLLoader(getClass().getResource("MainInterfaceAdmin.fxml"));
        Parent roott = (Parent) loader5.load();
        Stage staget = new Stage();

        staget.setScene(new Scene(roott));
        staget.show();
    }

    @FXML
    private void toPlace(ActionEvent event) {
    }
}
