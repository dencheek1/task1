package by.vsu.lab.task4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.vsu.lab.task4.entitys.User;
import by.vsu.lab.task4.service.UserService;
import by.vsu.lab.task4.service.exception.ServiceException;

public class LoginServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	HttpSession session = req.getSession(false);
	User user = null;
	if(session != null) {
	    user = (User) session.getAttribute("currentUser");
	}
	req.setAttribute("currentUser",user);
	getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String login = req.getParameter("login");
	String password = req.getParameter("password");
	
	if(login != null && password != null) {
	    UserService service = getServiceFactory().getUserService();
	    User user = null;
	    try {
		user = service.findBy(login, password);
	    } catch (ServiceException e) {
		e.printStackTrace();
	    }
	    if(user != null) {
		HttpSession session = req.getSession();
		session.setAttribute("currentUser", user);
		resp.sendRedirect(req.getContextPath()+ "/index.html?message=User " + user.getLogin());
	    }
	}
	else {
	    getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp?message=elogin error").forward(req, resp);
	}
    }

}
