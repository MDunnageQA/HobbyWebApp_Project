package com.qa.rest;

import com.qa.domain.Stories;
import com.qa.dto.StoriesDTO;
import com.qa.service.StoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoriesController {

    private final StoriesService service;

    @Autowired
    public StoriesController(StoriesService service) {
        this.service = service;
    }

    @GetMapping("/getAllStories")
    public ResponseEntity<List<StoriesDTO>> getAllStories() {
        return ResponseEntity.ok(this.service.readStories());
    }

    @PostMapping("/createStories")
    public ResponseEntity<StoriesDTO> createStories(@RequestBody Stories stories) {
        return new ResponseEntity<StoriesDTO>(this.service.createStories(stories), HttpStatus.CREATED);
    }

}
