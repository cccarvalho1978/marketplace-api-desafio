package com.cristiano.marketplace.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cristiano.marketplace.domain.NoticiaCategoria;
import com.cristiano.marketplace.repository.NoticiaCategoriaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class NewsApiServiceTest {

	private final String NOME_CATEGORIA_SCIENCE = "science";
	private final String NOME_CATEGORIA_TECHNOLOGY = "technology";
	
	@Autowired
	private NewsApiService newsApiService;
	
	@Autowired
	private NoticiaCategoriaRepository noticiaCategoriaRepository;
	
	/**
	 * Test find news by category
	 */
	@Test
	public void testFindNewsByCategory() {
		
		Integer scienceTotal = newsApiService.findNewsByCategory(NOME_CATEGORIA_SCIENCE);
		assertTrue(scienceTotal>=0);

		Integer technologyTotal = newsApiService.findNewsByCategory(NOME_CATEGORIA_TECHNOLOGY);
		assertTrue(technologyTotal>=0);
		
	}
	
	/**
	 * Test find news all categories
	 */
	@Test
	public void testFindNewsAllCategories() {
		
		newsApiService.findNewsAllCategories();;

		// Verify in the database
		List<NoticiaCategoria> noticias = noticiaCategoriaRepository.findAll();
		assertTrue(!noticias.isEmpty());
		
		
	}
	
}
