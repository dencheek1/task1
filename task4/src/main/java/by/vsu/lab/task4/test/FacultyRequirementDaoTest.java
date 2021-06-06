package by.vsu.lab.task4.test;

import java.sql.Connection;

import by.vsu.lab.task4.dao.daoimpl.DaoException;
import by.vsu.lab.task4.dao.daoimpl.FacultyRequirementsDaoImpl;
import by.vsu.lab.task4.entitys.Examination;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.entitys.FacultyRequirement;

public class FacultyRequirementDaoTest extends BaseTest {

    private FacultyRequirementsDaoImpl requirementDao;
    private static FacultyRequirement baseRequirement;

    static {
	baseRequirement = new FacultyRequirement();
	baseRequirement.setGroup(2);
	baseRequirement.setFaculty(new Faculty());
	baseRequirement.getFaculty().setId(1L);
	baseRequirement.setValue(35);
	baseRequirement.setExam(Examination.PHITH);
    }

    public FacultyRequirementDaoTest(Connection connectoin) {
	super(connectoin);
	requirementDao = new FacultyRequirementsDaoImpl();
	requirementDao.setConnection(connectoin);
    }

    @Override
    public void readTest() {
	System.out.println("\nRead test\n");
	try {
	    System.out.println(requirementDao.read(baseRequirement.getFaculty(), Examination.PHITH)+"\n");
	    for (FacultyRequirement requirement : requirementDao
		    .readFacultyRequirements(baseRequirement.getFaculty())) {
		System.out.println(requirement);
	    }
	} catch (DaoException e) {
	    System.out.println("test fail" + e);
	}
    }

    @Override
    public void createTest() {
	System.out.println("Create test");
	try {
	    System.out.println(baseRequirement);
	    requirementDao.create(baseRequirement);
	} catch (DaoException e) {
	    System.out.println("test fail" + e);
	}
    }

    @Override
    public void updateTest() {
	System.out.println("Update test\n");
	try {
	    FacultyRequirement requirement = requirementDao.read(baseRequirement.getFaculty(), Examination.PHITH);
	    System.out.println("Before\n" + requirement);
	    requirement.setValue(80);
	    requirementDao.update(requirement);
	    System.out.println(requirementDao.read(baseRequirement.getFaculty(), Examination.PHITH));
	} catch (DaoException e) {
	    System.out.println("test fail" + e);
	}
    }

    @Override
    public void deleteTest() {
	System.out.println("Delete test\n");
	try {
	    requirementDao.delete(baseRequirement);
	    for (FacultyRequirement requirement : requirementDao
		    .readFacultyRequirements(baseRequirement.getFaculty())) {
		System.out.println(requirement);
	    }
	} catch (DaoException e) {
	    System.out.println("test fail" + e);
	}
    }

}
