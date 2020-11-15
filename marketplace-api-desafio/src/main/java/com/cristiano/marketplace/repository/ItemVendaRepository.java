package com.cristiano.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cristiano.marketplace.domain.ItemVenda;

@Repository 
public interface ItemVendaRepository extends JpaRepository<ItemVenda,Long>{

	/**
	 * Amount of sales per days of existence of the product
	 * @param productId
	 * @param daysProductExistence
	 * @return
	 */
	@Query(value="SELECT round(sum( vendasDia.qtd)/:daysProductExistence ) "
			+ "	  FROM ( "
			+ "			 SELECT count(i.ID_ITEM_VENDA ) as qtd "
			+ "			 FROM ITEM_VENDA as i  "
			+ "				  join VENDA as v on i.ID_VENDA = v.ID_VENDA "
			+ "			 WHERE i.ID_PRODUTO = :productId "
			+ "		   ) as vendasDia ", nativeQuery = true)	
	Long findSalesPerDaysExistenceProduct(@Param("productId") Long idProduto, @Param("daysProductExistence") Integer dias);
}
