package com.cpsc476;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/short/*")
public class redirection extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        	
	        	
	        	String shortUrl = request.getRequestURL().toString();
	       
	        	System.out.println(shortUrl);
	        	
	        	String longUrl="";
	        	for(String item: PublicServlet.database.keySet()){
	        	    Url urlObj= (Url)PublicServlet.database.get(item);
	        	    if(urlObj.getUrl().equals(shortUrl)){
	        	    	longUrl=item;
	        	    	break;
	        	    }
	        	}
	        	if(longUrl.contains("http://")|| longUrl.contains("https://") ){
					response.sendRedirect( longUrl);
				return;
				}
				else{
					response.sendRedirect("http://" + longUrl);
				}
	}

}
