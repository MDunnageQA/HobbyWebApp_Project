package com.qa.service;

import com.qa.domain.User;
import com.qa.dto.UserDTO;
import com.qa.exceptions.UserNotFoundException;
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

    private UserDTO mapToDTO(User user){
        return this.mapper.map(user, UserDTO.class);
    }

    public List<UserDTO> readUsers() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public UserDTO createUser(User user) {
        return this.mapToDTO((User) this.repo.save(user));
    }

    public UserDTO findUserById(Long id){
        return this.mapToDTO(this.repo.findById(id).orElseThrow(UserNotFoundException::new));
    }

    public UserDTO updateUser(Long id, User user){
        User update = this.repo.findById(id).orElseThrow(UserNotFoundException::new);
        update.setUserName(user.getUserName());
        update.setPassword(user.getPassword());
        update.setFirstName(user.getFirstName());
        update.setSurname(user.getSurname());
        update.setDateOfBirth(user.getDateOfBirth());
        update.setEmail(user.getEmail());
        User tempUser = this.repo.save(update);
        return this.mapToDTO(tempUser);
    }

    public boolean deleteUser(Long id){
        if(!this.repo.existsById(id)){
            throw new UserNotFoundException();
        }
        this.repo.deleteById(id);
        return this.repo.existsById(id);
    }

}
