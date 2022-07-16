/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.mysql.jdbc.MySQLConnection;
import com.sun.media.sound.ModelWavetable;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import entities.Reclamation;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Tox
 */
public class ReclamationAdminController implements Initializable {
    private Stage stage;
private Scene scene;
@FXML
    private MediaView mediaViewAdmin;
    @FXML
    private Pane pnlOverview;
    @FXML
    private VBox nb;
    @FXML
    private Label nbrReclamation;
    @FXML
    private Label nbrReclamationEnAttente;
    @FXML
    private TableColumn<Reclamation, String> id;
    @FXML
    private TableColumn<Reclamation, String> Nom;
    @FXML
    private TableColumn<Reclamation, String> Prenom;
    @FXML
    private TableColumn<Reclamation, String> Email;
    @FXML
    private TableColumn<Reclamation, String> Description;
    @FXML
    private TableColumn<Reclamation, String> DateCreation;
    @FXML
    private TableColumn<Reclamation, String> Reference;
    @FXML
    private TableColumn<Reclamation, String> Etat;
    @FXML
    private TableView<Reclamation> table;
    /**
     * Initializes the controller class.
     */
       LocalDate actuelle = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = actuelle.format(formatter);
        String dc = date;
        Connection connection;
        PreparedStatement pst;
        PreparedStatement pstt;
        ResultSet rs;
        ObservableList<Reclamation>liste;
    
    
        ObservableList<Reclamation> oblist= FXCollections.observableArrayList();
    @FXML
    private TextField searchKey;
    @FXML
    private Button replyBtn;
    @FXML
    private Button deleteBtn;
        static Reclamation selected;
    @FXML
    private Button xl;
    @FXML
    private Button imprimer;
    @FXML
    private Button back;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     Media media = new Media("file:///C:/Users/Tox/Desktop/tunisia.mp4");
        MediaPlayer player = new MediaPlayer(media);
        mediaViewAdmin.setMediaPlayer(player);
        player.setVolume(0);
        player.play();
        
        
        populateTableView();
        
