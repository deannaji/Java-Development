	package com.cpsc476;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected static pojo[] pj=new pojo[100];	//array of user objects

	private static Map<String, String> userDatabase = new HashMap<>();
	
	    static {
	        userDatabase.put("Nicholas", "password");
	        userDatabase.put("Sarah", "drowssap");
	        userDatabase.put("Mike", "wordpass");
	        userDatabase.put("John", "green");
	    }

	    public Map<String, String> getPeopleMap() { 	
	        return userDatabase;
	   }

	    	    

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	
	    	HttpSession session = request.getSession();
	    	
	        if(request.getParameter("logout") != null){
	        	session.invalidate();
	            response.sendRedirect("public");
	            return;

	        }

	        else if(session.getAttribute("username") != null){
	            response.sendRedirect("private");
	            return;
	        }

	        request.setAttribute("loginFailed", false);
	        request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp")
	               .forward(request, response);
	    }



	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	    	
	    	String action = request.getParameter("action");

	    	switch(action){
	    	case "login":
	    		login(request,response);
	    		break;

	    	case "signup":
	    		newUser(request,response);
	    		break;

	    	default:
	    		System.out.println("internal error");

	    	}
	    }

	    
	    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

	    	HttpSession session = request.getSession();
	        if(session.getAttribute("username") != null){
	            response.sendRedirect("private?action=view");
	            return;

	        }


	        String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        if(username == null || password == null ||
	                !LoginServlet.userDatabase.containsKey(username) ||
	                !password.equals(LoginServlet.userDatabase.get(username))){

	            request.setAttribute("loginFailed", true);
	            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp")
	                   .forward(request, response);

	        }

	        else{

	            session.setAttribute("username", username);
	            response.sendRedirect("private?action=view");

	        }
	    }

	   

	    private void newUser(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{

	        String username = request.getParameter("new_user");
	        String password = request.getParameter("new_password");

	        LoginServlet.userDatabase.put(username, password);

	        

	        HttpSession session = request.getSession();

	        session.setAttribute("username", username);
	        response.sendRedirect("private?action=view");

	        

	    }

}
