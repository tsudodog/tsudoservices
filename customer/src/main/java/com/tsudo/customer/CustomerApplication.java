package com.tsudo.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "com.tsudo.amqp",
                "com.tsudo.customer"
        }
)
@EnableFeignClients(basePackages = "com.tsudo.clients")
@PropertySources({@PropertySource("classpath:clients-${spring.profiles.active}.properties")})
public class CustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
