/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Plan;
import static entities.Plan.pste;
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
public class PlanService implements IPlan<Plan>  {
Connection cnx;
    Statement stm;
    public PlanService() {
                        cnx= MyDB.getInstance().getConnexion();

    }

    @Override
    public void create(Plan plan) throws SQLException {
        String req = "INSERT INTO `plan` (`idGuide`,`prix`,`note`,`titre`,`description`,`nmbrPlacesMax`,`nmbrPlacesReste`,`dateDebut`,`dateFin`,`pointDepart`) VALUE (?,?,?,?,?,?,?,?,?,?)";
        try {
            pste = cnx.prepareStatement(req);
            pste.setInt(    1, plan.getIdGuide());
            pste.setDouble( 2, plan.getPrix());
            pste.setDouble( 3, plan.getNote());
            pste.setString( 4, plan.getTitre());
            pste.setString( 5, plan.getDescription());
            pste.setInt(    6, plan.getNmbrPlacesMax());
            pste.setInt(    7, plan.getNmbrPlacesReste());
            pste.setString( 8, plan.getDateDebut());
            pste.setString( 9, plan.getDateFin());
            pste.setString(10, plan.getPointDepart());

            pste.executeUpdate();
            System.out.println("Creation Plan");
        } catch (SQLException ex) {
            Logger.getLogger(Plan.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public Plan read(int id) throws SQLException {
        String req = "SELECT * FROM `plan` WHERE id = ? ";
        Plan p = new Plan();
        try {
            pste = cnx.prepareStatement(req);
            pste.setInt(1, id);
            ResultSet rs = pste.executeQuery();

            while (rs.next()) {

                p.setId(rs.getInt("id"));
                p.setIdGuide(rs.getInt("idGuide"));
                p.setPrix(rs.getDouble("prix"));
                p.setNote(rs.getDouble("note"));
                p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
                p.setNmbrPlacesMax(rs.getInt("nmbrPlacesMax"));
                p.setNmbrPlacesReste(rs.getInt("nmbrPlacesReste"));
                p.setDateDebut(rs.getString("dateDebut"));
                p.setDateFin(rs.getString("dateFin"));
                p.setPointDepart(rs.getString("pointDepart"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Plan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;    }

    @Override
    public List<Plan> readAll() throws SQLException {
        
        List<Plan> Plans = new ArrayList<>();
        String req = "SELECT * FROM `plan` ";

        try {
            pste = cnx.prepareStatement(req);
            ResultSet rs = pste.executeQuery();

            while (rs.next()) {
                Plan p = new Plan();
                p.setId(rs.getInt("id"));
                p.setIdGuide(rs.getInt("idGuide"));
                p.setPrix(rs.getDouble("prix"));
                p.setNote(rs.getDouble("note"));
                p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
                p.setNmbrPlacesMax(rs.getInt("nmbrPlacesMax"));
                p.setNmbrPlacesReste(rs.getInt("nmbrPlacesReste"));
                p.setDateDebut(rs.getString("dateDebut"));
                p.setDateFin(rs.getString("dateFin"));
                p.setPointDepart(rs.getString("pointDepart"));
                Plans.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Plan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Plans;
    }

        @Override
    public List<Plan> readPerGuide(int idGuide) throws SQLException {
        
        List<Plan> Plans = new ArrayList<>();
        String req = "SELECT * FROM `plan` WHERE `idGuide` = ?";

        try {
            pste = cnx.prepareStatement(req);
            pste.setInt(1, idGuide);
            ResultSet rs = pste.executeQuery();

            while (rs.next()) {
                Plan p = new Plan();
                p.setId(rs.getInt("id"));
                p.setIdGuide(rs.getInt("idGuide"));
                p.setPrix(rs.getDouble("prix"));
                p.setNote(rs.getDouble("note"));
                p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
                p.setNmbrPlacesMax(rs.getInt("nmbrPlacesMax"));
                p.setNmbrPlacesReste(rs.getInt("nmbrPlacesReste"));
                p.setDateDebut(rs.getString("dateDebut"));
                p.setDateFin(rs.getString("dateFin"));
                p.setPointDepart(rs.getString("pointDepart"));
                Plans.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Plan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Plans;
    }
    
    @Override
    public void update(Plan plan) throws SQLException {
        String req = "UPDATE  `plan` SET `idGuide` = ?, `prix` = ?, `note` = ?,`titre` = ?,`description` = ?,`nmbrPlacesMax` = ?,`nmbrPlacesReste` = ?,`dateDebut` = ?,`dateFin` = ?,`pointDepart` = ? WHERE id = ?";
        try {
            pste = cnx.prepareStatement(req);

            pste.setInt(    1, plan.getIdGuide());
            pste.setDouble( 2, plan.getPrix());
            pste.setDouble( 3, plan.getNote());
            pste.setString( 4, plan.getTitre());
            pste.setString( 5, plan.getDescription());
            pste.setInt(    6, plan.getNmbrPlacesMax());
            pste.setInt(    7, plan.getNmbrPlacesReste());
            pste.setString( 8, plan.getDateDebut());
            pste.setString( 9, plan.getDateFin());
            pste.setString(10, plan.getPointDepart());
            pste.setInt(   11, plan.getId());
            pste.executeUpdate();
            System.out.println("\n\nUPDATE Plan");
        } catch (SQLException ex) {
            Logger.getLogger(Plan.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM `plan` WHERE id = ?";
        try {
            pste = cnx.prepareStatement(req);
            pste.setString(1, Integer.toString(id));
            pste.executeUpdate();
            System.out.println("\n\nDELETE Plan");
        } catch (SQLException ex) {
            Logger.getLogger(Plan.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public Plan readLastPlanAdded() throws SQLException {
        String req = "SELECT * FROM plan ORDER BY ID";
        Plan p = new Plan();
        try {
            pste = cnx.prepareStatement(req);
            ResultSet rs = pste.executeQuery();

            while (rs.next()) {

                p.setId(rs.getInt("id"));
                p.setIdGuide(rs.getInt("idGuide"));
                p.setPrix(rs.getDouble("prix"));
                p.setNote(rs.getDouble("note"));
                p.setTitre(rs.getString("titre"));
                p.setDescription(rs.getString("description"));
                p.setNmbrPlacesMax(rs.getInt("nmbrPlacesMax"));
                p.setNmbrPlacesReste(rs.getInt("nmbrPlacesReste"));
                p.setDateDebut(rs.getString("dateDebut"));
                p.setDateFin(rs.getString("dateFin"));
                p.setPointDepart(rs.getString("pointDepart"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Plan.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;    
    }

    
}
