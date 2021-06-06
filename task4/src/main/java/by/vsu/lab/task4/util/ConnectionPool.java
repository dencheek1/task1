package by.vsu.lab.task4.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ConnectionPool {

    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;
    private int maxSize;
    private int validationTimout;
    
    private Queue<Connection> freeConnections = new ConcurrentLinkedQueue<>();
    private Set<Connection> usedConnections = new ConcurrentSkipListSet<>();
    
    private static ExecutorService closer = Executors.newSingleThreadExecutor();
    private static ConnectionPool connectionPool;
    
    private ConnectionPool() {}
    
    public static ConnectionPool getInstance() {
	if(connectionPool == null) {
	    synchronized (ConnectionPool.class) {
		if(connectionPool == null) {
		    connectionPool = new ConnectionPool();
		}
	    }
	}
	return connectionPool;
    }
    
    public void close(Connection connection) {
	closer.execute(()->{
	    synchronized(connection) {
		try { connection.rollback();} catch( SQLException e) {}
		try { connection.close();} catch(SQLException e) {}
	    }
	});
    }
    
    public Connection getConnection() throws SQLException{
	Connection connection = null;
		while(connection == null) {
		    try {
			connection = freeConnections.poll();
			if(connection != null) {
				if(!connection.isValid(validationTimout)) {
					close(connection);
					connection = null;
				}
			} else if(maxSize == 0 || usedConnections.size() < maxSize) {
				connection = establishConnection();
			} else {
				throw new IllegalArgumentException();
			}
		    } catch(SQLException e) {
			throw new SQLException(e);
		    }
		}
		return connection;
    }
       
	public void init(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPassword, int minSize, int maxSize, int validationTimeout) throws IllegalArgumentException {
		try {
			if(minSize <= maxSize) {
				Class.forName(jdbcDriver);
				this.jdbcUrl = jdbcUrl;
				this.jdbcUser = jdbcUser;
				this.jdbcPassword = jdbcPassword;
				for(int i = 0; i < minSize; i++) {
					freeConnections.add(establishConnection());
				}
				this.maxSize = maxSize;
				this.validationTimout = validationTimeout;
			} else {
				throw new IllegalArgumentException();
			}
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		synchronized(usedConnections) {
			synchronized(freeConnections) {
				//usedConnections.addAll(freeConnections);
				//freeConnections.clear();
				for(Connection connection : usedConnections) {
					close(connection);
				}
				for(Connection connection : freeConnections) {
				    close(connection);
				}
				usedConnections.clear();
				freeConnections.clear();
				closer.shutdown();
			}
		}
	}
	
	void freeConnection(Connection connection) throws SQLException {
		try {
			usedConnections.remove(connection);
			connection.clearWarnings();
			connection.setAutoCommit(true);
			freeConnections.add(connection);
		} catch(SQLException | NullPointerException e) {
			close(connection);
		}
	}
	
	private Connection establishConnection() throws SQLException {
		return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
	}
    
}
