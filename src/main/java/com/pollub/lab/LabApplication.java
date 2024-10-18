package com.pollub.lab;

import com.pollub.lab.service.lab3.Lab3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LabApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabApplication.class, args);
       // Lab2 lab2 = new Lab2();
        //lab2.runLab2();
        Lab3 lab3 = new Lab3();
        lab3.runLab3();
    }
}
