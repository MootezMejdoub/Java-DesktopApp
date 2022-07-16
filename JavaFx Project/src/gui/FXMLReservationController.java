/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.collections.transformation.FilteredList;
import entities.Facture;
import entities.Reservation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import services.ReservationService;
import java.lang.Integer;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import static javafx.fxml.FXMLLoader.load;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javax.swing.JOptionPane;
import utils.MyDB;
import javax.print.*;
import com.itextpdf.*;
import com.itextpdf.text.Document;
import de.jensd.fx.glyphs.icons525.Icons525View;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.ImageView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import javafx.scene.control.DatePicker;

import javafx.stage.Popup;
import javafx.stage.PopupBuilder;
/**
 * FXML Controller class
 *
 * @author mbell
 */
public class FXMLReservationController implements Initializable {

        
    @FXML
    private TableColumn<Reservation, String> ClnNom;
    
     @FXML
    private Button BtnDelete;

    @FXML
    private Button BtnReservation;

    @FXML
    private Button BtnFacture;

      
    
    @FXML
    private TableColumn<Reservation, String> ClnPrenom;

    @FXML
    private TableColumn<Reservation, String> ClnPlan;

    @FXML
    private TableColumn<Reservation, Integer> ClnNbPlace;
//    @FXML
//    privat-e TableColumn<Reservation, Integer>ClnNumRes ;

    @FXML
    private Label LblNumRes;
    @FXML
    private Label NbPlace;
    
    @FXML
    private TextField TxtNom;

    @FXML
    private TextField TxtPrenom;

    @FXML
    private TextField TxtPlan;
    
     @FXML
    private TextField TxtNbPlace;

    public TextField getTxtNom() {
        return TxtNom;
    }

    public void setTxtNom(TextField TxtNom) {
        this.TxtNom = TxtNom;
    }

    public TextField getTxtPrenom() {
        return TxtPrenom;
    }

    public void setTxtPrenom(TextField TxtPrenom) {
        this.TxtPrenom = TxtPrenom;
    }

    public TextField getTxtPlan() {
        return TxtPlan;
    }

    public void setTxtPlan(TextField TxtPlan) {
        this.TxtPlan = TxtPlan;
    }


    public TextField getTxtNbPlace() {
        return TxtNbPlace;
    }

    public void setTxtNbPlace(TextField TxtNbPlace) {
        this.TxtNbPlace = TxtNbPlace;
    }

    
    
        
    @FXML
    private Button BtnAjout;

    @FXML
    private Button BtnSupp;
     @FXML
    private Button BtnInitialiser;
    
    @FXML
    private TableView<Reservation> TableReservation;
    @FXML
    private Icons525View searchIcon;
    @FXML
    private Icons525View DatePikFin;
     @FXML
    private ImageView ImgRes;
     
    @FXML
    private DatePicker DatePFin;

     

    @FXML
    private Icons525View DatePi;
    @FXML
    private TextField TxtDateP;

    @FXML
    private DatePicker DatePDeb;
    
    @FXML
    private TextField TxtPeriode;

    public TextField getTxtDateP() {
        return TxtDateP;
    }

    public void setTxtDateP(TextField TxtDateP) {
        this.TxtDateP = TxtDateP;
    }

    public TextField getTxtPeriode() {
        return TxtPeriode;
    }

    public void setTxtPeriode(TextField TxtPeriode) {
        this.TxtPeriode = TxtPeriode;
    }
    
    
    
    private Button BtnMod;

        Popup pop =new Popup ();
    
    
    ReservationService rs =new ReservationService();
     Statement stmt;
    PreparedStatement pst;
    ResultSet rst;
     Connection cnx=MyDB.getInstance().getConnexion(); 
     ObservableList <Reservation> Reservationlist=observableArrayList();
     ObservableList <Integer> nb=observableArrayList(1,2,3,4); 
      @FXML
    private Spinner<Integer> SpinNbPlace;
      
      @FXML
    private ListView<Reservation> ListViewReservation;
 @FXML
    private ComboBox<Integer> CmbNbP;
       @FXML
    private TextField TxtSearch;

    public TextField getTxtSearch() {
        return TxtSearch;
    }

