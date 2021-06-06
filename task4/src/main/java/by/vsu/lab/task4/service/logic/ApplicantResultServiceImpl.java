package by.vsu.lab.task4.service.logic;

import java.util.List;

import by.vsu.lab.task4.dao.ExamResultDao;
import by.vsu.lab.task4.dao.daoimpl.DaoException;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.ExamResult;
import by.vsu.lab.task4.entitys.Examination;
import by.vsu.lab.task4.service.ApplicantResultService;
import by.vsu.lab.task4.service.exception.ServiceException;

public class ApplicantResultServiceImpl implements ApplicantResultService {

    private ExamResultDao resultDao;

    
    
    public void setResultDao(ExamResultDao resultDao) {
        this.resultDao = resultDao;
    }

    @Override
    public void create(ExamResult result) throws ServiceException {
	try {
	    resultDao.create(result);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	    // TODO: handle exception
	}
	
    }

    @Override
    public void update(ExamResult result) throws ServiceException {
	try {
	    resultDao.update(result);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	    // TODO: handle exception
	}
    }

    
    
    @Override
    public void update(ExamResult oldResult, ExamResult newResult) throws ServiceException {
	if(oldResult.getAplicant().getId() == newResult.getAplicant().getId() 
		&& oldResult.getExam().equals(newResult.getExam())) {
	    update(newResult);
	}
	else {
	    delete(oldResult);
	    create(newResult);
	}	
    }

    @Override
    public void delete(ExamResult result) throws ServiceException {
	// TODO Auto-generated method stub
	try {
	    resultDao.delete(result);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	    // TODO: handle exception
	}
    }

    @Override
    public ExamResult find(Applicant apl, Examination exam) throws ServiceException {
	
	try {
	    return resultDao.read(apl, exam);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public List<ExamResult> findAll(Applicant apl) throws ServiceException {
	try {
	    return resultDao.readByAplicant(apl);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

}
