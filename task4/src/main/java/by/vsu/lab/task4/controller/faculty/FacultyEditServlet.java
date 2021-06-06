package by.vsu.lab.task4.controller.faculty;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vsu.lab.task4.controller.BaseServlet;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.util.ServiceFactory;

public class FacultyEditServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Long id = null;
	
	try {
	    id = Long.parseLong(req.getParameter("id"));
	} catch (NumberFormatException e) {
	    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
	}
	if (id != null) {
	    ServiceFactory factory = getServiceFactory();
	    Faculty faculty = null;
	    if (id == 0) {
		faculty = new Faculty();
		faculty.setId(0L);
	    } else {
		try {
		    faculty = factory.getFacultyService().find(id);
		} catch (Exception e) {

		}
	    }
	    factory.closeConnection();
	    req.setAttribute("faculty", faculty);

	    getServletContext().getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);

	}
    }
}
