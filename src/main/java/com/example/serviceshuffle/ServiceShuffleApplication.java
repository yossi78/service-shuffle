package com.example.serviceshuffle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
public class ServiceShuffleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceShuffleApplication.class, args);
    }

}
