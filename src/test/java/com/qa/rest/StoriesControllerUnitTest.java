package com.qa.rest;

import com.qa.domain.Stories;
import com.qa.dto.StoriesDTO;
import com.qa.service.StoriesService;
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
public class StoriesControllerUnitTest {

    @InjectMocks
    private StoriesController storiesController;

    @Mock
    private StoriesService service;

    private List<Stories> storiesList;

    private Stories testStories;

    private Stories testStoriesWithId;

    private long id = 1L;

    private StoriesDTO storiesDTO;

    private final ModelMapper mapper = new ModelMapper();

    private StoriesDTO mapToDTO(Stories stories) {
        return this.mapper.map(stories, StoriesDTO.class);
    }

    @Before
    public void setUpForTests() {
        this.storiesList = new ArrayList<>();
        this.testStories = new Stories("title", "genre", "content");
        this.storiesList.add(testStories);
        this.testStoriesWithId = new Stories(testStories.getTitle(), testStories.getGenre(), testStories.getContent());
        this.testStoriesWithId.setId(this.id);
        this.storiesDTO = this.mapToDTO(testStoriesWithId);
    }

    @Test
    public void getAllStoriesTest() {
        when(service.readStories()).thenReturn(this.storiesList.stream().map(this::mapToDTO).collect(Collectors.toList()));
        assertFalse("No stories found", this.storiesController.getAllStories().getBody().isEmpty());
        verify(service, times(1)).readStories();
    }

    @Test
    public void createStoriesTest() {
        when(this.service.createStories(testStories)).thenReturn(this.storiesDTO);
        assertEquals(this.storiesController.createStories(testStories),
                new ResponseEntity<StoriesDTO>(this.storiesDTO, HttpStatus.CREATED));
        verify(this.service, times(1)).createStories(testStories);
    }
}

