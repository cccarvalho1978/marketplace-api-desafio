package com.cristiano.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristiano.marketplace.domain.ItemVenda;

@Repository 
public interface ItemVendaRepository extends JpaRepository<ItemVenda,Long>{

}
