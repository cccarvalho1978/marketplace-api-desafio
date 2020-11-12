package com.cristiano.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristiano.marketplace.domain.Vendedor;

@Repository 
public interface VendedorRepository extends JpaRepository<Vendedor,Long>{

}
