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
import by.vsu.lab.task4.util.ServiceFactory;

public class ApplicantRegistrationServlet extends BaseServlet {

    private static final Logger logger = LogManager.getLogger();

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {

	HttpSession session = req.getSession(false);
	User currentUser = (User) session.getAttribute("currentUser");
	Applicant applicant = null;
	String name = req.getParameter("name");
	String surname = req.getParameter("surname");
	Long facultyId = null;
	Integer sertificate = null;
	Long id = null;

	try {
	    id = Long.parseLong(req.getParameter("applicant_id"));
	} catch (IllegalArgumentException e) {
	}
	try {
	    facultyId = Long.parseLong(req.getParameter("faculty"));
	} catch (IllegalArgumentException e) {
	}
	try {
	    sertificate = Integer.parseInt(req.getParameter("sertificate"));
	} catch (IllegalArgumentException e) {
	}
	
	logger.debug(" id={}\nfacultyId={}\nsertificate={}",id,facultyId,sertificate);

	if (currentUser.getType().equals(UserType.ADMIN)) {
	    if (id != null && facultyId != null && !name.isBlank()
		    && !surname.isBlank()) {
		applicant = new Applicant();
		applicant.setId(id);
		applicant.setName(name);
		applicant.setSurname(surname);
		applicant.setSertificate(sertificate);
		applicant.setFacultyId(facultyId);
	    } else {
		resp.sendRedirect(
			req.getContextPath() + "/index.html?message= whell done");
		return;
	    }
	} else if (facultyId != null && sertificate != null && !name.isBlank()
		&& !surname.isBlank()) {
	    applicant = new Applicant();
	    applicant.setId(currentUser.getId());
	    applicant.setName(name);
	    applicant.setSurname(surname);
	    applicant.setSertificate(sertificate);
	    applicant.setFacultyId(facultyId);
	}
	
	logger.debug("Applicant on registration servlet {}", applicant);

	if (applicant != null) {

	    try (ServiceFactory factory = getServiceFactory()) {
		if (factory.getApplicantService()
			.findById(applicant.getId()) == null) {
		    factory.getApplicantService().create(applicant);

		}

		resp.sendRedirect(req.getContextPath()
			+ "/applicant.html?applicant_id=" + applicant.getId()
			+ "&message= applicant registred");
		return;
	    } catch (Exception e) {
	    }

	}

	req.setAttribute("applicant", applicant);

	getServletContext().getRequestDispatcher("/WEB-INF/jsp/applicant/edit.jsp")
		.forward(req, resp);
    }

}
