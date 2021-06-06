package by.vsu.lab.task4.test;

import java.sql.Connection;

import by.vsu.lab.task4.dao.daoimpl.DaoException;
import by.vsu.lab.task4.dao.daoimpl.FacultyDaoImpl;
import by.vsu.lab.task4.entitys.Faculty;

public class FacultyDaoTest extends BaseTest {

    private FacultyDaoImpl facultyDao;
    private static Faculty baseFaculty;
    
    static {
	baseFaculty = new Faculty();
	baseFaculty.setCount(150);
	baseFaculty.setName("Test faculty");
    }
    
    
    public FacultyDaoTest(Connection connection) {
	super(connection);
	facultyDao = new FacultyDaoImpl();
	facultyDao.setConnection(connection);
    }

    @Override
    public void readTest() {
	    System.out.println("Read test");
	try {
	    System.out.println(facultyDao.read(baseFaculty.getId())+ "\n");
	    for(Faculty faculty: facultyDao.readAll()) {
		System.out.println(faculty);
	    }
	} catch (DaoException e) {
	    System.out.println("test fail" + e);
	}
    }

    @Override
    public void createTest() {
	    System.out.println("Create test");
	try {
	    baseFaculty.setId(facultyDao.create(baseFaculty));
	    System.out.println(baseFaculty);
	} catch (DaoException e) {
	    System.out.println("test fail" + e);
	}
    }

    @Override
    public void updateTest() {
	    System.out.println("Update test");
	try {
	    Faculty faculty = facultyDao.read(baseFaculty.getId());
	    faculty.setCount(303);
	    facultyDao.update(faculty);
	    for(Faculty facult: facultyDao.readAll()) {
		System.out.println(facult);
	    }
	} catch (DaoException e) {
	    System.out.println("test fail" + e);
	}
    }

    @Override
    public void deleteTest() {
	    System.out.println("Delete test");
	try {
	    System.out.println("before");
	    for(Faculty faculty: facultyDao.readAll()) {
		System.out.println(faculty);
	    }
	    facultyDao.delete(baseFaculty.getId());
	    System.out.println("After");
	    for(Faculty faculty: facultyDao.readAll()) {
		System.out.println(faculty);
	    }
	    
	} catch (DaoException e) {
	    System.out.println("test fail" + e);
	}
    }

}
