package com.example.demo.web.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoriaDTO;
import com.example.demo.service.CategoriaService;

@RestController
@RequestMapping("/categories")
public class CategoriaController {

    private CategoriaService categoriaService;

    @GetMapping("/allCategories")
    public List<CategoriaDTO> listarCategorias() {
        List<CategoriaDTO> categorias = categoriaService.getAll();
        return categorias;
    }
}
