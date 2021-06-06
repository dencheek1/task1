package by.vsu.lab.task4.dao.daoimpl;

import java.sql.Connection;

public abstract class BaseDao {

    private Connection connection;
    
    public void setConnection(Connection connection) {
	this.connection = connection;
    }
    
    public Connection getConnection() {
	return connection;
    }
    
}
