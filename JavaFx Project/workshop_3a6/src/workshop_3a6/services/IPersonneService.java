/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package workshop_3a6.services;
import workshop_3a6.entities.Personne;
import java.util.List;
/**
 *
 * @author remo
 */
public interface IPersonneService {
    void ajouter(Personne p);
    void modifier(Personne p);
    void supprimer(Personne p);
    List<Personne> afficher();
}
