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
import by.vsu.lab.task4.service.exception.ServiceException;
import by.vsu.lab.task4.util.ServiceFactory;

public class ApplicantResultDeleteServlet extends BaseServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	logger.info("on result delete servlser");
	HttpSession session = req.getSession(false);
	User user = (User) session.getAttribute("currentUser");
	Long id = null;
	Examination exam = null;
	try {
	    exam = Examination.valueOf(req.getParameter("oldExam"));
	} catch (Exception e) {
	}
	try {
	    id = Long.parseLong(req.getParameter("applicant_id"));
	} catch (IllegalArgumentException e) {
	}
	if (id != null && exam != null && (id.equals(user.getId())
		|| user.getType().equals(UserType.ADMIN))) {
	    try (ServiceFactory factory = getServiceFactory()) {
		ExamResult result = new ExamResult();
		result.setAplicant(new Applicant(id));
		result.setExam(exam);
		factory.getApplicantResultService().delete(result);
		resp.sendRedirect(req.getContextPath()
			+ "/applicant.html?applicant_id=" + user.getId());
	    } catch (Exception e) {
		logger.warn(e);
	    }
	} else
	    resp.sendRedirect(req.getContextPath() + "/applicant.html?applicant_id="
		    + user.getId());
    }

}
