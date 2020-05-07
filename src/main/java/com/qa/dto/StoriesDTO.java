package com.qa.dto;

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

}
