package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.repositories.dao.UserRepository;
import com.example.demo.repositories.entity.UsuarioEntity;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<UsuarioEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UsuarioEntity save(UsuarioDTO usuarioDTO) {
        log.info("UserServiceImpl - save: Guardamos usuario");

        if (usuarioDTO != null) {
            UsuarioEntity userEntity = UsuarioDTO.convertToEntity(usuarioDTO);
            return userRepository.save(userEntity);
        } else {
            return null;
        }
    }

    public UsuarioDTO findByEmail(String email) {
        log.info("UserServiceImpl - findByEmail: Busca de usuario por email");

        Optional<UsuarioEntity> userEntity = userRepository.findByEmail(email);

        if (userEntity.isPresent()) {
            UsuarioDTO userDTO = UsuarioDTO.convertToDTO(userEntity.get());
            return userDTO;
        } else {
            return null;
        }
    }

    public UsuarioDTO findByToken(String token) {
        log.info("UserServiceImpl - findByToken: Busca de usuario por token");

        Optional<UsuarioEntity> userEntity = userRepository.findByToken(token);

        if (userEntity.isPresent()) {
            UsuarioDTO userDTO = UsuarioDTO.convertToDTO(userEntity.get());
            return userDTO;
        } else {
            return null;
        }
    }

    public UsuarioDTO logout(UsuarioDTO usuarioDTO) {
        // Buscar el usuario por el token
        UsuarioDTO usuario = findByToken(usuarioDTO.getToken());

        if (usuario != null) {
            // Establecer el token como null
            usuario.setToken(null);

            return usuario;
        } else {
            log.info("Usuario no encontrado para el token proporcionado: {}", usuarioDTO.getToken());
            return null;
        }
    }
}
