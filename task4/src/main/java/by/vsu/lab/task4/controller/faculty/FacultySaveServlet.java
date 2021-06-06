package by.vsu.lab.task4.controller.faculty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vsu.lab.task4.controller.BaseServlet;
import by.vsu.lab.task4.entitys.Examination;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.entitys.FacultyRequirement;
import by.vsu.lab.task4.util.ServiceFactory;

public class FacultySaveServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	Faculty faculty = new Faculty();
	System.out.println("On save servlet");
	try {
	    faculty.setId(Long.parseLong(req.getParameter("id")));
	    faculty.setCount(Integer.parseInt(req.getParameter("count")));

	} catch (NumberFormatException e) {
	}
	faculty.setName(req.getParameter("login"));
	if (faculty.getName() != null  && !faculty.getName().isBlank()) {
	    try {
		ServiceFactory factory = getServiceFactory();
		factory.getFacultyService().update(faculty);
		factory.closeConnection();
	    } catch (Exception e) {
		throw new ServletException(e);
	    }
	}

	resp.sendRedirect(req.getContextPath() + "/facultylist.html");
    }
    
    
}
