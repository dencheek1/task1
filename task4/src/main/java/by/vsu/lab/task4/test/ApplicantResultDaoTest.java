package by.vsu.lab.task4.test;

import java.sql.Connection;

import by.vsu.lab.task4.dao.daoimpl.BaseDao;
import by.vsu.lab.task4.dao.daoimpl.DaoException;
import by.vsu.lab.task4.dao.daoimpl.ExamResultDaoImpl;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.ExamResult;
import by.vsu.lab.task4.entitys.Examination;

public class ApplicantResultDaoTest extends BaseTest {

    public static Applicant baseApplicant;
    public ExamResultDaoImpl resultDao;

    static {
	baseApplicant = new Applicant();
	baseApplicant.setId(1L);
    }

    public ApplicantResultDaoTest(Connection connection) {
	super(connection);
	resultDao = new ExamResultDaoImpl();
	resultDao.setConnection(connection);
    }

    @Override
    public void readTest() {
	System.out.println("\nRead test");
	try {
	    System.out.println(resultDao.read(baseApplicant, Examination.PHITH));
	    for(ExamResult result: resultDao.readByAplicant(baseApplicant)) {
		System.out.println(result);
	    }
	} catch (DaoException e) {
	    System.out.println("test fail" + e);
	}
    }

    @Override
    public void createTest() {
	System.out.println("\nCreate test");
	try {
	    ExamResult result = new ExamResult();
	    result.setAplicant(baseApplicant);
	    result.setExam(Examination.PHITH);
	    result.setResult(16);
	    result.setChecked(false);
	    resultDao.create(result);
	    System.out.println(result);
	} catch (DaoException e) {
	    System.out.println("test fail" + e);
	}
    }

    @Override
    public void updateTest() {
	System.out.println("\nUpdate test");
	try {
	    System.out.println(resultDao.read(baseApplicant, Examination.PHITH));
	    ExamResult result = resultDao.read(baseApplicant, Examination.PHITH);
	    result.setChecked(true);
	    result.setResult(26);
	    resultDao.update(result);
	    System.out.println(resultDao.read(baseApplicant, Examination.PHITH));	    
	} catch (DaoException e) {
	    System.out.println("test fail" + e);
	}
    }

    @Override
    public void deleteTest() {
	System.out.println("\nDelete test");
	try {
	    System.out.println("Before");
	    for(ExamResult result: resultDao.readByAplicant(baseApplicant)) {
		System.out.println(result);
	    }
	    System.out.println("After");
	    resultDao.delete(resultDao.read(baseApplicant, Examination.PHITH));
	    for(ExamResult result: resultDao.readByAplicant(baseApplicant)) {
		System.out.println(result);
	    }
	} catch (DaoException e) {
	    System.out.println("test fail" + e);
	}
    }

}
