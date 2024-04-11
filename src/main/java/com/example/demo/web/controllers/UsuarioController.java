package com.example.demo.web.controllers;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.repositories.entity.UsuarioEntity;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserService userService;

    @PostMapping("/loginUser")
    public ResponseEntity<UsuarioDTO> loginUser(@RequestBody UsuarioDTO usuarioDTO) {
        // Validar que los campos no sean nulos
        if (usuarioDTO == null || usuarioDTO.getEmail() == null || usuarioDTO.getContrasenya() == null) {
            log.info("Empty fields");
            return ResponseEntity.badRequest().build();
        }
        UsuarioDTO usuario = userService.findByEmail(usuarioDTO.getEmail());

        // Validar que el usuario exista
        if (usuario == null) {
            log.info("User with email {} not found", usuarioDTO.getEmail());
            return ResponseEntity.notFound().build();
        }

        // Validar que el usuario no este logueado
        if (usuario.getToken() != null) {
            log.info("User already logged in");
            return ResponseEntity.badRequest().build();
        }

        // Encripto la contrasenya
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(usuarioDTO.getContrasenya().trim(), usuario.getContrasenya().trim())) {
            log.info("Wrong password");
            return ResponseEntity.badRequest().build();
        }

        // Genero el token
        String token = UUID.randomUUID().toString();
        usuario.setToken(token);

        // Guardo el usuario
        userService.save(usuario);

        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> registerUser(@RequestBody UsuarioDTO usuarioDTO) {
        // Validar que el usuarioDTO no tenga un ID
        if (usuarioDTO.getId() != null) {
            log.info("User with ID {} already exists", usuarioDTO.getId());
            return ResponseEntity.badRequest().build();
        }

        // Busco el usuario por su email
        if (userService.findByEmail(usuarioDTO.getEmail()) != null) {
            log.info("User with email {} already exists", usuarioDTO.getEmail());
            return ResponseEntity.badRequest().build();
        }

        // Genero el token
        String token = UUID.randomUUID().toString();
        usuarioDTO.setToken(token);

        // Encripto la contrasenya
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(usuarioDTO.getContrasenya());
        usuarioDTO.setContrasenya(encodedPassword);

        // Guardo el usuario
        UsuarioEntity usuarioEntity = userService.save(usuarioDTO);
        return ResponseEntity.ok(UsuarioDTO.convertToDTO(usuarioEntity));
    }

    @PostMapping("/logoutUser")
    public ResponseEntity<String> logout(@RequestBody UsuarioDTO usuarioDTO) {
        // Validar que el usuarioDTO tenga un token
        if (usuarioDTO.getToken() == null || usuarioDTO.getToken().isEmpty()) {
            return ResponseEntity.badRequest().body("Token is required");
        }

        // Buscar al usuario por el token
        UsuarioDTO userDTO = userService.findByToken(usuarioDTO.getToken());
        if (userDTO == null) {
            return ResponseEntity.notFound().build();
        }

        // Establecer el token como null en el UsuarioDTO
        userDTO.setToken(null);

        // Guardar el UsuarioDTO actualizado en la base de datos
        userService.save(userDTO);

        return ResponseEntity.ok("User with token " + usuarioDTO.getToken() + " logged out");
    }

    @PostMapping("/userInformation")
    public ResponseEntity<UsuarioDTO> userInformation(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuario = userService.findByToken(usuarioDTO.getToken());

        if (usuario == null) {
            log.info("User with token {} not found", usuarioDTO.getToken());
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }
}
