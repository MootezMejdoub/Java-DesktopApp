///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package pidevvvv;
//
//import DbConnection.MyDB;
//import entities.Reclamation;
//import entities.Reponse;
//import entities.Utilisateur;
//import java.sql.Date;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import services.ReclamationService;
//import services.ReponseService;
//
///**
// *
// * @author Tox
// */
//public class PiDevvvv {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        MyDB.getConnexion();
//        
//            
//            Utilisateur user=new  Utilisateur("aya", "warta", "aya.ouer@esprit.tn");
//            Reclamation rec = new Reclamation(user.getNom(), user.getPrenom(), user.getEmail(), "k nami");
//            Reclamation reclamation =new Reclamation("toxic", "toxic","mootezmejdoub@gmail.com","plan 0000 ");
//            ReclamationService rs= new ReclamationService();
//            Reclamation rec =new Reclamation("hama", "samir","hahahaha@gmail.com","site  0");
//            rs.ajouterReclamation(rec); 
//            rs.ajouterReclamation(rr);
//            ArrayList<Reclamation>aa=new ArrayList<>();
//            aa=rs.afficherReclamationzzzzz();
//            for(Reclamation r:aa)
//            {
//                System.out.println(r.toString());
//            }
//            
//            /////////reponse
//            Reponse rep=new Reponse("toxic bhim  barcha", 1, "pyiSVQyi");
//            Reponse rep2= new Reponse("jawha bahy tgadit", "pyiSVQyi");
//             Reponse rep1=new Reponse("toxic jawo bahy  barcha", 1, "sXGVzxlg");
//            ReponseService reps=new ReponseService();
//            reps.ajouterReponse(rep2);
//            
//            
//            //affichage reponse
//            ArrayList<Reponse> reparraylist=new ArrayList<>();
//            reparraylist=(ArrayList<Reponse>) reps.afficherReponse();
//       for (Reponse repns:reparraylist)
//       {
//           System.out.println(repns.toString());
//           
//       }
//            //delete reponse
//           reps.deleteReponse("sXGVzxlg"); 
//       reps.updateReponse("tSBmi7jp", rep);
//     
//         
//       ArrayList<Reclamation> rc=new ArrayList<>();
//       rc=(ArrayList<Reclamation>) rs.rechercheReclamationsEmail("aya.ouer@esprit.tn");
//       for (Reclamation re:rc)
//       {
//           System.out.println(re.toString());
//           
//       
//       rs.deleteReclamation("hahahaha@gmail.com");
//       
//      
//       
//       rs.updateReclamation("mootez@gmail.com",rec);
//      
//
//     
//        
//        
//        
//        
//    }
//    
//}
