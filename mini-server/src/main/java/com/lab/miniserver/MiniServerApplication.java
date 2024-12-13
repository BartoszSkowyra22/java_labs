package com.lab.miniserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MiniServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniServerApplication.class, args);
    }

}
