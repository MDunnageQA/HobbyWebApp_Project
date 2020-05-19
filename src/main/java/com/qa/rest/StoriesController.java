package com.qa.rest;

import com.qa.domain.Stories;
import com.qa.dto.StoriesDTO;
import com.qa.service.StoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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

    @GetMapping("/getStoriesByID/{id}")
    public ResponseEntity<StoriesDTO> getStoriesByID(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findStoriesByID(id));
    }

    @PutMapping("/updateStories/{id}")
    public ResponseEntity<StoriesDTO> updateStories(@PathVariable Long id, @RequestBody Stories stories){
        return ResponseEntity.ok(this.service.updateStories(id, stories));
    }

    @DeleteMapping("/deleteStories/{id}")
    public ResponseEntity<?> deleteStories(@PathVariable Long id){
        return this.service.deleteStories(id)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

}
