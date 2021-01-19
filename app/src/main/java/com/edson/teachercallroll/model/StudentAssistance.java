package com.edson.teachercallroll.model;

import java.util.Date;

public class StudentAssistance {

    private Long id;
    private String firstName;
    private String lastName;
    private String identifierNumber;
    private Date date;

    public StudentAssistance(Long id, String firstName, String lastName, String identifierNumber, Date date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.identifierNumber = identifierNumber;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdentifierNumber() {
        return identifierNumber;
    }

    public Date getDate() {
        return date;
    }
}
