package com.cristiano.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristiano.marketplace.domain.Categoria;

@Repository 
public interface CategoriaRepository extends JpaRepository<Categoria,Long>{

}
