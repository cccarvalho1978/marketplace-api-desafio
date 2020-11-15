package com.cristiano.marketplace.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cristiano.marketplace.domain.Produto;

@Repository 
public interface ProdutoRepository extends JpaRepository<Produto,Long>, RevisionRepository<Produto,Long,Long> {
	
	/**
	 * Search products by name with pagination
	 * @param nomeProduto
	 * @param pageable
	 * @return
	 */
	@Query(" FROM Produto p " +
			" WHERE LOWER(p.nome) like %:nomeProduto% " ) 
	Page<Produto> search(@Param("nomeProduto") String nomeProduto, Pageable pageable);
}
