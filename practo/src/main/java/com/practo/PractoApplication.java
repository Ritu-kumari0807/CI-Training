package com.practo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class PractoApplication {
    public static void main(String[] args) {
        SpringApplication.run(PractoApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();

    }
}
