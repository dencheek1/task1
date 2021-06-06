package by.vsu.lab.task4.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.vsu.lab.task4.util.ConnectionPool;

@WebListener
public class ApplicationStartListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public void contextInitialized(ServletContextEvent event) {
	System.out.println("StartInit");
        ServletContext context = event.getServletContext();
	String jdbcDriver   = context.getInitParameter("jdbc-driver");
	String jdbcUrl      = context.getInitParameter("jdbc-url");
	String jdbcUsername = context.getInitParameter("jdbc-username");
	String jdbcPassword = context.getInitParameter("jdbc-password");
	ConnectionPool.getInstance().init(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword, 5, 10, 30);
	logger.info("Connector was initialized,\njdbc-driver = {},\njdbc-url = {},\njdbc-username = {}\njdbc-password = {}", jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
	ConnectionPool.getInstance().destroy();
    }
    
    
    
}
