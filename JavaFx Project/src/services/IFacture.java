/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Facture;
import java.util.List;

/**
 *
 * @author mbell
 */
public interface IFacture<T> {

    public void ajouterFacture(Facture f);

    public void rechercheFacture(int idFacture);

    public void modifierFacture(Facture f );

    public void supprimerFacture(int idFacture);
    
     List <Facture> afficherAll(); 
    
    

}
