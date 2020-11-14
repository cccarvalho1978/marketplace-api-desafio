package com.cristiano.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristiano.marketplace.domain.AvaliacaoItemVenda;

@Repository 
public interface AvaliacaoItemVendaRepository extends JpaRepository<AvaliacaoItemVenda,Long>{

}
