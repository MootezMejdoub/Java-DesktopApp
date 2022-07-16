/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.PlanPaneClientController;
import entities.Plan;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.PlanService;

/**
 *
 * @author bahe
 */
public class PlanInterfaceClientController {
   @FXML
    private VBox PlanVboxOfClient;
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
    private Button     createPlan;
    @FXML
    private Button     createPlanZina;
    public void setPlansList( ActionEvent event ) throws IOException, SQLException{

        PlanService plan_service = new PlanService();
        for (final Plan plan : plan_service.readAll()) {
            FXMLLoader load = new FXMLLoader(getClass().getResource("PlanPaneClient.fxml"));
            Pane PlanPane= load.load();
            PlanPaneClientController PPCC = load.getController();
//setPlan(String idGuide, String note, String titre, String description, String nmbrPlacesMax, String nmbrPlacesReste, String prix, String pointDepart, String dateDebut, String dateFin, String id)
            PPCC.setPlan(Integer.toString(plan.getIdGuide()),
                         Double.toString(plan.getNote()),
                         plan.getTitre(),
                         plan.getDescription(),
                         Integer.toString(plan.getNmbrPlacesMax()),
                         Integer.toString(plan.getNmbrPlacesReste()),
                         Double.toString(plan.getPrix()),
                         plan.getPointDepart(),
                         plan.getDateDebut(),
                         plan.getDateFin(),
                         Integer.toString(plan.getId()));
            PlanVboxOfClient.getChildren().add(PlanPane);
            System.out.print("hi");
        }
    }
    public void create() throws SQLException{
        PlanService plan_service = new PlanService();
        
        Plan plan = new Plan(Integer.parseInt(this.idGuide.getText()),
                             Double.parseDouble(this.prix.getText()),
                             Double.parseDouble(this.note.getText()),
                             this.titre.getText(),
                             this.description.getText(),
                             Integer.parseInt(this.nmbrPlaceMax.getText()),
                             Integer.parseInt(this.nmbrPlaceReste.getText()),
                             this.dateDebut.getValue().toString(),
                             this.dateFin.getValue().toString(),
                             this.pointDepart.getText());
        plan_service.create(plan);
        
        BIGPANE.getChildren().remove(createPlan);
    }
}
