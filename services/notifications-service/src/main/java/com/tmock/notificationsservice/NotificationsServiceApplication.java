package com.tmock.notificationsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class NotificationsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationsServiceApplication.class, args);
	}

}
