/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Reservation;
import java.util.List;
/**
 *
 * @author mbell
 */
public interface Iservice<T> {
    
    void ajouter(T entity);
    void modifier(T entity);
    void supprimer(int idClient);
    List<T> afficherAll();
    void afficherById(int idClient);
    
}
