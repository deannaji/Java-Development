package com.cpsc476;

import java.io.IOException;
import java.util.Hashtable;
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
       
    private static final Map<String,String> usersDB = new Hashtable<>();
    static{
      usersDB.put("dean","dean" );
      usersDB.put("admin","admin");
    }
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	   HttpSession session = request.getSession();
  	   
  	   if(request.getParameter("logout")!= null){
  		   session.invalidate();
  		   response.sendRedirect("login");
  		   return;
  	   }else if(session.getAttribute("username") != null){
           response.sendRedirect("tickets");
           return;
       }
  	   
  	   request.setAttribute("loginFailed", false);
  	   request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
	}
    
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = request.getSession();
      if(session.getAttribute("username") != null){
            response.sendRedirect("tickets");
            return;
        }
    	
      String username= request.getParameter("username");
	  String password= request.getParameter("password");
	  
	  if (username ==null || password==null || !usersDB.containsKey(username) || !password.equals(usersDB.get(username)) ){
		  request.setAttribute("loginFailed",true);
		  request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
	  }
	  else{
		  session.setAttribute("username", username);
		  request.changeSessionId();
		  response.sendRedirect("tickets");
	  }
	}

}
