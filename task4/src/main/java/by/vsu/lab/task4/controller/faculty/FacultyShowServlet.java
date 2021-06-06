package by.vsu.lab.task4.controller.faculty;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vsu.lab.task4.controller.BaseServlet;
import by.vsu.lab.task4.entitys.Applicant;
import by.vsu.lab.task4.entitys.Faculty;
import by.vsu.lab.task4.util.ServiceFactory;

public class FacultyShowServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	    throws ServletException, IOException {
		try(ServiceFactory factory = getServiceFactory();) {
			
			List<Faculty> faculty = factory.getFacultyService().findAll();
			req.setAttribute("faculty", faculty);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/faculty.jsp").forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
		}	
    }
   
}
