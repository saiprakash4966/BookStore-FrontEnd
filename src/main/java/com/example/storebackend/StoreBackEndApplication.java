package com.example.storebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreBackEndApplication {

    public static void main(String[] args) {

        SpringApplication.run(StoreBackEndApplication.class, args);

        System.out.println("Book Store Application");
    }

}
