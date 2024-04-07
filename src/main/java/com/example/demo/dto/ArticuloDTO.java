package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.repositories.entity.ArticuloEntity;

import lombok.Data;

@Data
public class ArticuloDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nombre;
    private String descripcion;
    private String img;
    private Float precio;
    private String marca;
    private Float talla;
    private String genero;

    public static ArticuloDTO convertToDTO(ArticuloEntity articuloEntity) {
        ArticuloDTO articuloDTO = new ArticuloDTO();

        articuloDTO.setId(articuloEntity.getId());
        articuloDTO.setNombre(articuloEntity.getNombre());
        articuloDTO.setDescripcion(articuloEntity.getDescripcion());
        articuloDTO.setImg(articuloEntity.getImg());
        articuloDTO.setPrecio(articuloEntity.getPrecio());
        articuloDTO.setMarca(articuloEntity.getMarca());
        articuloDTO.setTalla(articuloEntity.getTalla());
        articuloDTO.setGenero(articuloEntity.getGenero());

        return articuloDTO;
    }

    public static ArticuloEntity convertToEntity(ArticuloDTO articuloDTO) {
        ArticuloEntity articuloEntity = new ArticuloEntity();

        articuloEntity.setId(articuloDTO.getId());
        articuloEntity.setNombre(articuloDTO.getNombre());
        articuloEntity.setDescripcion(articuloDTO.getDescripcion());
        articuloEntity.setImg(articuloDTO.getImg());
        articuloEntity.setPrecio(articuloDTO.getPrecio());
        articuloEntity.setMarca(articuloDTO.getMarca());
        articuloEntity.setTalla(articuloDTO.getTalla());
        articuloEntity.setGenero(articuloDTO.getGenero());

        return articuloEntity;
    }

    public ArticuloDTO() {
        super();
    }
}
