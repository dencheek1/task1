package by.vsu.lab.task4.service.logic;

import java.sql.SQLException;
import java.util.List;

import by.vsu.lab.task4.dao.ApplicantDao;
import by.vsu.lab.task4.dao.ExamResultDao;
import by.vsu.lab.task4.dao.daoimpl.DaoException;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.ExamResult;
import by.vsu.lab.task4.entitys.Examination;
import by.vsu.lab.task4.service.ApplicantService;
import by.vsu.lab.task4.service.exception.ServiceException;

public class ApplicantServiceImpl extends BaseService implements ApplicantService {

    private ApplicantDao applicantDao;
    private ExamResultDao resultDao;
    
    @Override
    public Applicant findById(Long id) throws ServiceException {
	try {
	    
	    return applicantDao.read(id);
	}catch( DaoException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public List<Applicant> findAll() throws ServiceException {
	try {
	    return applicantDao.readAll();
	} catch(DaoException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public List<ExamResult> findResults(Applicant applicant) throws ServiceException{
	try {
	    return resultDao.readByAplicant(applicant);
	}catch(DaoException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public ExamResult findResult(Applicant applicant, Examination examination) throws ServiceException {
	try {
	    return resultDao.read(applicant, examination);
	}catch(DaoException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public void update(Applicant applicant) {
		
	try {
	    if(applicantDao.read(applicant.getId()) != null) {
			applicantDao.update(applicant);
		}
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }
    
    @Override
    public List<Applicant> findByFaculty(Long facultyId) throws ServiceException {
	try {
	    return applicantDao.readByFacultetId(facultyId);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public void delete(Applicant applicant) {
	try {
	    applicantDao.delete(applicant.getId());
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }
    

    public void setAplicantDao(ApplicantDao aplicantDao) {
        this.applicantDao = aplicantDao;
    }

    public void setResultDao(ExamResultDao resultDao) {
        this.resultDao = resultDao;
    }

    @Override
    public void create(Applicant applicant) throws ServiceException {
	try {
	    applicantDao.create(applicant);
	} catch (DaoException e) {
	    throw new ServiceException();
	}
	
    }   

}
