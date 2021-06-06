package by.vsu.lab.task4.controller.applicant.result;

import java.io.IOException;
import java.util.List;

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
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.entitys.FacultyRequirement;
import by.vsu.lab.task4.entitys.User;
import by.vsu.lab.task4.entitys.UserType;
import by.vsu.lab.task4.util.ServiceFactory;

public class ApplicantResultEditServlet extends BaseServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {

	logger.debug("on edit save");
	Long id = null;
	Examination exam = null;
	HttpSession session = req.getSession(false);
	User user = (User) session.getAttribute("currentUser");
	try {
	    id = Long.parseLong(req.getParameter("id"));
	    exam = Examination.valueOf(req.getParameter("exam"));
	} catch (Exception e) {
	}
	if (id != null && exam != null && (id.equals(user.getId())
		|| user.getType().equals(UserType.ADMIN))) {
	    try (ServiceFactory factory = getServiceFactory()) {

		Applicant applicant = factory.getApplicantService().findById(id);
		ExamResult result = factory.getApplicantResultService()
			.find(applicant, exam);
		req.setAttribute("result", result);
		req.setAttribute("exams", Examination.values());
		req.setAttribute("oldExam", exam);
		getServletContext()
			.getRequestDispatcher("/WEB-INF/jsp/result/resultedit.jsp")
			.forward(req, resp);
	    } catch (Exception e) {
		logger.warn(e);
		resp.sendError(HttpServletResponse.SC_NOT_FOUND,
			"can't do something");
	    }
	} else {
	    logger.debug(id);
	    logger.debug(exam);
	    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Incorrect requiest");
	}

    }

}
