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
import org.sg.eventcalendar.core.services.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.users.UserServiceFactory;

// TODO: Auto-generated Javadoc
/**
 * Klasa IndexController
 */
@Controller
@RequestMapping(value = "/")
public class IndexController {
		
	/** Odwołanie do rejestru użytkownikow */
	@Autowired
	private UserRegistry registry;
	
	/** Odwołanie do EmailService */
	@Autowired
	IEmailService emailService;

	/**
	 * Mapowanie strony głownej
	 *
	 * @return strona głowna
	 */
	@RequestMapping
	public String getIndexPage() {
		return "index";
	}
	
	/**
	 * Mapowanie strony z rejestracją
	 *
	 * @return register.html
	 */
	@RequestMapping(value = "register")
    public String registrationForm() {
        return "register";
    }

	/**
	 * Mapowanie strony disabled
	 *
	 * @return disabled.html
	 */
	@RequestMapping(value = "disabled")
	public String getDisabledPage() {
		return "disabled";
	}
	
	/**
	 * Mapowanie panelu użytkownika
	 *
	 * @return dashboard.html
	 */
	@RequestMapping(value = "dashboard")
	public String getDashboardPage() {
		return "dashboard";
	}

	/**
	 * Ządanie POST rejestrujące nowego użytkownika
	 *
	 * @param forename imię
	 * @param surname nazwisko
	 * @return przkierowanie do dashboard.html
	 */
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
	    
	    String content = "Właśnie dokonałeś rejestracji w systemie. Od teraz możesz korzystać "
	    		+ "z aplikacji logując się za pomocą swojego konta Google.";
	    emailService.sendEMail(currentUser.getEmail(),"Witaj w aplikacji 'Kalendarz wydarzeń'", content);

	    // Update the context with the full authentication
	    SecurityContextHolder.getContext().setAuthentication(new GaeUserAuthentication(user, authentication.getDetails()));

	    return "redirect:/dashboard";
	}
	
	/**
	 * Ządanie GET wylogowujące użytkownika
	 *
	 * @param request żądanie
	 * @param response odpowiedź serwera
	 * @throws IOException wyjątek wyjścia/wejścia
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		request.getSession().invalidate();
		String logoutUrl = UserServiceFactory.getUserService().createLogoutURL(
				"/");
		response.sendRedirect(logoutUrl);
	}
	
	/**
	 * Ządanie GET pobierające informacje o zalogowanym użytkowniku
	 *
	 * @return obiekt z zalogowanym użytkownikiem
	 */
	@RequestMapping(value = "currentUser", method = RequestMethod.GET)
	public @ResponseBody GaeUser getCurrentUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    GaeUser currentUser = (GaeUser)authentication.getPrincipal();
		
	    return currentUser;
	}
}
