package com.azienda.catalogoProdotti.bin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.azienda.catalogoProdotti.service", "com.azienda.catalogoProdotti.controller"})
@EnableJpaRepositories(basePackages = {"com.azienda.catalogoProdotti.repository"})
@EntityScan(basePackages = {"com.azienda.catalogoProdotti.model"})
public class CatalogoProdottiApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(CatalogoProdottiApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
