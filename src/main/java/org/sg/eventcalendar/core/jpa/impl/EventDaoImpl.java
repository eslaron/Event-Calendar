package org.sg.eventcalendar.core.jpa.impl;

import javax.persistence.EntityManager;

import org.sg.eventcalendar.core.jpa.EMF;
import org.sg.eventcalendar.core.jpa.IEventDao;
import org.springframework.stereotype.Repository;

/**
 * Implementacja klasy EventDao
 */
@Repository
public class EventDaoImpl implements IEventDao {

    /**
     * Getter dla instancji Entity Managera
     *
     * @return EntityManager
     */
  	public EntityManager getEM(){  
	       return  EMF.get().createEntityManager();  
	  } 
	
  	/**
  	 * Implementacja metody usuwajacej encję według statusu
  	 * @param status status wydarzenia(niezakończone, w trakcie, zakończone) 
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