package by.vsu.lab.task4.service;

import java.sql.SQLException;
import java.util.List;

import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.ExamResult;
import by.vsu.lab.task4.entitys.Examination;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.service.exception.ServiceException;

public interface ApplicantService {

    public Applicant findById(Long id)  throws ServiceException;
    
    public List<Applicant> findAll()  throws ServiceException;
    
    public List<Applicant> findByFaculty(Long facultyId)  throws ServiceException;
    
    public List<ExamResult> findResults(Applicant applicant)  throws ServiceException;
    
    public ExamResult findResult(Applicant applicant, Examination examination)
	    throws ServiceException;
    
    public void create(Applicant applicant) throws ServiceException;
     
    public void update(Applicant applicant) throws ServiceException;
    
    public void delete(Applicant applicant) throws ServiceException;
    
    
}
