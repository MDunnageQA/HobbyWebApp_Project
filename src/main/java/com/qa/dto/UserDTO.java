package com.qa.dto;

public class UserDTO {

    private Long id;
    private String userName;
    private String password;
    private String firstName;
    private String surname;
    private String dateOfBirth;
    private String email;

    public UserDTO() {
    }

    public UserDTO(String userName, String password, String firstName, String surname, String dateOfBirth, String email) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

}
