package com.qa.domain;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "power", fetch = FetchType.LAZY)
    private List<Stories> stories = new ArrayList<>();

}
