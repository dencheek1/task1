package by.vsu.lab.task4.dao;

import by.vsu.lab.task4.dao.daoimpl.DaoException;

public interface Dao<T> {
    
    void update(T entity)  throws DaoException ;
    
    void delete(Long id)  throws DaoException ;
    
    T read(Long id)  throws DaoException ;
    
    Long create(T entity)  throws DaoException ;
}
