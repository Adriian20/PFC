package com.example.demo.repositories.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "usuarios", schema = "pfc")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "contrasenya", nullable = false)
    private String contrasenya;

    @Column(name = "cuenta_bancaria", nullable = true)
    private String cuenta_bancaria;

    @Column(name = "token", nullable = true)
    private String token;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioArticulo> articulos = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UsuarioVisita> visitas = new HashSet<>();

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
