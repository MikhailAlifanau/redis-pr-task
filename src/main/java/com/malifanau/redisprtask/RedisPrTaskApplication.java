package com.malifanau.redisprtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisPrTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisPrTaskApplication.class, args);
    }

}
