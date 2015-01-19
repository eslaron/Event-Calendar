package org.sg.eventcalendar.core.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// TODO: Auto-generated Javadoc
/**
 * The Class EMF.
 */
public class EMF {
	
    /** The Constant emfInstance. */
    private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("transactions-optional");
    
    /**
     * Instantiates a new emf.
     */
    public EMF() {}
     
    /**
     * Gets the.
     *
     * @return the entity manager factory
     */
    public static EntityManagerFactory get() {
        return emfInstance;
    }
}