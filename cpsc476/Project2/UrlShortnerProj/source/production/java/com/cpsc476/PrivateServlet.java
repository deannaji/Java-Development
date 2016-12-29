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

import java.util.Base64;


@WebServlet("/private")
public class PrivateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int numusers = 0; // number of users
	UrlDaoInterface urlDao;
	WebApplicationContext ctx;

	@Override
	public void init(ServletConfig config)
			throws ServletException{
		super.init(config);
		ServletContext context = getServletContext();
		ctx = WebApplicationContextUtils.getWebApplicationContext(context);
	} 



	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		urlDao= (UrlDaoInterface) ctx.getBean( "UrlDao" );
		if (request.getSession().getAttribute("username") == null) {
			response.sendRedirect("login");
			return;
		}

		// redirect to long url when you click on short url link
		String action = (String) request.getParameter("action");
		if (action == null) {
			action = "view";


		} 

		String tsturl = request.getRequestURL().toString();
		System.out.println("testurl"+tsturl);

		if (action.equals("redirect")) {
			String url = (String) request.getParameter("url");
			String username = (String) request.getSession().getAttribute("username");

			Url db = (Url)urlDao.getlongurl(url);
			urlDao.updateclicks(url, username);

			if(db.getLUrl().contains("http://")|| db.getLUrl().contains("https://") ){
				response.sendRedirect( db.getLUrl());
				return;
			}
			else{
				response.sendRedirect("http://" + db.getLUrl());
			}

		}
		if (action.equals("view")) {

			String uname = (String) request.getSession().getAttribute("username");
			request.setAttribute("username", uname);

			String x[][] = this.showListURL(request, response); // shows list of
			// urls
			// shortened

			request.setAttribute("url", x);
			request.setAttribute("urlpresent", false);

			request.getRequestDispatcher("/WEB-INF/jsp/view/private.jsp").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)

			throws ServletException, IOException {

			String surl = "";

			String uname = (String) request.getSession().getAttribute("username");

			if (uname == null) {

			response.sendRedirect("login");

			return;

			}

			request.setAttribute("username", uname);

			String lurl = request.getParameter("longurl");


			if(lurl.equals("") || lurl == null){

			request.setAttribute("urlpresent", true);

			String x[][] = this.showListURL(request, response);

			request.setAttribute("shorturl", "");

			request.setAttribute("longurl", "");

			request.setAttribute("url", x);

			request.getRequestDispatcher("/WEB-INF/jsp/view/private.jsp").forward(request, response);



			}

			else{

			surl = this.createURL(request, response);


			Url dao = (Url)urlDao.checkforUrl(uname, lurl);

			if (dao.geturlCount() == 0){

			urlDao.insertoneurl(uname, lurl, surl,0); // insert into Database for

			String x[][] = this.showListURL(request, response);

			request.setAttribute("url", x);

			request.setAttribute("shorturl", surl);

			request.setAttribute("longurl", lurl);

			request.setAttribute("urlpresent", false);

			request.getRequestDispatcher("/WEB-INF/jsp/view/private.jsp").forward(request, response);





			}else{

			String x[][] = this.showListURL(request, response);

			request.setAttribute("shorturl", surl);

			request.setAttribute("longurl", lurl);

			request.setAttribute("url", x);

			request.setAttribute("urlpresent", true);

			request.getRequestDispatcher("/WEB-INF/jsp/view/private.jsp").forward(request, response);

			}

			}



			}

	// performs Base64 encoding
	private String createURL(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String longurl1 = request.getParameter("longurl");

		String shorturlstr = Base64.getUrlEncoder().encodeToString(longurl1.getBytes("utf-8")).substring(0, 10);
		String shorturl = "http://localhost:8080/short/" + shorturlstr;
		return shorturl;

	}

	// display user's personal shortened url list
	private String[][] showListURL(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uname = (String) request.getSession().getAttribute("username");
		String[][] myStringArray = new String[100][100]; // stores the list of
		// urls and clicks
		// which will be
		// displayed ons jsp
		// page
		int i = 0;
		for(Url s : urlDao.findAllurl(uname)){
			System.out.println("list of short urls"+ s.getSUrl());	
			myStringArray[i][0] = s.getSUrl();
			myStringArray[i][1] = String.valueOf(s.getClicks());
			System.out.println("total number of clicks"+myStringArray[i][1]);
			i++;
		}


		return myStringArray;
	}



}