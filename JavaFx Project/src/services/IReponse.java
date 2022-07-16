/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Tox
 */
public interface IReponse <T>{
    public  void ajouterReponse(T p) ;
    public void deleteReponse(String email);
    public List<T> afficherReponse() ;
    public  void updateReponse(String email,T u);
}
