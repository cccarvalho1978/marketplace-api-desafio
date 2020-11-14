package com.cristiano.marketplace.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cristiano.marketplace.domain.Categoria;
import com.cristiano.marketplace.domain.NoticiaCategoria;
import com.cristiano.marketplace.dto.NewsApiDTO;
import com.cristiano.marketplace.repository.CategoriaRepository;
import com.cristiano.marketplace.repository.NoticiaCategoriaRepository;

@Service
public class NewsApiService {

	// Log to show the behavior of the methods
	private static final Logger log = LoggerFactory.getLogger(NewsApiService.class);
	
	@Value("${newsapi.url}")
	private String url;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private NoticiaCategoriaRepository noticiaCategoriaRepository;

	
	/**
	 * Find news by category
	 * @param category
	 * @return
	 */
	public Integer findNewsByCategory(String category) {
		log.info("Find news by category {}", category);
		
		String newsAPIUrl =  url + "&category="+category;
	
		// Newsapi 
		NewsApiDTO result = newsApiTopHeadlines(newsAPIUrl);

		// Initializing total variable
		Integer total = 0;
		
		if( result != null ) {
			
			// Checking how many pages there are of result
			int pages = Math.round(result.getTotalResults()/100) + 1;
			
			// Find the news total with the current date based on the result returned
			total = result.getTotalByDateSearch(new Date());
			
			if(pages>1) {
				// Call from the other pages
				for(int page=2;page<=pages;page++) {
					String newPage = newsAPIUrl + "&page=" + page;
					result = newsApiTopHeadlines(newPage);
					// Find the news total with the current date based on the result returned and adding to the total 
					total += result.getTotalByDateSearch(new Date());
				}
			}
		}
		
		log.info("Category {} total = {}", category, total);
				
		return total;
	}

	/**
	 * Call api newsApi TopHeadLines
	 * 
	 * http://newsapi.org/v2/top-headlines
	 * 
	 * @param newsAPIUrl
	 * @return
	 */
	private NewsApiDTO newsApiTopHeadlines(String newsAPIUrl) {
		
		RestTemplate restTemplate = new RestTemplate();
		NewsApiDTO result = restTemplate
				  .getForObject(newsAPIUrl, NewsApiDTO.class);
		
		return result;
	}
	
	
	/**
	 * For all categories, find the total news on the day
	 */
	public void findNewsAllCategories() {
		
		// Find all categories
		List<Categoria> categorias = categoriaRepository.findAll();
		
		// For each category 
		categorias.forEach(categoria->{
			
			// Finding the total news 
			Integer total = this.findNewsByCategory(categoria.getNome());

			// Persist in the database 
			NoticiaCategoria n = new NoticiaCategoria();
			n.setCategoria(categoria);
			n.setTotalNoticia(total);
			n.setDataPesquisa(new Date());
			noticiaCategoriaRepository.save(n);
			
		});
	}
	
	
}
