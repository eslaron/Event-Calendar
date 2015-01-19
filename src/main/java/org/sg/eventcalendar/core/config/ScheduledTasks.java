package org.sg.eventcalendar.core.config;

import org.sg.eventcalendar.core.services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

// TODO: Auto-generated Javadoc
/**
 * Klasa 'Zaplanowane zadania'
 */
@EnableScheduling
public class ScheduledTasks {
	
	/** Odwołanie do instancji IEventService */
	@Autowired
	IEventService eventService;
	
	/** Polecenie CRON ustawiajace wykonanie zadnia co 7 dni */
	final private String deleteConcludedEventsCron = "0 0 0 */7 * ?";
	
    /**
     * Metoda usuwajaca zakończone wydarzenia.
     */
    @Scheduled(cron=deleteConcludedEventsCron)
    public void deleteConcludedEvents() {
    	eventService.deleteEventByStatus("Zakończone");
    }
}