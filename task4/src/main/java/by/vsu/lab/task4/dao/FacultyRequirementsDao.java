package by.vsu.lab.task4.dao;

import java.sql.SQLException;
import java.util.List;

import by.vsu.lab.task4.dao.daoimpl.DaoException;
import by.vsu.lab.task4.entitys.Examination;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.entitys.FacultyRequirement;

public interface FacultyRequirementsDao {

    public void create(FacultyRequirement requirements)  throws DaoException ;
    
    public void update(FacultyRequirement requirements)  throws DaoException ;
    
    public FacultyRequirement read(Faculty faculty, Examination exam)  throws DaoException ;
    
    public void delete(FacultyRequirement requirements) throws DaoException;
    
    public List<FacultyRequirement> readFacultyRequirements(Faculty faculty) 
	    throws DaoException ;
    
}
