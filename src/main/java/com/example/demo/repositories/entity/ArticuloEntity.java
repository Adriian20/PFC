package com.example.demo.repositories.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@Table(name = "articulos", schema = "pfc")
public class ArticuloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Basic
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Basic
    @Column(name = "img", nullable = true)
    private String img;

    @Basic
    @Column(name = "precio", nullable = false)
    private Float precio;

    @Basic
    @Column(name = "marca", nullable = false)
    private String marca;

    @Basic
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Basic
    @Column(name = "talla", nullable = true)
    private String talla;

    @Basic
    @Column(name = "genero", nullable = true)
    private String genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CategoriaEntity categoria;

    @ManyToMany(mappedBy = "articulos", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<UsuarioEntity> usuarios = new HashSet<>();

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
