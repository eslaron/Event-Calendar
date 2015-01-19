package org.sg.eventcalendar.core.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;

// TODO: Auto-generated Javadoc
/**
 * Encja Wydarzenie
 */
@Entity
public class Event extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -7667375296843308200L;

	/** Nazwa */
	private String name;
    
	/** Typ wydarzenia */
	private String type;

    /** Data rozpoczęcia */
    private Date startDate;

    /** Data zakończenia */
    private Date endDate;
    
    /** Miejsce wydarzenia */
    private String location;
    
    /** Status wydarzenia */
    private String status;
    
	/** nazwa użytkownika konta Google */
	private String userNickname;

	/**
	 * Inicjalizuje nowe wydarzenie
	 */
	public Event() {}
    
    /**
     * Getter dla nazwy
     *
     * @return name
     */
    public String getName() {
		return name;
	}

	/**
	 * Setter dla nazwy
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter dla typu
	 *
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter dla typu
	 *
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Getter dla daty rozpoczęcia
	 *
	 * @return startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Setter dla daty rozpoczęcia
	 *
	 * @param startDate
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Getter dla daty zakonczenia
	 *
	 * @return endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Setter dla daty zakończenia
	 *
	 * @param endDate
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Getter dla miejsca wydarzenia
	 *
	 * @return location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Setter dla wydarzenia
	 *
	 * @param location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

    /**
     * Getter dla status
     *
     * @return status
     */
    public String getStatus() {
		return status;
	}

	/**
	 * Setter dla statusu
	 *
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	  
    /**
     * Getter dla nazwy użytkownika
     *
     * @return userNickname
     */
    public String getUserNickname() {
		return userNickname;
	}

	/**
	 * Setter dla nazwy użytkownika
	 *
	 * @param userNickname
	 */
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
}