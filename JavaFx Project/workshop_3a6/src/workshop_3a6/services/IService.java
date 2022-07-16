/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package workshop_3a6.services;

import java.util.List;

/**
 *
 * @author remo
 */
public interface IService<T> {
    void ajouter(T entity);
    void modifier(T entity);
    void supprimer(T entity);
    List<T> afficher();
}
