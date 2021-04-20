package com.profile_messages.profile_messages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProfileMessagesApplication {



	public static void main(String[] args) {
		SpringApplication.run(ProfileMessagesApplication.class, args);
	}
}
