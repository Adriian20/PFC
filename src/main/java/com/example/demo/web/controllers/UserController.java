package com.example.demo.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modeldto.UserDTO;
import com.example.demo.repositories.entity.UserEntity;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserDTO userDTO) {
        if(userDTO == null || userDTO.getEmail() == null || userDTO.getContrasenya() == null) {
            return ResponseEntity.badRequest().build();
        }

        UserDTO user = userService.findByEmail(userDTO.getEmail());

        if(user == null || !user.getContrasenya().equals(userDTO.getContrasenya())) {
            log.info("Login error");
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        if (userDTO.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        UserEntity userEntity = userService.save(userDTO);
        return ResponseEntity.ok(UserDTO.convertToDTO(userEntity));
    }
}
