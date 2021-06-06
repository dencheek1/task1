package by.vsu.lab.task4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends BaseServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	HttpSession session = req.getSession(false);
	if(session != null) {
	    session.invalidate();
	}
	resp.sendRedirect(req.getContextPath()+"/");
    }

}
