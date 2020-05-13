package com.qa.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.domain.Stories;
import com.qa.dto.StoriesDTO;
import com.qa.repository.StoriesRepository;
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
public class StoriesControllerIntegrationTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private StoriesRepository repository;

    @Autowired
    private ModelMapper mapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Stories testStories;

    private Stories testStoriesWithID;

    private long id;

    private StoriesDTO storiesDTO;

    private StoriesDTO mapToDTO(Stories stories){
        return this.mapper.map(stories, StoriesDTO.class);
    }

    @Before
    public void setUpForTests() {
        this.repository.deleteAll();
        this.testStories = new Stories("title", "genre", "content");
        this.testStoriesWithID = this.repository.save(testStories);
        this.id = testStoriesWithID.getId();
        this.storiesDTO = this.mapToDTO(testStoriesWithID);
    }

    @Test
    public void getAllStoriesTest() throws Exception {
        List<StoriesDTO> storiesDTOList = new ArrayList<>();
        storiesDTOList.add(storiesDTO);
        String jsonContent = this.mock.perform(request(HttpMethod.GET, "/getAllStories")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn()
                .getResponse().getContentAsString();
        assertEquals(jsonContent, this.objectMapper.writeValueAsString(storiesDTOList));
    }

    @Test
    public void createStoriesTest() throws Exception {
        String result = this.mock.perform(request(HttpMethod.POST, "/createStories")
                .contentType(MediaType.APPLICATION_JSON).content(this.objectMapper.writeValueAsString
                        (testStories)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        assertEquals(result, this.objectMapper.writeValueAsString(storiesDTO));
    }
}