        reclamationNbr();
        reclamationPendingNbr();
        rechercheReclamation();
        imprimer.setOnAction(event -> {
            pdf();
        });
        
    }
    
    private void populateTableView() {
        try {
        liste=FXCollections.observableArrayList();
        String query="select * from  reclamation join utilisateur on utilisateur.id=reclamation.user_id";
        connection=MyDB.getInstance().getConnexion();
    
        ResultSet rst=connection.createStatement().executeQuery(query);
        while(rst.next()){
           Reclamation r = new Reclamation();
               r.setId(rst.getInt("id"));
                r.setDescription(rst.getString("description"));
                r.setEmail(rst.getString("utilisateur.email"));
                r.setNom(rst.getString("utilisateur.nom"));
                r.setPrenom(rst.getString("utilisateur.prenom"));
                r.setReference(rst.getNString( "rec_reference"));
                r.setDate_creation(rst.getDate("date"));
                r.setEtat(rst.getString("etat"));
                liste.add(r);
    }    
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        Email.setCellValueFactory(new PropertyValueFactory<>("email"));
                Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Reference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        DateCreation.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        table.setItems(liste);
    } catch (SQLException ex) {
        Logger.getLogger(ReclamationAdminController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    public void rechercheReclamation(){
          try {
        liste=FXCollections.observableArrayList();
       String query="select * from  reclamation join utilisateur on utilisateur.id=reclamation.user_id";
        connection=MyDB.getInstance().getConnexion();
    
        ResultSet rst=connection.createStatement().executeQuery(query);
        while(rst.next()){
           Reclamation r = new Reclamation();
               r.setId(rst.getInt("id"));
                r.setDescription(rst.getString("description"));
                r.setEmail(rst.getString("utilisateur.email"));
                r.setNom(rst.getString("utilisateur.nom"));
                r.setPrenom(rst.getString("utilisateur.prenom"));
                r.setReference(rst.getNString( "rec_reference"));
                r.setDate_creation(rst.getDate("date"));
                r.setEtat(rst.getString("etat"));
                liste.add(r);
    }    
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        Email.setCellValueFactory(new PropertyValueFactory<>("email"));
                Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Reference.setCellValueFactory(new PropertyValueFactory<>("reference"));
        DateCreation.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        table.setItems(liste);
        
		FilteredList<Reclamation> filteredData = new FilteredList<>(liste, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		searchKey.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(rec -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (rec.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (rec.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
                                        } else if (rec.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
                                        } else if (rec.getEtat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
                                        } else if (String.valueOf(rec.getDate_creation()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches last name.
                                        } else if (rec.getReference().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                        
				else if (String.valueOf(rec.getEmail()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
               

		
        
    } catch (SQLException ex) {
        Logger.getLogger(ReclamationAdminController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

  public void rechercheReclamations(String valeur) {
        
        liste = FXCollections.observableArrayList();
        List<Reclamation> myList = new ArrayList<Reclamation>();
        String requete = null;
        ResultSet rst;
        PreparedStatement stm;
        connection = MyDB.getInstance().getConnexion();
        try {
            requete = "SELECT * from reclamation join utilisateur on utilisateur.id=reclamation.user_id  where description like '%" + valeur + "%' or nom like '%" + valeur + "%' or prenom like '%" + valeur + "%' or email like '%" + valeur + "%' or date like '%" + valeur + "%' or etat like '%" + valeur + "%'";
            stm = connection.prepareStatement(requete);
            rst = stm.executeQuery();
            while (rs.next()) {
                Reclamation R = new Reclamation();
                R.setDescription(rs.getString("reclamation.description"));
                R.setNom(rs.getString("utilisataeur.nom"));
                   R.setPrenom(rs.getString("utilisataeur.prenom"));
                R.setEmail(rs.getString("utilisataeur.email"));
                R.setDate_creation(rs.getDate("date"));
                R.setEtat(rs.getString("reclamation.etat"));
                liste.add(R);
            }
            
            Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            Description.setCellValueFactory(new PropertyValueFactory<>("description"));
            Reference.setCellValueFactory(new PropertyValueFactory<>("reference"));
            DateCreation.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
            Etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
            table.setItems(liste);
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    public void reclamationNbr(){
    try {
        pst=connection.prepareStatement("SELECT COUNT(*) as reclamationCount FROM reclamation");
        ResultSet rs=pst.executeQuery();
        while(rs.next()){
            int count=rs.getInt("reclamationCount");
            nbrReclamation.setText(String.valueOf(count));
        }
 
    } catch (SQLException ex) {
        Logger.getLogger(ReclamationAdminController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    
    
    public void reclamationPendingNbr(){
        try {
        pst=connection.prepareStatement("select count(etat)as Number from reclamation where etat='en attent'");
        ResultSet rs=pst.executeQuery();
        while(rs.next()){
            int count=rs.getInt("Number");
            nbrReclamationEnAttente.setText(String.valueOf(count));
        }} catch (SQLException ex) {
        Logger.getLogger(ReclamationAdminController.class.getName()).log(Level.SEVERE, null, ex);
    }
        
        }
    
    

    @FXML
    private void replyReclamation(ActionEvent event) {
         selected=table.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReponseAdmin.fxml"));
        
        try {
        
            Parent root1= loader.load();
             stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
             scene=new Scene(root1);
             stage.setScene(scene);
             stage.show();  
        
        } catch (IOException ex) {
            Logger.getLogger(ReclamationVideoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ReponseAdminController reponseAdminController=loader.getController();
        reponseAdminController.setData(selected.getId(), selected.getReference(),selected.getEtat());
        //reponseAdminController.envoyerReponse(new ActionEvent(), selected.getId(), selected.getReference());
    }

    @FXML
    private void deleteReclamation(ActionEvent event) {
        Reclamation selected=table.getSelectionModel().getSelectedItem();
        
       
        
   String query="delete from reclamation where id=?";
           connection=MyDB.getInstance().getConnexion();

        try {
     
        pst=connection.prepareStatement(query);
        pst.setInt(1,selected.getId());
        pst.executeUpdate();
        
       
        
    } catch (SQLException ex) {
        Logger.getLogger(ReclamationAdminController.class.getName()).log(Level.SEVERE, null, ex);
    }
    populateTableView();
    rechercheReclamation();
    reclamationNbr();
    reclamationPendingNbr();
    }

    @FXML
    private void imprimer(ActionEvent event) {

    }        
void pdf() {
        System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            Window primaryStage = null;
            job.showPrintDialog(primaryStage);

            Node root = this.table;

            job.printPage(root);
            job.endJob();

        }
    }
    @FXML
    private void exporterXl(ActionEvent event) {
        try {
            String filename = "C:\\Users\\Tox\\Desktop\\fichierXL\\data.xls";
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("nom");
            rowhead.createCell((short) 1).setCellValue("prenom");
            rowhead.createCell((short) 2).setCellValue("email");
            rowhead.createCell((short) 3).setCellValue("description");
            rowhead.createCell((short) 4).setCellValue("date");
            rowhead.createCell((short) 5).setCellValue("reference");
            rowhead.createCell((short) 6).setCellValue("etat");

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from  reclamation join utilisateur on utilisateur.id=reclamation.user_id");
            int i = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow((short) i);

                row.createCell((short) 0).setCellValue(rs.getString("utilisateur.nom"));
                row.createCell((short) 1).setCellValue(rs.getString("utilisateur.prenom"));
                row.createCell((short) 2).setCellValue(rs.getString("utilisateur.email"));
                row.createCell((short) 3).setCellValue(rs.getString("reclamation.description"));
                row.createCell((short) 4).setCellValue(rs.getDate("reclamation.date"));
                row.createCell((short) 5).setCellValue((rs.getString("reclamation.rec_reference")));
                row.createCell((short) 6).setCellValue(rs.getString("reclamation.etat"));
                i++;
            }
            FileOutputStream fileOut = new FileOutputStream(filename);
            hwb.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");

        } catch (Exception ex) {
            System.out.println(ex);

        }
    }

    @FXML
    private void backhome(ActionEvent event) {
         Stage stage= (Stage) back.getScene().getWindow();
                    stage.close();
    }
    
        
    
    
    
}
    

