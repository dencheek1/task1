package by.vsu.lab.task4.controller;

import javax.servlet.http.HttpServlet;

import by.vsu.lab.task4.util.ServiceFactory;
import by.vsu.lab.task4.util.ServiceFactoryImpl;

public abstract class BaseServlet extends HttpServlet{

    public ServiceFactory getServiceFactory() {
	return new ServiceFactoryImpl();
    }
    
}
