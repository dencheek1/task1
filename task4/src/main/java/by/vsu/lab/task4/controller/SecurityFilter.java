package by.vsu.lab.task4.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.vsu.lab.task4.entitys.User;
import by.vsu.lab.task4.entitys.UserType;

public class SecurityFilter implements Filter {

    private static final Map<String, Set<UserType>> permissions = new HashMap<>();
    
    private static final Logger logger = LogManager.getLogger();
    
    static {
	Set<UserType> all = new HashSet<>();
	all.addAll(Arrays.asList(UserType.values()));
	Set<UserType> admin = new HashSet<>();
	admin.add(UserType.ADMIN);

	permissions.put("/facultyedit", admin);
	permissions.put("/requirementedit", admin);
	permissions.put("/user/add", admin);
	permissions.put("/facultysave", admin);
	permissions.put("/facultydelete", admin);
	permissions.put("/requirementsave", admin);
	permissions.put("/requirementdelete", admin);
	
	permissions.put("/user/add", admin);
	permissions.put("/user/edit", admin);
	permissions.put("/user/list", admin);
	permissions.put("/user/create", admin);
	permissions.put("/user/create", admin);
	permissions.put("/user/update", admin);
	
	permissions.put("/requirementedit", all);
	permissions.put("/user/update", all);
	
	permissions.put("/applicant", all);
	permissions.put("/applicant/update", all);
	permissions.put("/applicant/registration", all);
	permissions.put("/applicant/edit", all);
	permissions.put("/applicant/update", all);
	permissions.put("/applicant/delete", all);
	permissions.put("/applicant/add", all);

	
	permissions.put("/applicant/resultedit", all);
	permissions.put("/applicant/result/delete", all);
	permissions.put("/applicant/result/create", all);
	permissions.put("/applicant/result/update", all);
	permissions.put("/applicant/resultadd",all);
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	HttpServletRequest httpReq = (HttpServletRequest)request;
	HttpServletResponse httpResp =(HttpServletResponse) response;
	
	String url = httpReq.getRequestURI();
	String context = httpReq.getContextPath();
	url = url.replaceAll(".html(?!.*\\.html)[\\w\\W]*", "");
	url = url.substring(context.length());
	System.out.println(url);
	logger.info(url);
	logger.debug(url);
	Set<UserType> types = permissions.get(url);
	if(types != null) {
	    HttpSession session = httpReq.getSession(false);
	    if(session != null) {
		User user = (User)session.getAttribute("currentUser");
		if(user != null && types.contains(user.getType())) {
		    chain.doFilter(request, response);
		    return ;
		}
	    }
	}
	else {
	    chain.doFilter(request, response);
	    return;
	}
	httpResp.sendRedirect(context + "/login.html");
    }

}
