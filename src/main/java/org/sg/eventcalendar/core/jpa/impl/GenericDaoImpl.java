package org.sg.eventcalendar.core.jpa.impl;

import java.io.Serializable;

import org.sg.eventcalendar.core.jpa.AbstractGenericDao;
import org.sg.eventcalendar.core.jpa.IGenericDao;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * The Class GenericDaoImpl.
 *
 * @param <T> the generic type
 */
@Repository
@Scope("prototype")
public class GenericDaoImpl< T extends Serializable > extends AbstractGenericDao< T > implements IGenericDao< T >{

}