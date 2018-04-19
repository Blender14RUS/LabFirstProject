package com.epam.lab.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.epam.lab.library")
public class LibApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibApplication.class);
    }
}
