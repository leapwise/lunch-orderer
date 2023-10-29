package com.leapwise.lunchorderer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The main application class for the Lunch Orderer application.
 */
@SpringBootApplication
@EnableScheduling
public class LunchOrdererApplication {

	public static void main(String[] args) {
		SpringApplication.run(LunchOrdererApplication.class, args);
	}
}
