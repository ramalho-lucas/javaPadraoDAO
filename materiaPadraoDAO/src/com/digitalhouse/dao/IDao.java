package com.digitalhouse.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDao<T> {
    public T salvar(T t) throws SQLException, ClassNotFoundException;
    public List<T> buscarTodos() throws SQLException, ClassNotFoundException;
}
