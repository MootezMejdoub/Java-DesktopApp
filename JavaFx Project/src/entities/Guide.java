/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.Utilisateur;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import entities.Utilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import utils.MyDB;

/**
 *
 * @author eyaou
 */
public class Guide extends Utilisateur{
    
   // private String type;
    private String Description;
    private int evaluation;
    Connection connexion;
    Statement stm;

    public Guide() {
        connexion = MyDB.getInstance().getConnexion();
    }

    public Guide( String nom, String prenom, Date date_naissance, String adresse, String num_tel, String email, String mdp, String type,String Description, int evaluation) {
        super(nom, prenom, date_naissance, adresse, num_tel, email, mdp, type);
        this.Description = Description;
        this.evaluation = evaluation;
    }
    
    public Guide( String nom, String prenom, Date date_naissance, String adresse, String num_tel, String email, String mdp, String type,String Description) {
        super(nom, prenom, date_naissance, adresse, num_tel, email, mdp, type);
        this.Description = Description;
        
    }

    public Guide( int id, String nom, String prenom, Date date_naissance, String adresse, String num_tel, String email, String mdp, String type,String Description, int evaluation) {
        super(id, nom, prenom, date_naissance, adresse, num_tel, email, mdp, type);
        this.Description = Description;
        this.evaluation = evaluation;
    }

   

   

    /*public String getType() {
        return type;
    }*/

    public String getDescription() {
        return Description;
    }

    public int getEvaluation() {
        return evaluation;
    }

    /*public void setType(String type) {
        this.type = type;
    }*/

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public String toString() {
        
                return super.toString()+"Guide{" + ", Description=" + Description + ", evaluation=" + evaluation + '}';
    }
    
    
    /* public void ajouterUser(Guide p) throws SQLException {
        String req = "INSERT INTO `heritage` (`nom`, `prenom`,`date_naissance`,`adresse`, `num_tel`, `email`,`mdp`, `type`,`description`, `evaluation`) "
                + "VALUES ( ?, ?,?, ?,?, ?,?, ?,?,?) ";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, p.getNom());
        ps.setString(2, p.getPrenom());
         ps.setString(4, p.getAdresse());
        ps.setString(3,  p.getDate_naissance());
         ps.setString(5, p.getNum_tel());
        ps.setString(6, p.getEmail());
         ps.setString(7, p.getMdp());
        ps.setString(8, p.getType());
        ps.setString(7, p.getDescription());
        ps.setInt(8, p.getEvaluation());
        
        ps.executeUpdate();
    }*/

   

    
    

   
    
}
