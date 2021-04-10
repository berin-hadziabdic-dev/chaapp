package com.pixelcatsoftware.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@EnableJpaRepositories
@SpringBootApplication
@EnableDiscoveryClient
public class AuthApplication {

	/**Jenkins test 3.*/
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
