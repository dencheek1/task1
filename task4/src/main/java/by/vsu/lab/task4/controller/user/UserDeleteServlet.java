package by.vsu.lab.task4.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.vsu.lab.task4.controller.BaseServlet;
import by.vsu.lab.task4.entitys.User;
import by.vsu.lab.task4.entitys.UserType;
import by.vsu.lab.task4.service.UserService;
import by.vsu.lab.task4.util.ServiceFactory;

public class UserDeleteServlet extends BaseServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {

	HttpSession session = req.getSession(false);
	User currentUser = (User) session.getAttribute("currentUser");
	Long id = null;
	UserType type = UserType.USER;
	try {
	    id = Long.parseLong(req.getParameter("user_id"));
	} catch (NumberFormatException e) {
	}

	try (ServiceFactory factory = getServiceFactory()) {
	    UserService service = factory.getUserService();
	    if (id != null && currentUser != null) {
		User user = service.find(id);
		logger.debug(currentUser);
		if (user != null && currentUser.getType().equals(UserType.ADMIN)) {
		    	factory.getUserService().delete(user);
			resp.sendRedirect(req.getContextPath() + "/user/list.html");
			return;
		    }
		
	    }
	} catch (Exception e) {
	    logger.warn(e);
	}

	resp.sendRedirect(req.getContextPath() + "/login.html");
    }
    
}
