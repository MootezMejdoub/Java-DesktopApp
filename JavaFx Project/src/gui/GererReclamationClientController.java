/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.ReclamationAdminController.selected;
import entities.Reclamation;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Delayed;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Tox
 */
public class GererReclamationClientController implements Initializable {

    static Reclamation selected;
    private Stage stage;
    private Scene scene;
    /**
     * Initializes the controller class.
     */
    @FXML
    private MediaView mediaViewClient;
    @FXML
    private Button back;
    @FXML
    private TableView<Reclamation> table;
    @FXML
    private TableColumn<Reclamation, String> Reference;
    @FXML
    private TableColumn<Reclamation, String> Nom;
    @FXML
    private TableColumn<Reclamation, String> Description;
    @FXML
    private TableColumn<Reclamation, String> DateCreation;
    @FXML
    private TableColumn<Reclamation, String> Etat;
    ObservableList<Reclamation> liste;

    ObservableList<Reclamation> oblist = FXCollections.observableArrayList();
    Connection connection;
    PreparedStatement pst;
    PreparedStatement pstt;
    ResultSet rs;
    @FXML
    private Label id;
    @FXML
    private TableColumn<?, ?> Prenom;
    @FXML
    private Button CheckButton;

    @Override

    public void initialize(URL url, ResourceBundle rb) {

        Media media = new Media("file:///C:/Users/Tox/Desktop/tunisia.mp4");
        MediaPlayer player = new MediaPlayer(media);
        mediaViewClient.setMediaPlayer(player);
        player.setVolume(0);
        player.play();
        populateTableView();
    }

    private void populateTableView() {
        try {
            liste = FXCollections.observableArrayList();
            String query = "select * from  reclamation join utilisateur on utilisateur.id=reclamation.user_id where reclamation.user_id=" + ReclamationVideoController.id;

            connection = MyDB.getInstance().getConnexion();
            ResultSet rst = connection.createStatement().executeQuery(query);

            while (rst.next()) {
                Reclamation r = new Reclamation();

                r.setDescription(rst.getString("reclamation.description"));

                r.setEmail(rst.getString("utilisateur.email"));
                r.setNom(rst.getString("utilisateur.nom"));
                r.setPrenom(rst.getString("utilisateur.prenom"));
                r.setReference(rst.getNString("reclamation.rec_reference"));
                r.setUser_id(rst.getInt("reclamation.user_id"));
                r.setDate_creation(rst.getDate("reclamation.date"));
                r.setEtat(rst.getNString("reclamation.etat"));

                liste.add(r);

            }

            Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

            Description.setCellValueFactory(new PropertyValueFactory<>("description"));
            Reference.setCellValueFactory(new PropertyValueFactory<>("reference"));
            DateCreation.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
            Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
            table.setItems(liste);

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backToLobby(ActionEvent event) {
        try {//bhy lahdha 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationVideo.fxml"));

            Parent root1 = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root1);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ReclamationVideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setId(String id) {
        this.id.setText(id);
    }


    @FXML
    private void checkReponse(ActionEvent event
    ) {
        selected = table.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ReponseRecClient.fxml"));

        try {

            Parent root1 = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root1);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ReclamationVideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ReponseRecClientController reponseRecClientController = loader.getController();
        reponseRecClientController.setData(selected.getId(), selected.getReference(), selected.getEtat());
    }

}
