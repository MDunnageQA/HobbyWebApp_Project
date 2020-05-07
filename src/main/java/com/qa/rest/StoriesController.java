package com.qa.rest;

import com.qa.service.StoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoriesController {

    private final StoriesService service;

    @Autowired
    public StoriesController(StoriesService service) {
        this.service = service;
    }

}
