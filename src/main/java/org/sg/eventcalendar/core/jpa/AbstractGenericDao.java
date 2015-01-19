package org.sg.eventcalendar.core.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;


// TODO: Auto-generated Javadoc
/**
 * The Class AbstractGenericDao.
 *
 * @param <T> the generic type
 */
public abstract class AbstractGenericDao< T extends Serializable > {

	/** The clazz. */
	private Class< T > clazz;
	
	/**
	 * Sets the clazz.
	 *
	 * @param clazzToSet the new clazz
	 */
	public void setClazz( Class< T > clazzToSet ){
	      this.clazz = clazzToSet;
	}
	
    /**
     * Gets the em.
     *
     * @return the em
     */
    public EntityManager getEM(){  
       return  EMF.get().createEntityManager();  
    }  
  	 
	/**
	 * Creates the.
	 *
	 * @param entity the entity
	 */
	public void create(Object entity) {  
	    EntityManager em = this.getEM();  
	    try{  
	        em.persist(entity);  
	    }finally{  
	        em.close();  
	    }         
	}  
	 
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {  
		EntityManager em = this.getEM();  
		try{  
		  	return em.createQuery("SELECT a FROM "+clazz.getName()+" a").getResultList();
		}finally{  
		    em.close();  
		}  
	}  

	/**
	 * Find one by id.
	 *
	 * @param pk the pk
	 * @return the t
	 */
	public T findOneById(Object pk) {  
		EntityManager em = this.getEM();  
		try{  
			return (T) em.find(clazz, pk);  
		}finally{  
			em.close();  
		}     
    }  	 
	
	/**
	 * Update.
	 *
	 * @param entity the entity
	 */
	public void update(Object entity) {  
	    EntityManager em = this.getEM();  
	    try{  
	    	em.merge(entity);
	    }finally{  
	        em.close();  
	    }  
	} 
	
	/**
	 * Delete.
	 *
	 * @param entity the entity
	 */
	public void delete(Object entity) {  
	    EntityManager em = this.getEM();  
	    try{  
	         em.remove(entity);  
	    }finally{  
	         em.close();  
	    }         
	}  

	/**
	 * Delete by id.
	 *
	 * @param pk the pk
	 */
	public void deleteById(Object pk) {  
	    EntityManager em = this.getEM();  
	    try{  
	        T t = em.find(clazz, pk);  
	        em.remove(t);  
	    }finally{  
	        em.close();  
	    }  
	}   
}