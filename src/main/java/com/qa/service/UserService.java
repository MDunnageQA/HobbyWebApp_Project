package com.qa.service;

import com.qa.domain.User;
import com.qa.dto.UserDTO;
import com.qa.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository repo;

    private final ModelMapper mapper;

    @Autowired
    public UserService(UserRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private UserDTO mapToDTO(User user) {
        return this.mapper.map(user, UserDTO.class);
    }

    public List<UserDTO> readUsers() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public UserDTO createUser(User user) {
        return this.mapToDTO((User) this.repo.save(user));
    }

}
