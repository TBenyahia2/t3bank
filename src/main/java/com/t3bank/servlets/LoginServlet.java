package com.t3bank.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.t3bank.dao.DAOImpl;
import com.t3bank.model.User;


/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);
	private static DAOImpl dao = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }
    
    

    @Override
	public void init() throws ServletException {
		super.init();
		try {
			dao = new DAOImpl();
		} catch (Exception e) {
			LOGGER.warn("Problem: LoginServlet init()");
			e.printStackTrace();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/html;charset=UTF-8");
	    //PrintWriter out = response.getWriter();
	    int user_id = Integer.parseInt(request.getParameter("user_id"));
	    String password = request.getParameter("password");
	    try {
	    	User user = dao.validateUser(user_id, password);
	    	String destination = "index.jsp";
	    	if (user != null) {	    		
	    		HttpSession session = request.getSession();
	    		session.setAttribute("user", user);	    		
	    		if(user.getRole() == 1) {
	    			destination = "CustControlServlet";
	    		} else if (user.getRole() == 2) {
	    			destination = "EmpControlServlet";
	    		}
	    	} else {
	    		String message = "Invalid User ID/Password. Try Again.";
	    		request.setAttribute("message", message);
	    	}
	    	RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
	    	dispatcher.forward(request, response);
	    } catch (Exception e) {
	    	LOGGER.warn("Problem in LoginServlet doPost");
	    	throw new ServletException(e);
	    } 
	     	
	    	
	}
}

