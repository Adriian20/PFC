package com.example.demo.repositories.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repositories.entity.ArticuloEntity;

public interface ArticuloRepository extends JpaRepository<ArticuloEntity, Long> {

}
