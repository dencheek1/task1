package by.vsu.lab.task4.dao;

import java.sql.SQLException;
import java.util.List;

import by.vsu.lab.task4.dao.daoimpl.DaoException;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.ExamResult;
import by.vsu.lab.task4.entitys.Examination;

public interface ExamResultDao {

    public ExamResult read(Applicant applicant, Examination exam) 
	    throws DaoException ;
    
    public void delete(ExamResult result)  throws DaoException ;
    
    public void update(ExamResult result)  throws DaoException ;
    
    public void create(ExamResult result)  throws DaoException ;
    
    public List<ExamResult> readByAplicant(Applicant applicant) 
	    throws DaoException ;

}
