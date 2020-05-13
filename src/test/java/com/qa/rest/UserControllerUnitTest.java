package com.qa.rest;

import com.qa.domain.User;
import com.qa.dto.UserDTO;
import com.qa.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerUnitTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService service;

    private List<User> userList;

    private User testUser;

    private User testUserWithId;

    private long id = 1L;

    private UserDTO userDTO;

    private final ModelMapper mapper = new ModelMapper();

    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    @Before
    public void setUpForTests() {
        this.userList = new ArrayList<>();
        this.testUser = new User("username", "password",
                "firstname", "surname", "DOB", "email");
        this.userList.add(testUser);
        this.testUserWithId = new User(testUser.getUserName(), testUser.getPassword(),
                testUser.getFirstName(), testUser.getSurname(), testUser.getDateOfBirth(), testUser.getEmail());
        this.testUserWithId.setId(this.id);
        this.userDTO = this.mapToDTO(testUserWithId);
    }

    @Test
    public void getAllUsersTest() {
        when(service.readUsers()).thenReturn(this.userList.stream().map(this::mapToDTO).collect(
                Collectors.toList()));
        assertFalse("No Users found", this.userController.getAllUsers().getBody().isEmpty());
        verify(service, times(1)).readUsers();
    }

    @Test
    public void createUserTest() {
        when(this.service.createUser(testUser)).thenReturn(this.userDTO);
        assertEquals(this.userController.createUser(testUser), new ResponseEntity<UserDTO>(
                this.userDTO, HttpStatus.CREATED));
        verify(this.service, times(1)).createUser(testUser);
    }

    @Test
    public void getUserByIDTest() {
        when(this.service.findUserById(id)).thenReturn(this.userDTO);
        assertEquals(this.userController.getUserById(id), new ResponseEntity<UserDTO>(
                this.userDTO, HttpStatus.OK));
        verify(service, times(1)).findUserById(id);
    }

    @Test
    public void deleteUserTestFalse() {
        this.userController.deleteUser(id);
        verify(service, times(1)).deleteUser(id);
    }

    @Test
    public void deleteUserTestTrue() {
        when(service.deleteUser(3L)).thenReturn(true);
        this.userController.deleteUser(3L);
        verify(service, times(1)).deleteUser(3L);
    }
}
