package com.cristiano.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristiano.marketplace.domain.Venda;

@Repository 
public interface VendaRepository extends JpaRepository<Venda,Long>{

}
