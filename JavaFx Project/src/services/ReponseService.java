/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import utils.MyDB;
import entities.Reclamation;
import entities.Reponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tox
 */
public class ReponseService implements IReponse<Reponse> {
Connection connexion;
Statement stm;

    public ReponseService() {
        connexion=MyDB.getInstance().getConnexion();
    }

LocalDate actuelle = LocalDate.now();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = actuelle.format(formatter);
        String dc = date;
    @Override
    public void ajouterReponse(Reponse p) {
//        LocalDate actuelle = LocalDate.now();
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String date = actuelle.format(formatter);
//        String dc = date;

          
          try {
              String req = "INSERT INTO reponse (user_id,rec_reference,datecreation,message) VALUES ( '"
               + p.getUser_id()+"','"+ p.getRec_ref()+ "', '" +dc+ "', '" +p.getMessage()+ "') ";
              stm = connexion.createStatement();
              stm.executeUpdate(req);
              System.out.println("Bien ajoutee");
              
          } catch (SQLException ex) {
              System.err.println(ex.getMessage());
              
          }
    }

    @Override
    public void deleteReponse(String rec_reference) {
         try {
            String req = "DELETE FROM reponse where rec_reference = ?";

            PreparedStatement pst = connexion.prepareStatement(req);

            pst.setString(1, rec_reference);
            pst.executeUpdate();
            System.out.println("suppression effectuée");
            //delete=true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public List<Reponse> afficherReponse() {
        try {
            ArrayList<Reponse> reponse = new ArrayList<>();
            String req = "select * from  reponse";
            stm = connexion.createStatement();
            //ensemble de resultat
            ResultSet rst = stm.executeQuery(req);

            while (rst.next()) {
                Reponse r = new Reponse();
                r.setId(rst.getInt("id"));
                r.setMessage(rst.getString("message"));
                r.setRec_ref(rst.getString("rec_reference"));
                r.setDatecreation(rst.getDate("datecreation"));
                r.setUser_id(rst.getInt("user_id"));
                reponse.add(r);
            }
            return reponse;
        } catch (SQLException ex) {
            return null;
        }
    }


    @Override
   
    public void updateReponse(String rec_reference, Reponse u) {
          PreparedStatement select;
        PreparedStatement update;
        ResultSet resultat;

        try {
            select = connexion.prepareStatement("select * from reponse where rec_reference='" + rec_reference + "'");

            resultat = select.executeQuery();
            if (resultat.isBeforeFirst()) {
                update = connexion.prepareStatement("update reponse set user_id = ? , datecreation = ? , rec_reference= ? ,message= ? where rec_reference = ?");

                    update.setInt(1, u.getUser_id());
                update.setString(2,dc);
                update.setString(3, u.getRec_ref());
                update.setString(4, u.getMessage());
                update.setString(5, rec_reference);
                update.executeUpdate();

                System.out.println("modification effectuée");

            } else {
                System.err.println("non effectuée");
            }

        } catch (SQLException ex) {
            System.err.println("erreur de modification");
        }

    }
    
}
