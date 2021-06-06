package by.vsu.lab.task4.test;

import java.sql.Connection;

public interface TestInterface {

    public void init(Connection connection);
    public void readTest();
    public void createTest();
    public void updateTest();
    public void deleteTest();
    public void test();
    
}
