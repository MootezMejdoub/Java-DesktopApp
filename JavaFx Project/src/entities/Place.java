/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.sql.PreparedStatement;

/**
 *
 * @author bahe
 */
public class Place {
    private int id;
    private String nom;
    private double note;
    private String address;
    private String description;
    private String type;
    public static PreparedStatement pste;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getNote() {
        return note;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public static PreparedStatement getPste() {
        return pste;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static void setPste(PreparedStatement pste) {
        Place.pste = pste;
    }

    public Place() {
    }

    public Place(String nom, double note, String address, String description, String type) {
        this.id = id;
        this.nom = nom;
        this.note = note;
        this.address = address;
        this.description = description;
        this.type = type;
    }
    
    public Place(int id, String nom, double note, String address, String description, String type) {
        this.id = id;
        this.nom = nom;
        this.note = note;
        this.address = address;
        this.description = description;
        this.type = type;
    }

    @Override
    public String toString() {
        return "\nPlace{" + "id=" + id + ", nom=" + nom + ", note=" + note + ", address=" + address + ", description=" + description + ", type=" + type + '}';
    }
    
    
}
