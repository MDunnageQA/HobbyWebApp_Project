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
    private String title;
    private String genre;
    private String content;



}
