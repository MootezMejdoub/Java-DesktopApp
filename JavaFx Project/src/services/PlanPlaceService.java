/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import static entities.PlanPlace.pste;
import entities.PlanPlace;
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
public class PlanPlaceService implements IPlanPlace<PlanPlace> {
Connection cnx;
    Statement stm;
    public PlanPlaceService() {
                        cnx= MyDB.getInstance().getConnexion();

    }
   
    @Override
    public void create(PlanPlace planPlace) throws SQLException {
        String req = "INSERT INTO `plan_place` (`idplan`,`idplace`) VALUE (?,?)";
        try {
            pste = cnx.prepareStatement(req);
            pste.setInt( 1, planPlace.getIdPlan());
            pste.setDouble( 2, planPlace.getIdPlace());

            pste.executeUpdate();
            System.out.println("Creation PlanPlace");
        } catch (SQLException ex) {
            Logger.getLogger(PlanPlace.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public PlanPlace read(int idPlan,int idPlace) throws SQLException {
        String req = "SELECT * FROM `plan_place` WHERE idplan = ? AND idplace = ? ";
        PlanPlace p = new PlanPlace();
        try {
            pste = cnx.prepareStatement(req);
            pste.setInt(1, idPlan);
            pste.setInt(2, idPlace);
            ResultSet rs = pste.executeQuery();

            while (rs.next()) {
                p.setIdPlan(rs.getInt("idPlan"));
                p.setIdPlace(rs.getInt("idPlace"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlanPlace.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;    }

    @Override
    public List<PlanPlace> readAll() throws SQLException {
        
        List<PlanPlace> planPlaces = new ArrayList<>();
        String req = "SELECT * FROM `plan_place` ";

        try {
            pste = cnx.prepareStatement(req);
            ResultSet rs = pste.executeQuery();

            while (rs.next()) {
                PlanPlace p = new PlanPlace();
                p.setIdPlan(rs.getInt("idPlan"));
                p.setIdPlace(rs.getInt("idPlace"));
                planPlaces.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlanPlace.class.getName()).log(Level.SEVERE, null, ex);
        }

        return planPlaces;
    }

    @Override
    public void update(PlanPlace OLD_planPlace, PlanPlace NEW_planPlace) throws SQLException {
        String req = "UPDATE  `plan_place` SET `idplan` = ?, `idplace` = ? WHERE idplan = ? AND idplace = ?";
        try {
            pste = cnx.prepareStatement(req);

            pste.setInt( 1, NEW_planPlace.getIdPlan());
            pste.setInt( 2, NEW_planPlace.getIdPlace());
            pste.setInt( 3, OLD_planPlace.getIdPlan());
            pste.setInt( 4, OLD_planPlace.getIdPlace());
            pste.executeUpdate();
            System.out.println("\n\nUPDATE PlanPlace");
        } catch (SQLException ex) {
            Logger.getLogger(PlanPlace.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void delete(PlanPlace planPlace) throws SQLException {
        String req = "DELETE FROM `plan_place` WHERE idplan = ? AND idplace = ?";
        try {
            pste = cnx.prepareStatement(req);
            pste.setInt( 1, planPlace.getIdPlan());
            pste.setInt( 2, planPlace.getIdPlace());
            pste.executeUpdate();
            System.out.println("\n\nDELETE PlanPlace");
        } catch (SQLException ex) {
            Logger.getLogger(PlanPlace.class.getName()).log(Level.SEVERE, null, ex);
        }    }

}
