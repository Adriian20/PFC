package com.example.demo.repositories.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios", schema = "pfc")
public class UsuarioEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "apellidos")
    private String apellidos;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "contrasenya")
    private String contrasenya;
    @Basic
    @Column(name = "cuenta_bancaria")
    private String cuenta_bancaria;
    @Basic
    @Column(name = "token")
    private String token;
    @OneToMany(mappedBy = "usuarioId")
    private Collection<ArticuloEntity> articuloByIdusuario;
    @ManyToMany
    @JoinTable(name = "usuarios_visitas", joinColumns = @JoinColumn(name = "usuario_visita_id"), 
    inverseJoinColumns = @JoinColumn(name = "visita_usuario_id"))
    private Set<VisitaEntity> visitas = new HashSet<>();

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UsuarioEntity other = (UsuarioEntity) obj;
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
