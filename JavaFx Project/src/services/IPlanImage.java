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
 * @author bahe
 */
public interface IPlanImage<T> {
    public void    create(T planImage) throws SQLException;
    public T       read(int id, int idPlan)   throws SQLException;
    public T       readLastPlanImageAdded()   throws SQLException;
    public List<T> readAll()      throws SQLException;
    public List<T> readPerPlan(int idPlan)      throws SQLException;
    public void    update(T OLD_planImage, T NEW_planImage) throws SQLException;
    public void    delete(T planImage) throws SQLException;
}
