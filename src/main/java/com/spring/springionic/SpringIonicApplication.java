package com.spring.springionic;

import java.util.Arrays;

import com.spring.springionic.domain.Category;
import com.spring.springionic.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringIonicApplication implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringIonicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}
