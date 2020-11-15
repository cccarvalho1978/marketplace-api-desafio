package com.cristiano.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cristiano.marketplace.domain.NoticiaCategoria;

@Repository 
public interface NoticiaCategoriaRepository extends JpaRepository<NoticiaCategoria,Long>{

	/**
	 * Average category news found for the day
	 * 
	 * @param categoryId
	 * @return
	 */
	@Query(value="SELECT sum(nc.TOTAL_NOTICIA)/count(nc.ID_NOTICIA_CATEGORIA ) as mediaNoticias "
			+ "   FROM NOTICIA_CATEGORIA nc "
			+ "   WHERE nc.ID_CATEGORIA = :categoryId "
			+ "         and nc.DATA_PESQUISA >= CURRENT_DATE"
			+ "         and nc.DATA_PESQUISA < DATEADD(DAY, 1, CURRENT_DATE) ", nativeQuery = true)	
	Long findNewsByCategory(@Param("categoryId") Long categoryId);

}
