package com.cristiano.marketplace.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cristiano.marketplace.service.ProdutoService;

/**
 * Scheduler that runs at 23 hours to calculate the product score
 * 
 * @author cristiano.carvalho
 *
 */
@Component
public class CalculoScoreScheduler {

	// Log to show the behavior of the methods
	private static final Logger log = LoggerFactory.getLogger(CalculoScoreScheduler.class);
		
	@Autowired
	private ProdutoService produtoService;
	
	@Scheduled(cron = "0 0 23 ? * *")
	protected void calcularScore() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		log.info("Begin -- Calculate Score ..."+ sdf.format(new Date()));
		produtoService.calculateScore();
		log.info("End   -- Calculate Score ..."+ sdf.format(new Date()));
		
	}
}
