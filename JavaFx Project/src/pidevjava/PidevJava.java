/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;

import entities.Client;
import entities.CryptWithMD5;
import entities.Guide;



import entities.Utilisateur;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import services.UserService;

/**
 *
 * @author macbook
 */
public class PidevJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
      /*  Guide p = new Guide("cherif","amel","26-08-1967","manouba","52295267","amelcherif@live.fr","xxx","simpleUser","description",5);
         Guide p2 = new Guide(9,"ayaaa","warta","04-04-2000","manoupa","56035478","aya.ouerrrr","xxx11","admin","good",4);
         Guide p3 = new Guide(2,"ayaaa","warta","04-04-2000","manoupa","56035478","aya.ouerrrr","xxx11","admin","good",4);

Guide p4 = new Guide(10,"aya","warta","04-04-2000","manoupaaaaaa","56035477//","aya.ouer@esprit.tn","zzz","touriste","desc",3);
Client c= new Client("nour","cherif","26-09-1997","nabeul","25111111","123","bdiehzbdoahnof","touriste");
        Client c2= new Client(42,"mootez","mejdoub","26-09-1997","nabeul","25111111","mootez.mejdoub@esprit.tn","zzz","touriste");*/

UserService sp = new UserService();

        //System.out.println(CryptWithMD5.cryptWithMD5("khkdjhf"));

        
       /* try {
            sp.ajouterGuide(p);
            System.out.println("ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
       try {
            System.out.println(sp.afficherUser());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        try {
            sp.modifierGuidePst(p4);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
         try {
            sp.suppGuidePst(p2);
            System.out.println("suppression avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         try {
            System.out.println(sp.readAll());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
        
       
        //sp.ajouterClient(c);   //ajout client
        /* System.out.println(sp.afficherClient());  //affichage des clients
         sp.modifierClientPst(c2);    
        sp.suppClientPst(c2);
        */
         
         
      // System.out.println(sp.encrypt("bdiehzbdoahnof"));
      // System.out.println(sp.decrypt("RTYUXJRT_QX^_V"));
        //System.out.println(sp.encrypt("191JFT1082"));
        
        
       
    }
    
}
