/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import ClickSend.Api.SmsApi;
import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Model.SmsMessage;
import ClickSend.Model.SmsMessageCollection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.squareup.okhttp.*;
import com.squareup.okhttp.internal.http.HttpMethod;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.squareup.okhttp.logging.HttpLoggingInterceptor.Level;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import okio.BufferedSink;
import okio.Okio;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author eyaou
 */
public class VerifSignUpController implements Initializable {

    /**
     * Initializes the controller class.
     */
    PreparedStatement preparedStatement  ;
    java.sql.Connection connexion;
    Statement stm;
    
    public VerifSignUpController() {
                connexion = MyDB.getInstance().getConnexion();

    }

    
     @FXML
    private TextField code;
     @FXML
    private TextField password;

    @FXML
    private Button verifier;

    @FXML
    private TextField grr;

    @FXML
    private TextField username;

    
    
    public void function(String mdp,String user,String gr)
        
        
{
   grr.setText(gr);
   username.setText(user);
   password.setText(mdp);
    
}
    @FXML
    void verifier(ActionEvent event) throws IOException {
        
                                           FXMLLoader loader3 = new FXMLLoader(getClass().getResource("InterClient.fxml"));
                                         Parent root2=(Parent) loader3.load();
                                          Stage stage2=new Stage();
                                        stage2.setScene(new Scene(root2));
                                        
        InterClientController cl =loader3.getController();
        //////////////////////
                                           FXMLLoader loader4 = new FXMLLoader(getClass().getResource("InterfGuide.fxml"));
                                         Parent root4=(Parent) loader4.load();
                                          Stage stage4=new Stage();
                                        stage4.setScene(new Scene(root4));
                                    InterfGuideController c2=loader4.getController();
                                    System.out.println(code.getText());
                                    System.out.println(grr.getText());
        if((grr.getText()).equals(code.getText()))
        {
            System.out.println("test");
            System.out.println("eeeeeeeeeeeeyyyyy");
        String   usr=username.getText();
        String sql= "select * from utilisateur where email= '"+usr+"'";
        try {
            preparedStatement= connexion.prepareStatement(sql);
             ResultSet rst = preparedStatement.executeQuery();
             if(rst.next())
             {
                String mdp=rst.getString("mdp");
                String password="";
                 String Nom=rst.getString("nom");
      String Prenom=rst.getString("prenom");
      
        String Adresse=rst.getString("adresse");
       String Tel =rst.getString("num_tel");
       
       int id=rst.getInt("id");
       String des=rst.getString("description");
      
       
        
       String Email=rst.getString("email");
       String Type=rst.getString("type");
       Date date=rst.getDate("date_naissance");
                 LocalDate DN=date.toLocalDate();
                 java.util.Date sqlDate=java.util.Date.from(DN.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
                 if(Type.equals("client"))
                 {
                     
                  
                   cl.afficher(password,id,Nom,Prenom,Tel,Adresse,Email,Type,mdp,DN);
                   stage2.show();
                   
                 }
                 else if(Type.equals("admin"))
                 {
                    
                    
                 }
                 else if(Type.equals("guide"))
                 {
                     
                      c2.afficher(des,password,id,Nom,Prenom,Tel,Adresse,Email,Type,mdp,DN);
                   stage4.show();
                 }
                 
             }
        } catch (SQLException ex) {
            Logger.getLogger(TableUsersController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        }
        else
        {
            System.out.println("leeeeeeeeeeeeeeeeeeeeee");
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    
   /* public void sens_sms(String num_tel,String gr)
    {
        
         ApiClient defaultClient = new ApiClient();
            defaultClient.setUsername("aya.ouertatani@esprit.tn");
            defaultClient.setPassword("254E3275-3A3D-7843-F253-926273CF4EA2");
            //defaultClient.setApiKey();
            SmsApi apiInstance = new SmsApi(defaultClient);

            SmsMessage smsMessage = new SmsMessage();
            smsMessage.body("Registered successfully.Please verify your account using this code: " +gr );
            smsMessage.to("+216"+num_tel);
            smsMessage.source("Sign up");
            smsMessage.setFrom("MatchiGo");
           // smsMessage.setFrom("Notex Team");

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
            grr.setText(gr);
            
    }
    */
    
}
