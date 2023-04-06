package com.sa.youtube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class SaYoutubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaYoutubeApplication.class, args);
	}

}
