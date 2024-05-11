package com.example.demo.web.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ArticuloDTO;
import com.example.demo.service.ArticuloService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/articles")
public class ArticulosController {

    @Autowired
    private ArticuloService articuloService;

    @GetMapping("/allArticles")
    public List<ArticuloDTO> listarArticulos() {
        List<ArticuloDTO> articulos = articuloService.getAll();
        return articulos;
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<ArticuloDTO> articulosId(@PathVariable Long id) {
        if (id == 0) {
            return ResponseEntity.badRequest().build();
        } else {
            ArticuloDTO articuloDTO = new ArticuloDTO();
            articuloDTO.setId(id);
            Optional<ArticuloDTO> opt = Optional.ofNullable(articuloService.findById(articuloDTO));
            if (opt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            } else {
                return ResponseEntity.ok(opt.get());
            }
        }
    }

    @PostMapping("/buy/{id}")
    public ResponseEntity<ArticuloDTO> comprarArticulo(@PathVariable Long id) {
        if (id == 0) {
            return ResponseEntity.badRequest().build();
        } else {
            ArticuloDTO articuloDTO = new ArticuloDTO();
            articuloDTO.setId(id);
            Optional<ArticuloDTO> opt = Optional.ofNullable(articuloService.findById(articuloDTO));
            if (opt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            } else {
                articuloService.updateStock(id);
                return ResponseEntity.ok(opt.get());
            }
        }
    }

    @GetMapping("/findByCategoria/{id}")
    public ResponseEntity<ArticuloDTO> findByCategoria(@PathVariable Long categoriaid) {
        if (categoriaid == 0) {
            return ResponseEntity.badRequest().build();
        } else {
            ArticuloDTO articuloDTO = new ArticuloDTO();
            articuloDTO.setCategoriaId(categoriaid);
            Optional<ArticuloDTO> opt = Optional.ofNullable(articuloService.findByCategoria(articuloDTO));
            if (opt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            } else {
                return ResponseEntity.ok(opt.get());
            }
        }
    }
}
