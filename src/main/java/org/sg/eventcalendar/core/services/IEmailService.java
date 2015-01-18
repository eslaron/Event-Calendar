package org.sg.eventcalendar.core.services;

public interface IEmailService {

	public void sendEMail(String email, String subject, String content);

}