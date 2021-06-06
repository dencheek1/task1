package by.vsu.lab.task4.service;

import java.util.List;

import by.vsu.lab.task4.entitys.Examination;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.entitys.FacultyRequirement;
import by.vsu.lab.task4.service.exception.ServiceException;

public interface FacultyRequirementsService {

    public FacultyRequirement read(Faculty faculty, Examination exam) throws ServiceException;
    public List<FacultyRequirement> readAll(Faculty faculty) throws ServiceException;
    public void update(FacultyRequirement req) throws ServiceException;
    public void update(FacultyRequirement prevreq, FacultyRequirement newreq) throws ServiceException;
    public void delete(FacultyRequirement req) throws ServiceException;
    public void create(FacultyRequirement req) throws ServiceException;
    
}
