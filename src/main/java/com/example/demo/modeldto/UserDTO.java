package com.example.demo.modeldto;

import java.io.Serializable;

import com.example.demo.repositories.entity.UserEntity;

import lombok.Data;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String contrasenya;
    private String cuenta_bancaria;

    public static UserDTO convertToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setNombre(userEntity.getNombre());
        userDTO.setApellidos(userEntity.getApellidos());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setContrasenya(userEntity.getContrasenya());
        userDTO.setCuenta_bancaria(userEntity.getCuenta_bancaria());

        return userDTO;
    }

    public static UserEntity convertToEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setNombre(userDTO.getNombre());
        userEntity.setApellidos(userDTO.getApellidos());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setContrasenya(userDTO.getContrasenya());
        userEntity.setCuenta_bancaria(userDTO.getCuenta_bancaria());
        return userEntity;
    }

    public UserDTO() {
        super();
    }
}
