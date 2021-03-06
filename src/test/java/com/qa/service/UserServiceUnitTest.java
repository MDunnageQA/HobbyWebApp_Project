package com.qa.service;

import com.qa.domain.Stories;
import com.qa.domain.User;
import com.qa.dto.StoriesDTO;
import com.qa.dto.UserDTO;
import com.qa.exceptions.UserNotFoundException;
import com.qa.repository.StoriesRepository;
import com.qa.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceUnitTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private List<User> userList;

    private User testUser;

    private Long id = 1L;

    private String userName = "usename";

    private User testUserWithID;

    private UserDTO userDTO;

    private UserDTO maptoDTO(User user) {
        return this.mapper.map(user, UserDTO.class);
    }

    @Before
    public void setUpForTests() {
        this.userList = new ArrayList<>();
        this.testUser = new User("username", "password",
                "first name", "surname", "DOB", "email");
        this.userList.add(testUser);
        this.testUserWithID = new User(testUser.getUserName(), testUser.getPassword(), testUser.getFirstName(),
                testUser.getSurname(), testUser.getDateOfBirth(), testUser.getEmail());
        this.testUserWithID.setId(id);
        this.userDTO = this.maptoDTO(testUserWithID);
    }

    @Test
    public void getAllUsersTest() {
        when(repository.findAll()).thenReturn(this.userList);
        when(this.mapper.map(testUserWithID, UserDTO.class)).thenReturn(userDTO);
        assertFalse("Service returned no Users", this.service.readUsers().isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void createUserTest() {
        when(repository.save(testUser)).thenReturn(testUserWithID);
        when(this.mapper.map(testUserWithID, UserDTO.class)).thenReturn(userDTO);
        assertEquals(this.service.createUser(testUser), this.userDTO);
        verify(repository, times(1)).save(this.testUser);
    }

    @Test
    public void findUserByIDTest() {
        when(this.repository.findById(id)).thenReturn(java.util.Optional.ofNullable(testUserWithID));
        when(this.mapper.map(testUserWithID, UserDTO.class)).thenReturn(userDTO);
        assertEquals(this.service.findUserById(this.id), userDTO);
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void findUserByUserNameTest() {
        when(this.repository.findByUserName(userName)).thenReturn(java.util.Optional.ofNullable(testUserWithID));
        when(this.mapper.map(testUserWithID, UserDTO.class)).thenReturn(userDTO);
        assertEquals(this.service.findUserByUserName(this.userName), userDTO);
        verify(repository, times(1)).findByUserName(userName);
    }

    @Test
    public void deleteUserByExistingID() {
        when(this.repository.existsById(id)).thenReturn(true, false);
        assertFalse(service.deleteUser(id));
        verify(repository, times(1)).deleteById(id);
        verify(repository, times(2)).existsById(id);
    }

    @Test(expected = UserNotFoundException.class)
    public void deleteUserByNonExistingID() {
        when(this.repository.existsById(id)).thenReturn(false);
        service.deleteUser(id);
        verify(repository, times(1)).existsById(id);
    }
}
