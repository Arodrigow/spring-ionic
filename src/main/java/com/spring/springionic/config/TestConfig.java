package com.spring.springionic.config;

import java.text.ParseException;

import com.spring.springionic.services.DBService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
    
    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException{
        dbService.instantiateTestDatabase();
        return true;
    }
}
