package org.sg.eventcalendar.core.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * Publiczny interfejs dla GenericDao
 *
 * @param <T> typ generyczny
 */
public interface IGenericDao < T extends Serializable > {
		
	/**
	 * Setter dla pola clazz
	 *
	 * @param clazzToSet
	 */
	public void setClazz(Class<T> clazz);

	/**
	 * Tworzy nową encję w bazie danych.
	 *
	 * @param entity obiekt dowolnej klasy
	 */
	public void create(Object entity);  
    
	/**
	 * Znajduje wszystkie encje danego typu
	 *
	 * @return List<T> lista dowolnego typu
	 */
	public List<T> findAll(); 
	
	/**
	 * Znajduje encję po id
	 *
	 * @param pk obiekt zawierający klucz (primary key)
	 * @return (T) obiekt dowolnego typu
	 */
	public T findOneById(Object pk);
	
	/**
	 * Aktualizuje daną encję
	 *
	 * @param entity obiekt dowolnej klasy
	 */
	public void update(Object entity);
	
	/**
	 * Usuwa daną encję
	 *
	 * @param entity obiekt dowolnej klasy
	 */
	public void delete(Object entity);  

	/**
	 * Usuwa encję po id
	 *
	 * @param pk obiekt dowolnej klasy zawierający klucz (primary key)
	 */
    public void deleteById(Object pk);
    
    /**
     * Pobiera instację Entity Managera
     *
     * @return EntityManager
     */
    public EntityManager getEM(); 
} 