    public void setTxtSearch(TextField TxtSearch) {
        this.TxtSearch = TxtSearch;
    }

    Label lbl ;
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        Image img=new Image("C:/Users/yaou/Music/PI/src/resImg.png");
//           ImgRes.setImage(img);
//          pop.setAutoHide(true);
//         
//        showReservation();
//        setCellValueFromTableToTxtRes();
          CmbNbP=new ComboBox(nb);
        
      
       SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(nb);
                
      valueFactory.setValue(1);
        SpinNbPlace.setValueFactory(valueFactory);
            
       lbl =new Label("votre reservation dure  : "+TxtPeriode.getText()+"\n"+"enjoy it " );
       pop.getContent().add(lbl);
       
       
        
    }  
           
    
      
            
        
   
    public void setCellValueFromTableToTxtRes(){
        
        TableReservation.setOnMouseClicked((MouseEvent event)->{
          
            
            Reservation res= TableReservation.getItems().get(TableReservation.getSelectionModel().getSelectedIndex());
//        Reservation res = ListViewReservation.getItems().get(ListViewReservation.getSelectionModel().getSelectedIndex());
     //   String selectedItem=ListViewReservation.getSelectionModel().toString();
          
           TxtNom.setText(res.getNom());
            TxtPrenom.setText(res.getPrenom());
            TxtPlan.setText(res.getPlan());
            
            
            
        });
        
    }
    
    
    
    
//        public void  search(){
//            
//      FilteredList <Reservation> filterData=new FilteredList(Reservationlist,b->true);
//        TxtNom.textProperty().addListener((observable,oldV , newV )->{
//            filterData.setPredicate(Reservationlist->{
//            if (newV.isEmpty() || newV ==null){
//                return true ;
//            }
//                String skw=newV.toLowerCase();
//            if(Reservation.getNom().toLowerCase().indexOf(skw)>-1){
//                return true;
//            }
//            else return false ;
//        });   
//                
//    });    
//             
//            
//            
//        } 
    
    public FXMLReservationController(){
        
    }
    
    public ObservableList<Reservation>getReservationList(){
            
        
        ObservableList <Reservation> Reservationlist=observableArrayList(); 
        try {
                
            //String req = " select * from reservation ";
            String req ="SELECT u.nom,u.prenom,p.titre ,r.nbrPlace from (reservation r JOIN utilisateur u ON r.idClient=u.id) JOIN plan p ON r.idPlan=p.id WHERE u.type LIKE 'touriste';  ";
            
           // String req ="SELECT u.nom,u.prenom from (reservation r JOIN utilisateur u ON r.idClient=u.id) JOIN plan p ON r.idPlan=p.id WHERE u.type LIKE 'guide'and u.type LIKE 'guide';  ";
            
            stmt=cnx.createStatement();
            rst= stmt.executeQuery(req);
             
            while(rst.next()){
                
             Reservation res;
                   res= new Reservation(rst.getString("nom"),
                        rst.getString("prenom"),
                           rst.getString("titre"),
                           rst.getInt("nbrPlace")) ; 
           
//                            
//                        TxtNom.setText(rst.getString("nom"));
//                        TxtPrenom.setText(rst.getString("prenom"));
//                      TxtPlan.setText(rst.getString("titre"));
                    
                   
               Reservationlist.add(res);
                //System.out.println(res);
                
            }
        
        }
                catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
           return Reservationlist ;
        }
    
    
    
    
    
    public void showReservation(){
        //List <Facture>f = new ArrayList<Facture>();
            
        //System.out.println(getReservationList());
        //Facturelist
        ObservableList<Reservation> ReservationList=getReservationList();
        
        ClnNom.setCellValueFactory(new PropertyValueFactory<Reservation,String>("nom"));
        ClnPrenom.setCellValueFactory(new PropertyValueFactory<Reservation,String>("prenom"));
        ClnPlan.setCellValueFactory(new PropertyValueFactory<Reservation,String>("plan"));
        ClnNbPlace.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("nbrPlace"));
        
       TableReservation.setItems(ReservationList);
