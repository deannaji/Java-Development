package com.cpsc476.site;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cpsc476.site.url.Url;
import com.cpsc476.site.url.UrlDaoInterface;
import com.cpsc476.site.user.UserDaoInterface;

import java.util.Base64;
import java.util.Map;

@Controller
public class PrivateController {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	public int numusers = 0; // number of users
	UserDaoInterface userDao;
	UrlDaoInterface urlDao;

	@Inject
	public void setUserDaoInterface(UserDaoInterface userDao) {
		this.userDao = userDao;
	}

	@Inject
	public void setUrlDaoInterface(UrlDaoInterface urlDao) {
		this.urlDao = urlDao;
	}

	@RequestMapping(value = { "/private" }, method = RequestMethod.GET)
	public String getPrivate(HttpServletRequest request, Map<String, Object> model,
			@RequestParam("action") String action) throws ServletException, IOException {

		if (request.getSession().getAttribute("username") == null) {

			return "login";
		}

		// redirect to long url when you click on short url link

		if (action == null) {
			action = "view";

		}

		if (action.equals("redirect")) {
			String url = (String) request.getParameter("url");
			String username = (String) request.getSession().getAttribute("username");

			Url db = (Url) urlDao.getlongurl(url);
			urlDao.updateclicks(url, username);
			String s = db.getLongurl();
			if (s.contains("http://") || s.contains("https://")) {
				return "redirect:" + s;
			} else {
				String x = "http://" + s;
				return "redirect:" + x;
			}

		}
		if (action.equals("view")) {

			String uname = (String) request.getSession().getAttribute("username");
			model.put("username", uname);

			String x[][] = this.showListURL(request, model); // shows list of
			// urls
			// shortened

			model.put("url", x);
			model.put("urlpresent", false);

		}
		return "private";

	}

	@RequestMapping(value = { "/private" }, method = RequestMethod.POST)
	public String doPost(HttpServletRequest request, Map<String, Object> model,
			@ModelAttribute("longurlform") Url urlform) throws ServletException, IOException {

		String surl = "";

		String uname = (String) request.getSession().getAttribute("username");

		if (uname == null) {

			return "login";

		}

		model.put("username", uname);

		String lurl = urlform.getLongurl();

		if (lurl == null || lurl.equals("")) {

			model.put("urlpresent", true);

			String x[][] = this.showListURL(request, model);

			model.put("shorturl", "");

			model.put("longurl", "");

			model.put("url", x);

			return "private";

		}

		else {

			surl = this.createURL(request, lurl);

			Url dao = (Url) urlDao.checkforUrl(uname, lurl);

			if (dao.geturlCount() == 0) {

				urlDao.insertoneurl(uname, lurl, surl, 0); // insert into
															// Database for

				String x[][] = this.showListURL(request, model);

				model.put("url", x);

				model.put("shorturl", surl);

				model.put("longurl", lurl);

				model.put("urlpresent", false);

				return "private";

			} else {

				String x[][] = this.showListURL(request, model);

				model.put("shorturl", surl);

				model.put("longurl", lurl);

				model.put("url", x);

				model.put("urlpresent", true);

				return "private";

			}

		}

	}

	// performs Base64 encoding
	private String createURL(HttpServletRequest request, String lurl) throws ServletException, IOException {

		String shorturlstr = Base64.getUrlEncoder().encodeToString(lurl.getBytes("utf-8")).substring(0, 10);
		String shorturl = "http://localhost:8080/short/" + shorturlstr;
		return shorturl;

	}

	// display user's personal shortened url list
	private String[][] showListURL(HttpServletRequest request, Map<String, Object> model)
			throws ServletException, IOException {

		String uname = (String) request.getSession().getAttribute("username");
		String[][] myStringArray = new String[100][100]; // stores the list of
		// urls and clicks
		// which will be
		// displayed ons jsp
		// page
		int i = 0;
		for (Url s : urlDao.findAllurl(uname)) {

			myStringArray[i][0] = s.getSUrl();
			myStringArray[i][1] = String.valueOf(s.getClicks());

			i++;
		}

		return myStringArray;
	}

}