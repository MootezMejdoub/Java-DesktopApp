/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import entities.CryptWithMD5;
import services.UserService;

/**
 *
 * @author eyaou
 */
public abstract class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String num_tel;
    private Date date_naissance;
    private String email;
    private String mdp;
    private String type;

    UserService us =new UserService();
    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom,  Date date_naissance,String adresse, String num_tel, String email, String mdp,String type) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.date_naissance = date_naissance;
        this.email = email;
        this.mdp = mdp;
        this.type=type;
    }
    public Utilisateur(String nom, String prenom,  Date date_naissance,String adresse, String num_tel, String email, String type) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.date_naissance = date_naissance;
        this.email = email;
        
        this.type=type;
    }
    
    

    public Utilisateur(int id, String nom, String prenom,  Date date_naissance,String adresse, String num_tel, String email, String mdp, String type) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.date_naissance = date_naissance;
        this.email = email;
        this.mdp = mdp;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public String getEmail() {
        return email;
    }

    public String getMdp() {
        return CryptWithMD5.cryptWithMD5(mdp);
      // return us.encrypt(mdp);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMdp(String mdp) {
       // this.mdp = mdp;
       this.mdp=CryptWithMD5.cryptWithMD5(mdp);
       //this.mdp=us.encrypt(mdp);
            
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", num_tel=" + num_tel + ", date_naissance=" + date_naissance + ", email=" + email + ", mdp=" + mdp + ", type=" + type + '}';
    }

    
    
    
    
}
