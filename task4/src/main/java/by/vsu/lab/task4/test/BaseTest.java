package by.vsu.lab.task4.test;

import java.sql.Connection;

import by.vsu.lab.task4.dao.daoimpl.DaoException;

public class BaseTest implements TestInterface, AutoCloseable {

    private Connection connection;

    public BaseTest(Connection connectoin) {
	this.connection = connectoin;
    }

    public Connection getConnection() {
	return connection;
    }

    @Override
    public void readTest() {
	System.out.println("No read tests for " + this.getClass().getName());

    }

    @Override
    public void createTest() {
	System.out.println("No create tests for " + this.getClass().getName());
    }

    @Override
    public void updateTest() {
	System.out.println("No update tests for " + this.getClass().getName());
    }

    @Override
    public void deleteTest() {
	System.out.println("No delete tests for " + this.getClass().getName());
    }

    @Override
    public void close() throws Exception {
	try {
	    connection.rollback();
	    connection.close();
	} catch (Exception e) {
	}

    }

    @Override
    public void test() {
	this.createTest();
	this.readTest();
	this.updateTest();
	this.deleteTest();
    }

    @Override
    public void init(Connection connection) {
	this.connection = connection;

    }

}
