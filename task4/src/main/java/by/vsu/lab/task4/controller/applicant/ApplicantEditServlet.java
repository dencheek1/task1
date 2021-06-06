package by.vsu.lab.task4.controller.applicant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.vsu.lab.task4.controller.BaseServlet;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.entitys.User;
import by.vsu.lab.task4.entitys.UserType;
import by.vsu.lab.task4.util.ServiceFactory;

public class ApplicantEditServlet extends BaseServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {

	HttpSession session = req.getSession(false);
	User currentUser = (User) session.getAttribute("currentUser");
	Long id = null;
	List<Faculty> facultys = new ArrayList<Faculty>();
	if (currentUser.getType().equals(UserType.ADMIN)) {
	    try {
		id = Long.parseLong(req.getParameter("user_id"));
	    } catch (IllegalArgumentException e) {
	    }
	} else {
	    id = currentUser.getId();
	}

	if (id != null) {
	    logger.debug("applicant id id={}", id);
	    try (ServiceFactory factory = getServiceFactory()) {
		Applicant applicant = new Applicant(id);
		logger.debug("applicant id id={}", id);
		if ((applicant = factory.getApplicantService().findById(id)) == null) {
		    resp.sendRedirect(req.getContextPath() + "/applicant/add.html");
		    return;
		}
		logger.debug(applicant);
		facultys = factory.getFacultyService().findAll();
		req.setAttribute("applicant", applicant);
		req.setAttribute("facultys", facultys);
		req.setAttribute("currentUser", currentUser);
		req.setAttribute("isNew", false);
		getServletContext()
			.getRequestDispatcher("/WEB-INF/jsp/applicant/edit.jsp")
			.forward(req, resp);

	    } catch (Exception e) {
		logger.warn(e);
		throw new ServletException(e);
	    }

	} else {
	    resp.sendRedirect(req.getContextPath() + "/");
	}
    }

}
