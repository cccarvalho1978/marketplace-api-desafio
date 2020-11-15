package com.cristiano.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cristiano.marketplace.domain.AvaliacaoItemVenda;

@Repository 
public interface AvaliacaoItemVendaRepository extends JpaRepository<AvaliacaoItemVenda,Long>{

	/**
	 * Average product rating over the past 12 monthss
	 * @param productId
	 * @return
	 */
	@Query(value="SELECT ROUND(sum(av.AVALIACAO)/ count(v.ID_VENDA)) "
			+ "   FROM ITEM_VENDA as i "
			+ "        join VENDA as v on i.ID_VENDA = v.ID_VENDA "
			+ "	       join AVALIACAO_ITEM_VENDA as av on i.ID_ITEM_VENDA = av.ID_ITEM_VENDA "
			+ "   WHERE i.ID_PRODUTO = :productId "
			+ "         and v.DATA_VENDA >= DATEADD(YEAR, -1, CURRENT_DATE) "
			+ "         and v.DATA_VENDA < DATEADD(DAY, 1, CURRENT_DATE) ", nativeQuery = true)	
	Long findAverageEvaluationProductYearly(@Param("productId") Long productId);
}
