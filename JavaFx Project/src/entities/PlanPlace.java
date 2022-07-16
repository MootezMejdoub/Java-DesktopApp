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
public class PlanPlace {
    private int idPlan;
    private int idPlace;
    public static PreparedStatement pste;

    public int getIdPlan() {
        return idPlan;
    }

    public int getIdPlace() {
        return idPlace;
    }

    public static PreparedStatement getPste() {
        return pste;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

    public static void setPste(PreparedStatement pste) {
        PlanPlace.pste = pste;
    }

    public PlanPlace() {
    }

    public PlanPlace(int idPlan, int idPlace) {
        this.idPlan = idPlan;
        this.idPlace = idPlace;
    }

    @Override
    public String toString() {
        return "\nPlanPlace{" + "idPlan=" + idPlan + ", idPlace=" + idPlace +'}';
    }
    
    
}
