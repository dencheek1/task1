package by.vsu.lab.task4.controller.faculty.requirements;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vsu.lab.task4.controller.BaseServlet;
import by.vsu.lab.task4.entitys.Examination;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.entitys.FacultyRequirement;
import by.vsu.lab.task4.util.ServiceFactory;

public class RequirementsDeleteServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Faculty id = new Faculty();
	try {
	    id.setId(Long.parseLong(req.getParameter("faculty_id")));
	} catch(NumberFormatException e) {
	}
	if(id.getId() != null ) {
	    ServiceFactory factory = getServiceFactory();
	    
	    try {
			System.out.println("On delete");
		    FacultyRequirement requirement = new FacultyRequirement();
		    requirement.setExam(Examination.valueOf((req.getParameter("exam"))));
		    requirement.setFaculty(id);
		    factory.getFacultyRequirementsService().delete(requirement);
		} catch(Exception e) {

		    throw new ServletException(e);
		} finally {
		    factory.closeConnection();
		}
	}
	resp.sendRedirect("facultylist.html");
    }
    
}
