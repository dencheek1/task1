package by.vsu.lab.task4.controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vsu.lab.task4.controller.BaseServlet;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.util.ServiceFactory;

public class ApplicantShowServlet extends BaseServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Long id = null;
		try {
		    id = Long.parseLong(req.getParameter("id"));
		} catch(NumberFormatException e) {resp.sendError(HttpServletResponse.SC_NOT_FOUND);}
		if(id != null) {
		    try {
			ServiceFactory factory = getServiceFactory();
			List<Applicant> apl = factory.getApplicantService().findByFaculty(id);
			req.setAttribute("aplicants", apl);
			factory.closeConnection();
		    	getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
		    	    
		    }catch(Exception e) {
			
		    }
		}
    }
}
