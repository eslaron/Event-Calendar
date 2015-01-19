package org.sg.eventcalendar.core.services;

import java.util.List;

import org.sg.eventcalendar.core.entities.Event;

/**
 * Publiczny interfejs dla EventService
 */
public interface IEventService {
	
	/**
	 * Tworzy wydarzenie.
	 *
	 * @param entity obiekt klasy Event
	 */
	public void createEvent(Event entity);  
    
	/**
	 * Znajduje wszystkie wydarzenia
	 *
	 * @return List<Event> list wydarzeń
	 */
	public List<Event> findAllEvents(); 
	
	/**
	 * Znajduje wydarzenie po id
	 *
	 * @param id 
	 * @return obiekt klasy Event
	 */
	public Event findEventById(long id);
	
	/**
	 * Aktualizuje wydarzenie
	 *
	 * @param entity obiekt klasy Event
	 */
	public void updateEvent(Event entity);
	
	/**
	 * Usuwa wydarzenie
	 *
	 * @param entity obiekt klasy Event
	 */
	public void deleteEvent(Event entity);  

    /**
     * Usuwa wydarzenie po id
     *
     * @param id 
     */
    public void deleteEventById(long id);
    
    /**
     * Usuwa wydarzenie według statusu
     *
     * @param status status wydarzenia(w trakcie, zakończone, niezakończone)
     */
    public void deleteEventByStatus(String status);
}