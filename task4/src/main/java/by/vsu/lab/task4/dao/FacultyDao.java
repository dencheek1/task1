package by.vsu.lab.task4.dao;

import java.util.List;

import by.vsu.lab.task4.dao.daoimpl.DaoException;
import by.vsu.lab.task4.entitys.Faculty;

public interface FacultyDao extends Dao<Faculty> {
    
    public List<Faculty> readAll()  throws DaoException ;
    
}