//       ListViewReservation.setItems(ReservationList) ;
    
    }
    
    
    
    
    
    public void AjouterReservation(ActionEvent ev){
        
        String nom=TxtNom.getText();
        String prenom=TxtPrenom.getText();
        String Plan=TxtPlan.getText();
        
       // Date date =Date.valueOf(java.time.LocalDate.now());
//        String nbPlace=TxtNbPlace.getText();
        
//        if(!"".equals(nbPlace)){
//            int valPl=Integer.parseInt(nbPlace);
//        }
        
       int nb= SpinNbPlace.getValue();
            
              boolean data ;
         
//              if (TxtSearch.getText().isEmpty()){
//                  Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Erreur");
//                alert.setHeaderText("Erreur de saisie !");
//                alert.setContentText("le champ de recherche est vide ");
//                alert.show();
//
//                data = false;
//                  
//              }
//              
//              if(nom.isEmpty()){
//             Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Erreur");
//                alert.setHeaderText("Erreur de saisie !");
//                alert.setContentText("Vous navez pas saisie nom");
//                alert.show();
//
//                data = false;
//            
//        }
//        if(prenom.isEmpty()){
//             Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Erreur");
//                alert.setHeaderText("Erreur de saisie !");
//                alert.setContentText("Vous navez pas saisie prenom");
//                alert.show();
//
//                data = false;
//            
//        }
//        if (Plan.isEmpty()){
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Erreur");
//                alert.setHeaderText("Erreur de saisie !");
//                alert.setContentText("Vous navez pas saisie le plan");
//                alert.show();
//
//                data = false;
//      
//        }
         if (DatePDeb.getEditor().toString().isEmpty()|DatePFin.toString().isEmpty()) {
             
             Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur de saisie !");
                alert.setContentText("Vous navez pas saisie la date ");
                alert.show();

                data = false;
         }   
         java.util.Date dd = Date.from(DatePDeb.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateDebut = new java.sql.Date(dd.getTime());
        java.util.Date df = Date.from(DatePFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateFin = new java.sql.Date(df.getTime());
            
       if(validateFields()){
       
       try{
                  
           // String req="UPDATE reservation r INNER JOIN utilisateur u ON r.idClient=u.id INNER JOIN plan p ON r.idPlan=p.id SET nbrPlace=? WHERE u.nom LIKE ? and f.date=? ";
             String req="UPDATE reservation r INNER JOIN utilisateur u ON r.idClient=u.id INNER JOIN plan p ON r.idPlan=p.id INNER JOIN facture f ON r.idClient=f.idClient SET nbrPlace=?,f.date=?,r.dateDebut=?,r.dateFin=? WHERE u.nom LIKE ?";  
                // stmt = cnx.createStatement();
                pst=cnx.prepareStatement(req);
                pst.setInt(1, nb);
                pst.setDate(2,dateDebut);
                pst.setDate(3,dateDebut);
                pst.setDate(4,dateFin);
                pst.setString(5, nom);
                pst.executeUpdate();
           // stmt.executeUpdate(req);
//                   String nom1 =rst.getString("nom");
//                   String prenom1 = rst.getString("prenom");
                   String out = nom +" "+prenom ;                    
                  
                   generate_qr(nom, out );
        
        JOptionPane.showMessageDialog(null,"add avec succée");
        
//            showReservation();
       
    } catch(SQLException e){
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null,"error");
            e.printStackTrace();
            }  
       }
    }
    
    
    public ResultSet charger(){
        
        
        try{
            
            stmt=cnx.createStatement();
            rst=stmt.executeQuery("select* from reservation  ");
        }
        catch(Exception e){
            	JOptionPane.showConfirmDialog(null,"un probleme se produit !!", " Message", JOptionPane.PLAIN_MESSAGE);
      
    }
    return rst;
    }
    
    
    public void Initialiser(ActionEvent ev){
        TxtSearch.setText("");
      TxtNom.setText("");
        TxtPrenom.setText("");
       TxtPlan.setText("");   
        
      // TxtNbPlace.setText("");
       
    }
    
    @FXML
    public void modifierReservation (ActionEvent ev) throws Exception{
//        
       String nom=TxtNom.getText();
        String prenom=TxtPrenom.getText();
        String Plan=TxtPlan.getText();
        java.util.Date dd = Date.from(DatePDeb.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateDebut = new java.sql.Date(dd.getTime());
        java.util.Date df = Date.from(DatePFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateFin = new java.sql.Date(df.getTime());
    
        int nb= SpinNbPlace.getValue();
        int a =0;
        
        
        boolean data ;
         
        if(validateFields()){
        
        try {
            
    //String req="UPDATE reservation r INNER JOIN utilisateur u ON r.idClient=u.id INNER JOIN plan p ON r.idPlan=p.id INNER JOIN facture f ON r.idClient=f.idClient SET nbrPlace=?,f.date=?,r.dateDebut=?,r.dateFin=?,u.nom =?,u.prenom=? WHERE u.nom LIKE ?";  
    // String req= "UPDATE reservation r INNER JOIN utilisateur u ON r.idClient=u.id INNER JOIN plan p ON r.idPlan=p.id INNER JOIN facture f ON r.idClient=f.idClient SET r.nbrPlace=?,f.date=?,r.dateDebut=?,r.dateFin=?,u.nom =?,u.prenom=? WHERE u.nom LIKE ? "; 
   
String req= "UPDATE reservation r INNER JOIN utilisateur u ON r.idClient=u.id INNER JOIN plan p ON r.idPlan=p.id INNER JOIN facture f ON r.idClient=f.idClient SET p.titre='"+Plan+"',r.nbrPlace='"+nb+"',f.date='"+dateDebut+"',r.dateDebut='"+dateDebut+"',r.dateFin='"+dateFin+"' WHERE u.nom LIKE '"+nom+"'";
       
                // stmt = cnx.createStatement();
                 
                pst=cnx.prepareStatement(req);
                
                //stmt.execute(req);
//                pst.setInt(1, nb);
//                pst.setDate(2,dateDebut);
//                pst.setDate(3,dateDebut);
//                pst.setDate(4,dateFin);
//                pst.setString(5,nom);
//                pst.setString(6,prenom);
//                pst.setString(7,nom);
////       
                  pst.execute(req);
                pst.executeUpdate(req);
               System.out.println( cnx.getAutoCommit());
             //  System.out.println("update",+pst+"\n"    );         
         JOptionPane.showMessageDialog(null, "update avec succée"); 
            a =1;
        }
         catch(Exception e){
        JOptionPane.showMessageDialog(null,"un probleme se produit !!", " Message", JOptionPane.PLAIN_MESSAGE);
     // e.printStackTrace();
            
             System.out.println("thr ,"+e);
             throw e;
    }
       if (a==0){
            Alert alt =new Alert (AlertType.ERROR,"aucun élement n'est modifée ",ButtonType.OK);
             alt.showAndWait();
        }
        
    }
    
    }
        
        
        
    public void deleteReservation(ActionEvent ev){
        
        String nom=TxtNom.getText();
        String prenom=TxtPrenom.getText();
        //String idPlan=TxtIdPlan.getText();
         //String nbPlace=TxtNbPlace.getText();
            String plan=TxtPlan.getText();
              int nbPlace=SpinNbPlace.getValue();
              
            int a =0;
        try{
           // String req="delete from reservation join utilisateur on utilisateur.id=reservation.idClient where utilisateur.nom like "+nom+"";
              
            String req=" DELETE FROM reservation WHERE idClient IN (SELECT id FROM utilisateur u WHERE u.nom='gai');  ";

            
            pst = cnx.prepareStatement(req);
           
             
            // pst.setDouble(1,Double.parseDouble(nbPlace));
            pst.executeUpdate();
             JOptionPane.showMessageDialog(null, "delete avec succée"); 
             a=1;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"un probleme se produit !!", " Message", JOptionPane.PLAIN_MESSAGE);
      e.printStackTrace();
            
        }
        if (a==0){
        Alert alt =new Alert (AlertType.ERROR,"aucun élement n'est supprimée ",ButtonType.OK);
             alt.showAndWait();}
    }
    
    
    public void afficheRes(MouseEvent ev){
       String sh=TxtSearch.getText();
       int n =0;
       
       
       boolean data=true ;
         
              if (TxtSearch.getText().isEmpty()){
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur de saisie !");
                alert.setContentText("le champ de recherche est vide ");
                alert.show();

                data = false;
                  
              }
              
     
        try{
     String req ="SELECT u.nom,u.prenom,p.titre ,r.nbrPlace from (reservation r JOIN utilisateur u ON r.idClient=u.id) JOIN plan p ON r.idPlan=p.id WHERE u.nom LIKE '"+TxtSearch.getText()+"' ";
            
           // String req ="SELECT u.nom,u.prenom from (reservation r JOIN utilisateur u ON r.idClient=u.id) JOIN plan p ON r.idPlan=p.id WHERE u.type LIKE 'guide'and u.type LIKE 'guide';  ";
            
            stmt=cnx.createStatement();
            //pst=cnx.prepareStatement(req);
           // pst.setString(1, sh);
            rst= stmt.executeQuery(req);
             
            while(rst.next()){
                
             Reservation res;
                   res= new Reservation(rst.getString("nom"),
                        rst.getString("prenom"),
                           rst.getString("titre"),
                           rst.getInt("nbrPlace")) ; 
                  
//                   String nom =rst.getString("nom");
//                   String prenom = rst.getString("prenom");
//                   String out = nom +" "+prenom ;                    
//                  
//                   generate_qr(nom, out );
                     
                   TxtNom.setText(rst.getString("nom"));
                        TxtPrenom.setText(rst.getString("prenom"));
                      TxtPlan.setText(rst.getString("titre"));
                 
               Reservationlist.add(res);
                //System.out.println(res);  
                n=1;
            }
        
        }
      catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        //   return Reservationlist ;
//        if (n==0){
//            Alert alt =new Alert (AlertType.ERROR,"aucun élement trouvé ",ButtonType.OK);
//             alt.showAndWait();
//        }
//        
       
    }
       
    
    @FXML
    void periode(MouseEvent ev){
        try{
        java.util.Date dat =Date.from(DatePDeb.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date  DatePDeb =new java.sql.Date (dat.getTime());
        
        java.util.Date da =Date.from(DatePFin.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date  DatePFin =new Date (da.getTime());
        
        int days =nbDays(DatePDeb,DatePFin);
        int mois =days/30;
//     TxtPeriode.setText(String.valueOf(days));
        
       TxtPeriode.setText(String.valueOf(days));
        JOptionPane.showMessageDialog(null, "votre durée de reservation est de "+TxtPeriode.getText()+ " jour "); 
        System.out.println(days);
       
         
        
                
                
                }
    
        catch(Exception e){
                e.printStackTrace();
                }
    }
    
    public int nbDays (java.sql.Date d1 ,java.sql.Date d2){
        
        return (int )((d2.getTime()-d1.getTime())/(1000*60*60*24) );
        
    }
    
    
    
    
    public boolean validateFields(){
        
        
        if(TxtSearch.getText().isEmpty()|TxtNom.getText().isEmpty()|TxtNom.getText().isEmpty()|TxtPlan.getText().isEmpty()|SpinNbPlace.getValue().toString().isEmpty()){
            
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur !");
                alert.setContentText("Veuillez remplir les champs ");
                alert.showAndWait();

                return false;
           
        }
        
        
        return true ;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  
    
    
    public void SceneRes(ActionEvent e ){
        
        Parent root;
        Stage stage;
        Scene scene;
        try {
        root = FXMLLoader.load(getClass().getResource("Reservation.fxml"));
        stage =(Stage)((Node)e.getSource()).getScene().getWindow();
         scene = new Scene(root);
          Image icon =new Image("resicon.jpg");
           stage.getIcons().add(icon);
        stage.setTitle("Reservation ");
        stage.setScene(scene);
        stage.show();
          } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
     public void SceneFacture(ActionEvent e ){
        
        Parent root;
        Stage stage;
        Scene scene;
        try {
        root = FXMLLoader.load(getClass().getResource("FXMLFacture.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
         scene = new Scene(root);
//         Image icon =new Image("factureIcon.jpg");
//           stage.getIcons().add(icon);
        stage.setTitle("Facture");
        stage.setScene(scene);
        stage.show();
          } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
        public static void generate_qr(String image_name,String qrCodeData) {
        try {
            String filePath = "C:\\code\\"+image_name+".png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (Exception e) {
            System.err.println(e);
        }
        
        
        
        
    } 
     
     
     
     
     
     
     
     
     
     
 
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
}
