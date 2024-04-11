package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoriaDTO;
import com.example.demo.repositories.dao.CategoriaRepository;
import com.example.demo.repositories.entity.CategoriaEntity;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaDTO> getAll() {
        List<CategoriaEntity> categoriaes = categoriaRepository.findAll();
        return categoriaes.stream()
                .map(CategoriaDTO::convertToDTO)
                .collect(Collectors.toList());
    }
}
