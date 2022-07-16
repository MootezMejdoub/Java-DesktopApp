///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package entities;
//
//import java.sql.Date;
//
///**
// *
// * @author Tox
// */
//public class Avis {
//    private int id;
//    private int ref_plan;
//    private int ref_client;
//    private int rating;
//    private String nom; 
//
//    public Avis() {
//    }
//
//    public Avis(int ref_plan, int ref_client, int rating, String nom) {
//        this.ref_plan = ref_plan;
//        this.ref_client = ref_client;
//        this.rating = rating;
//        this.nom = nom;
//       
//    }
//
//    public Avis(int id, int ref_plan, int ref_client, int rating, String nom) {
//        this.id = id;
//        this.ref_plan = ref_plan;
//        this.ref_client = ref_client;
//        this.rating = rating;
//        this.nom = nom;
//        
//    }
//
//    public Avis(int rating, String nom) {
//        this.rating = rating;
//        this.nom = nom;
//      
//    }
//
//    public Avis(int ref_plan, int ref_client, int rating) {
//        this.ref_plan = ref_plan;
//        this.ref_client = ref_client;
//        this.rating = rating;
//    }
//    
//    
//    
//    
//    
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getRef_plan() {
//        return ref_plan;
//    }
//
//    public void setRef_plan(int ref_plan) {
//        this.ref_plan = ref_plan;
//    }
//
//    public int getRef_client() {
//        return ref_client;
//    }
//
//    public void setRef_client(int ref_client) {
//        this.ref_client = ref_client;
//    }
//
//    public int getRating() {
//        return rating;
//    }
//
//    public void setRating(int rating) {
//        this.rating = rating;
//    }
//
//    public String getNom() {
//        return nom;
//    }
//
//    public void setNom(String nom) {
//        this.nom = nom;
//    }
//
//  
//
//    @Override
//    public String toString() {
//        return "Avis{" + "id=" + id + ", ref_plan=" + ref_plan + ", ref_client=" + ref_client + ", rating=" + rating + ", nom=" + nom + ", datecreation=" + datecreation + '}';
//    }
//    
//    
//    
//    
//    
//}
