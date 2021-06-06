package by.vsu.lab.task4.test;

import java.sql.Connection;
import java.sql.SQLException;

import by.vsu.lab.task4.util.Connector;

public class DaoTest {

    private static Connection connection;
    
    public static void main(String[] args) {
	try {
	    Connector.init("com.mysql.cj.jdbc.Driver", 
		    "jdbc:mysql://localhost:3306/test", "den", "password");
		
	} catch (ClassNotFoundException e) {
	    System.out.println("init exception");
	    return;
	}
	System.out.println("//////////////////////////////////////////////////");
	try (BaseTest test = new UserDaoTests(Connector.getConnection());){
	    test.test();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	System.out.println("//////////////////////////////////////////////////");
	try (BaseTest test = new ApplicantDaoTest(Connector.getConnection());){
	    test.test();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	System.out.println("//////////////////////////////////////////////////");
	try (BaseTest test = new ApplicantResultDaoTest(Connector.getConnection());){
	    test.test();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	System.out.println("//////////////////////////////////////////////////");
	try (BaseTest test = new FacultyDaoTest(Connector.getConnection());){
	    test.test();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	
	System.out.println("//////////////////////////////////////////////////");
	try (BaseTest test = new FacultyRequirementDaoTest(Connector.getConnection());){
	    test.test();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

}
