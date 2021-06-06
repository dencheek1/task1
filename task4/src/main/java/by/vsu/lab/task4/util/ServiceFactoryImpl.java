package by.vsu.lab.task4.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import by.vsu.lab.task4.dao.ApplicantDao;
import by.vsu.lab.task4.dao.ExamResultDao;
import by.vsu.lab.task4.dao.FacultyDao;
import by.vsu.lab.task4.dao.FacultyRequirementsDao;
import by.vsu.lab.task4.dao.UserDao;
import by.vsu.lab.task4.dao.daoimpl.ApplicantDaoImpl;
import by.vsu.lab.task4.dao.daoimpl.ExamResultDaoImpl;
import by.vsu.lab.task4.dao.daoimpl.FacultyDaoImpl;
import by.vsu.lab.task4.dao.daoimpl.FacultyRequirementsDaoImpl;
import by.vsu.lab.task4.dao.daoimpl.UserDaoImpl;
import by.vsu.lab.task4.entitys.Examination;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.entitys.FacultyRequirement;
import by.vsu.lab.task4.service.ApplicantService;
import by.vsu.lab.task4.service.ApplicantResultService;
import by.vsu.lab.task4.service.FacultyRequirementsService;
import by.vsu.lab.task4.service.FacultyService;
import by.vsu.lab.task4.service.UserService;
import by.vsu.lab.task4.service.exception.ServiceException;
import by.vsu.lab.task4.service.logic.ApplicantServiceImpl;
import by.vsu.lab.task4.service.logic.ApplicantResultServiceImpl;
import by.vsu.lab.task4.service.logic.FacultyRequirementServiceImpl;
import by.vsu.lab.task4.service.logic.FacultyServiceImpl;
import by.vsu.lab.task4.service.logic.UserServiceImpl;

public class ServiceFactoryImpl implements ServiceFactory {
    private Connection connection;
    
    public Connection getConnection() {
       if(connection == null)
	try {
	    connection = ConnectionPool.getInstance().getConnection();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
       return connection;
    }

    @Override
    public ApplicantDao getApplicantDao() {
	ApplicantDaoImpl applDao = new ApplicantDaoImpl();
	applDao.setConnection(getConnection());
	return applDao;
    }

    @Override
    public FacultyDao getFacultyDao() {
	FacultyDaoImpl facultyDao = new FacultyDaoImpl();
	facultyDao.setConnection(getConnection());
	return facultyDao;
    }

    @Override
    public FacultyRequirementsDao getFacultyRequirementsDao() {
	FacultyRequirementsDaoImpl reqDao = new FacultyRequirementsDaoImpl();
	reqDao.setConnection(getConnection());
	return reqDao;
    }

    @Override
    public ExamResultDao getExamResultDao() {
	ExamResultDaoImpl resultDao = new ExamResultDaoImpl();
	resultDao.setConnection(getConnection());
	return resultDao;
    }

    @Override
    public ApplicantService getApplicantService() {
	ApplicantServiceImpl aplicantService = new ApplicantServiceImpl();
	aplicantService.setAplicantDao(getApplicantDao());
	aplicantService.setResultDao(getExamResultDao());
	return aplicantService;
    }

    @Override
    public FacultyService getFacultyService() {
	FacultyServiceImpl facultyService = new FacultyServiceImpl();
	//facultyService.setAplicantDao(getAplicantDao());
	facultyService.setFacultyDao(getFacultyDao());
	facultyService.setFacultyRquirementsDao(getFacultyRequirementsDao());
	facultyService.setResultDao(getExamResultDao());
	return facultyService;
    }

    @Override
    public void closeConnection() {
	try {
	    ConnectionPool.getInstance().freeConnection(connection);
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
    }

    @Override
    public UserDao getUserDao() {
	UserDaoImpl userDao = new UserDaoImpl();
	userDao.setConnection(getConnection());
	return userDao;
    }

    @Override
    public UserService getUserService() {
	UserServiceImpl userService = new UserServiceImpl();
	userService.setUserDao(getUserDao());
	return userService;
    }

    @Override
    public FacultyRequirementsService getFacultyRequirementsService() {
	FacultyRequirementServiceImpl reqService = new FacultyRequirementServiceImpl();
	reqService.setFacultyDao(getFacultyRequirementsDao());
	return reqService;
    }

    @Override
    public ApplicantResultService getApplicantResultService() {
	ApplicantResultServiceImpl aplService = new ApplicantResultServiceImpl();
	aplService.setResultDao(getExamResultDao());
	return aplService;
    }

    @Override
    public void close() throws ServiceException {
	try {
	    ConnectionPool.getInstance().freeConnection(connection);
	} catch (SQLException e) {
	    throw new ServiceException(e);
	}
    }
    
    

}
