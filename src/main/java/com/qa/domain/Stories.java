package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Stories
{
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String genre;
    private String content;

    @ManyToOne(targetEntity = User.class)
    private User user;

}
