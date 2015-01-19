package org.sg.eventcalendar.core.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

/**
 * Klasa zawierająca metody generyczne, wykonujące operacje na bazie danych. Można zastosować je do obiektow każdej klasy.
 *
 * @param <T> typ generyczny
 */
public abstract class AbstractGenericDao< T extends Serializable > {

	/** Pole zawierające dowolną klasę */
	private Class< T > clazz;
	
	/**
	 * Setter dla pola clazz
	 *
	 * @param clazzToSet
	 */
	public void setClazz( Class< T > clazzToSet ){
	      this.clazz = clazzToSet;
	}
	
    /**
     * Getter dla instancji Entity Managera
     *
     * @return EntityManager
     */
    public EntityManager getEM(){  
       return  EMF.get().createEntityManager();  
    }  
  	 
	/**
	 * Tworzy nową encję w bazie danych.
	 *
	 * @param entity obiekt dowolnej klasy
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
	 * Znajduje wszystkie encje danego typu
	 *
	 * @return List<T> lista dowolnego typu
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
	 * Znajduje encję po id
	 *
	 * @param pk obiekt zawierający klucz (primary key)
	 * @return (T) obiekt dowolnego typu
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
	 * Aktualizuje daną encję
	 *
	 * @param entity obiekt dowolnej klasy
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
	 * Usuwa daną encję
	 *
	 * @param entity obiekt dowolnej klasy
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
	 * Usuwa encję po id
	 *
	 * @param pk obiekt dowolnej klasy zawierający klucz (primary key)
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