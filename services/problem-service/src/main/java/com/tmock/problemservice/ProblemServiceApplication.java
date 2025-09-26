package com.tmock.problemservice;

import com.tmock.problemservice.config.AwsS3Props;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AwsS3Props.class)
public class ProblemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProblemServiceApplication.class, args);
	}

}
