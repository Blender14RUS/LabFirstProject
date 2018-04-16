package com.epam.lab.library.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.epam.lab.library")
public class LibApplication {

    private static final Logger LOG = LoggerFactory.getLogger(LibApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LibApplication.class);
    }
}
