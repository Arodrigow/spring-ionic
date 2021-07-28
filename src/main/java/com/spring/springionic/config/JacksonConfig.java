package com.spring.springionic.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.springionic.domain.BilletPayment;
import com.spring.springionic.domain.CreditCardPayment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
    
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder(){
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
        public void configure(ObjectMapper objectMapper) {
            objectMapper.registerSubtypes(CreditCardPayment.class);
            objectMapper.registerSubtypes(BilletPayment.class);  
            super.configure(objectMapper);          
        }
        };
        return builder;
    }
}
