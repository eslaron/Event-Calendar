package org.sg.eventcalendar.core.services.impl;

import java.util.List;

import org.sg.eventcalendar.core.entities.Event;
import org.sg.eventcalendar.core.jpa.IGenericDao;
import org.sg.eventcalendar.core.services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Service
@Transactional
public class EventServiceImpl implements IEventService {

	IGenericDao<Event> dao;
	 
	   @Autowired
	   public void setDao(IGenericDao<Event> daoToSet){
	      dao = daoToSet;
	      dao.setClazz(Event.class);
	   }

	@Override
	public void createEvent(Event entity) {
		dao.create(entity);
	}

	@Override
	public List<Event> findAllEvents() {
		return dao.findAll();
	}

	@Override
	public Event findEventById(long id) {	
		Key pk = KeyFactory.createKey("Event", id);	
		return dao.findOneById(pk);
	}

	@Override
	public void updateEvent() {
		dao.update();
	}

	@Override
	public void deleteEvent(Event entity) {
		dao.delete(entity);
	}

	@Override
	public void deleteEventById(long id) {
		Key pk = KeyFactory.createKey("Event", id);	
		dao.deleteById(pk);
	}
}