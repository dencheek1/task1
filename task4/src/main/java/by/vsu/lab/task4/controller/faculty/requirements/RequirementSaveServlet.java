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

public class RequirementSaveServlet extends BaseServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	FacultyRequirement facultyReq = new FacultyRequirement();
	logger.debug("Save new requirement,\nisNew={},\nAll atribut={}", req.getParameter("isNew"),
		req.getAttributeNames().toString());
	Examination oldExam = null;
	try {
	    Faculty faculty = new Faculty();
	    faculty.setId(Long.parseLong(req.getParameter("faculty_id")));
	    facultyReq.setFaculty(faculty);
	    facultyReq.setGroup(Integer.parseInt(req.getParameter("group")));
	    facultyReq.setValue(Integer.parseInt(req.getParameter("value")));
	    facultyReq.setExam(Examination.valueOf((req.getParameter("exam"))));
	    if(!req.getParameter("oldExam").isEmpty())
	    oldExam = Examination.valueOf(req.getParameter("oldExam"));
	} catch (NumberFormatException | NullPointerException  e) {
	    //resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
	    logger.warn(e);
	}
	logger.debug(oldExam);
	if (facultyReq.getExam() != null  ) {
	    try (ServiceFactory factory = getServiceFactory()) {
		if (req.getParameter("isNew").equals("true")) {
		    logger.info("Create new requirement");
		    FacultyRequirement existReq = factory.getFacultyRequirementsService().read(facultyReq.getFaculty(),
			    facultyReq.getExam());
		    if (existReq != null) {
			factory.getFacultyRequirementsService().update(facultyReq);
		    } else {
			factory.getFacultyRequirementsService().create(facultyReq);
		    }
		} else {
		    FacultyRequirement existReq = null;
		    logger.debug(existReq);
		    if(oldExam != null) {
			logger.debug(existReq);
			existReq = factory.getFacultyRequirementsService().read(facultyReq.getFaculty(),
				    oldExam);
		    }
		    logger.debug(existReq);
		    factory.getFacultyRequirementsService().update( existReq,facultyReq);
		}

	    } catch (Exception e) {
		throw new ServletException(e);
	    }
	}

	resp.sendRedirect(req.getContextPath() + "/requirements.html?id=" + facultyReq.getFaculty().getId());
    }

}
