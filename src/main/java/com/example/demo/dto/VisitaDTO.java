package com.example.demo.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import com.example.demo.repositories.entity.VisitaEntity;

import lombok.Data;

@Data
public class VisitaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Date fecha_visita;
    private Time hora_entrada;
    private Time hora_salida;
    private String tipo_entrada;
    private Float precio_entrada;
    private String comentarios;
    private Integer stock_entradas;

    public static VisitaDTO convertToDTO(VisitaEntity visitaEntity) {
        VisitaDTO visitaDTO = new VisitaDTO();

        visitaDTO.setId(visitaEntity.getId());
        visitaDTO.setFecha_visita(visitaEntity.getFecha_visita());
        visitaDTO.setHora_entrada(visitaEntity.getHora_entrada());
        visitaDTO.setHora_salida(visitaEntity.getHora_salida());
        visitaDTO.setTipo_entrada(visitaEntity.getTipo_entrada());
        visitaDTO.setPrecio_entrada(visitaEntity.getPrecio_entrada());
        visitaDTO.setComentarios(visitaEntity.getComentarios());
        visitaDTO.setStock_entradas(visitaEntity.getStock_entradas());

        return visitaDTO;
    }

    public static VisitaEntity convertToEntity(VisitaDTO visitaDTO) {
        VisitaEntity visitaEntity = new VisitaEntity();

        visitaEntity.setId(visitaDTO.getId());
        visitaEntity.setFecha_visita(visitaDTO.getFecha_visita());
        visitaEntity.setHora_entrada(visitaDTO.getHora_entrada());
        visitaEntity.setHora_salida(visitaDTO.getHora_salida());
        visitaEntity.setTipo_entrada(visitaDTO.getTipo_entrada());
        visitaEntity.setPrecio_entrada(visitaDTO.getPrecio_entrada());
        visitaEntity.setComentarios(visitaDTO.getComentarios());
        visitaEntity.setStock_entradas(visitaDTO.getStock_entradas());

        return visitaEntity;
    }

    public VisitaDTO() {
        super();
    }
}
