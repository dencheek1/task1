package by.vsu.lab.task4.dao;


import java.util.List;

import by.vsu.lab.task4.dao.daoimpl.DaoException;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.ExamResult;
import by.vsu.lab.task4.entitys.Examination;

public interface ApplicantDao extends Dao<Applicant> {
    
    public List<Applicant> readAll()  throws DaoException ;
        
    public List<Applicant> readByFacultetId(long id)  throws DaoException ;
    
    //public List<ExamResult> readResultsOfAplicant(Applicant aplicant);
    
    //public ExamResult readResult(Applicant aplicant, Examination examination);
    
}
