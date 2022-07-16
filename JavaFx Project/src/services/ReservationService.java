
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Facture;
import entities.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author mbell
 */
public class ReservationService implements Iservice<Reservation> {

    Connection cnx;
    Statement stmt;
    ResultSet rst;
    PreparedStatement pst;    
    
    
    public ReservationService(){
        cnx=MyDB.getInstance().getConnexion();
    }
    


  @Override
    public void ajouter(Reservation res) {
            
        try {
        String req = "INSERT INTO reservation (idClient,idPlan,nbrPlace)VALUES('"+res.getIdClient()+"' , '"+res.getIdPlan()+"' , '"+res.getNbrPlace()+"')       "; 
            
            stmt = cnx.createStatement();
            stmt.executeUpdate(req);
            System.out.println("res créée");
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
        }
        }

    
    
    public void ajouterReservation(Reservation r,int nb  ){
        
        if (nb>r.getNbMax()){
            
        System.out.println("nombre depasse les places maximales ");
        nb=r.getNbMax();    
        }
        
         
        try{
        String req = " INSERT INTO reservation (idClient,idPlan,nbrPlace)VALUES('"+r.getIdClient()+"' , '"+r.getIdPlan()+"' ,'"+nb+"'  )"   ;
                 
        stmt = cnx.createStatement();
            stmt.executeUpdate(req);
   
             System.out.println("res créée");
        }
        catch(Exception ex){
             System.err.println(ex.getMessage());
                ex.printStackTrace();
        }
        
        
    }
    
    

    @Override
    public void modifier(Reservation res) {
       
        try {

            String req = " UPDATE reservation SET idClient=?,idPlan=?,nbrPlace=?             ";

            pst = cnx.prepareStatement(req);
            pst.setInt(1,7);
            
            pst.setInt(2, 8);
            
            pst.setInt(3, 9);
            
            pst.executeUpdate();
            System.err.println("update done");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
    }

    @Override
    public void supprimer(int idClient ) {
        
          try {

            String req = " delete from  reservation where idClient=?             ";

             pst = cnx.prepareStatement(req);
            pst.setInt(1,idClient);
            
            pst.executeUpdate();
            System.out.println("delete done");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        
        
        
    }

    @Override
    public List afficherAll() {
        
         List<Reservation>rs  =new ArrayList<Reservation>();
        try {
                
            String req = " select * from reservation   ";
             stmt=cnx.createStatement();
            rst= stmt.executeQuery(req);
             
            while(rst.next()){
                
             Reservation r = new Reservation();   
                r.setIdClient(rst.getInt(1));
                r.setIdPlan(rst.getInt(2));
                r.setNbrPlace(rst.getInt(3));
                
                rs.add(r);
                
            }

        }
         catch(SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
            
       System.out.println(rs); 
        
        return rs;    

            

    }
    
    
    
    @Override
    public void afficherById(int idClient)
    {
       List<Reservation> rs = new ArrayList<>();
          try {
              
              String req ="select * from reservation where idClient=1";
              
//                pst=cnx.prepareStatement(req);
                
//                rst = pst.executeQuery();
                    stmt=cnx.createStatement();
                    rst=stmt.executeQuery(req);
              //  pst.setInt(1,1);
                
               while(rst.next()){
                
                 Reservation r = new Reservation();
                r.setIdClient(rst.getInt(1));
                r.setIdPlan(rst.getInt(2));
                r.setNbrPlace(rst.getInt(3));
                
                rs.add(r);
              
                
            }
              
              
              
          }
        catch(Exception e ){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        
          System.out.println(rs);
          
    }
        
    





   

    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
   
    
}
