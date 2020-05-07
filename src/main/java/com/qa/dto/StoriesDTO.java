package com.qa.dto;

import java.util.Objects;

public class StoriesDTO {

    private Long id;
    private String title;
    private String genre;
    private String content;

    public StoriesDTO() {
    }

    public StoriesDTO(String title, String genre, String content) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StoriesDTO)) return false;
        StoriesDTO that = (StoriesDTO) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getGenre(), that.getGenre()) &&
                Objects.equals(getContent(), that.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getGenre(), getContent());
    }
}
