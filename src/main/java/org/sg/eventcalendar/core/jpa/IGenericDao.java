package org.sg.eventcalendar.core.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

public interface IGenericDao < T extends Serializable > {
		
	public void setClazz(Class<T> clazz);

	public void create(Object entity);  
    
	public List<T> findAll(); 
	
	public T findOneById(Object pk);
	
	public void update();
	
	public void delete(Object entity);  

    public void deleteById(Object pk);
    
    public EntityManager getEM(); 
} 