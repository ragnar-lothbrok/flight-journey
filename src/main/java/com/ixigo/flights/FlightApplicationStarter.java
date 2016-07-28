package com.ixigo.flights;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main Class from where Application will start running.
 * 
 */
@SpringBootApplication
@EnableTransactionManagement
@Configuration
@EnableAutoConfiguration(
		exclude = { DataSourceTransactionManagerAutoConfiguration.class, DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
				SecurityAutoConfiguration.class, ManagementSecurityAutoConfiguration.class })
public class FlightApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(FlightApplicationStarter.class, args);
	}
	
}
