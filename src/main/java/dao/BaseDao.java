package dao;

import exception.DaoException;

import java.io.Serializable;
import java.util.List;

public interface BaseDao <T, ID extends Serializable> {

    void save(T entity) throws DaoException;

    T findById(ID id) throws DaoException;

    void update(T entity) throws DaoException;

    void delete(T entity) throws DaoException;

    List<T> findAll() throws DaoException;
}
