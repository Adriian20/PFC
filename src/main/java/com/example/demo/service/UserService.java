package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.repositories.entity.UsuarioEntity;

public interface UserService {

    List<UsuarioEntity> getAllUsers();

    UsuarioEntity save(UsuarioDTO usuarioDTO);

    UsuarioDTO findByEmail(String email);

    UsuarioDTO findByToken(String token);

    void updateUser(UsuarioDTO usuarioDTO, UsuarioDTO userDTO);

    UsuarioEntity comprarArticulos(Long usuarioId, Map<Long, Integer> articulos) throws Exception;
    
    UsuarioEntity comprarVisitas(Long usuarioId, Map<Long, Integer> visitas) throws Exception;
}
