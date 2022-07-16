/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.email.durgesh.Email;
import entities.Mail;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import services.UserService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author eyaou
 */
public class SigInController implements Initializable {
    static int id_client=43;
    static String nom,prenom,adresse,num_tel,type,mdp,description,email,password;
    static LocalDate dn;
    static int id;
    
@FXML
    private Button mdpOub;

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnSignin;
    
     Connection connexion;
    Statement stm;
    String gr=getRandom();
    
    
    PreparedStatement preparedStatement  ;
    
    public SigInController() {
        connexion = MyDB.getInstance().getConnexion();
        
    }

     
    private String search(String password,String username,FXMLLoader loader3,FXMLLoader loader4,Parent root2,Stage stage,Stage stageg,FXMLLoader loader5,Stage staget) throws IOException
    {
     
                                       
                                       
                                         

                                        
                                    InterClientController cl =loader3.getController();
                                    InterfGuideController c2=loader4.getController();
                                    TableUsersController t=loader5.getController();
                                    
        String res="";
        String sql= "select * from utilisateur where email= '"+username+"'";
        try {
            preparedStatement= connexion.prepareStatement(sql);
             ResultSet rst = preparedStatement.executeQuery();
             if(rst.next())
             {
                String mdp=password;
               
                 String Nom=rst.getString("nom");
                 this.nom=rst.getString("nom");
                 this.mdp=rst.getString("mdp");
                 this.password=password;
                 
      String Prenom=rst.getString("prenom");
      this.prenom=rst.getString("prenom");
      
        String Adresse=rst.getString("adresse");
        this.adresse=rst.getString("adresse");
       String Tel =rst.getString("num_tel");
       this.num_tel=rst.getString("num_tel");
       this.id_client=rst.getInt("id");
       int id=rst.getInt("id");
       String des=rst.getString("description");
       this.description=rst.getString("description");
       this.id=rst.getInt("id");
       
      
       
        
       String Email=rst.getString("email");
       this.email=rst.getString("email");
       
       String Type=rst.getString("type");
       this.type=rst.getString("type");
       Date date=rst.getDate("date_naissance");
                 LocalDate DN=date.toLocalDate();
                 java.util.Date sqlDate=java.util.Date.from(DN.atStartOfDay(ZoneId.systemDefault()).toInstant());
            this.dn=date.toLocalDate();
                 if(Type.equals("client"))
                 {
                     
                   res="client";  
                   cl.afficher(password,id,Nom,Prenom,Tel,Adresse,Email,Type,mdp,DN);
                   stage.show();
                   
                 }
                 else if(Type.equals("admin"))
                 {
                     res="admin";
                     t.afficher(password,id,Nom,Prenom,Tel,Adresse,Email,Type,mdp,DN);
                     staget.show();
                    
                 }
                 else if(Type.equals("guide"))
                 {
                     res="guide";
                      c2.afficher(des,password,id,Nom,Prenom,Tel,Adresse,Email,Type,mdp,DN);
                   stageg.show();
                 }
                 
             }
        } catch (SQLException ex) {
            Logger.getLogger(TableUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return res;
    }
 public String getRandom(){
     
        Random rnd= new Random();
        int number=rnd.nextInt(9999999);
        return String.format("%06d", number);
        
    } 

    @FXML
    void signin(ActionEvent event) throws Exception {
 try {
     
                   
            UserService us= new UserService();
               String username= txtUsername.getText();    
                    String password= txtPassword.getText();
                    boolean rs=us.login(username,password);
                  // System.out.println(sp.afficherMdp(username));
                    if(rs==true)
                    {
                         JOptionPane.showMessageDialog(null, "mdp et username corrects");
                        /*  Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                          Scene scene = new Scene(FXMLLoader.load(getClass().getResource("SignIn.fxml")));
                    stage.setScene(scene);
                    stage.show();*/
                    
                  /*  Stage stage= (Stage) btnSignin.getScene().getWindow();
                    stage.close();
                     FXMLLoader fxml=FXMLLoader.load(getClass().getResource("SignIn.fxml"));
                   Stage primaryStage=new Stage();
                    Parent root =(FXMLLoader.load(getClass().getResource("SignIn.fxml")));
                    primaryStage.setTitle("verification");
                 */
                  Stage stage= (Stage) btnSignin.getScene().getWindow();
                    stage.close();
                
                         
       

                         
                        /*  Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                          Scene scene = new Scene(FXMLLoader.load(getClass().getResource("SignIn.fxml")));
                    stage.setScene(scene);
                    stage.show();*/
                  
                                           FXMLLoader loader3 = new FXMLLoader(getClass().getResource("InterClient.fxml"));
                                         Parent root2=(Parent) loader3.load();
                                          Stage stage2=new Stage();
                                        stage2.setScene(new Scene(root2));
                                       /////////////////guide/////////////
                                       
                                           FXMLLoader loader4 = new FXMLLoader(getClass().getResource("InterfGuide.fxml"));
                                        Parent  rootg=(Parent) loader4.load();
                                          Stage stageg=new Stage();
                                        stageg.setScene(new Scene(rootg));
                                        /////////////
                                         FXMLLoader loader5 = new FXMLLoader(getClass().getResource("TableUsers.fxml"));
                                      Parent  roott=(Parent) loader5.load();
                                          Stage staget=new Stage();
                                        staget.setScene(new Scene(roott));
                                       
                                        
                                        
                                        
                                        
                                       
                                      
                                        ////////////////
                                      
                                       String res=search(password,username,loader3,loader4,root2,stage2,stageg,loader5,staget);
                                       if(res.equals("admin"))
                                       {
                                           
                                           System.out.println("admin");
                                           /* FXMLLoader loader2 = new FXMLLoader(getClass().getResource("SignIn.fxml"));
                                         root=(Parent) loader2.load();
                                          Stage stage=new Stage();
                                        stage.setScene(new Scene(root));
                                        stage.show();*/
                                          

                                        /*
                                         FXMLLoader loader2 = new FXMLLoader(getClass().getResource("SignIn.fxml"));
                                      Parent  roota=(Parent) loader2.load();
                                           stage2=new Stage();
                                        stage2.setScene(new Scene(roota));
                                        stage2.show();
                                        VerifController verif=loader2.getController();
                                        verif.function(username, gr);*/
                    
                   
                    
                    
                    
                
                                       
                                        
                                      
                                         //Mail.sendMail(username, gr);
                                       }
                                       else if(res.equals("client"))
                                       {
                                          /* System.out.println("client");
                                           FXMLLoader loader3 = new FXMLLoader(getClass().getResource("InterClient.fxml"));
                                         Parent root=(Parent) loader3.load();
                                          Stage stage2=new Stage();
                                        stage2.setScene(new Scene(root));
                                        stage2.show();
                                        
          InterClientController cc= loader3.getController();*/
                                        
                                               
                                      // cc.afficher(txtUsername.getText(),txtPassword.getText());
                                       
                                        
                                       }
                                       else if(res.equals("guide"))
                                       {
                                          
                                           
 
                                        
                                        
                                        
                                       }
                                       
                                       
                                        
                    

                    
                    }
                    else
                    {
                       JOptionPane.showMessageDialog(null, "mdp et username incorrects");
                       
                    }
                    txtUsername.setText("");
                    txtPassword.setText("");
                    
                    
            
        } catch (IOException ex) {
            Logger.getLogger(LoginPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
 
    }
    
    
    
    
    	
    
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    
    
     @FXML
    void mdpOub(ActionEvent event) throws IOException {
       /*  Stage stage= (Stage) btnSignin.getScene().getWindow();
                    stage.close();*/
                    
        
        
         FXMLLoader loadermdp = new FXMLLoader(getClass().getResource("mdpOub.fxml"));
                                      Parent  rootmdp=(Parent) loadermdp.load();
                                          Stage stagemdp=new Stage();
                                        stagemdp.setScene(new Scene(rootmdp));
                                        stagemdp.show();
        

    }
}
