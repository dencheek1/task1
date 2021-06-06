package by.vsu.lab.task4.util;

import by.vsu.lab.task4.dao.ApplicantDao;
import by.vsu.lab.task4.dao.ExamResultDao;
import by.vsu.lab.task4.dao.FacultyDao;
import by.vsu.lab.task4.dao.FacultyRequirementsDao;
import by.vsu.lab.task4.dao.UserDao;
import by.vsu.lab.task4.service.ApplicantService;
import by.vsu.lab.task4.service.ApplicantResultService;
import by.vsu.lab.task4.service.FacultyRequirementsService;
import by.vsu.lab.task4.service.FacultyService;
import by.vsu.lab.task4.service.UserService;
import by.vsu.lab.task4.service.logic.ApplicantResultServiceImpl;

public interface ServiceFactory extends AutoCloseable {

    public ApplicantDao getApplicantDao();
    public FacultyDao getFacultyDao();
    public FacultyRequirementsDao getFacultyRequirementsDao();
    public ExamResultDao getExamResultDao();
    public UserDao getUserDao();
    
    public ApplicantResultService getApplicantResultService();
    public FacultyRequirementsService getFacultyRequirementsService();
    public UserService getUserService();
    public ApplicantService getApplicantService();
    public FacultyService getFacultyService();
    public void closeConnection();
    
}
