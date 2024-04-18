package com.example.demo.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoriaDTO;
import com.example.demo.service.CategoriaService;

@RestController
@RequestMapping("/categories")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/allCategories")
    public List<CategoriaDTO> listarCategorias() {
        List<CategoriaDTO> categorias = categoriaService.getAll();
        return categorias;
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoriaDTO> categoriasId(@PathVariable Long id) {
        if (id == 0) {
            return ResponseEntity.badRequest().build();
        } else {
            CategoriaDTO categoriaDTO = new CategoriaDTO();
            categoriaDTO.setId(id);
            Optional<CategoriaDTO> opt = Optional.ofNullable(categoriaService.findById(categoriaDTO));
            if (opt.isEmpty()) {
                return ResponseEntity.badRequest().build();
            } else {
                return ResponseEntity.ok(opt.get());
            }
        }
    }
}
