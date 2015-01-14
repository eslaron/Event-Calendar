package org.sg.eventcalendar.core.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.sg.eventcalendar.core.config.EMF;

public abstract class AbstractGenericDao< T extends Serializable > {

	private Class< T > clazz;
	
	public void setClazz( Class< T > clazzToSet ){
	      this.clazz = clazzToSet;
	}
	
    public EntityManager getEM(){  
       return  EMF.get().createEntityManager();  
    }  
  	 
	public void create(Object entity) {  
	    EntityManager em = this.getEM();  
	    try{  
	        em.persist(entity);  
	    }finally{  
	        em.close();  
	    }         
	}  
	 
	@SuppressWarnings("unchecked")
	public List<T> findAll() {  
		EntityManager em = this.getEM();  
		try{  
		  	return em.createQuery("SELECT a FROM "+clazz.getName()+" a").getResultList();
		}finally{  
		    em.close();  
		}  
	}  

	public T findOneById(Object pk) {  
		EntityManager em = this.getEM();  
		try{  
			return (T) em.find(clazz, pk);  
		}finally{  
			em.close();  
		}     
    }  	 
	
	public void update() {  
	    EntityManager em = this.getEM();  
	    try{  
	        em.createQuery(clazz.getName()).executeUpdate();  
	    }finally{  
	        em.close();  
	    }  
	} 
	
	public void delete(Object entity) {  
	    EntityManager em = this.getEM();  
	    try{  
	         em.remove(entity);  
	    }finally{  
	         em.close();  
	    }         
	}  

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