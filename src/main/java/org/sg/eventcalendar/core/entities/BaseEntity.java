package org.sg.eventcalendar.core.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Bazowa encja, z ktorej korzystają inne encje.
 */
@Entity
@MappedSuperclass
public abstract class BaseEntity {
	
	/** ID encji. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Getter dla id
	 *
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter dla id.
	 *
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
}