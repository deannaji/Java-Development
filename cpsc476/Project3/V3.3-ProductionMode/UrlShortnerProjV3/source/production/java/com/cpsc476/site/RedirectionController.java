package com.cpsc476.site;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cpsc476.site.url.Url;
import com.cpsc476.site.url.UrlDaoInterface;
import com.cpsc476.site.user.UserDaoInterface;

@Controller
public class RedirectionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

	@RequestMapping(value = { "/S*" }, method = RequestMethod.GET)
	protected String redirect(HttpServletRequest request) throws IOException {

		String shortUrl = request.getRequestURL().toString();

		String longUrl = "";

		Url db = (Url) urlDao.getlongurl(shortUrl);

		longUrl = db.getLongurl();
		if (longUrl.contains("http://") || longUrl.contains("https://")) {
			return "redirect:" + longUrl;
		} else {
			return "redirect:" + "http://" + longUrl;
		}
	}

}
