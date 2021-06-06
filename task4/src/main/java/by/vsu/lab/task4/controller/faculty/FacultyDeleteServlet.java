package by.vsu.lab.task4.controller.faculty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vsu.lab.task4.controller.BaseServlet;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.util.ServiceFactory;

public class FacultyDeleteServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Faculty id = new Faculty();
	try {
	    id.setId(Long.parseLong(req.getParameter("id")));
	} catch(NumberFormatException e) {
	}
	if(id.getId() != 0 ) {
	    try(ServiceFactory factory = getServiceFactory();) {
		    
		    factory.getFacultyService().delete(id);
		} catch(Exception e) {

		    throw new ServletException(e);
		}
	}
	resp.sendRedirect(req.getContextPath() + "/facultylist.html");
    }
    
}
