package com.qa.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Stories {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String genre;
    @Column(length = 5000)
    private String content;

    @ManyToOne(targetEntity = User.class)
    private User user;

    public Stories(){

    }

    public Stories(String title, String genre, String content){
        this.title = title;
        this.genre = genre;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stories)) return false;
        Stories stories = (Stories) o;
        return Objects.equals(getId(), stories.getId()) &&
                Objects.equals(getTitle(), stories.getTitle()) &&
                Objects.equals(getGenre(), stories.getGenre()) &&
                Objects.equals(getContent(), stories.getContent()) &&
                Objects.equals(getUser(), stories.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getGenre(), getContent(), getUser());
    }
}
