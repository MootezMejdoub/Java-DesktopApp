/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author mbell
 */
public class Reservation {
   
    public Reservation(String nom, String prenom, String plan, int nbrPlace) {
        this.nom = nom;
        this.prenom = prenom;
        this.plan = plan;
        this.nbrPlace = nbrPlace;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
        
    public int getNumRes() {
        return numRes;
    }

    public void setNumRes(int numRes) {
        this.numRes = numRes;
    }
    private int idClient;
    private int idPlan;

    private int nbrPlace;

    private static int nbMax=4;
    public static int getNbMax() {
        return nbMax;
    }
     private int numRes;
    private String nom , prenom , plan ;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Reservation(String nom) {
        this.nom = nom;
    }

    public Reservation(String nom, String prenom, String plan) {
        this.nom = nom;
        this.prenom = prenom;
        this.plan = plan;
    }


    
    public Reservation(){
        
}
    
     public Reservation(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    
    
    
    public Reservation(int numRes,int idClient, int idPlan, int nbrPlace) {
         this.numRes=numRes;       
        this.idClient = idClient;
        this.idPlan = idPlan;
        this.nbrPlace = nbrPlace;

    }

     public Reservation(int idClient, int idPlan) {

        this.idClient = idClient;
        this.idPlan = idPlan;
        

    }
    
    
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public int getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(int nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

//    @Override
//    public String toString() {
//        return "Reservation{" + "idClient=" + idClient + ", idPlan=" + idPlan + ", nbrPlace=" + nbrPlace + '}';
//    }

//    @Override
//    public String toString() {
//        return "Reservation{" + "numRes=" + numRes + ", idClient=" + idClient + ", idPlan=" + idPlan + ", nbrPlace=" + nbrPlace + '}';
//    }

    @Override
    public String toString() {
        return "Reservation{" + "nom=" + nom + ", prenom=" + prenom + ", plan=" + plan + ", nbPlace=" + nbrPlace + '}';
    }

   

    
    
}
