package com.example.demo.repositories.dao;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.repositories.entity.UsuarioEntity;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UsuarioEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    public Optional<UsuarioEntity> findByEmail(@Param("email") String email); 

    @Query("SELECT u FROM UserEntity u WHERE u.token = :token")
    public Optional<UsuarioEntity> findByToken(@Param("token") String token);
}
