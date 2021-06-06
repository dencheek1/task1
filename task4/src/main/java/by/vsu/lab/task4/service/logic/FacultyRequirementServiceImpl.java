package by.vsu.lab.task4.service.logic;

import java.util.List;

import by.vsu.lab.task4.dao.FacultyDao;
import by.vsu.lab.task4.dao.FacultyRequirementsDao;
import by.vsu.lab.task4.dao.daoimpl.DaoException;
import by.vsu.lab.task4.entitys.Examination;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.entitys.FacultyRequirement;
import by.vsu.lab.task4.service.FacultyRequirementsService;
import by.vsu.lab.task4.service.exception.ServiceException;

public class FacultyRequirementServiceImpl extends BaseService implements FacultyRequirementsService {

    private FacultyRequirementsDao facultyDao;
    
    @Override
    public FacultyRequirement read(Faculty faculty, Examination exam)  throws ServiceException{
	try {
	    return facultyDao.read(faculty, exam);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public List<FacultyRequirement> readAll(Faculty faculty)  throws ServiceException{
	try {
	 return facultyDao.readFacultyRequirements(faculty);   
	} catch (DaoException e) {
	    throw new ServiceException();
	}
    }

    @Override
    public void update(FacultyRequirement req)  throws ServiceException{
	try {
	    if(facultyDao.read(req.getFaculty(), req.getExam())!=null) {
		facultyDao.update(req);
	    }
	    
	}catch(DaoException e) {
	    throw new ServiceException();
	}

    }

    @Override
    public void update(FacultyRequirement prevreq, FacultyRequirement newreq) throws ServiceException{
	if(prevreq.getExam().equals(newreq.getExam()) && prevreq.getFaculty().getId()==newreq.getFaculty().getId()){
	    update(newreq);
	}
	else {
	    delete(prevreq);
	    create(newreq);
	}
    }
    
    @Override
    public void delete(FacultyRequirement req)  throws ServiceException{
	try {
	    facultyDao.delete(req);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}

    }

    @Override
    public void create(FacultyRequirement req)  throws ServiceException{
	try {
	    facultyDao.create(req);
	} catch(DaoException e) {
	    throw new ServiceException();
	}

    }

    public void setFacultyDao(FacultyRequirementsDao facultyDao) {
        this.facultyDao = facultyDao;
    }

    
    
}
