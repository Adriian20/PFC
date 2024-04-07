package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.repositories.entity.UsuarioEntity;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String contrasenya;
    private String cuenta_bancaria;
    private String token;

    public static UsuarioDTO convertToDTO(UsuarioEntity userEntity) {
        UsuarioDTO userDTO = new UsuarioDTO();

        userDTO.setId(userEntity.getId());
        userDTO.setNombre(userEntity.getNombre());
        userDTO.setApellidos(userEntity.getApellidos());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setContrasenya(userEntity.getContrasenya());
        userDTO.setCuenta_bancaria(userEntity.getCuenta_bancaria());
        userDTO.setToken(userEntity.getToken());

        return userDTO;
    }

    public static UsuarioEntity convertToEntity(UsuarioDTO userDTO) {
        UsuarioEntity userEntity = new UsuarioEntity();

        userEntity.setId(userDTO.getId());
        userEntity.setNombre(userDTO.getNombre());
        userEntity.setApellidos(userDTO.getApellidos());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setContrasenya(userDTO.getContrasenya());
        userEntity.setCuenta_bancaria(userDTO.getCuenta_bancaria());
        userEntity.setToken(userDTO.getToken());

        return userEntity;
    }

    public UsuarioDTO() {
        super();
    }
}
