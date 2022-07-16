/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.Utilisateur;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import services.UserService;

/**
 *
 * @author eyaou
 */
public class Client extends Utilisateur{
   

    public Client() {
    }

    public Client(String nom, String prenom, Date date_naissance, String adresse, String num_tel, String email, String mdp, String type) {
        super(nom, prenom, date_naissance, adresse, num_tel, email, mdp, type);
    }

    public Client(int id, String nom, String prenom, Date date_naissance, String adresse, String num_tel, String email, String mdp, String type) {
        super(id, nom, prenom, date_naissance, adresse, num_tel, email, mdp, type);
    }

   public Client(int id,String nom, String prenom, Date date_naissance, String adresse, String num_tel, String email, String type) {
        super(nom, prenom, date_naissance, adresse, num_tel, email, type);
    }

    

 
  
    
    
}
