package org.sg.eventcalendar.core.services;

import java.util.List;

import org.sg.eventcalendar.core.entities.Event;

public interface IEventService {
	
	public void createEvent(Event entity);  
    
	public List<Event> findAllEvents(); 
	
	public Event findEventById(long id);
	
	public void updateEvent(Event entity);
	
	public void deleteEvent(Event entity);  

    public void deleteEventById(long id);
    
    public void deleteEventByStatus(String status);
}