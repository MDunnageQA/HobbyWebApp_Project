package com.qa.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class User
{

    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String surname;
    private String dateOfBirth;
    private String email;

}
