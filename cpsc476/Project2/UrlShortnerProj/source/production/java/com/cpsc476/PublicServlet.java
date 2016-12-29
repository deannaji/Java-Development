package com.cpsc476;

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

import com.cpsc476.Url;

@WebServlet("/public")
public class PublicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//The URL's database:
WebApplicationContext ctx;
UrlDaoInterface urlDao;	
	@Override
	   public void init(ServletConfig config)
	           throws ServletException{
	       super.init(config);
	       ServletContext context = getServletContext();
	       ctx = WebApplicationContextUtils.getWebApplicationContext(context);
	   } 
    


    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	urlDao= (UrlDaoInterface) ctx.getBean( "UrlDao" );
    	
    	//if no user is loged in take the client to the guest page:
    	if (request.getSession().getAttribute("username")==null){
    	request.getRequestDispatcher("/WEB-INF/jsp/view/guest.jsp").forward(request,response);
    	}
    	else{
    		response.sendRedirect("private?action=view");
    	}
	}
	
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		

		switch(action){
		case "guest":
		default:
			guest(request,response);
		}
    	
	}

    //The public user's post request method:
    private void guest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	//getting the short URL entered by the guest user:
    	String shortUrl= request.getParameter("shortUrl");
    	String originalUrl="";
    	
    	if(shortUrl==""){
    	    request.setAttribute("blank", true);
    	    request.setAttribute("emptyDB", false);
    	    request.setAttribute("itemnotfound", false);
    	}else if(urlDao.checkifUrlExists(shortUrl).geturlCount()!=0){
    	
    	//looking, in the URL DB, for the long version of the URL:     	
    	Url lrl = (Url)urlDao.getlongurl(shortUrl);
    	originalUrl = lrl.getLUrl();
    	System.out.println("originalurl"+lrl.getLUrl());
    	//if the DB is empty, i.e no URL's are stored yet:
	   if (!originalUrl.equals("")){	    
	    request.setAttribute("url", originalUrl);
	    request.setAttribute("emptyDB", false);
	    request.setAttribute("itemnotfound", false);
	    request.setAttribute("blank", false);
	    }	    
    	}
	    //finally, if the requested URL is not found in the DB:
    	else{
    		request.setAttribute("itemnotfound", true);
		    request.setAttribute("emptyDB", false);
		    request.setAttribute("blank", false);
    	}
	    request.getRequestDispatcher("WEB-INF/jsp/view/public.jsp").forward(request, response);
    }
    
	

}
