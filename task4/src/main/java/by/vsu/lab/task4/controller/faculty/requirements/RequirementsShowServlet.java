package by.vsu.lab.task4.controller.faculty.requirements;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.vsu.lab.task4.controller.BaseServlet;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.entitys.FacultyRequirement;
import by.vsu.lab.task4.util.ServiceFactory;

public class RequirementsShowServlet extends BaseServlet {

    private static final Logger logger = LogManager.getLogger();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Long id = null;
	
	try {
	    id = Long.parseLong(req.getParameter("id"));
	} catch (Exception e) {
	}

	try(ServiceFactory factory = getServiceFactory()) {
		
		Faculty faculty = factory.getFacultyService().find(id);
		List<FacultyRequirement> requirements = factory.getFacultyRequirementsService().readAll(faculty);
		req.setAttribute("requirements", requirements);
		req.setAttribute("faculty", faculty);
		System.out.println("on Show");
	    } catch(Exception e) {
		logger.warn(e);
	    }
	getServletContext().getRequestDispatcher("/WEB-INF/jsp/requirements.jsp").forward(req, resp);	
    }
    
}
