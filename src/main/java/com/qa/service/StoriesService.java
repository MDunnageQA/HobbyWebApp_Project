package com.qa.service;

import com.qa.domain.Stories;
import com.qa.dto.StoriesDTO;
import com.qa.exceptions.StoriesNotFoundException;
import com.qa.repository.StoriesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoriesService {

    private final StoriesRepository repo;

    private final ModelMapper mapper;

    @Autowired
    public StoriesService(StoriesRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private StoriesDTO mapToDTO(Stories stories) {
        return this.mapper.map(stories, StoriesDTO.class);
    }

    public List<StoriesDTO> readStories() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public StoriesDTO createStories(Stories stories) {
        Stories tempStories = this.repo.save(stories);
        return this.mapToDTO(tempStories);
    }

    public StoriesDTO findStoriesByID(Long id){
        return this.mapToDTO(this.repo.findById(id).orElseThrow(StoriesNotFoundException::new));
    }

    public StoriesDTO updateStories(Long id, Stories stories) {
        Stories update = this.repo.findById(id).orElseThrow(StoriesNotFoundException::new);
        update.setTitle(stories.getTitle());
        update.setGenre(stories.getGenre());
        update.setContent(stories.getContent());
        Stories tempStories = this.repo.save(stories);
        return this.mapToDTO(tempStories);
    }

    public boolean deleteStories(Long id){
        if(!this.repo.existsById(id)){
            throw new StoriesNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }

}
