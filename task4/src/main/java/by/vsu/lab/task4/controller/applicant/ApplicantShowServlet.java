package by.vsu.lab.task4.controller.applicant;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.vsu.lab.task4.controller.BaseServlet;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.ExamResult;
import by.vsu.lab.task4.service.ApplicantResultService;
import by.vsu.lab.task4.service.ApplicantService;
import by.vsu.lab.task4.service.exception.ServiceException;
import by.vsu.lab.task4.util.ServiceFactory;

public class ApplicantShowServlet extends BaseServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	Long id = null;
	try {
	    id = Long.parseLong(req.getParameter("applicant_id"));
	} catch (Exception e) {
	}
	logger.debug(id);
	if (id != null) {
	    try (ServiceFactory factory = getServiceFactory()) {
		ApplicantService service = factory.getApplicantService();
		ApplicantResultService resultService = factory
			.getApplicantResultService();
		List<ExamResult> applicantResult = null;
		Applicant applicant = null;
		try {
		    applicant = service.findById(id);
		    if (applicant != null) {
			applicantResult = resultService.findAll(applicant);
			req.setAttribute("applicant", applicant);
			req.setAttribute("results", applicantResult);
		    }else {
			resp.sendRedirect(req.getContextPath()+"/applicant/add.html");
			return;
		    }
		} catch (ServiceException e) {
		    logger.warn(e);

		}
		logger.debug(applicant);
		logger.debug(applicantResult);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    getServletContext()
		    .getRequestDispatcher("/WEB-INF/jsp/applicant/applicant.jsp")
		    .forward(req, resp);

	    }
	
    }

}
