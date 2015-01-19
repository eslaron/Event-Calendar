package org.sg.eventcalendar.core.config;

import org.sg.eventcalendar.core.services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class ScheduledTasks {
	
	@Autowired
	IEventService eventService;
	
	final private String deleteConcludedEventsCron = "0 0 0 */7 * ?";
	
    @Scheduled(cron=deleteConcludedEventsCron)
    public void deleteConcludedEvents() {
    	eventService.deleteEventByStatus("Zakończone");
    }
}