package org.sg.eventcalendar.core.services.impl;

import java.util.List;

import org.sg.eventcalendar.core.entities.Event;
import org.sg.eventcalendar.core.jpa.IEventDao;
import org.sg.eventcalendar.core.jpa.IGenericDao;
import org.sg.eventcalendar.core.services.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


/**
 * Implementacja interfejsu EventService
 */
@Service
@Transactional
public class EventServiceImpl implements IEventService {

	/** Obiekt dao działący na encji Event. */
	IGenericDao<Event> dao;
	 
	/**
   	 * Ustawia parametry dla dao.
   	 *
   	 * @param daoToSet ustawia dao danej encji.
   	 */
   	@Autowired
	   public void setDao(IGenericDao<Event> daoToSet){
	      dao = daoToSet;
	      dao.setClazz(Event.class);
	   }
	   
	   /** Obiekt interfejsu EventDao */
   	@Autowired
	   IEventDao eventDao;

	/**
	 * @see org.sg.eventcalendar.core.services.IEventService#createEvent(org.sg.eventcalendar.core.entities.Event)
	 */
	@Override
	public void createEvent(Event entity) {
		dao.create(entity);
	}

	/**
	 * @see org.sg.eventcalendar.core.services.IEventService#findAllEvents()
	 */
	@Override
	public List<Event> findAllEvents() {
		return dao.findAll();
	}

	/**
	 * @see org.sg.eventcalendar.core.services.IEventService#findEventById(long)
	 */
	@Override
	public Event findEventById(long id) {	
		Key pk = KeyFactory.createKey("Event", id);	
		return dao.findOneById(pk);
	}

	/**
	 * @see org.sg.eventcalendar.core.services.IEventService#updateEvent(org.sg.eventcalendar.core.entities.Event)
	 */
	@Override
	public void updateEvent(Event entity) {
		dao.update(entity);
	}

	/**
	 * @see org.sg.eventcalendar.core.services.IEventService#deleteEvent(org.sg.eventcalendar.core.entities.Event)
	 */
	@Override
	public void deleteEvent(Event entity) {
		dao.delete(entity);
	}

	/**
	 * @see org.sg.eventcalendar.core.services.IEventService#deleteEventById(long)
	 */
	@Override
	public void deleteEventById(long id) {
		Key pk = KeyFactory.createKey("Event", id);	
		dao.deleteById(pk);
	}

	/**
	 * @see org.sg.eventcalendar.core.services.IEventService#deleteEventByStatus(java.lang.String)
	 */
	@Override
	public void deleteEventByStatus(String status) {
		eventDao.deleteEventByStatus(status);
	}
}