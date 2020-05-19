package com.qa.service;

import com.qa.domain.Stories;
import com.qa.dto.StoriesDTO;
import com.qa.repository.StoriesRepository;
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
public class StoriesServiceIntegrationTest {

    @Autowired
    private StoriesService service;

    @Autowired
    private StoriesRepository repository;

    @Autowired
    private ModelMapper mapper;

    private Stories testStories;
    private Stories testStoriesWithID;

    private StoriesDTO mapToDTO(Stories stories){
        return this.mapper.map(stories, StoriesDTO.class);
    }

    @Before
    public void testsSetUp() {
        this.testStories = new Stories("title", "genre", "content");
        this.repository.deleteAll();
        this.testStoriesWithID = this.repository.save(this.testStories);
    }

    @Test
    public void readStoriesTest() {
        assertThat(this.service.readStories()).isEqualTo(
                Stream.of(this.mapToDTO(testStoriesWithID)).collect(Collectors.toList()));
    }

    @Test
    public void createStoriesTest() {
        assertEquals(this.mapToDTO(this.testStoriesWithID), this.service.createStories(testStories));
    }

    @Test
    public void findStoriesByIDTest() {
        assertThat(this.service.findStoriesByID(this.testStoriesWithID.getId())).isEqualTo(this.mapToDTO(this.testStoriesWithID));
    }

    @Test
    public void deleteStoriesTest() {
        assertThat(this.service.deleteStories(this.testStoriesWithID.getId())).isFalse();
    }
}
