package com.qa.dto;

import java.util.Objects;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getId(), userDTO.getId()) &&
                Objects.equals(getUserName(), userDTO.getUserName()) &&
                Objects.equals(getPassword(), userDTO.getPassword()) &&
                Objects.equals(getFirstName(), userDTO.getFirstName()) &&
                Objects.equals(getSurname(), userDTO.getSurname()) &&
                Objects.equals(getDateOfBirth(), userDTO.getDateOfBirth()) &&
                Objects.equals(getEmail(), userDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getPassword(), getFirstName(), getSurname(), getDateOfBirth(), getEmail());
    }
}
