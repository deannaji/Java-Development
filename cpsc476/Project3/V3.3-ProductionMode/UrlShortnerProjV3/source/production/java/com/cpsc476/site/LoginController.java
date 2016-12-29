package com.cpsc476.site;

import java.io.IOException;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.cpsc476.site.user.User;
import com.cpsc476.site.user.UserDaoInterface;

@Controller
public class LoginController {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	UserDaoInterface userDao;

	@Inject
	public void setUserDaoInterface(UserDaoInterface userDao) {
		this.userDao = userDao;
	}


	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginhome(Map<String, Object> model, HttpServletRequest request) {

		HttpSession session = request.getSession();

		if (session.getAttribute("username") != null) {

			return "redirect:/private?action=view";

		}

		model.put("loginFailed", false);
		model.put("userdoesnotexists", false);

		return "login";

	}

	// Implementing the form handling

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	protected String login(HttpServletRequest request, @ModelAttribute("loginForm") User userForm,
			Map<String, Object> model) throws ServletException, IOException {

		String username = userForm.getUsername();
		String password = userForm.getPassword();
		HttpSession session = request.getSession();

		if (session.getAttribute("username") != null) {

			return "private";

		}

		User dao = (User) userDao.getOneRow(username, password);

		int count = dao.getuserCount();

		if (username == null || password == null || count == 0 || username.equalsIgnoreCase("")
				|| password.equalsIgnoreCase("")) {

			model.put("loginFailed", true);
			model.put("userdoesnotexists", false);
			return "login";

		}

		session.setAttribute("username", username);
		return "redirect:/private?action=view";

	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	protected String newUser(HttpServletRequest request, @ModelAttribute("signUpForm") User userForm,
			Map<String, Object> model) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String username = userForm.getUsername();
		String password = userForm.getPassword();

		if (username == null || password == null || username.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {

			model.put("userdoesnotexists", false);
			model.put("loginFailed", true);
			return "login";
		} else {
			
			User dao = (User) userDao.checkforuser(username);
			int count = dao.getuserCount();

			if (dao.getuserCount() != 0) {

				model.put("userdoesnotexists", true);
				model.put("loginFailed", false);
				return "login";

			}

			else if (count == 0) {

				userDao.insertOneRow(username, password);
				session.setAttribute("username", username);
				return "redirect:/private?action=view";

			}
			return "redirect:/private?action=view";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/login";
	}

}
