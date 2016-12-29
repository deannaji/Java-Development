package com.cpsc476.site;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


@WebServlet("/short/*")
public class redirection extends HttpServlet {
	private static final long serialVersionUID = 1L;

WebApplicationContext ctx;
	
	@Override
	   public void init(ServletConfig config)
	           throws ServletException{
	       super.init(config);
	       ServletContext context = getServletContext();
	       ctx = WebApplicationContextUtils.getWebApplicationContext(context);
	   } 
	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
		UrlDaoInterface urlDao= (UrlDaoInterface) ctx.getBean( "UrlDao" );
	        	
	        	String shortUrl = request.getRequestURL().toString();
	       	        	
	        	String longUrl="";
	        		        	
	        	Url db = (Url)urlDao.getlongurl(shortUrl);
	        	System.out.println("redirection"+db.getLUrl());
	        	longUrl = db.getLUrl();
	        	if(longUrl.contains("http://")|| longUrl.contains("https://") ){
					response.sendRedirect( longUrl);
				return;
				}
				else{
					response.sendRedirect("http://" + longUrl);
				}
	}

}
