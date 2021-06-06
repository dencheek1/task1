package by.vsu.lab.task4.controller.applicant.result;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.vsu.lab.task4.controller.BaseServlet;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.ExamResult;
import by.vsu.lab.task4.entitys.Examination;
import by.vsu.lab.task4.entitys.User;
import by.vsu.lab.task4.util.ServiceFactory;

public class ApplicantResultAddServlet extends BaseServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	logger.debug("on result add");

	HttpSession session = req.getSession(false);
	if (session != null) {
	    User currentUser = (User) session.getAttribute("currentUser");
	    if (currentUser != null) {
		try (ServiceFactory factory = getServiceFactory()) {

		    Applicant applicant = factory.getApplicantService()
			    .findById(currentUser.getId());
		    ExamResult result = new ExamResult();
		    result.setAplicant(applicant);
		    req.setAttribute("result", result);
		    req.setAttribute("exams", Examination.values());
		    req.setAttribute("isNew", true);
		    getServletContext()
			    .getRequestDispatcher(
				    "/WEB-INF/jsp/result/resultedit.jsp")
			    .forward(req, resp);
		    return;
		} catch (Exception e) {
		    logger.warn(e);
		    resp.sendError(HttpServletResponse.SC_NOT_FOUND,
			    "can't do something");
		}
	    }
	}
	resp.sendRedirect(req.getContextPath() + "/index.html?message= whell done");

    }

}
