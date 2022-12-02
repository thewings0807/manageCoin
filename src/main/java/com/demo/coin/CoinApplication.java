package com.demo.coin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.demo.coin.entity")
@EnableJpaRepositories(basePackages = "com.demo.coin.repository")
@ComponentScan(basePackages = {"com.demo.coin.*"})
public class CoinApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoinApplication.class, args);
    }
}