package org.sg.eventcalendar.web.controllers;

import java.util.List;

import org.sg.eventcalendar.core.config.security.GaeUser;
import org.sg.eventcalendar.core.entities.Event;
import org.sg.eventcalendar.core.services.IEmailService;
import org.sg.eventcalendar.core.services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

/**
 * Klasa kontroler wydarzeń
 */
@RestController
@RequestMapping("/events")
public class EventsController {
	
	/** Komunikat. */
	String message = "";	

	/** Odwołanie do IEventservice */
	@Autowired
	IEventService eventService;
	
	/** Odwołanie do IEmailservice */
	@Autowired
	IEmailService emailService;

    /**
     * Ządanie POST tworzące nową encję klasy Event
     *
     * @param data dane przesłane do serwera
     * @return response entity odpowiedź dla frontendu
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody Event data) {

    	message = "eventCreated";
    	HttpStatus responseStatus = HttpStatus.OK;
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    GaeUser currentUser = (GaeUser)authentication.getPrincipal();
	    
    	data.setUserNickname(currentUser.getNickname());
    	
    	eventService.createEvent(data);
    	
    	String content="Wydarzenie o nazwie "+data.getName()+" odbędzie się w dniu "
    				+data.getStartDate()+"w "+data.getLocation();
   
    	emailService.sendEMail(currentUser.getEmail(), "Kalendarz wydarzeń - Nowe wydarzenie", content);
    	
    	JsonObject jsonResponse = new JsonObject();
		jsonResponse.addProperty("message", message);
		return new ResponseEntity<String>(jsonResponse.toString(), responseStatus);
	}
    
    /**
     * Ządanie GET znajdujące wszystkie wydarzenia.
     *
     * @return List<Event>
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Event> findAllEvents(){
       return eventService.findAllEvents();
    }
    
    /**
     * Ządanie GET znajdujące wydarzenie po id.
     *
     * @param id
     * @return obiekt klasy Event
     */
    @RequestMapping(value ="/{id}", method = RequestMethod.GET)
    public Event findEventById(@PathVariable long id){
       return eventService.findEventById(id);
    }
        
    /**
     * Ządanie PUT aktualizującę daną encję
     *
     * @param data dane przesyłane do serwera
     * @return the response entity odpowiedź dla frontendu
     */
    @RequestMapping(value ="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateEvent(@RequestBody Event data) {
    	
    	message = "eventUpdated";
    	HttpStatus responseStatus = HttpStatus.OK;

     	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    GaeUser currentUser = (GaeUser)authentication.getPrincipal();
	    
    	data.setUserNickname(currentUser.getNickname());
    	
    	eventService.updateEvent(data);
    	
    	String content="Wydarzenie o nazwie "+data.getName()+" zostało zaktualizowane. "
    			+ "Zaloguj się do systemu, aby sprawdzić szczegóły";

    	emailService.sendEMail(currentUser.getEmail(), data.getName()+" - aktualizacja wydarzenia", content);
    	
			JsonObject jsonResponse = new JsonObject();
			jsonResponse.addProperty("message", message);
			return new ResponseEntity<String>(jsonResponse.toString(), responseStatus);
	}
    
    /**
     * Ządanie DELETE usuwające encję klasy Event.
     *
     * @param id
     * @return the response entity odpowiedź dla frontendu
     */
    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEventById(@PathVariable long id) {
    	
    	message = "eventDeleted";
    	HttpStatus responseStatus = HttpStatus.OK;
 	
    	eventService.deleteEventById(id);
    	
			JsonObject jsonResponse = new JsonObject();
			jsonResponse.addProperty("message", message);
			return new ResponseEntity<String>(jsonResponse.toString(), responseStatus);
	}
}