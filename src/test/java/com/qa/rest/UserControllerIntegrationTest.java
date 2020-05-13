package com.qa.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.User;
import com.qa.dto.UserDTO;
import com.qa.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    private User testUser;

    private User testUserWithID;

    private long id;

    private UserDTO userDTO;

    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    @Before
    public void setUpForTests() {
        this.repository.deleteAll();
        this.testUser = new User("username", "password", "firstname",
                "surname", "DOB", "email");
        this.testUserWithID = this.repository.save(testUser);
        this.id = testUserWithID.getId();
        this.userDTO = this.mapToDTO(testUserWithID);
    }

    @Test
    public void getAllUsersTest() throws Exception {
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(userDTO);
        String jsonContent = this.mock.perform(request(HttpMethod.GET, "/getAllUsers")
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        assertEquals(jsonContent, this.objectMapper.writeValueAsString(userDTOList));
    }

    @Test
    public void createUserTest() throws Exception {
        String result = this.mock.perform(request(HttpMethod.POST, "/createUser").contentType(
                MediaType.APPLICATION_JSON).content(this.objectMapper.writeValueAsString(testUser))
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn().getResponse()
                .getContentAsString();
        assertEquals(result, this.objectMapper.writeValueAsString(userDTO));
    }

    @Test
    public void getUserByID() throws Exception {
        String jsonContent = this.mock.perform(request(HttpMethod.GET, "/getUserByID/" + this.id)
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse()
                .getContentAsString();
        assertEquals(jsonContent, this.objectMapper.writeValueAsString(userDTO));
    }

    @Test
    public void deleteUserTest() throws Exception {
        this.mock.perform(request(HttpMethod.DELETE, "/deleteUser/" + this.id))
                .andExpect(status().isNoContent());
    }
}
