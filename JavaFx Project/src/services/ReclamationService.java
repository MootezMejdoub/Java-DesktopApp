/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.JavaMailUtil;
import utils.MyDB;
import com.sun.org.apache.bcel.internal.generic.D2F;
import entities.Reclamation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tox
 */
public class ReclamationService implements IReclamation<Reclamation> {

    Connection connexion;
    PreparedStatement stm;

    public ReclamationService() {
        connexion = MyDB.getInstance().getConnexion();
    }
    

    @Override
    public void ajouterReclamation(Reclamation p) {

        LocalDate actuelle = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = actuelle.format(formatter);
        String dc = date;

        try {
            String ref = Reclamation.reference();
            String req = "INSERT INTO reclamation (nom, prenom,email,description,date,rec_reference,user_id) VALUES ( '"
                    + p.getNom() +  "', '" + p.getPrenom() + "','" + p.getEmail() + "', '" +p.getDescription() + "', '" + dc +"','"
                    +ref+"',"+p.getUser_id()+") "; 
            stm = connexion.prepareStatement(req);
            stm.executeUpdate(); 
            System.out.println("Bien ajoutee");
           JavaMailUtil.sendEmail( p.getEmail(), "Referance de reclamation : "+ref+"\n Votre demande sera prise en compte et nous vous répondrons dans les meilleurs délais. \n Vous serez notifiés via une maill les details de traitement de votre reclamation \n Merci !! ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        } catch (Exception ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deleteReclamation(String reference) {
        //boolean delete=false;
        try {
            String req = "DELETE FROM reclamation where rec_reference = ?";

            PreparedStatement pst = connexion.prepareStatement(req);

            pst.setString(1, reference);
            pst.executeUpdate();
            System.out.println("suppression effectuée");
            //delete=true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void updateReclamation(String reference, Reclamation u) {
        PreparedStatement select;
        PreparedStatement update;
        ResultSet resultat;

        try {
            select = connexion.prepareStatement("select * from reclamation where rec_reference='" + reference + "'");

            resultat = select.executeQuery();
            if (resultat.isBeforeFirst()) {
                update = connexion.prepareStatement("update reclamation set  description= ? where reference = ?");

                
                update.setString(1, u.getDescription());
                update.setString(2, reference);
                update.executeUpdate();

                System.out.println("modification effectuée");

            } else {
                System.err.println("non effectuée");
            }

        } catch (SQLException ex) {
            System.err.println("erreur de modification");
        }

    }

    @Override
    public ArrayList<Reclamation> afficherReclamation() {
        try {
            ResultSet rst;
            ArrayList<Reclamation> reclamations = new ArrayList<>();
            String req = "select * from  reclamation join utilisateur on utilisateur.id=reclamation.user_id";
            stm = connexion.prepareStatement(req);
           
           rst=stm.executeQuery();

            while (rst.next()) {
               
                Reclamation r = new Reclamation();
                r.setId(rst.getInt("id"));
                r.setDescription(rst.getString("description"));
                r.setEmail(rst.getString("utilisateur.email"));
                r.setNom(rst.getString("utilisateur.nom"));
                r.setPrenom(rst.getString("utilisateur.prenom"));
                r.setReference(rst.getNString( "rec_reference"));
                r.setDate_creation(rst.getDate("date"));
                
                reclamations.add(r);
            }
            return  reclamations;
        } catch (SQLException ex) {
            return null;
        }
        
    }
    

    public List<Reclamation> rechercheReclamationsEmail(String eemail) {
        List<Reclamation> myList = new ArrayList<Reclamation>();
        String requete = null;
        Statement pst;
        try {

            pst=connexion.prepareStatement("select * from reclamation full join utilisateur where  reclamation.email="+eemail+" and utilisateur.id="+eemail+" or email="+eemail);
        
            
       
            // = connexion.prepareStatement(requete); // import java.sql.Statement
            ResultSet rst = pst.executeQuery(eemail);
            while (rst.next()) {
                
                 Reclamation r = new Reclamation();
                r.setId(rst.getInt("id"));
                r.setDescription(rst.getString("description"));
                r.setEmail(rst.getString("email"));
                r.setNom(rst.getString("nom"));
                r.setPrenom(rst.getString("prenom"));
                r.setReference(rst.getNString( "rec_reference"));
                r.setDate_creation(rst.getDate("date"));
                myList.add(r);
        }} catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
         return myList;
    }
        
    public ArrayList<Reclamation> afficherReclamationzzzzz() {
        try {
            ResultSet rst;
            ArrayList<Reclamation> reclamations = new ArrayList<>();
            String req = "select * from  reclamation,utilisateur where reclamation.user_id="+43;
            stm = connexion.prepareStatement(req);
           
           rst=stm.executeQuery();

            while (rst.next()) {
               
                Reclamation r = new Reclamation();
                r.setId(rst.getInt("id"));
                r.setDescription(rst.getString("description"));
                r.setEmail(rst.getString("utilisateur.email"));
                r.setNom(rst.getString("utilisateur.nom"));
                r.setPrenom(rst.getString("utilisateur.prenom"));
                r.setReference(rst.getNString( "rec_reference"));
                r.setUser_id(rst.getInt("user_id"));
                r.setDate_creation(rst.getDate("date"));
                r.setEtat(rst.getNString( "etat"));
                
                reclamations.add(r);
            }
            return  reclamations;
        } catch (SQLException ex) {
            return null;
        }
        
    }
    
}
