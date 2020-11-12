package com.cristiano.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristiano.marketplace.domain.Produto;

@Repository 
public interface ProdutoRepository extends JpaRepository<Produto,Long>{
	
}
