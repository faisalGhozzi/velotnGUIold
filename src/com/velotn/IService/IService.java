package com.velotn.IService;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    void ajouter(T t) throws SQLException;
    boolean delete(T t) throws SQLException;
    boolean update(T t) throws SQLException;
    List<T> readAll() throws SQLException;
    T search(T t) throws SQLException;
    List<T> sortByPrice() throws SQLException;
}
