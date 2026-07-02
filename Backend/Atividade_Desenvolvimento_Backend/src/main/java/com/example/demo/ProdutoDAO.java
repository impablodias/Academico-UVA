package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoDAO extends JpaRepository<Produto, Long> {
    @Query("SELECT p FROM Produto p WHERE p.modelo ILIKE %:termo% OR p.marca ILIKE %:termo%")
    List<Produto> buscarPorNomeOuCategoria(@Param("termo") String termo);
}