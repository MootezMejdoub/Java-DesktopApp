/**32
 * 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author mbell
 */
public class Facture {

    private int idFacture;
    private int idClient;
    private double prixTotal;
    private Date date;
    private String nom , prenom ; 

    public String getNom() {
        return nom;
    }

    public Facture(Date date, String nom, String prenom) {
        this.date = date;
        this.nom = nom;
        this.prenom = prenom;
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

    public Facture(double prixTotal, Date date) {
        this.prixTotal = prixTotal;
        this.date = date;
    }

        public Facture (){
            
        }

    public Facture(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
         public Facture (String nom , String prenom , double prixTotal,Date date ){
            this.nom=nom;
            this.prenom=prenom;
            this.prixTotal = prixTotal;
            this.date = date;
        }
        
    
    public Facture(int idFacture, int idClient, double prixTotal, Date date) {
        this.idFacture = idFacture;
        this.idClient = idClient;
        this.prixTotal = prixTotal;
        this.date = date;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Facture{" + "idFacture=" + idFacture + ", idClient=" + idClient + ", prixTotal=" + prixTotal + ", date=" + date + '}';
    }

}
