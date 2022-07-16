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
public interface IPlan<T>{
    public void    create(T plan)           throws SQLException;
    public T       read(int id)             throws SQLException;
    public T       readLastPlanAdded()      throws SQLException;
    public List<T> readAll()                throws SQLException;
    public List<T> readPerGuide(int idGuide)throws SQLException;
    public void    update(T plan)           throws SQLException;
    public void    delete(int id)           throws SQLException;
}
