package com.qa.service;

import com.qa.repository.StoriesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoriesService {

    private final StoriesRepository repo;

//    private final ModelMapper mapper;

    @Autowired
    public StoriesService(StoriesRepository repo/*, ModelMapper mapper*/) {
        this.repo = repo;
//        this.mapper = mapper;
    }

}
