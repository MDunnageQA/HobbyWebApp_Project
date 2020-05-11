package com.qa.service;

import com.qa.domain.User;
import com.qa.dto.UserDTO;
import com.qa.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitServiceIntegrationTest {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    private User testUser;
    private User testUserWithID;

    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    @Before
    public void setUpForTests() {
        this.testUser = new User("username", "password",
                "first name", "surname", "DOB", "email");
        this.repository.deleteAll();
        this.testUserWithID = this.repository.save(this.testUser);
    }

    @Test
    public void readUsersTest() {
        assertThat(this.service.readUsers()).isEqualTo(
                Stream.of(this.mapToDTO(testUserWithID)).collect(Collectors.toList()));
    }

    @Test
    public void createUserTest() {
        assertEquals(this.mapToDTO(this.testUserWithID), this.service.createUser(testUser));
    }

    @Test
    public void findUserByIDTest() {
        assertThat(this.service.findUserById(this.testUserWithID.getId())).isEqualTo(this.mapToDTO(this.testUserWithID));
    }

    @Test
    public void deleteUserTest() {
        assertThat(this.service.deleteUser(this.testUserWithID.getId())).isFalse();
    }
}
