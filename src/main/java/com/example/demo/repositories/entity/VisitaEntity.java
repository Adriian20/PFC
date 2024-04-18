package com.example.demo.repositories.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
@Data
@Table(name = "visitas", schema = "pfc")
public class VisitaEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "fecha_visita")
    private Date fecha_visita;
    @Basic
    @Column(name = "hora_entrada")
    private Time hora_entrada;
    @Basic
    @Column(name = "hora_salida")
    private Time hora_salida;
    @Basic
    @Column(name = "tipo_entrada")
    private String tipo_entrada;
    @Basic
    @Column(name = "precio_entrada")
    private Float precio_entrada;
    @Basic
    @Column(name = "comentarios")
    private String comentarios;
    @Basic
    @Column(name = "stock_entradas")
    private Integer stock_entradas;
    @ManyToMany(mappedBy = "visitas", fetch = FetchType.LAZY)
    private Set<UsuarioEntity> usuarios;

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
