/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author Tox
 */
public class Reclamation {

    private int id,user_id;
    private String etat, description, screenshot;
    
    Date date_creation, date_traitement;
    String reference;
    String nom,prenom,email;

    public Reclamation() {
    }
    
    
    public Reclamation(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Reclamation(String nom, String prenom, String email, String description, String screenshot) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.description = description;
        this.screenshot = screenshot;
    }

    public Reclamation(int id, String description, Date date_creation, String reference, String nom, String prenom, String email) {
        this.id = id;
        this.description = description;
        this.date_creation = date_creation;
        this.reference = reference;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
    

    public Reclamation(String nom, String prenom, String email, String description, String screenshot, Date date_creation) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.description = description;
        this.screenshot = screenshot;
        this.date_creation = date_creation;
    }

    public Reclamation(int id, String nom, String prenom, String etat, String email, String description, String screenshot, Date date_creation) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.etat = etat;
        this.email = email;
        this.description = description;
        this.screenshot = screenshot;
        this.date_creation = date_creation;
    }

    public Reclamation(int id, String nom, String prenom, String etat, String email, String description, String screenshot, Date date_creation, Date date_traitement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.etat = etat;
        this.email = email;
        this.description = description;
        this.screenshot = screenshot;
        this.date_creation = date_creation;
        this.date_traitement = date_traitement;
    }

    
    public Reclamation(int id, String nom, String prenom, String etat, String email, String description, Date date_creation, Date date_traitement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.etat = etat;
        this.email = email;
        this.description = description;
        this.date_creation = date_creation;
        this.date_traitement = date_traitement;
    }

    public Reclamation(String nom, String prenom, String etat, String email, String description, Date date_creation, Date date_traitement) {
        this.nom = nom;
        this.prenom = prenom;
        this.etat = etat;
        this.email = email;
        this.description = description;
        this.date_creation = date_creation;
        this.date_traitement = date_traitement;
    }

    public Reclamation(String nom, String prenom, String email, String description) {

        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.description = description;

    }
    public static String reference(){
    String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";
    StringBuilder s = new StringBuilder(8);
    for(int i=0;i<8;i++){ 
    int index= (int) (AlphaNumericString.length()* Math.random());
    s.append(AlphaNumericString.charAt(index));
}
    return s.toString();
    }

    public Reclamation(String string, String string0, String string1, String string2, String string3, String string4, String string5, String string6) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Reclamation(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Date getDate_traitement() {
        return date_traitement;
    }

    public void setDate_traitement(Date date_traitement) {
        this.date_traitement = date_traitement;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

   

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
  

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", user_id=" + user_id + ", etat=" + etat + ", description=" + description + ", screenshot=" + screenshot + ", date_creation=" + date_creation + ", date_traitement=" + date_traitement + ", reference=" + reference + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + '}';
    }

   

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    
}
