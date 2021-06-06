package by.vsu.lab.task4;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.vsu.lab.task4.controller.BaseServlet;
import by.vsu.lab.task4.dao.daoimpl.ExamResultDaoImpl;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.ExamResult;
import by.vsu.lab.task4.entitys.Examination;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.entitys.FacultyRequirement;
import by.vsu.lab.task4.service.ApplicantService;
import by.vsu.lab.task4.util.ConnectionPool;
import by.vsu.lab.task4.util.Connector;
import by.vsu.lab.task4.util.ServiceFactoryImpl;



public class JDBCConnectionTest{

    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
	
	List<FacultyRequirement> resultSet = new ArrayList<>();
	List<ExamResult> aplicants = new ArrayList<ExamResult>();
	Applicant apl = null;
	ServiceFactoryImpl serviceFactory = new ServiceFactoryImpl();
	ConnectionPool pool = ConnectionPool.getInstance();
	
	try {
	    pool.init("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/epam_tests", "den", "password",10,40,5);
	    apl = serviceFactory.getApplicantService().findById(1L);
	    aplicants = serviceFactory.getApplicantResultService().findAll(apl);
	    for(ExamResult res : aplicants) {
		System.out.println(res);
	    }
	    ExamResult result = new ExamResult();
	    result.setAplicant(apl);
	    result.setExam(Examination.PHITH);
	    result.setResult(100);
	    result.setChecked(false);
	    serviceFactory.getApplicantResultService().create(result);
	    aplicants = serviceFactory.getApplicantResultService().findAll(apl);
	    for(ExamResult res : aplicants) {
		System.out.println(res);
	    }
	    result.setResult(20);
	    serviceFactory.getApplicantResultService().update(result);
	    aplicants = serviceFactory.getApplicantResultService().findAll(apl);
	    for(ExamResult res : aplicants) {
		System.out.println(res);
	    }
	    serviceFactory.getApplicantResultService().delete(result);
	    
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    serviceFactory.closeConnection();
	    pool.destroy();
	}
	
	
	/*
	 * apl = serviceFactory.getFacultyService().find(1L);
	    resultSet.addAll(serviceFactory.getFacultyRequirementsService().readAll(apl));
	    for(FacultyRequirement req : resultSet) {
		System.out.println(req);
	    }
	    FacultyRequirement requirement = new FacultyRequirement();
	    requirement.setExam(Examination.PHITH);
	    requirement.setFaculty(apl);
	    requirement.setGroup(8);
	    requirement.setValue(15);
	    serviceFactory.getFacultyRequirementsService().create(requirement);
	    resultSet = serviceFactory.getFacultyRequirementsService().readAll(apl);
	    for(FacultyRequirement req : resultSet) {
		System.out.println(req);
	    }
	    /*serviceFactory.getFacultyRequirementsService().delete(requirement);
	    resultSet = serviceFactory.getFacultyRequirementsService().readAll(apl);
	    for(FacultyRequirement req : resultSet) {
		System.out.println(req);
	    }
	    requirement.setValue(40);
	    
	    
	    serviceFactory.getFacultyRequirementsService().update(requirement);
	    resultSet = serviceFactory.getFacultyRequirementsService().readAll(apl);
	    for(FacultyRequirement req : resultSet) {
		System.out.println(req);
	    }
	 */
    }

}
