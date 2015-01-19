package org.sg.eventcalendar.core.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Klasa inicjalizująca instancję EntityManagera
 */
public class EMF {
	
    /** Pole zawierające instancję Entity Managera */
    private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("transactions-optional");
    
    /**
     * Inicjalizuje nowy obiekt EMF
     */
    public EMF() {}
     
    /**
     * Getter dla instancji Entity Managera.
     *
     * @return emfInstance instancja Entity Managera
     */
    public static EntityManagerFactory get() {
        return emfInstance;
    }
}