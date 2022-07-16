/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package workshop_3a6.services;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import workshop_3a6.entities.Personne;
import workshop_3a6.utilis.Datasource;
/**
 *
 * @author remo
 */
public class PersonneService implements IService<Personne>{
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    public PersonneService() {
        conn = Datasource.getInstance().getCnx();
    }

    @Override
    public void ajouter(Personne p) {
        String req = "INSERT INTO `personne` (`nom`,`prenom`) VALUE ('" + p.getNom() + "','" + p.getPrenom() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("personne créée");
        } catch (SQLException ex) {
            Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ajouter2(Personne p) {
        String req = "INSERT INTO `personne` (`nom`,`prenom`) VALUE (?,?)";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1, p.getNom());
            pste.setString(2, p.getPrenom());
            pste.executeUpdate();
            System.out.println("personne créée");
        } catch (SQLException ex) {
            Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(Personne p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void supprimer(Personne p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Personne> afficher() {
        List<Personne> personnes = new ArrayList<>();
        String req = "SELECT * FROM `personne`";
        
        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();
            
            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);
            
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString(2));
                p.setPrenom(rs.getString(3));
                personnes.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return personnes;
    }
    
}
