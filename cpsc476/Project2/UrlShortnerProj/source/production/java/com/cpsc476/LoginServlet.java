package com.cpsc476;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDaoInterface userDao;
	WebApplicationContext ctx;
	
	@Override
	   public void init(ServletConfig config)
	           throws ServletException{
	       super.init(config);
	       ServletContext context = getServletContext();
	       ctx = WebApplicationContextUtils.getWebApplicationContext(context);
	   } 
	
	   

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	
	    	
	    	userDao= (UserDaoInterface) ctx.getBean( "UserDao" );	       	
	    	
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
	        request.setAttribute("userdoesnotexists", false);
	        request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
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
	        System.out.println("username"+username);
	        Url dao = (Url)userDao.getOneRow(username,password);
	        
	        int count=dao.getuserCount();
	        
	        if(username == null || password == null ||count==0||username.equalsIgnoreCase("")||password.equalsIgnoreCase("")){

	            request.setAttribute("loginFailed", true);
	            request.setAttribute("userdoesnotexists", false);
	            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);

	        }

	        else{

	            session.setAttribute("username", username);
	            response.sendRedirect("private?action=view");

	        }
	    }

	   

	    private void newUser(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{

	        HttpSession session = request.getSession();

	            String username = request.getParameter("new_user");

	            String password = request.getParameter("new_password");



	            Url dao = (Url)userDao.checkforuser(username);

	            int count=dao.getuserCount();

	            int c = 1;

	           

	            if(dao.getuserCount() != 0){

	           

	            request.setAttribute("userdoesnotexists", true);

	            request.setAttribute("loginFailed", false); 

	            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);  

	           



	            }else if(username.equalsIgnoreCase("")||password.equalsIgnoreCase("")||username == null || password == null){

	            request.setAttribute("userdoesnotexists", false);

	            request.setAttribute("loginFailed", true); 

	            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);  



	            }

	            else if(count==0){

	            userDao.insertOneRow(username,password);

	            session.setAttribute("username", username);

	            response.sendRedirect("private?action=view"); 

	            }

	           

	           

	        }

}
