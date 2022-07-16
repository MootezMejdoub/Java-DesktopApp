/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Tox
 */
public interface IReclamation<T> {
    
    public  void ajouterReclamation(T p) ;
    public void deleteReclamation(String email);
    public List<T> afficherReclamation() throws SQLException;
}
    
