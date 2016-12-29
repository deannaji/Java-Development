package com.cpsc476;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/private")
public class PrivateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int numusers = 0; // number of users
	
	//(FreeMarker)creating configuration:
    Configuration config = new Configuration(Configuration.VERSION_2_3_25);
    //(FreeMarker)getting the model:		
	Map<String, Object> root = new HashMap<>();
	
    
	@Override public void init(){
    	//(FreeMarker)looking for templates in a specific (user defined) path:
    	config.setServletContextForTemplateLoading(this.getServletContext(), "WEB-INF/ftl");					
    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getSession().getAttribute("username") == null) {
			response.sendRedirect("login");
			return;
		}

		// redirect to long url when you click on short url link
		String action = (String) request.getParameter("action");
		if (action == null) {
			action = "view";
		}

		if (action.equals("redirect")) {
			String url = (String) request.getParameter("url");
			String longUrl = "";

			for (String item : PublicServlet.database.keySet()) {
				Url shortUrl = (Url) PublicServlet.database.get(item);
				if (shortUrl.getUrl().equals(url)) {
					longUrl = item;
					shortUrl.Click(); // increment clicks
					if(longUrl.contains("http://")|| longUrl.contains("https://") ){
						response.sendRedirect( longUrl);
					return;
					}
					else{
						response.sendRedirect("http://" + longUrl);
					}
				}
			}

		}
		if (action.equals("view")) {

			String uname = (String) request.getSession().getAttribute("username");
			String urlsList[][] = this.showListURL(request, response); // shows list of
										        						// urls
												        				// shortened
			
			List<ShortUrl> urlObjects = new ArrayList<>();// This list stores ShortUrl objects, to be sent to the view. 
			for (String[] item : urlsList){
				if (item[0] == null){
					//do nothing...
				}
				else{
	                ShortUrl urlObj = new ShortUrl();//An instance of ShortUrl object.		
	 		        urlObj.setUrl(item[0]);
	 		        urlObj.setClicks(item[1]);
			        urlObjects.add(urlObj);
				}
			}
			
			//(FreeMarker)creating free marker Template, and tell it where the template file is:			
			Template privateTemplate = config.getTemplate("private.ftl");
			//(FreeMarker) loading the root (the model) with values: 
    		root.put("username", uname);
    		root.put("url", urlObjects);
    		
			try {
    			//(FreeMarker)feeding FreeMarker template with both the model & the writer: 
				privateTemplate.process(root, response.getWriter());
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uname = (String) request.getSession().getAttribute("username");
		if (uname == null) {
			response.sendRedirect("login");
			return;
		}
		String lurl = request.getParameter("longurl");
		String surl = this.createURL(request, response); // shortens url
		String urlsList[][] = this.showListURL(request, response);
   
		//(FreeMarker)creating free marker Template, and tell it where the template file is:
		List<ShortUrl> urlObjects = new ArrayList<>();
		for (String[] item : urlsList){
			if (item[0] == null){
               //do nothing...				
			}
			else{
           ShortUrl urlObj = new ShortUrl();		
 		   urlObj.setUrl(item[0]);
 		   urlObj.setClicks(item[1]);
		    urlObjects.add(urlObj);
			}
		}
		
		Template privateTemplate = config.getTemplate("private.ftl");
		root.put("username", uname);
		root.put("urls", urlObjects);
		root.put("short", surl);
		root.put("long", lurl);
		
		
		
		try {
			//(FreeMarker)feeding FreeMarker template with both the model & the writer: 
			privateTemplate.process(root, response.getWriter());
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	// performs Base64 encoding
	private String createURL(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String longurl1 = request.getParameter("longurl");

		String shorturlstr = Base64.getUrlEncoder().encodeToString(longurl1.getBytes("utf-8")).substring(1, 8);

		String shorturl = "http://localhost:8080/short/" + shorturlstr;
		Url u = new Url(shorturl);
		String uname = (String) request.getSession().getAttribute("username");

		if (!PublicServlet.database.containsKey(longurl1)) {
			PublicServlet.database.put(longurl1, u); // insert into Database for
														// public page
		}

		// insert into database for user's private object
		Boolean b = false; // user not present
		int m = 0;

		// checks if current user is already present in array of user objects
		for (int i = 0; i < numusers; i++) {
			if (LoginServlet.pj[i].username.equals(uname)) {
				b = true;
				m = i;
				break;
			}
		}

		// if user is not present create new user object and insert url in the
		// object
		if (b != true) {
			LoginServlet.pj[numusers] = new pojo(uname);
			LoginServlet.pj[numusers].insertUrl(longurl1, u);
			
			numusers++;
		} // if user is present just insert url in the object
		else {
			LoginServlet.pj[m].insertUrl(longurl1, u);
			

		}

		return shorturl;

	}

	// display user's personal shortened url list
	private String[][] showListURL(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uname = (String) request.getSession().getAttribute("username");
		int m = -1; // assuming user not present

		// checks if current user is already present in array of user objects

		for (int i = 0; i < numusers; i++) {
			if (LoginServlet.pj[i].username.equals(uname)) {
				m = i;

				break;
			}
		}

		String[][] myStringArray = new String[100][100]; // stores the list of
															// urls and clicks
															// which will be
															// displayed ons jsp
															// page

		int i = 0;
		if (m != -1) {
			int n = LoginServlet.pj[m].database.size();
			Url values[] = new Url[n];

			// stores short url and clicks for all urls user has shortened
			// inside myStringArray
			for (Map.Entry<String, Object> entry : LoginServlet.pj[m].database.entrySet()) {
				String key = entry.getKey();
				values[i] = (Url) entry.getValue();
				myStringArray[i][0] = values[i].getUrl();
				int j = PrivateServlet.showClicks(key); // getting clicks from
														// public db:
														// PublicServlet.database
				myStringArray[i][1] = new Integer(j).toString();
				i++;
			}

		}

		return myStringArray;
	}

	// return clicks from common url database for all
	// users-PublicServlet.database

	public static int showClicks(String s) {

		int click = 0;

		for (String item : PublicServlet.database.keySet()) {
			Url shortUrl = (Url) PublicServlet.database.get(item);
			if (item.equals(s)) {

				click = shortUrl.getClicks(); // get clicks

				return click;
			}
		}

		return click;
	}

}