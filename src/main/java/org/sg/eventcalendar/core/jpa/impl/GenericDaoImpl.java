package org.sg.eventcalendar.core.jpa.impl;

import java.io.Serializable;

import org.sg.eventcalendar.core.jpa.AbstractGenericDao;
import org.sg.eventcalendar.core.jpa.IGenericDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Klasa dziedzicząca po AbstractGenericDao i implementująca interfejs IGenericDao
 *
 * @param <T> typ generyczny
 */
@Repository
@Scope("prototype")
public class GenericDaoImpl< T extends Serializable > extends AbstractGenericDao< T > implements IGenericDao< T >{

}