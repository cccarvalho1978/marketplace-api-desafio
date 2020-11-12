package com.cristiano.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristiano.marketplace.domain.Comprador;

@Repository 
public interface CompradorRepository extends JpaRepository<Comprador,Long>{

}
