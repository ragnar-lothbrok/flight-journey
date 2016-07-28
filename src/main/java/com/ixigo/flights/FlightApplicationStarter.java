package com.ixigo.flights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main Class from where Application will start running.
 * 
 * @author raghunandangupta
 *
 */
@SpringBootApplication
@EnableTransactionManagement
@Configuration
@EnableAutoConfiguration
public class FlightApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(FlightApplicationStarter.class, args);
	}
	
}
