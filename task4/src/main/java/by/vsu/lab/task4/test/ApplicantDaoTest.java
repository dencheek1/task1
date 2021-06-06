package by.vsu.lab.task4.test;

import java.sql.Connection;

import by.vsu.lab.task4.dao.daoimpl.ApplicantDaoImpl;
import by.vsu.lab.task4.dao.daoimpl.DaoException;
import by.vsu.lab.task4.entitys.Applicant;

public class ApplicantDaoTest extends BaseTest {

    private Applicant baseApplicant;
    private ApplicantDaoImpl applicantDao;
    
    public ApplicantDaoTest(Connection connection) {
	super(connection);
	applicantDao = new ApplicantDaoImpl();
	applicantDao.setConnection(connection);
	baseApplicant = new Applicant();
	baseApplicant.setFacultyId(1L);
	baseApplicant.setName("Den");
	baseApplicant.setSertificate(55);
	baseApplicant.setSurname("UUUUU");
	baseApplicant.setId(9L);
    }

    @Override
    public void readTest() {
	System.out.println("Read test");
	try {
	    Applicant applicant = applicantDao.read(baseApplicant.getId());
	    System.out.println(applicant);
	    System.out.println("read all");
	    for(Applicant appl : applicantDao.readAll()) {
		System.out.println(appl);
	    }
	    System.out.println("read by faculty"); 
	    for(Applicant appl : applicantDao.readByFacultetId(1L)) {
		System.out.println(appl);
	    }
	    
	}catch(DaoException e) {
	    System.out.println("Read test fail" + e);
	}
;
    }

    @Override
    public void createTest() {
	System.out.println("create test");
	try {
	    applicantDao.create(baseApplicant);
	    System.out.println("Created " + baseApplicant);
	}catch(DaoException e) {
	    System.out.println("Create test fail" + e);
	}
    }

    @Override
    public void updateTest() {
	System.out.println("\nUpdate test");
	try {
	    Applicant applicant = applicantDao.read(baseApplicant.getId());
	    applicant.setName("Not den");
	    applicantDao.update(applicant);
	    for(Applicant appl : applicantDao.readAll()) {
		System.out.println(appl);
	    }
	}catch(DaoException e) {
	    System.out.println("Update test fail" + e);
	}
    }

    @Override
    public void deleteTest() {
	System.out.println("Delete test");
	try {
	   applicantDao.delete(baseApplicant.getId());
	    for(Applicant appl : applicantDao.readAll()) {
		System.out.println(appl);
	    }
	}catch(DaoException e) {
	    System.out.println("Delete test fail" + e);
	}
    }
    
    

}
