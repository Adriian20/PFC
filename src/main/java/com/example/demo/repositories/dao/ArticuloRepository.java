package com.example.demo.repositories.dao;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.repositories.entity.ArticuloEntity;

public interface ArticuloRepository extends JpaRepository<ArticuloEntity, Long> {

    @Query("SELECT a FROM ArticuloEntity a WHERE a.categoriaId = :categoriaId")
    List<ArticuloEntity> findByCategoriaId(@Param("categoriaId") Long categoriaId);
}
