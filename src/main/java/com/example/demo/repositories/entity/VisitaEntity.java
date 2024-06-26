package com.example.demo.repositories.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "visitas", schema = "pfc")
public class VisitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "fecha_visita", nullable = false)
    private Date fecha_visita;

    @Column(name = "hora_entrada", nullable = false)
    private Time hora_entrada;

    @Column(name = "hora_salida", nullable = true)
    private Time hora_salida;

    @Column(name = "precio_entrada", nullable = false)
    private Float precio_entrada;

    @Column(name = "comentarios", nullable = true)
    private String comentarios;

    @Column(name = "stock_entradas", nullable = false)
    private Integer stock_entradas;

    @Basic
    @Column(name = "img", nullable = true)
    private String img;

    @ManyToMany(mappedBy = "visita", fetch = FetchType.LAZY)
    private Set<UsuarioVisita> usuarioVisitas = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VisitaEntity other = (VisitaEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
}
