package com.web.webscrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebscrapperApplication {
	
	 @Bean
	  public RBIScrapper schedulerRunner() {
	    return new RBIScrapper();
	  }
	 
	public static void main(String[] args) {
		SpringApplication.run(WebscrapperApplication.class, args);
	}

}
