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
import by.vsu.lab.task4.util.ServiceFactory;

public class UserEditServlet extends BaseServlet {

    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	User currentUser = null;
	User user = null;
	Long id = null;
	try {
	    id = Long.parseLong(req.getParameter("id"));
	} catch (NumberFormatException e) {
	}
	HttpSession session = req.getSession(false);
	if (session != null && id != null) {
	    currentUser = (User) (session.getAttribute("currentUser"));
	    try (ServiceFactory factory = getServiceFactory()) {
		user = factory.getUserService().find(id);
	    } catch (Exception e) {
		logger.warn(e);
	    }
	    if (user != null && currentUser != null) {
		if (user.getId() == currentUser.getId() || UserType.ADMIN.equals(currentUser.getType())) {
		    req.setAttribute("types", UserType.values());
		    req.setAttribute("isNew", false);
		    req.setAttribute("currentUser", currentUser);
		    req.setAttribute("user", user);
		    logger.debug(currentUser);
		    getServletContext().getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(req, resp);
		} else
		    resp.sendRedirect(req.getContextPath() + "/user/list.html?");
	    }
	    else resp.sendRedirect(req.getContextPath() + "/user/list.html?message=Ahahaha");
	} else {
	    resp.sendRedirect(req.getContextPath() + "/user/list.html?message=Ahahaha");
	}

    }

}
