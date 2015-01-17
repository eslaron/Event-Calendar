package org.sg.eventcalendar.core.models;

/**
 * @author Luke Taylor
 */
public class RegistrationForm {

    private String forename;
    private String surname;
    
    public RegistrationForm() {}

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}