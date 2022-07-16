/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Place;
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
public class PlaceService implements IPlace<Place> {
Connection cnx;
    Statement stm;
    public PlaceService() {
                        cnx= MyDB.getInstance().getConnexion();

    }
   
    @Override
    public void create(Place place) throws SQLException {
        String req = "INSERT INTO `place` (`nom`,`note`,`address`,`description`,`type`) VALUE (?,?,?,?,?)";
        try {
            pste = MyDB.getInstance().getConnexion().prepareStatement(req);
            pste.setString( 1, place.getNom());
            pste.setDouble( 2, place.getNote());
            pste.setString( 3, place.getAddress());
            pste.setString( 4, place.getDescription());
            pste.setString( 5, place.getType());

            pste.executeUpdate();
            System.out.println("Creation Place");
        } catch (SQLException ex) {
            Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public Place read(int id) throws SQLException {
        String req = "SELECT * FROM `place` WHERE id = ? ";
        Place p = new Place();
        try {
            pste = cnx.prepareStatement(req);
            pste.setInt(1, id);
            ResultSet rs = pste.executeQuery();

            while (rs.next()) {

                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setNote(rs.getDouble("note"));
                p.setAddress(rs.getString("address"));
                p.setDescription(rs.getString("description"));
                p.setType(rs.getString("type"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;    }

    @Override
    public List<Place> readAll() throws SQLException {
        
        List<Place> Places = new ArrayList<>();
        String req = "SELECT * FROM `place` ";

        try {
            pste = cnx.prepareStatement(req);
            ResultSet rs = pste.executeQuery();

            while (rs.next()) {
                Place p = new Place();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setNote(rs.getDouble("note"));
                p.setAddress(rs.getString("address"));
                p.setDescription(rs.getString("description"));
                p.setType(rs.getString("type"));
                Places.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Places;
    }

    @Override
    public void update(Place place) throws SQLException {
        String req = "UPDATE  `place` SET `nom` = ?, `note` = ?, `address` = ?, `description` = ?,`type` = ? WHERE id = ?";
        try {
            pste = cnx.prepareStatement(req);

            pste.setString( 1, place.getNom());
            pste.setDouble( 2, place.getNote());
            pste.setString( 3, place.getAddress());
            pste.setString( 4, place.getDescription());
            pste.setString( 5, place.getType());
            pste.setInt(    6, place.getId());
            pste.executeUpdate();
            System.out.println("\n\nUPDATE Place");
        } catch (SQLException ex) {
            Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void delete(int id) throws SQLException {
        String req = "DELETE FROM `place` WHERE id = ?";
        try {
            pste = cnx.prepareStatement(req);
            pste.setString(1, Integer.toString(id));
            pste.executeUpdate();
            System.out.println("\n\nDELETE Plan");
        } catch (SQLException ex) {
            Logger.getLogger(Place.class.getName()).log(Level.SEVERE, null, ex);
        }    }

}
