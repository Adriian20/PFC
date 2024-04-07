package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.repositories.entity.CategoriaEntity;

import lombok.Data;

@Data
public class CategoriaDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private Long id;
    private String nombre;
    private String descripcion;
    private String img;

    public static CategoriaDTO convertToDTO(CategoriaEntity categoriaEntity) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();

        categoriaDTO.setId(categoriaEntity.getId());
        categoriaDTO.setNombre(categoriaEntity.getNombre());
        categoriaDTO.setDescripcion(categoriaEntity.getDescripcion());
        categoriaDTO.setImg(categoriaEntity.getImg());

        return categoriaDTO;
    }

    public static CategoriaEntity convertToEntity(CategoriaDTO categoriaDTO) {
        CategoriaEntity categoriaEntity = new CategoriaEntity();

        categoriaEntity.setId(categoriaDTO.getId());
        categoriaEntity.setNombre(categoriaDTO.getNombre());
        categoriaEntity.setDescripcion(categoriaDTO.getDescripcion());
        categoriaEntity.setImg(categoriaDTO.getImg());

        return categoriaEntity;
    }

    public CategoriaDTO() {
        super();
    }
}
