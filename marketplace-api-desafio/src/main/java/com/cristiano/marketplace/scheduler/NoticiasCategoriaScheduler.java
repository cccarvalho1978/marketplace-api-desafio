package com.cristiano.marketplace.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cristiano.marketplace.service.NewsApiService;
import com.cristiano.marketplace.service.ProdutoService;

/**
 * Scheduler that runs every 6 hours finding the total news by category
 * and persists in the table noticia_categoria
 * 
 * @author cristiano.carvalho
 *
 */
@Component
public class NoticiasCategoriaScheduler {

	// Log to show the behavior of the methods
	private static final Logger log = LoggerFactory.getLogger(NoticiasCategoriaScheduler.class);
	
	@Autowired
	private NewsApiService newsApiService;
	
	/**
	 * 
	 * 00:01
	 * 06:01
	 * 12:01
	 * 18:01
	 * 
	 */
	//@Scheduled(cron = "0 1 */6 * * *")
	@Scheduled(cron = "0 */5 * * * *") // For test five minutes
	protected void findNewsAllCategories() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		log.info("Begin -- findNewsAllCategories ..."+ sdf.format(new Date()));
		newsApiService.findNewsAllCategories();
		log.info("End    - findNewsAllCategories ..."+ sdf.format(new Date()));
	}
}
