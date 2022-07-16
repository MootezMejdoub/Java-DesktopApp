/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author bahe
 */
public interface IPlanPlace<T> {
    public void    create(T planPlace) throws SQLException;
    public T       read(int idPlan, int idPlace)   throws SQLException;
    public List<T> readAll()      throws SQLException;
    public void    update(T OLD_planPlace, T NEW_planPlace) throws SQLException;
    public void    delete(T planPlace) throws SQLException;
}
