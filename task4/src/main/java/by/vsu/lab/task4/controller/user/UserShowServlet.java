package by.vsu.lab.task4.controller.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vsu.lab.task4.controller.BaseServlet;
import by.vsu.lab.task4.entitys.User;
import by.vsu.lab.task4.util.ServiceFactory;

public class UserShowServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	    throws ServletException, IOException {

	try (ServiceFactory factory = getServiceFactory()) {
	    List<User> users = factory.getUserDao().readAll();
	    Map<User, Boolean> usersMap = new HashMap<User, Boolean>();
	    for(User user: users) {
		usersMap.put(user,factory.getApplicantService().findById(user.getId())!=null);
	    }
	    req.setAttribute("users", usersMap);
	    getServletContext().getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(req, resp);
	} catch (Exception e) {
	    throw new ServletException(e);
	}
	
    }   
    
}
