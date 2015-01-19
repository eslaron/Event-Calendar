package org.sg.eventcalendar.core.jpa.impl;

import javax.persistence.EntityManager;

import org.sg.eventcalendar.core.jpa.EMF;
import org.sg.eventcalendar.core.jpa.IEventDao;
import org.springframework.stereotype.Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class EventDaoImpl.
 */
@Repository
public class EventDaoImpl implements IEventDao {

	  /**
  	 * Gets the em.
  	 *
  	 * @return the em
  	 */
  	public EntityManager getEM(){  
	       return  EMF.get().createEntityManager();  
	  } 
	
	/* (non-Javadoc)
	 * @see org.sg.eventcalendar.core.jpa.IEventDao#deleteEventByStatus(java.lang.String)
	 */
	@Override
	public void deleteEventByStatus(String status) {
		 EntityManager em = this.getEM();  
		    try{  
		        em.createQuery("Delete Event where status = "+status).executeUpdate();
		    }finally{  
		        em.close();  
		    } 	
	}
}