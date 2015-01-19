package org.sg.eventcalendar.core.services;


/**
 * Publiczny interfejs dla EmailSerivice	
 */
public interface IEmailService {

	/**
	 * Wysyła email
	 *
	 * @param email adres email odbiorcy
	 * @param subject temat
	 * @param content treść
	 */
	public void sendEMail(String email, String subject, String content);

}