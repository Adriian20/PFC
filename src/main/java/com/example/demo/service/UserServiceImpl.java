package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.modeldto.UserDTO;
import com.example.demo.repositories.dao.UserRepository;
import com.example.demo.repositories.entity.UserEntity;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity save(UserDTO userDTO) {
        log.info("UserServiceImpl - save: Guardamos usuario");

        if (userDTO != null) {
            UserEntity userEntity = UserDTO.convertToEntity(userDTO);
            userEntity.setContrasenya(passwordEncoder.encode(userDTO.getContrasenya()));
            return userRepository.save(userEntity);
        } else {
            return null;
        }
    }

    public UserDTO findByEmail(String email) {
        log.info("UserServiceImpl - findByEmail: Busca de usuario por email");

        Optional<UserEntity> userEntity = userRepository.findByEmail(email);

        if (userEntity.isPresent()) {
            UserDTO userDTO = UserDTO.convertToDTO(userEntity.get());
            return userDTO;
        } else {
            return null;
        }
    }
}
