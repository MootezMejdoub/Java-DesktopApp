/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.PlanImage;
import static entities.PlanImage.pste;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyDB;

/**
 *
 * @author bahe
 */
 
public class PlanImageService  implements IPlanImage<PlanImage> {
Connection cnx;
    Statement stm;
    public PlanImageService() {
                cnx= MyDB.getInstance().getConnexion();

    }

    @Override
    public void create(PlanImage planImage) throws SQLException {
        String req = "INSERT INTO `plan_image` (`idplan`,`path`) VALUE (?,?)";
        try {
            pste = cnx.prepareStatement(req);
            pste.setInt( 1, planImage.getIdPlan());
            pste.setString( 2, planImage.getPath());
            pste.executeUpdate();
            System.out.println("Creation PlanImage");
        } catch (SQLException ex) {
            Logger.getLogger(PlanImage.class.getName()).log(Level.SEVERE, null, ex);
        }        }

    @Override
    public PlanImage read(int id, int idPlan) throws SQLException {
        String req = "SELECT * FROM `plan_image` WHERE id = ? AND idplan = ? ";
        PlanImage p = new PlanImage();
        try {
            pste = cnx.prepareStatement(req);
            pste.setInt(1, id);
            pste.setInt(2, idPlan);
            ResultSet rs = pste.executeQuery();

            while (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setIdPlan(rs.getInt("idPlan"));
                p.setPath(rs.getString("path"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlanImage.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }

    @Override
    public List<PlanImage> readAll() throws SQLException {
        
        List<PlanImage> planImages = new ArrayList<>();
        String req = "SELECT * FROM `plan_image` ";

        try {
            pste = cnx.prepareStatement(req);
            ResultSet rs = pste.executeQuery();

            while (rs.next()) {
                PlanImage p = new PlanImage();
                p.setId(rs.getInt("id"));
                p.setIdPlan(rs.getInt("idPlan"));
                p.setPath(rs.getString("path"));
                planImages.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlanImage.class.getName()).log(Level.SEVERE, null, ex);
        }

        return planImages;    }

    @Override
    public List<PlanImage> readPerPlan(int idPlan) throws SQLException {
        
        List<PlanImage> planImages = new ArrayList<>();
        String req = "SELECT * FROM `plan_image` WHERE idPlan = ? ";

        try {
            pste = cnx.prepareStatement(req);
            pste.setInt(1, idPlan);
            ResultSet rs = pste.executeQuery();

            while (rs.next()) {
                PlanImage p = new PlanImage();
                p.setId(rs.getInt("id"));
                p.setIdPlan(rs.getInt("idPlan"));
                p.setPath(rs.getString("path"));
                planImages.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlanImage.class.getName()).log(Level.SEVERE, null, ex);
        }

        return planImages;       }

    @Override
    public void update(PlanImage OLD_planImage, PlanImage NEW_planImage) throws SQLException {
        String req = "UPDATE  `plan_image` SET `id` = ?, `idplan` = ?, `path` = ? WHERE id = ? AND idplan = ?";
        try {
            pste = cnx.prepareStatement(req);

            pste.setInt( 1, NEW_planImage.getId());
            pste.setInt( 2, NEW_planImage.getIdPlan());
            pste.setString( 3, NEW_planImage.getPath());
            pste.setInt( 4, OLD_planImage.getId());
            pste.setInt( 5, OLD_planImage.getIdPlan());
            pste.executeUpdate();
            System.out.println("\n\nUPDATE PlanImage");
        } catch (SQLException ex) {
            Logger.getLogger(PlanImage.class.getName()).log(Level.SEVERE, null, ex);
        }        }

    @Override
    public void delete(PlanImage planImage) throws SQLException {
        String req = "DELETE FROM `plan_image` WHERE id = ? AND idplan = ?";
        try {
            pste = cnx.prepareStatement(req);
            pste.setInt( 1, planImage.getId());
            pste.setInt( 2, planImage.getIdPlan());
            pste.executeUpdate();
            System.out.println("\n\nDELETE PlanImage");
        } catch (SQLException ex) {
            Logger.getLogger(PlanImage.class.getName()).log(Level.SEVERE, null, ex);
        }       }

    @Override
    public PlanImage readLastPlanImageAdded() throws SQLException {
        String req = "SELECT * FROM `plan_image`  ORDER BY ID";
        PlanImage p = new PlanImage();
        try {
            pste = cnx.prepareStatement(req);
            ResultSet rs = pste.executeQuery();

            while (rs.next()) {
                p.setId(rs.getInt("id"));
                p.setIdPlan(rs.getInt("idPlan"));
                p.setPath(rs.getString("path"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlanImage.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }
    
}
