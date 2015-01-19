package org.sg.eventcalendar.core.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

// TODO: Auto-generated Javadoc
/**
 * The Interface IGenericDao.
 *
 * @param <T> the generic type
 */
public interface IGenericDao < T extends Serializable > {
		
	/**
	 * Sets the clazz.
	 *
	 * @param clazz the new clazz
	 */
	public void setClazz(Class<T> clazz);

	/**
	 * Creates the.
	 *
	 * @param entity the entity
	 */
	public void create(Object entity);  
    
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<T> findAll(); 
	
	/**
	 * Find one by id.
	 *
	 * @param pk the pk
	 * @return the t
	 */
	public T findOneById(Object pk);
	
	/**
	 * Update.
	 *
	 * @param entity the entity
	 */
	public void update(Object entity);
	
	/**
	 * Delete.
	 *
	 * @param entity the entity
	 */
	public void delete(Object entity);  

    /**
     * Delete by id.
     *
     * @param pk the pk
     */
    public void deleteById(Object pk);
    
    /**
     * Gets the em.
     *
     * @return the em
     */
    public EntityManager getEM(); 
} 