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

public class UserCreateServlet extends BaseServlet {

    private static final Logger logger = LogManager.getLogger();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	HttpSession session = req.getSession(false); 
	if(session != null) {
	    User currentUser = (User) session.getAttribute("currentUser");
	    logger.debug(currentUser);
	    if(currentUser != null && UserType.ADMIN.equals(currentUser.getType())) {
		User user = null;
		String login = null;
		String password = null;
		login = req.getParameter("login");
		
		if(login != null && !login.isBlank()) {
		    try(ServiceFactory factory = getServiceFactory()){
			UserService service = factory.getUserService();
			user = new User();
			user.setType(UserType.USER);
			user.setLogin(login);
			logger.debug(user);
			if(!service.isExists(login)) {
			    service.create(user);
			    resp.sendRedirect(req.getContextPath() +"/user/list.html" );
			}else resp.sendRedirect(req.getContextPath()+ "/user/add.html?id=0&message=User alrety exist");
		    } catch (Exception e) {
			logger.warn("Exception{}, message={}", e,"Oh shit, here we go again!!" );
		    }
		}
	    }else
	    logger.warn("Current user {}, message={}", currentUser,"Yeah, you not admin :)" );
	}
	else resp.sendRedirect(req.getContextPath()+ "/login.html");
    }

    
    
}
