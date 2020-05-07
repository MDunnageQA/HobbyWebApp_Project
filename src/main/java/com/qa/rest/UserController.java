package com.qa.rest;

import com.qa.domain.User;
import com.qa.dto.UserDTO;
import com.qa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(this.service.readUsers());
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        return new ResponseEntity<UserDTO>(this.service.createUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteMonster(@PathVariable Long id){
        return this.service.deleteUser(id)
                ? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDTO> getNoteById(@PathVariable Long id){
        return ResponseEntity.ok(this.service.findUserById(id));
    }

    @PutMapping("/updatePowers/{id}")
    public ResponseEntity<UserDTO> updateNote(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.ok(this.service.updateUser(id, user));
    }

}
