package by.vsu.lab.task4.controller.faculty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vsu.lab.task4.controller.BaseServlet;
import by.vsu.lab.task4.dao.daoimpl.BaseDao;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.util.ServiceFactory;

public class FacultyShowSelectedServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Long id = null;
	
	try {
	    id = Long.parseLong(req.getParameter("id"));
	} catch (Exception e) {
	}
	
	try(ServiceFactory factory = getServiceFactory()) {
		Faculty faculty = factory.getFacultyService().find(id);
		List<Applicant> applicants = factory.getApplicantService().findByFaculty(faculty.getId());
		applicants = factory.getFacultyService().selectAplicants(applicants, faculty);
		req.setAttribute("aplicants", applicants);
	    }catch(Exception e) {
		e.printStackTrace();
	    }
	getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);	
    }
    
}
