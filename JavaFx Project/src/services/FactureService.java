/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Facture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import utils.MyDB;

/**
 *
 * @author mbell
 */
public class FactureService implements IFacture<Facture> {

    Connection cnx;
    Statement stmt;
    PreparedStatement pst;
    ResultSet rst;
     ObservableList <Facture> list=observableArrayList(); 
    
    public FactureService(){
        cnx=MyDB.getInstance().getConnexion();
        
    }

    @Override
    public void ajouterFacture(Facture f) {

        
        try {
            String req = "INSERT INTO facture (idFacture,idClient,prixTotal,date)VALUES('"+f.getIdFacture()+"','"+f.getIdClient()+"','"+f.getPrixTotal()+"','"+f.getDate()+"') ";
                
//            PreparedStatement pst = cnx.prepareStatement(req);
//
//            pst.setInt(1, f.getIdClient());
//            pst.setDouble(2, f.getPrixTotal());
//            pst.setString(3, f.getDate());
           // pst.execute();
           stmt = cnx.createStatement();
            stmt.executeUpdate(req);
            
            System.out.println(" f add succes");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void modifierFacture(Facture f ) {
        try {

            String req = " Update facture set idFacture=?,idClient=?,prixTotal=?,date=? where f.getIdfacture()=?            ";

            pst = cnx.prepareStatement(req);
            pst.setInt(1,7);
            
            pst.setInt(2, 8);
            
            pst.setInt(3, 9);
            
            pst.setDate(4,java.sql.Date.valueOf("07/08/1999"));    
            
            pst.executeUpdate();
            System.out.println("update done");
            
          //  pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }

    }

    @Override
    public void supprimerFacture(int idFacture) {
        try {

            String req = "DELETE  FROM facture WHERE idFacture=? ";
             pst = cnx.prepareStatement(req);
             
             pst.setInt(1,idFacture);
            pst.executeUpdate();
            System.out.println("delete done");

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }

        
    
      public void rechercheFacture(int idFacture){
          
          
          List<Facture> fa = new ArrayList<>();
          try {
              
              String req ="select * from facture where idFacture =1 ";
              
//                pst=cnx.prepareStatement(req);
               
//                rst = pst.executeQuery();
                    stmt=cnx.createStatement();
                    rst=stmt.executeQuery(req);
              //  pst.setInt(1,1);
                
               while(rst.next()){
                
                 Facture f = new Facture();
                f.setIdFacture(rst.getInt(1));
                f.setIdClient(rst.getInt(2));
                f.setPrixTotal(rst.getInt(3));
                f.setDate(rst.getDate("date"));
                
                fa.add(f);
          
               }
               
          }
               catch(Exception e ){
                       
                       System.err.println(e.getMessage());
                       e.printStackTrace();
                       }
        
      
          System.out.println(fa);
      
      }
      
      
     public  List <Facture> afficherAll(){
          
         
          List<Facture>fa =new ArrayList<Facture>();
        try {
                
           // String req = " select * from facture   ";
            String req ="SELECT nom , prenom ,prix_Total ,date FROM utilisateur INNER JOIN facture ON utilisateur.id=facture.idClient WHERE  utilisateur.type LIKE 'touriste'    "   ;

             stmt=cnx.createStatement();
            rst= stmt.executeQuery(req);
            // System.out.println(rst.next());
            while(rst.next()){
                //System.out.println(rst.next());
                
             Facture f = new Facture();   
                 f.setNom(rst.getString("nom"));
                f.setPrenom(rst.getString("prenom"));
                    
                f.setPrixTotal(rst.getDouble(3));
              f.setDate(rst.getDate(4));
                System.out.println(f);

               //     list.add(new Facture(rst.getInt(1),rst.getInt(2),rst.getInt(3),rst.getDate(4)));

                fa.add(f);
                
            }

        }
         catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
            
       System.out.println(fa); 
        
        return fa;    
        
        
        

    }
    
         
     public List <Facture> triPrixCroisssant(){
         
         List<Facture>fa =new ArrayList<Facture>();
        try {
                
            String req = " select * from facture  ORDER BY prixTotal ASC ";
             stmt=cnx.createStatement();
            rst= stmt.executeQuery(req);
             
            while(rst.next()){
                
             Facture f = new Facture();   
                f.setIdFacture(rst.getInt(1));
                f.setIdClient(rst.getInt(2));
                f.setPrixTotal(rst.getInt(3));
                f.setDate(rst.getDate(4));
                
                fa.add(f);
                
            }

        }
         catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
            
       System.out.println(fa); 
        
         return fa ;
         
         
     }
         
         
      public List <Facture> triPrixDescendant(){
         
         List<Facture>fa =new ArrayList<Facture>();
        try {
                
            String req = " select * from facture  ORDER BY prixTotal DESC ";
             stmt=cnx.createStatement();
            rst= stmt.executeQuery(req);
             
            while(rst.next()){
                
             Facture f = new Facture();   
                f.setIdFacture(rst.getInt(1));
                f.setIdClient(rst.getInt(2));
                f.setPrixTotal(rst.getInt(3));
                f.setDate(rst.getDate(4));
                
                fa.add(f);
                
            }

        }
         catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
            
       System.out.println(fa); 
        
         return fa ;
         
         
     }   
          
          
      
    
    
      
      
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
