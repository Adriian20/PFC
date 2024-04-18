package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.VisitaDTO;

public interface VisitaService {

    List<VisitaDTO> getAll();

    VisitaDTO findById(VisitaDTO visitaDTO);

    void updateStock(Long id);
}
