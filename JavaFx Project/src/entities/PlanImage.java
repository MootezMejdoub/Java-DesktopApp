/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.PreparedStatement;

/**
 *
 * @author bahe
 */
public class PlanImage {
    private int id;
    private int idPlan;
    private String path;
    public static PreparedStatement pste;

    public int getId() {
        return id;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public String getPath() {
        return path;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public PlanImage() {
    }

    public PlanImage(int id) {
        this.id = id;
    }

    public PlanImage(int id, int idPlan) {
        this.id = id;
        this.idPlan = idPlan;
    }
    public PlanImage(int idPlan, String path) {
        this.idPlan = idPlan;
        this.path = path;
    }
    public PlanImage(String path) {
        this.path = path;
    }
    public PlanImage(int id, int idPlan, String path) {
        this.id = id;
        this.idPlan = idPlan;
        this.path = path;
    }

    @Override
    public String toString() {
        return "PlanImage{" + "id=" + id + ", idPlan=" + idPlan + ", path=" + path + '}';
    }
    
}
