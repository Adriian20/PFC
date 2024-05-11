package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ArticuloDTO;
import com.example.demo.repositories.dao.ArticuloRepository;
import com.example.demo.repositories.entity.ArticuloEntity;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    private static final Logger log = LoggerFactory.getLogger(ArticuloService.class);

    @Autowired
    private ArticuloRepository articuloRepository;

    @Override
    public List<ArticuloDTO> getAll() {
        List<ArticuloEntity> articulos = articuloRepository.findAll();
        return articulos.stream()
                .map(ArticuloDTO::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArticuloDTO findById(ArticuloDTO articuloDTO) {
        Optional<ArticuloEntity> articuloEntity = articuloRepository.findById(articuloDTO.getId());
        if (articuloEntity.isPresent()) {
            articuloDTO = ArticuloDTO.convertToDTO(articuloEntity.get());
            return articuloDTO;
        } else {
            return null;
        }
    }

    @Override
    public void updateStock(Long id) {
        Optional<ArticuloEntity> articuloEntity = articuloRepository.findById(id);
        if (articuloEntity.isPresent()) {
            articuloEntity.get().setStock(articuloEntity.get().getStock() - 1);
            articuloRepository.save(articuloEntity.get());
        }
    }

    @Override
    public ArticuloDTO findByCategoria(ArticuloDTO articuloDTO) {
        log.info("ArticuloServiceImpl - findByCategoria: Busca de usuario por categoria_id");

        Optional<ArticuloEntity> articuloEntity = articuloRepository.findByCategoriaId(articuloDTO.getCategoriaId());

        if (articuloEntity.isPresent()) {
            return ArticuloDTO.convertToDTO(articuloEntity.get());
        } else {
            return null;
        }
    }
}
