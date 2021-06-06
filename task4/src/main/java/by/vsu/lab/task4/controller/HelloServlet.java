package by.vsu.lab.task4.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vsu.lab.task4.dao.daoimpl.ExamResultDaoImpl;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.ExamResult;
import by.vsu.lab.task4.service.ApplicantService;
import by.vsu.lab.task4.service.logic.ApplicantServiceImpl;
import by.vsu.lab.task4.util.ConnectionPool;
import by.vsu.lab.task4.util.Connector;
import by.vsu.lab.task4.util.ServiceFactory;
import by.vsu.lab.task4.util.ServiceFactoryImpl;

public class HelloServlet extends BaseServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Applicant> applicants = new ArrayList();
    	try (ServiceFactory serFactory = getServiceFactory();){
    	    applicants = serFactory.getApplicantService().findAll();
    	    request.setAttribute("aplicants", applicants);
    	    request.setAttribute("exams", serFactory.getFacultyService().findAll());
    	    getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    	    } catch (Exception e) {
    	    throw new ServletException(e.getMessage());
    	}
    }
}
