package com.qa.service;

import com.qa.domain.Stories;
import com.qa.dto.StoriesDTO;
import com.qa.exceptions.StoriesNotFoundException;
import com.qa.repository.StoriesRepository;
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
public class StoriesServiceUnitTest {

    @InjectMocks
    private StoriesService service;

    @Mock
    private StoriesRepository repository;

    @Mock
    private ModelMapper mapper;

    private List<Stories> storiesList;

    private Stories testStories;

    private Long id = 1L;

    private Stories testStoriesWithID;

    private StoriesDTO storiesDTO;

    private StoriesDTO maptoDTO(Stories stories) {
        return this.mapper.map(stories, StoriesDTO.class);
    }

    @Before
    public void testsSetUp() {
        this.storiesList = new ArrayList<>();
        this.testStories = new Stories("title", "genre", "content");
        this.storiesList.add(testStories);
        this.testStoriesWithID = new Stories(testStories.getTitle(), testStories.getGenre(), testStories.getContent());
        this.testStoriesWithID.setId(id);
        this.storiesDTO = this.maptoDTO(testStoriesWithID);
    }

    @Test
    public void getAllStoriesTest() {
        when(repository.findAll()).thenReturn(this.storiesList);
        when(this.mapper.map(testStoriesWithID, StoriesDTO.class)).thenReturn(storiesDTO);
        assertFalse("Service returned no Stories", this.service.readStories().isEmpty());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void createStoriesTest() {
        when(repository.save(testStories)).thenReturn(testStoriesWithID);
        when(this.mapper.map(testStoriesWithID, StoriesDTO.class)).thenReturn(storiesDTO);
        assertEquals(this.service.createStories(testStories), this.storiesDTO);
        verify(repository, times(1)).save(this.testStories);
    }

    @Test
    public void findStoriesByIDTest() {
        when(this.repository.findById(id)).thenReturn(java.util.Optional.ofNullable(testStoriesWithID));
        when(this.mapper.map(testStoriesWithID, StoriesDTO.class)).thenReturn(storiesDTO);
        assertEquals(this.service.findStoriesByID(this.id), storiesDTO);
        verify(repository, times(1)).findById(id);
    }


}
