package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UsuarioDTO;
import com.example.demo.repositories.dao.ArticuloRepository;
import com.example.demo.repositories.dao.UserRepository;
import com.example.demo.repositories.dao.VisitaRepository;
import com.example.demo.repositories.entity.ArticuloEntity;
import com.example.demo.repositories.entity.UsuarioArticulo;
import com.example.demo.repositories.entity.UsuarioEntity;
import com.example.demo.repositories.entity.UsuarioVisita;
import com.example.demo.repositories.entity.VisitaEntity;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private VisitaRepository visitaRepository;

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
            UsuarioDTO usuarioDTO = UsuarioDTO.convertToDTO(userEntity.get());
            return usuarioDTO;
        } else {
            return null;
        }
    }

    public UsuarioDTO findByToken(String token) {
        log.info("UserServiceImpl - findByToken: Busca de usuario por token");

        Optional<UsuarioEntity> userEntity = userRepository.findByToken(token);

        if (userEntity.isPresent()) {
            UsuarioDTO usuarioDTO = UsuarioDTO.convertToDTO(userEntity.get());
            return usuarioDTO;
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

    @Override
    public void updateUser(UsuarioDTO usuarioDTO, UsuarioDTO userDTO) {
        if (usuarioDTO.getContrasenya() != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(usuarioDTO.getContrasenya());
            userDTO.setContrasenya(encodedPassword);
        }
        if (usuarioDTO.getEmail() != null) {
            userDTO.setEmail(usuarioDTO.getEmail());
        }
        if (usuarioDTO.getNombre() != null) {
            userDTO.setNombre(usuarioDTO.getNombre());
        }
        if (usuarioDTO.getApellidos() != null) {
            userDTO.setApellidos(usuarioDTO.getApellidos());
        }
        if (usuarioDTO.getCuenta_bancaria() != null) {
            userDTO.setCuenta_bancaria(usuarioDTO.getCuenta_bancaria());
        }
    }

    @Transactional
    public UsuarioEntity comprarArticulos(Long usuarioId, Map<Long, Integer> articulos) throws Exception {
        Optional<UsuarioEntity> usuarioOpt = userRepository.findById(usuarioId);
        if (!usuarioOpt.isPresent()) {
            throw new Exception("Usuario no encontrado");
        }

        UsuarioEntity usuario = usuarioOpt.get();
        for (Map.Entry<Long, Integer> entry : articulos.entrySet()) {
            Long articuloId = entry.getKey();
            Integer cantidad = entry.getValue();
            Optional<ArticuloEntity> articuloOpt = articuloRepository.findById(articuloId);
            if (!articuloOpt.isPresent()) {
                throw new Exception("Artículo no encontrado con ID: " + articuloId);
            }
            ArticuloEntity articulo = articuloOpt.get();

            if (articulo.getStock() < cantidad) {
                throw new Exception("Stock insuficiente para el artículo con ID: " + articuloId);
            }

            articulo.setStock(articulo.getStock() - cantidad);
            articuloRepository.save(articulo);

            UsuarioArticulo usuarioArticulo = new UsuarioArticulo();
            usuarioArticulo.setUsuario(usuario);
            usuarioArticulo.setArticulo(articulo);
            usuarioArticulo.setCantidad(cantidad);

            usuario.getArticulos().add(usuarioArticulo);
        }

        return userRepository.save(usuario);
    }

    @Transactional
    public UsuarioEntity comprarVisitas(Long usuarioId, Map<Long, Integer> visitas) throws Exception {
        Optional<UsuarioEntity> usuarioOpt = userRepository.findById(usuarioId);
        if (!usuarioOpt.isPresent()) {
            throw new Exception("Usuario no encontrado");
        }

        UsuarioEntity usuario = usuarioOpt.get();
        for (Map.Entry<Long, Integer> entry : visitas.entrySet()) {
            Long visitaId = entry.getKey();
            Integer cantidad = entry.getValue();
            Optional<VisitaEntity> visitaOpt = visitaRepository.findById(visitaId);
            if (!visitaOpt.isPresent()) {
                throw new Exception("Visita no encontrada con ID: " + visitaId);
            }
            VisitaEntity visita = visitaOpt.get();

            if (visita.getStock_entradas() < cantidad) {
                throw new Exception("Stock insuficiente para la visita con ID: " + visitaId);
            }

            visita.setStock_entradas(visita.getStock_entradas() - cantidad);
            visitaRepository.save(visita);

            UsuarioVisita usuarioVisita = new UsuarioVisita();
            usuarioVisita.setUsuario(usuario);
            usuarioVisita.setVisita(visita);
            usuarioVisita.setCantidad(cantidad);

            usuario.getVisitas().add(usuarioVisita);
        }

        return userRepository.save(usuario);
    }
}
