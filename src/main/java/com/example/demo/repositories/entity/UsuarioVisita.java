package com.example.demo.repositories.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios_visitas", schema = "pfc")
public class UsuarioVisita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_visita_id", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "visita_usuario_id", nullable = false)
    private VisitaEntity visita;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
}