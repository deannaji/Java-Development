	package com.cpsc476;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//(FreeMarker)creating configuration:
    Configuration config = new Configuration(Configuration.VERSION_2_3_25);
    Map<String, Object> root = new HashMap<>();//FreeMarker model
	protected static pojo[] pj=new pojo[100];	//array of user objects

	@Override public void init(){
    	//(FreeMarker)looking for templates in a specific (user defined) path:
    	config.setServletContextForTemplateLoading(this.getServletContext(), "WEB-INF/ftl");					
    }
	
	
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
	            
	    	    //(FreeMarker):  	
	    		Template guestTemplate = config.getTemplate("login.ftl");//setting the template .ftl file	    		
				PrintWriter writer = response.getWriter();    
				
	    		
	    	
	        if(request.getParameter("logout") != null){
	        	session.invalidate();
	            response.sendRedirect("public");
	            return;

	        }

	        else if(session.getAttribute("username") != null){
	            response.sendRedirect("private");
	            return;
	        }

	        
	        //Template processing:
	        root.put("loginFailed", false);
	        try {
				guestTemplate.process(root, writer);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
	        
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
	    	//(FreeMarker):  	
    		Template loginTemplate = config.getTemplate("login.ftl");//setting the template .ftl file	    		
			PrintWriter writer = response.getWriter();    
			
	    	HttpSession session = request.getSession();
	        if(session.getAttribute("username") != null){
	            response.sendRedirect("private?action=view");
	            return;
	        }

	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
            
	        
	        if(username == null || password == null || username.equals("") || password.equals("") ||
	                !LoginServlet.userDatabase.containsKey(username) ||
	                !password.equals(LoginServlet.userDatabase.get(username))){

	        	//Template processing:
		        root.put("loginFailed", true);
		        try {
					loginTemplate.process(root, writer);
				} catch (TemplateException e) {
					e.printStackTrace();
				}	
	            
	        }

	        else{
	            session.setAttribute("username", username);
	            response.sendRedirect("private?action=view");
	        }
	    }

	   

	    private void newUser(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
	    	Template loginTemplate = config.getTemplate("login.ftl");//setting the template .ftl file
	        String username = request.getParameter("new_user");
	        String password = request.getParameter("new_password");
	        
	        if (username == null || password == null || username.equals("") || password.equals("")){
	        	//Template processing:
		        root.put("loginFailed", true);
		        try {
					loginTemplate.process(root, response.getWriter());
				} catch (TemplateException e) {
					e.printStackTrace();
				}
		        return;
	        }
	        else{
	            LoginServlet.userDatabase.put(username, password);
	            HttpSession session = request.getSession();
	            session.setAttribute("username", username);
	            response.sendRedirect("private?action=view");
	        }
	        

	    }

}
