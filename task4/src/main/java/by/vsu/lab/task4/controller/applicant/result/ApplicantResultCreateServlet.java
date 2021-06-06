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
import by.vsu.lab.task4.service.exception.ServiceException;
import by.vsu.lab.task4.util.ServiceFactory;

public class ApplicantResultCreateServlet extends BaseServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	HttpSession session = req.getSession(false);
	logger.debug("on result create");
	if (session != null) {
	    User user = (User) session.getAttribute("currentUser");
	    Long id = null;

	    try {
		id = Long.parseLong(req.getParameter("applicant_id"));
	    } catch (IllegalArgumentException e) {
	    }

	    if (user != null && id != null && user.getId().equals(id)) {
		Examination exam = null;
		Integer value = null;
		logger.debug(user);
		try {
		    exam = Examination.valueOf(req.getParameter("exam"));
		} catch (Exception e) {
		}

		try {
		    value = Integer.parseInt(req.getParameter("result"));
		} catch (Exception e) {
		}
		logger.debug("exam={}\n result={}", exam, value);
		if (exam != null && value != null && value >= 0 && value <= 100) {

		    try (ServiceFactory factory = getServiceFactory()) {
			if (null != factory.getApplicantResultService()
				.find(new Applicant(user.getId()), exam)) {
			    resp.sendRedirect(req.getContextPath()
				    + "/applicant.html?applicant_id=" + user.getId()
				    + "&message=first delete other result");
			    return;
			}

			Applicant applicant = factory.getApplicantService()
				.findById(id);
			if (applicant != null && factory.getApplicantResultService()
				.find(applicant, exam) == null) {
			    ExamResult result = new ExamResult();
			    result.setAplicant(applicant);
			    result.setChecked(false);
			    result.setExam(exam);
			    result.setResult(value);
			    factory.getApplicantResultService().create(result);
			    resp.sendRedirect(req.getContextPath()
				    + "/applicant.html?applicant_id="
				    + result.getAplicant().getId());
			    return;
			}
		    } catch (Exception e) {
			logger.warn(e);
		    }
		}

	    }
	}
	resp.sendRedirect(req.getContextPath());
    }

}
