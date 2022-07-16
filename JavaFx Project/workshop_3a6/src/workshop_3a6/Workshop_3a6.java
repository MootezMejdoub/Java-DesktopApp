/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package workshop_3a6;

import workshop_3a6.entities.Personne;
import workshop_3a6.services.PersonneService;
import workshop_3a6.utilis.Datasource;

/**
 *
 * @author remo
 */
public class Workshop_3a6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PersonneService ps = new PersonneService();
        Personne p = new Personne("test", "foulen");
//        ps.ajouter2(p);

        System.out.println(ps.afficher());
        
    }
    
}
