/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;





import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import entities.Facture;
import entities.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import services.FactureService;
import utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;


/**
 * FXML Controller class
 *
 * @author mbell
 */
public class FXMLFactureController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private Button BtnRes;

    @FXML
    private Button BtnFacture;

    
     @FXML
    private TextField TxtSearch;
   

    @FXML
    private Label LblIdClient;

    @FXML
    private TextField TxtNom;

    @FXML
    private TextField TxtPrix;

    @FXML
    private Label LblIdFacture;

    @FXML
    private TextField TxtPrenom;
      @FXML
    private TextField TxtDate;

  @FXML
    private TableColumn<Facture, String> ClnNom;

    @FXML
    private TableColumn<Facture, Double> ClnPrix;

    @FXML
    private TableColumn<Facture, Date> ClnDate;
    @FXML
    private TableColumn<Facture, String> ClnPrenom;
     @FXML
    private TableView<Facture> TableFacture;
     
      @FXML
    private DatePicker dP;
      
    @FXML
    private Button BtnModifier;

    FactureService fa =new FactureService();
    
    
     ObservableList <Facture> Facturelist=observableArrayList(); 
//     MaterialDesignIconView icon = new MaterialDesignIconView(MaterialDesignIcon.ACCOUNT);
     
      
    Statement stmt;
    PreparedStatement pst;
    ResultSet rst;
     Connection cnx=MyDB.getInstance().getConnexion();
     @FXML
    private BorderPane borderpane;
      @FXML
    private ImageView ImgFacture;
//      String idFacture=TxtIdFacture.getText();
//        String idClient=TxtIdClient.getText();
//        String prix=TxtPrix.getText(); 
      static String data,nom , prenom;
      
//     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // borderpane.setCenter(GlyphsDude.createIcon(FontAwesomeIcon.BARCODE,"40px");
//        showFacture();
  //      setCellValueFromTableToTxtF();
//        Image img=new Image("C://Users/eyaou/Music/PI/src/cession-de-facture.jpg");
      //     ImgFacture.setImage(img);
        search();
        
         
