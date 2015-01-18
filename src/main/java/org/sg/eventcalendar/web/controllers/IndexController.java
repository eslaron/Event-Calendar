package org.sg.eventcalendar.web.controllers;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sg.eventcalendar.core.config.security.AppRole;
import org.sg.eventcalendar.core.config.security.GaeUser;
import org.sg.eventcalendar.core.config.security.GaeUserAuthentication;
import org.sg.eventcalendar.core.config.security.UserRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.appengine.api.users.UserServiceFactory;

@Controller
@RequestMapping(value = "/")
public class IndexController {
	
	
	@Autowired
	private UserRegistry registry;

	@RequestMapping
	public String getIndexPage() {
		return "index";
	}
	
	@RequestMapping(value = "register")
    public String registrationForm() {
        return "register";
    }

	@RequestMapping(value = "dashboard")
	public String getSecretPage() {
		return "dashboard";
	}

	@RequestMapping(value = "disabled")
	public String getDisabledPage() {
		return "disabled";
	}

	@RequestMapping(value="register", method = RequestMethod.POST)
	public String register(@RequestParam(value="forename") String forename, @RequestParam(value="surname") String surname) {
	  
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    GaeUser currentUser = (GaeUser)authentication.getPrincipal();
	    Set<AppRole> roles = EnumSet.of(AppRole.USER);

	    if (UserServiceFactory.getUserService().isUserAdmin()) {
	        roles.add(AppRole.ADMIN);
	    }

	    GaeUser user = new GaeUser(currentUser.getUserId(), currentUser.getNickname(), currentUser.getEmail(),
	             forename, surname, roles, true);

	    registry.registerUser(user);

	    // Update the context with the full authentication
	    SecurityContextHolder.getContext().setAuthentication(new GaeUserAuthentication(user, authentication.getDetails()));

	    return "redirect:/dashboard";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.getSession().invalidate();
		String logoutUrl = UserServiceFactory.getUserService().createLogoutURL(
				"/");
		response.sendRedirect(logoutUrl);
	}
}
