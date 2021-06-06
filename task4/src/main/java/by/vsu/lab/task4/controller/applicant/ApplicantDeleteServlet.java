package by.vsu.lab.task4.controller.applicant;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.vsu.lab.task4.controller.BaseServlet;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.User;
import by.vsu.lab.task4.entitys.UserType;
import by.vsu.lab.task4.service.ApplicantService;
import by.vsu.lab.task4.util.ServiceFactory;

public class ApplicantDeleteServlet extends BaseServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {

	HttpSession session = req.getSession(false);
	User currentUser = (User) session.getAttribute("currentUser");
	Long id = null;
	Long facultyId = null;
	Integer sertificate = null;
	String name = req.getParameter("name");
	String surname = req.getParameter("surname");
	String message = "?message=";
	try {
	    id = Long.parseLong(req.getParameter("applicant_id"));
	} catch(IllegalArgumentException e) {}

	try (ServiceFactory factory = getServiceFactory()) {
	    ApplicantService service = factory.getApplicantService();
	    if (id != null && currentUser != null) {
		Applicant applicant = service.findById(id);
		logger.debug(currentUser);
		if (applicant != null && (currentUser.getId().equals(applicant.getId())
			|| currentUser.getType().equals(UserType.ADMIN))) {
		    
		    service.delete(applicant);
		    
		}
	    }
	} catch (Exception e) {
	    logger.warn(e);
	    message = message + "first delete applicant";
	}

	resp.sendRedirect(req.getContextPath() + "/index.html"+message);
    
    }

}
