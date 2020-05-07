package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stories
{
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String surname;
    private String dateOfBirth;
    private String email;


}
