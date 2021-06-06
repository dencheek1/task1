package by.vsu.lab.task4.controller.faculty.requirements;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.vsu.lab.task4.controller.BaseServlet;
import by.vsu.lab.task4.entitys.Examination;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.entitys.FacultyRequirement;
import by.vsu.lab.task4.util.ServiceFactory;

public class RequirementEditServlet extends BaseServlet {

    private static final Logger logger = LogManager.getLogger();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	Long id = null;
	Examination exam = null;
	try {
	    id = Long.parseLong(req.getParameter("facultyid"));
	    exam = Examination.valueOf(req.getParameter("exam"));
	} catch (NumberFormatException | NullPointerException e) {
	}
	if (id != null) {
	    Boolean isNew = true;
	    try (ServiceFactory factory = getServiceFactory()){
		Faculty faculty = null;
		faculty = factory.getFacultyService().find(id);
		FacultyRequirement facultyReq = new FacultyRequirement();
		facultyReq.setExam(Examination.MATH);
		logger.debug(exam);
		if (exam != null) {
		    isNew = false;
		    facultyReq = factory.getFacultyRequirementsService().read(faculty, exam);
		    req.setAttribute("req", facultyReq);
		    
		}
		req.setAttribute("isNew", isNew);
		req.setAttribute("faculty", faculty);
		req.setAttribute("oldExam", exam);
		req.setAttribute("exams", Examination.values());
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/requirementedit.jsp").forward(req, resp);

	    } catch (Exception e) {
		resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
	    }

	} else {
	    resp.sendRedirect(req.getContextPath() + "/index.html");
	}

    }

}
