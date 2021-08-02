package com.spring.springionic;

import com.spring.springionic.services.s3Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIonicApplication implements CommandLineRunner{

	@Autowired
	private s3Service service;

	public static void main(String[] args) {
		SpringApplication.run(SpringIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.uploadFile("C:\\Users\\Rodrigo-NB\\Pictures\\test.jpg");
	}

}
