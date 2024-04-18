package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.VisitaDTO;
import com.example.demo.repositories.dao.VisitaRepository;
import com.example.demo.repositories.entity.VisitaEntity;

@Service
public class VisitaServiceImpl implements VisitaService {

    @Autowired
    private VisitaRepository visitaRepository;

    @Override
    public List<VisitaDTO> getAll() {
        List<VisitaEntity> visitaes = visitaRepository.findAll();
        return visitaes.stream()
                .map(VisitaDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VisitaDTO findById(VisitaDTO visitaDTO) {
        Optional<VisitaEntity> visitaEntity = visitaRepository.findById(visitaDTO.getId());
        if (visitaEntity.isPresent()) {
            visitaDTO = VisitaDTO.convertToDTO(visitaEntity.get());
            return visitaDTO;
        } else {
            return null;
        }
    }

    @Override
    public void updateStock(Long id) {
        Optional<VisitaEntity> visitaEntity = visitaRepository.findById(id);
        if (visitaEntity.isPresent()) {
            visitaEntity.get().setStock_entradas(visitaEntity.get().getStock_entradas() - 1);
            visitaRepository.save(visitaEntity.get());
        }
    }
}
