package com.cristiano.marketplace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MarketplaceApiDesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceApiDesafioApplication.class, args);
	}

}
