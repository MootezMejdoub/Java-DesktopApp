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
public interface IPlace<T> {
    public void    create(T place) throws SQLException;
    public T       read(int id)   throws SQLException;
    public List<T> readAll()      throws SQLException;
    public void    update(T place) throws SQLException;
    public void    delete(int id) throws SQLException;
}