//           creQ();
//           readQ();
        
        
        
        
    }    

    public FXMLFactureController() {
        
      
     // showFacture();
     
      
    }
    
    
    
    
    public void SceneFacture(ActionEvent e ){
        
        Parent root;
        Stage stage;
        Scene scene;
        try {
        root = FXMLLoader.load(getClass().getResource("FXMLFacture.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
         scene = new Scene(root);
         Image icon =new Image("factureIcon.jpg");
           stage.getIcons().add(icon);
        stage.setTitle("Facture ");
        stage.setScene(scene);
        stage.show();
          } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    
    public void SceneRes(ActionEvent e ){
        
        Parent root;
        Stage stage;
        Scene scene;
        try {
        root = FXMLLoader.load(getClass().getResource("Reservation.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
         scene = new Scene(root);
//         Image icon =new Image("resicon.jpg");
          // stage.getIcons().add(icon);
        stage.setTitle("Reservation ");
        stage.setScene(scene);
        stage.show();
          } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    
    }
    
    
    public ObservableList<Facture>getFactureList(){
        
        ObservableList <Facture> Facturelist=observableArrayList(); 
        int n =0;
        try {
                
           // String req = " select * from facture ";
           String req ="SELECT nom , prenom ,prix_Total ,date FROM utilisateur INNER JOIN facture ON utilisateur.id=facture.idClient WHERE  utilisateur.type LIKE 'touriste'    "   ;

           stmt=cnx.createStatement();
            rst= stmt.executeQuery(req);
             
           
            while(rst.next()){
                
             Facture f;
                        
                                f= new Facture(rst.getString("nom"),
                          rst.getString("prenom"),
                           rst.getDouble("prix_Total"),
                           rst.getDate("date")) ; 
        
                Facturelist.add(f);
                System.out.println(f.getNom());
                System.out.println(f);
               // rst.refreshRow();
            }
        
        }
                catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
                return Facturelist;
        }
    
    
    
    
    
    public void showFacture(){
        //List <Facture>f = new ArrayList<Facture>();
            
        
        //Facturelist
        ObservableList<Facture> FactureList=getFactureList();
        
        ClnNom.setCellValueFactory(new PropertyValueFactory<Facture,String>("nom"));
        ClnPrenom.setCellValueFactory(new PropertyValueFactory<Facture,String>("prenom"));
        ClnPrix.setCellValueFactory(new PropertyValueFactory<Facture,Double>("PrixTotal"));
        ClnDate.setCellValueFactory(new PropertyValueFactory<Facture,Date>("Date"));
        
       TableFacture.setItems(FactureList);
        
    }
    
    public void setCellValueFromTableToTxtF(){
        
        TableFacture.setOnMouseClicked((MouseEvent event)->{
          
            Facture f= TableFacture.getItems().get(TableFacture.getSelectionModel().getSelectedIndex());
           
            TxtNom.setText(f.getNom());
            TxtPrenom.setText(f.getPrenom());
                
        });
        
    }
    
    public void search (){
        
        FilteredList<Facture> filteredData = new FilteredList<>(Facturelist, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
//		TxtNom.textProperty().addListener((observable, oldValue, newValue) -> {
//			filteredData.setPredicate(Facture -> {
//				// If filter text is empty, display all persons.
//								
//				if (newValue == null || newValue.isEmpty()) {
//					return true;
//				}
//				
//				// Compare first name and last name of every person with filter text.
//				String lowerCaseFilter = newValue.toLowerCase();
//				
//				if (Facture.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
//					return true; // Filter matches first name.}
//				} else if (Facture.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
//					return true; // Filter matches last name.
//				}
//				
//				     else  
//				    	 return false; // Does not match.
//			});
//		});
//		
//        
//        
 }
    
    
    
    
   
    
    
    
    public void AjouterFacture(ActionEvent ev){
        
        String nom=TxtNom.getText();
        String prenom=TxtPrenom.getText();
        String prix=TxtPrix.getText();
        Date date =Date.valueOf(java.time.LocalDate.now());
        double valPrix=0;
        int a =0;
        if(!"".equals(prix)){
            
             valPrix=Double.parseDouble(prix);
        }
        boolean data ;

if(validateFields()){
             try{
//           Facture f =new Facture(nom , prenom ,valPrix,date);
//           FactureService fs =new FactureService();
//           fs.ajouterFacture(f);
       
     // String req = "INSERT INTO facture (prix_Total,date)VALUES('"+valPrix+"','"+date+"')";
       String req= "UPDATE facture f INNER JOIN utilisateur u ON f.idClient=u.id SET f.prix_Total='"+valPrix+"' WHERE u.nom LIKE '"+nom+"'; ";                             
//                 stmt = cnx.createStatement();
//            stmt.executeUpdate(req);
             pst=cnx.prepareStatement(req);
 
               pst.executeUpdate(req);
        
//        String req1="INSERT INTO utilisateur (nom,prenom)VALUES('"+nom+"','"+prenom+"') ";
//              
//                stmt = cnx.createStatement();
//            stmt.executeUpdate(req1);
        
        JOptionPane.showMessageDialog(null,"add avec succée");
//        TableFacture.refresh();
//        charger();
            a=1;
        
    } catch(SQLException e){
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null,"error");
            e.printStackTrace();
            }  
             if(a==0){
              Alert alt =new Alert (AlertType.ERROR,"aucun élement n'est modifée ",ButtonType.OK);
             alt.showAndWait();
             }
       
    }
    }
    
    public ResultSet charger(){
        
        
        try{
            
            stmt=cnx.createStatement();
            rst=stmt.executeQuery("select* from facture  ");
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
        TxtPrix.setText("");    
       
    }
    
    
    public void modifier (ActionEvent ev){
        String nom=TxtNom.getText();
        String prenom=TxtPrenom.getText();
        String prix=TxtPrix.getText();
        double valPrix=0;
        if(!"".equals(prix)){
            
             valPrix=Double.parseDouble(prix);
        }
        int a=0;
        boolean data ;
         
        
        if(validateFields()){
        
        
        try {
            
      //  String req="Update facture set idFacture=?,idClient=?,prixTotal=?,date=? where f.getIdfacture()=idFcture";
         String req= "UPDATE facture f INNER JOIN utilisateur u ON f.idClient=u.id SET f.prix_Total='"+valPrix+"' WHERE u.nom LIKE '"+nom+"'; ";                             

         pst = cnx.prepareStatement(req);
         
                pst.execute(req);
               pst.executeUpdate(req);
//         pst.setString(1,(nom));
//         pst.setString(2,(prenom));
//         pst.setDouble(3,Double.parseDouble(prix));
//         
//         pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "update avec succée"); 
        a=1;
        }
         catch(Exception e){
        JOptionPane.showMessageDialog(null,"un probleme se produit !!", " Message", JOptionPane.PLAIN_MESSAGE);
      e.printStackTrace();
    }
        if(a==0){
        Alert alt =new Alert (AlertType.ERROR,"aucun élement n'est modifée ",ButtonType.OK);
             alt.showAndWait();}
        
    }
    
    }
        
    public void delete(ActionEvent ev){
        String nom=TxtNom.getText();
        String prenom=TxtPrenom.getText();
        String prix=TxtPrix.getText();
        double valPrix=0;
        if(!"".equals(prix)){
            
             valPrix=Double.parseDouble(prix);
        }
        
        int a =0;
        boolean data =true;
        if(validateFields()){
        
        try{
          // String req="DELETE * FROM facture f INNER JOIN utilisateur u ON (f.idClient=u.id)WHERE f.prix_Total='"+valPrix+"',u.nom LIKE '"+nom+"'             "                ;
                   
             String req="DELETE FROM facture WHERE idClient IN (SELECT id FROM utilisateur u WHERE u.nom='gai')  ";
             pst = cnx.prepareStatement(req);
             
//             pst.setDouble(1,valPrix);
//             pst.setString(2, nom);
            pst.executeUpdate();
             JOptionPane.showMessageDialog(null, "delete avec succée"); 
             a=1;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"un probleme se produit !!", " Message", JOptionPane.PLAIN_MESSAGE);
      e.printStackTrace();
            
        }
        if(a==0){
        Alert alt =new Alert (AlertType.ERROR,"aucun élement n'est supprmée ",ButtonType.OK);
             alt.showAndWait();}
        
    }
    
    }
        
    @FXML
    public void displayFacture(MouseEvent ev){
        String sh=TxtSearch.getText();
        
        double valPrix=0;
        int n =0;
        
        boolean data ;
         
              if (TxtSearch.getText().isEmpty()){
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur de saisie !");
                alert.setContentText("le champ de recherche est vide ");
                alert.show();

                data = false;
                  
              }
        
//           if(validateFields()){
              
         try {
                
          // 
           String req ="SELECT u.nom , u.prenom ,f.prix_Total ,date FROM (utilisateur u INNER JOIN facture f ON u.id=f.idClient) WHERE u.type LIKE 'touriste' and u.nom LIKE '"+TxtSearch.getText()+"'   "   ;

           stmt=cnx.createStatement();
            rst= stmt.executeQuery(req);
             
           
            while(rst.next()){
                
             Facture f;
                        
                                f= new Facture(rst.getString("nom"),
                          rst.getString("prenom"),
                           rst.getDouble("prix_Total"),
                           rst.getDate("date")) ; 
                                
                       String nom =rst.getString("nom");
                        String prenom = rst.getString("prenom");
                          String out = nom +" "+prenom ;                    
                               
                         // generate_qr(nom, out );
        
                Facturelist.add(f);
                TxtNom.setText(rst.getString("nom"));
                TxtPrenom.setText(rst.getString("prenom"));
                String prix = String.valueOf(rst.getDouble("prix_Total"));
                TxtPrix.setText(prix);
                TxtDate.setText(rst.getDate("date").toString());
                
//                System.out.println(f.getNom());
//                System.out.println(f);
              n=1;
            }
        
        }
                catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        
//        if (n==0){
//            Alert alt =new Alert (AlertType.ERROR,"aucun élement trouvé ",ButtonType.OK);
//             alt.showAndWait();
//        }
        
              

    }
    
    
    public boolean validateFields(){
        
        
        if(TxtSearch.getText().isEmpty()|TxtNom.getText().isEmpty()|TxtNom.getText().isEmpty()|TxtPrix.getText().isEmpty()){
            
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Erreur !");
                alert.setContentText("Veuillez remplir les champs ");
                alert.showAndWait();

                return false;
           
        }
        
        
        return true ;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    public void print(MouseEvent event ){
   
     Document doc =new Document();
     List<Document>l=new ArrayList();
     Map<Document,Facture>  m =new HashMap();
     
    try{
    PdfWriter.getInstance(doc,new FileOutputStream("facture.pdf"));
    doc.open();
    Date d=Date.valueOf(java.time.LocalDate.now());
    String fo=d.toString() ;
    SimpleDateFormat form=new SimpleDateFormat(fo);
  // com.itextpdf.text.Image image=com.itextpdf.text.Image.getInstance("C:\\Users\\Downloads\\cession-de-facture");
     com.itextpdf.text.Image image=com.itextpdf.text.Image.getInstance("C:\\Users\\mbell\\Documents\\NetBeansProjects\\PI\\src\\cession-de-facture.jpg");
    image.setAlignment(com.itextpdf.text.Image.ALIGN_CENTER);
    doc.add(image);
   String nom = TxtNom.getText();
   String prenom = TxtPrenom.getText();
   String prix =TxtPrix.getText();
   doc.add(new Paragraph("vous etes la bienvenu "
           + "\n you're welcome , I hope you enjoye your road  "
           + "\n Nom : "+nom+" "
                   + "\n Prenom : "+prenom+""
                           + "\n Prix :"+prix+""
                           + "\n Date : "+Date.valueOf(java.time.LocalDate.now())+""
                                   + "\n  ",FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.ITALIC, BaseColor.DARK_GRAY)));
   
   doc.bottomMargin();
   doc.addTitle("Facture");
   doc.setPageCount(1);
   doc.setMargins(10, 15, 20, 10);
   
   
   
   
    doc.close();
    Desktop.getDesktop().open(new File("facture.pdf"));
        System.out.println("download done");
    
    }
    catch(Exception e){
        e.printStackTrace();
    }
    
    }
    
    
    public void creQ() {
    try{
    String qrCodeData = "www.chillyfacts.com";
            String filePath = "C:\\code\\chillyfacts.png";
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
            
   public void readQ(){
       
       try {
                String filePath = "D:\\QRCODE\\chillyfacts.png";
                String charset = "UTF-8";
                Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                System.out.println("Data read from QR Code: " + readQRCode(filePath, charset, hintMap));
            } catch (Exception e) {
                // TODO: handle exception
            }
       
       
         
   }         
            
    public static String readQRCode(String filePath, String charset, Map hintMap)
    throws FileNotFoundException, IOException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
            new BufferedImageLuminanceSource(
                ImageIO.read(new FileInputStream(filePath)))));
        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
        return qrCodeResult.getText();
    }
    

    
    public static void generate_qr(String image_name ,String qrCodeData) {
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
