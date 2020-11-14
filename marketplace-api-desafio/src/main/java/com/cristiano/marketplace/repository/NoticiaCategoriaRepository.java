package com.cristiano.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristiano.marketplace.domain.NoticiaCategoria;

@Repository 
public interface NoticiaCategoriaRepository extends JpaRepository<NoticiaCategoria,Long>{


}
