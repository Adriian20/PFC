package com.example.demo.repositories.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "articulos", schema = "pfc")
public class ArticuloEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "descripcion")
    private String descripcion;
    @Basic
    @Column(name = "img")
    private String img;
    @Basic
    @Column(name = "precio")
    private Float precio;
    @Basic
    @Column(name = "marca")
    private String marca;
    @Basic
    @Column(name = "talla")
    private Float talla;
    @Basic
    @Column(name = "genero")
    private String genero;
    @Basic
    @Column(name = "usuario_id")
    private Long usuario_id;
    @Basic
    @Column(name = "categoria_id")
    private Long categoria_id;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ArticuloEntity other = (ArticuloEntity) obj;
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
