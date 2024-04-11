package com.example.demo.repositories.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repositories.entity.CategoriaEntity;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

}
