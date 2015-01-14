package org.sg.eventcalendar.core.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.google.appengine.api.datastore.Key;

@Entity
@MappedSuperclass
public abstract class BaseEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;

	public void setKey(Key key) {
		this.key = key;
	}

	public Key getKey() {
		return key;
	}
}