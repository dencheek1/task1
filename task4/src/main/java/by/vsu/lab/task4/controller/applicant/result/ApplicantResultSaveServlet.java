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
import by.vsu.lab.task4.entitys.UserType;
import by.vsu.lab.task4.service.ApplicantResultService;
import by.vsu.lab.task4.util.ServiceFactory;

public class ApplicantResultSaveServlet extends BaseServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {

	logger.debug("on result save");
	HttpSession session = req.getSession(false);

	User user = (User) session.getAttribute("currentUser");
	ExamResult result = new ExamResult();
	result.setChecked(false);
	Examination oldExam = null;
	logger.debug(user);
	try {
	    logger.debug("Checked={} \nExam value={} \nApplicant_id={}",
		    req.getParameter("checked"), req.getParameter("exam"),
		    req.getParameter("applicant_id"));
	    result.setExam(Examination.valueOf(req.getParameter("exam")));
	    result.setResult(Integer.parseInt(req.getParameter("result")));

	    result.setChecked(req.getParameter("checked") == null ? false : true);
	    result.setAplicant(
		    new Applicant(Long.parseLong(req.getParameter("applicant_id"))));
	    oldExam = Examination.valueOf(req.getParameter("oldExam"));
	} catch (NumberFormatException | NullPointerException e) {
	    logger.warn(e);
	}
	logger.debug(result);
	if (result.getAplicant() != null && result.getExam() != null) {
	    if (!user.getType().equals(UserType.ADMIN))
		result.setChecked(false);
	    try (ServiceFactory factory = getServiceFactory()) {
		ApplicantResultService resService = factory
			.getApplicantResultService();
		ExamResult collision = resService.find(result.getAplicant(),
			result.getExam());
		logger.debug(result);
		if (oldExam != null
			&& (collision == null || oldExam.equals(result.getExam()))) {
		    if (oldExam.equals(result.getExam())) {
			resService.update(result);
		    } else {
			ExamResult oldResult = new ExamResult();
			oldResult.setAplicant(result.getAplicant());
			oldResult.setExam(oldExam);
			resService.update(oldResult, result);
		    }
		}
		resp.sendRedirect(
			req.getContextPath() + "/applicant.html?applicant_id="
				+ result.getAplicant().getId()
				+ "&message=first delete other result");
	    } catch (Exception e) {
		logger.warn(e);
		logger.debug(result);
		resp.sendRedirect(req.getContextPath() + "/");
	    }
	} else {
	    resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
	}

    }

}
