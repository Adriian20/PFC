package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.repositories.entity.UsuarioEntity;

public interface UserService {

    List<UsuarioEntity> getAllUsers();

    UsuarioEntity save(UsuarioDTO usuarioDTO);

    UsuarioDTO findByEmail(String email);

    UsuarioDTO findByToken(String token);

    void updateUser(UsuarioDTO usuarioDTO, UsuarioDTO userDTO);
}
