package com.pollub.lab;

import com.pollub.lab.service.lab1.Lab1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LabApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabApplication.class, args);
        Lab1 lab1 = new Lab1();
        lab1.runLab1();
    }
}
