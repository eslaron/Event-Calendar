package org.sg.eventcalendar.core.jpa;


/**
 * Publiczny interfejs dla EventDao
 */
public interface IEventDao {
	
	/**
	 * Usuwa wydarzenie według danego statusu.
	 *
	 * @param status status wydarzenia(niezakończone, w trakcie, zakończone)
	 */
	public void deleteEventByStatus(String status);
	
}