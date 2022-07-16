/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import utils.JavaMailUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.sun.javafx.scene.control.skin.LabeledText;
import entities.Reclamation;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.ReclamationService;
import utils.MyDB;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * FXML Controller class
 *
 * @author Tox
 */
public class AjouterReclamationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public static final String ACCOUNT_SID = "AC11504623102e397beac52ffbea6fef4d";
    public static final String AUTH_TOKEN = "bda3355dce262c9246f1a90f0ce7611f";
   @FXML
    private MediaView mediaVieww;
    @FXML
    private Pane paneReclamation;
    @FXML
    private TextField NomTXFLD;
    @FXML
    private TextField PrenomTXFLD;
    @FXML
    private TextField EmailTXFLD;
    @FXML
    private TextField DescriptionTXFLD;
    int selectedReclamationID;
    int selectedCompteID;
    private ImageView recaptchaCheckMark;
    int etatrecaptcha = 0;
    private Connection connection=null;
    WebEngine webEngine;
    @FXML
    private Button EnvoyerReclamation;
    @FXML
    private ImageView nomCheckMark;
    @FXML
    private ImageView emailCheckMark;
    @FXML
    private ImageView prenomCheckMark;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private StackPane rootpanee;
    private Label email;
    @FXML
    private Label emailClient;
    @FXML
    private Label id;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Media media = new Media("file:///C:/Users/Tox/Desktop/tunisia.mp4");
        MediaPlayer playerr = new MediaPlayer(media);
        mediaVieww.setMediaPlayer(playerr);
        playerr.setVolume(0);
        playerr.play();
        connection=MyDB.getInstance().getConnexion();
//        System.out.println(this.id.getText());
//        
      EmailTXFLD.setText(ReclamationVideoController.email);
      NomTXFLD.setText(ReclamationVideoController.nom);
      PrenomTXFLD.setText(ReclamationVideoController.prenom);
           DescriptionTXFLD.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\D*")) {
                DescriptionTXFLD.setText(newValue.replaceAll("[^\\D]", ""));
            }
        });
    }

    @FXML
    private void EnvoyerReclamation(ActionEvent event) {
        LocalDate actuelle = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = actuelle.format(formatter);
        String dc = date;
            if (DescriptionTXFLD.getText()==null || DescriptionTXFLD.getText().isEmpty()){
                BoxBlur blur = new BoxBlur(3, 3, 3);
                 JFXDialogLayout dialogueLayout=new JFXDialogLayout();
                 JFXDialog dialog= new JFXDialog(rootpanee, dialogueLayout, JFXDialog.DialogTransition.TOP);
         
          dialogueLayout.setHeading(new Label("salut "+ReclamationVideoController.nom+" !  pouvez vous inserer votre description \n                                      merci ♥"));
           
           dialog.show();
           dialog.setOnDialogClosed((JFXDialogEvent event1)->{
            paneReclamation.setEffect(new BoxBlur(0, 0, 0));
             });
           paneReclamation.setEffect(blur);
            }
            else{    
           
        try {
            BoxBlur blur = new BoxBlur(3, 3, 3);
            String ref = Reclamation.reference();
            
            String req = "INSERT INTO reclamation (description,date,rec_reference,etat,user_id) VALUES ( '"
                    +DescriptionTXFLD.getText()+ "', '" + dc +"','"+ref+"','"+"en attent"+ "','"+ReclamationVideoController.id+"') ";
            Statement stm = connection.createStatement();
            stm.executeUpdate(req); 
            System.out.println("Bien ajoutee");
            Thread t= new Thread(()->{
                try {
                    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                      Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+21627066310"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                " "+ReclamationVideoController.nom+" a envoyee une Reclamation , vous pouvez le contacter sur : "+ReclamationVideoController.num_tel)
           .create();
                                  System.out.println("msg whatsapp envoyee");
                    JavaMailUtil.sendEmail( ReclamationVideoController.email, "Referance de reclamation : "+ref+"\n Votre demande sera prise en compte et nous vous répondrons dans les meilleurs délais. \n Vous serez notifiés via une maill les details de traitement de votre reclamation \n Merci !! ");
                } catch (Exception ex) {
                    Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
            t.start();
          JFXDialogLayout dialogueLayout=new JFXDialogLayout();
           
           JFXDialog dialog= new JFXDialog(rootpanee, dialogueLayout, JFXDialog.DialogTransition.TOP);
         
                   
          dialogueLayout.setHeading(new Label("Votre reclamation a été envoyée avec succès. \n Un email avec plus d'informations a ete envoye \n merci!"));
           
           dialog.show();
           dialog.setOnDialogClosed((JFXDialogEvent event1)->{
            paneReclamation.setEffect(new BoxBlur(0, 0, 0));
             });
           paneReclamation.setEffect(blur);

    
        } catch (SQLException ex) {
           
            System.err.println(ex.getMessage());

        } catch (Exception ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
    }
    public void setEmail(String email)
    {
        this.emailClient.setText(email);
    }
    public void setId(String id)
    {
        this.id.setText(id);
    }
private Stage stage;
private Scene scene;
    @FXML
    private void backToLoby(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationVideo.fxml"));
        try{
            
            Parent root1= loader.load();
             stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
             scene=new Scene(root1);
             stage.setScene(scene);
             stage.show();  
             
        } catch (IOException ex) {
            Logger.getLogger(ReclamationVideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
 }
