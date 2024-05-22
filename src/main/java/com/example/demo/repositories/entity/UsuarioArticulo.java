package com.example.demo.repositories.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "articulos_usuarios", schema = "pfc")
public class UsuarioArticulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_articulo_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "articulo_usuario_id", nullable = false)
    private ArticuloEntity articulo;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
}
