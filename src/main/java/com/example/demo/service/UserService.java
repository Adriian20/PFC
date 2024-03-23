package com.example.demo.service;

import java.util.List;

import com.example.demo.modeldto.UserDTO;
import com.example.demo.repositories.entity.UserEntity;

public interface UserService {

    List<UserEntity> getAllUsers();

    UserEntity save (UserDTO userDTO);

    UserDTO findByEmail(String email);
}
