/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Plan;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import services.PlanService;

/**
 *
 * @author bahe
 */
public class PlanPaneClientController {
    @FXML
    private Pane      BIGPANE;
    @FXML
    private TextField idGuide;
    @FXML
    private TextField note;
    @FXML
    private TextField titre;
    @FXML
    private TextField description;
    @FXML
    private TextField nmbrPlaceMax;
    @FXML
    private TextField nmbrPlaceReste;
    @FXML
    private TextField prix;
    @FXML
    private TextField pointDepart;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private Label      id;
    @FXML
    private Button     updatePlan;
    @FXML
    private Button     updatePlanZina;
    @FXML
    private Button     deletePlan;
    @FXML
    private Button     deletePlanZina;
    
    public static int X=0;
    public void setPlan(String idGuide, String note, String titre, String description, String nmbrPlacesMax, String nmbrPlacesReste, String prix, String pointDepart, String dateDebut, String dateFin, String id) throws IOException{
        X+=1;
        System.out.print("PLAN:"+X);
        this.id.setText(Integer.toString(X));
        this.idGuide.setText(idGuide);
        this.note.setText(note);
        this.titre.setText(titre);
        this.description.setText(description);
        this.nmbrPlaceMax.setText(nmbrPlacesMax);
        this.nmbrPlaceReste.setText(nmbrPlacesReste);
        this.prix.setText(prix);
        this.pointDepart.setText(pointDepart);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateDebut = LocalDate.parse(dateDebut, formatter);
        this.dateDebut.setValue(localDateDebut);
        LocalDate localDateFin = LocalDate.parse(dateFin, formatter);
        this.dateFin.setValue(localDateDebut);
        this.id.setText(id);
        
        
    }
    public void delete() throws IOException, SQLException{
        
        PlanService plan_service = new PlanService();
        plan_service.delete(Integer.parseInt(this.id.getText()));
        
        BIGPANE.setStyle("-fx-background-color: #ff0000");
        BIGPANE.getChildren().remove(updatePlan);
        BIGPANE.getChildren().remove(updatePlanZina);
        BIGPANE.getChildren().remove(deletePlan);
        
    }
    public void update() throws SQLException{
        PlanService plan_service = new PlanService();
        
        Plan plan = new Plan(Integer.parseInt(this.id.getText()),
                             Integer.parseInt(this.idGuide.getText()),
                             Double.parseDouble(this.prix.getText()),
                             Double.parseDouble(this.note.getText()),
                             this.titre.getText(),
                             this.description.getText(),
                             Integer.parseInt(this.nmbrPlaceMax.getText()),
                             Integer.parseInt(this.nmbrPlaceReste.getText()),
                             this.dateDebut.getValue().toString(),
                             this.dateFin.getValue().toString(),
                             this.pointDepart.getText());
        plan_service.update(plan);
        
        BIGPANE.setStyle("-fx-background-color: #00ff00");
        BIGPANE.getChildren().remove(deletePlan);
        BIGPANE.getChildren().remove(deletePlanZina);
        BIGPANE.getChildren().remove(updatePlan);
    }
}
