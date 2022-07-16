/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.PlanPaneGuideController;
import entities.Plan;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.PlanService;
import services.PlanImageService;
import entities.PlanImage;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *
 * @author bahe
 */
public class PlanInterfaceGuideController implements Initializable {
    
    private int ID_GUIDE = SigInController.id;
    
    @FXML
    private Pane PLAN_PANE_GUIDE;
    @FXML
    private VBox PlanVboxOfGuide;
    @FXML
    private VBox AddImageVBox;
    
    @FXML
    private Pane      BIGPANE;
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private ChoiceBox nmbrPlaceMax;
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
    private List<PlanImage>  planImages = new ArrayList<>();
    private PlanImage  planImage;
    private int        NmbrOfImageInWait=0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //initialize nmbrPlaceMax
        for(int i=1; i<1000 ;i++ ){nmbrPlaceMax.getItems().add(i);}
        nmbrPlaceMax.getSelectionModel().selectFirst();
        
    }


    public void setPlansList( ActionEvent event ) throws IOException, SQLException{
        PlanService plan_service = new PlanService();
        for (final Plan plan : plan_service.readPerGuide(ID_GUIDE)) {
            FXMLLoader load = new FXMLLoader(getClass().getResource("PlanPaneGuide.fxml"));
            Pane PlanPane= load.load();
            PlanPaneGuideController PPGC = load.getController();
//setPlan(String idGuide, String note, String titre, String description, String nmbrPlacesMax, String nmbrPlacesReste, String prix, String pointDepart, String dateDebut, String dateFin, String id)
            PPGC.setPlan(Integer.toString(plan.getIdGuide()),
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
            PlanVboxOfGuide.getChildren().add(PlanPane);
            System.out.print("hi");
          
        }
    }
    
    public void create() throws SQLException{
        PlanService plan_service = new PlanService();
        PlanImageService planImage_service = new PlanImageService();
        Plan plan = new Plan(ID_GUIDE,
                             Double.parseDouble(this.prix.getText()),
                             0.0,
                             this.titre.getText(),
                             this.description.getText(),
                             Integer.parseInt(this.nmbrPlaceMax.getSelectionModel().getSelectedItem().toString()),
                             Integer.parseInt(this.nmbrPlaceMax.getSelectionModel().getSelectedItem().toString()),
                             this.dateDebut.getValue().toString(),
                             this.dateFin.getValue().toString(),
                             this.pointDepart.getText());
        plan_service.create(plan);
        plan =plan_service.readLastPlanAdded();
        NmbrOfImageInWait=0;
        for(final PlanImage planImageI : planImages)
        {
            planImage_service.create(planImageI);
            PlanImage OLD_planImage = planImage_service.readLastPlanImageAdded();
            PlanImage NEW_planImage = new PlanImage(OLD_planImage.getId()
                                                   ,plan.getId()
                                                   ,"src/PlansImages/PLAN"+plan.getId()+"IMAGE"+OLD_planImage.getId()+"."+OLD_planImage.getPath());
            planImage_service.update(OLD_planImage, NEW_planImage);

            File OLDFile = new File("src/PlansImages/"+NmbrOfImageInWait+"."+OLD_planImage.getPath());
            File NEWFile = new File(NEW_planImage.getPath());
            OLDFile.renameTo(NEWFile);
            NmbrOfImageInWait++;
            
        }
        
        BIGPANE.getChildren().remove(createPlan);
    }

    public void addImage() throws IOException, SQLException{
        
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage)PLAN_PANE_GUIDE.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        int extensionPosition = file.toPath().toString().lastIndexOf('.');
        String extension = file.toPath().toString().substring(extensionPosition+1);
        
        if(file != null && (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("jfif") || extension.equalsIgnoreCase("pjpeg") || extension.equalsIgnoreCase("pjp") || extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("svg") || extension.equalsIgnoreCase("gif")))
        {
            File destFile = new File("src/PlansImages/"+NmbrOfImageInWait+"."+extension);
            Files.copy(file.toPath(),destFile.toPath());
            
            planImage = new PlanImage(extension);
            planImages.add(planImage);
            Image img = new Image(destFile.toPath().toUri().toString());
            ImageView imageView = new ImageView();
            imageView.setImage(img);
            AddImageVBox.getChildren().add(imageView);
            NmbrOfImageInWait++;
        }
        else
        {
            System.out.print("zab");
            
        }

        
    }

}
