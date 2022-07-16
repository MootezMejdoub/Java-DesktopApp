/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Client;
import entities.Guide;
import entities.Utilisateur;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author macbook
 */
public interface IUser<T> {
    public void ajouterGuide(Guide p) ;
    public List<Guide> afficherGuide() throws SQLException;
    public void modifierGuidePst(Guide u) throws SQLException;
    public void suppGuidePst(Guide u) throws SQLException;
     public void ajouterClient(Client p) ;
      public List<Client> afficherClient() ;
    public void modifierClientPst(Client u) ;
    public void suppClientPst(Client u) ;
    public boolean login(String username,String password);
     public String encrypt(String password);
     public String decrypt(String password);
      public List<String> afficherMdp(String username);
       public List<Client> afficherTout();
    
    
    
}
