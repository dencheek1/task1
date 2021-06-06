package by.vsu.lab.task4.service;

import java.sql.SQLException;
import java.util.List;

import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.entitys.FacultyRequirement;
import by.vsu.lab.task4.service.exception.ServiceException;

public interface FacultyService {
    
    public Faculty find(Long id) throws ServiceException ;
    public List<Faculty> findAll() throws ServiceException;
    public List<FacultyRequirement> getRequirements(Faculty faculty) throws ServiceException, SQLException;  
    public List<Applicant> selectAplicants(List<Applicant> applicants, Faculty faculty) throws ServiceException;
    
    public void update(Faculty faculty) throws ServiceException;
    public void delete(Faculty faculty) throws ServiceException;
    //public int countAplicantResult(Applicant aplicant);
}
