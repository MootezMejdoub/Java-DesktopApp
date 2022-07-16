/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author bahe
 */
public class Plan {

    private int id;
    private int idGuide;
    private double prix;
    private double note;
    private String titre;
    private String description;
    private int nmbrPlacesMax;
    private int nmbrPlacesReste;
    private String dateDebut;
    private String dateFin;
    private String pointDepart;
    public static PreparedStatement pste;

    public int getId() {
        return id;
    }

    public int getIdGuide() {
        return idGuide;
    }

    public double getPrix() {
        return prix;
    }

    public double getNote() {
        return note;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public int getNmbrPlacesMax() {
        return nmbrPlacesMax;
    }

    public int getNmbrPlacesReste() {
        return nmbrPlacesReste;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getPointDepart() {
        return pointDepart;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdGuide(int idGuide) {
        this.idGuide = idGuide;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNmbrPlacesMax(int nmbrPlacesMax) {
        this.nmbrPlacesMax = nmbrPlacesMax;
    }

    public void setNmbrPlacesReste(int nmbrPlacesReste) {
        this.nmbrPlacesReste = nmbrPlacesReste;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public void setPointDepart(String pointDepart) {
        this.pointDepart = pointDepart;
    }

    public Plan() {
    }
    
    public Plan(int idGuide, double prix, double note, String titre, String description, int nmbrPlacesMax, int nmbrPlacesReste, String dateDebut, String dateFin, String pointDepart) {
        this.idGuide = idGuide;
        this.prix = prix;
        this.note = note;
        this.titre = titre;
        this.description = description;
        this.nmbrPlacesMax = nmbrPlacesMax;
        this.nmbrPlacesReste = nmbrPlacesReste;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.pointDepart = pointDepart;
    }
    
    public Plan(int id, int idGuide, double prix, double note, String titre, String description, int nmbrPlacesMax, int nmbrPlacesReste, String dateDebut, String dateFin, String pointDepart) {
        this.id = id;
        this.idGuide = idGuide;
        this.prix = prix;
        this.note = note;
        this.titre = titre;
        this.description = description;
        this.nmbrPlacesMax = nmbrPlacesMax;
        this.nmbrPlacesReste = nmbrPlacesReste;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.pointDepart = pointDepart;
    }

    @Override
    public String toString() {
        return "\nPlan{" + "id=" + id + ", idGuide=" + idGuide + ", prix=" + prix + ", note=" + note + ", titre=" + titre + ", description=" + description + ", nmbrPlacesMax=" + nmbrPlacesMax + ", nmbrPlacesReste=" + nmbrPlacesReste + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", pointDepart=" + pointDepart + '}';
    }

}
