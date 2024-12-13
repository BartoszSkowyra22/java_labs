package com.pollub.lab;

import com.pollub.lab.service.lab2.Lab2;
import com.pollub.lab.service.lab3.Lab3;
import com.pollub.lab.service.lab4.Lab4;
import com.pollub.lab.service.lab7.Lab7;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class LabApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(LabApplication.class, args);
//        showMenu();
    }

    private static void showMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("------ Witaj w aplikacji ------");
        System.out.println("2. Uruchom Lab 2");
        System.out.println("3. Uruchom Lab 3");
        System.out.println("4. Uruchom Lab 4");
        System.out.println("7. Uruchom Lab 7 - JSoup");
        System.out.println("9. Uruchom Lab 10 - RabbitMQ");
        System.out.println("0. REST API będzie działać w tle");
        System.out.print("Wybierz opcję: ");

        String input = scanner.nextLine();

        switch (input) {
            case "2" -> {
                System.out.println("\n--- Uruchamianie Lab 2 ---");
                Lab2 lab2 = new Lab2();
                lab2.runLab2();
            }
            case "3" -> {
                System.out.println("\n--- Uruchamianie Lab 3 ---");
                Lab3 lab3 = new Lab3();
                lab3.runLab3();
            }
            case "4" -> {
                System.out.println("\n--- Uruchamianie Lab 4 ---");
                Lab4 lab4 = new Lab4();
                lab4.runLab4();
            }
            case "7" -> {
                System.out.println("\n--- Uruchamianie Lab 7 ---");
                Lab7 lab7 = new Lab7();
                lab7.runLab7();
            }
            case "9" -> {
                System.out.println("\n--- Uruchamianie Lab 10 ---");
                System.out.println("---Nasłuchiwanie kolejek RabbitMQ---");
            }
            case "0" -> System.out.println("\n--- REST API działa w tle---");
            default -> System.out.println("\n--- Nieprawidłowa opcja. REST API działa w tle---");
        }
    }
}
