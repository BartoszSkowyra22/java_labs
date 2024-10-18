package com.pollub.lab.service.lab2;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class Lab2 {

    public void runLab2() {
        System.out.println("------ Run Lab1 ------");

        System.out.println("\n\n------ ZESTAW 1 -------");
        List<String> cars = Arrays.asList(
                "BMW X5", "Audi A4", "Toyota Corolla", "Ford Mustang", "Mercedes C-Class",
                "BMW X5", "Audi A5", "Ford Fiesta"
        );
        runMethodsSet(cars, "Audi", 200, "wynik1.txt");


        System.out.println("\n\n------ ZESTAW 2 -------");
        List<String> cars2 = Arrays.asList(
                "Ferrari F8", "Lamborghini Huracan", "Porsche 911", "McLaren 720S",
                "Porsche 911 GT3-RS", "Audi R8", "BMW M4"
        );
        runMethodsSet(cars2, "Porsche", 900, "wynik2.txt");


        System.out.println("\n\n------ ZESTAW 3 -------");
        List<String> cars3 = Arrays.asList(
                "Mercedes S-Class", "Audi A8",
                "Jaguar XJ", "Maserati Quattroporte", "Porsche Panamera", "Maserati Ghibli"
        );
        runMethodsSet(cars3, "Maserati", 500, "wynik3.txt");
    }

    private void runMethodsSet(List<String> cars, String filterQuery, Integer bound, String filename) {
        List<String> filteredCars = filterCarBrands(cars, filterQuery);
        countDuplicates(cars);
        mapListToRentalPrices(cars, bound);
        try {
            saveFile(filteredCars, filename);
            readFile(filename);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private List<String> filterCarBrands(List<String> cars, String filterQuery) {
        System.out.println("------ Task 1: Filtrowanie marek samochodów ------");
        List<String> filteredCars = cars.stream()
                .filter(car -> car.toLowerCase(Locale.ROOT).contains(filterQuery.toLowerCase(Locale.ROOT)))
                .toList();

        System.out.println("Samochody marki " + filterQuery + ": " + filteredCars);
        return filteredCars;
    }

    private void countDuplicates(List<String> cars) {
        System.out.println("\n------ Task 2: Zliczanie duplikatów ------");

        Map<String, Long> carFrequencies = cars.stream()
                .collect(Collectors.groupingBy(car -> car, Collectors.counting()));

        System.out.println("Liczba powtórzeń samochodów: " + carFrequencies);
    }

    private void mapListToRentalPrices(List<String> cars, Integer bound) {
        System.out.println("\n------ Task 3: Mapowanie samochodów na pseudolosową cenę najmu ------");

        Random random = new Random();
        Stream<String> carsWithPrices = cars.stream()
                .map(car -> car + " - " + (random.nextInt(bound) + 100) + " PLN/24h");

        carsWithPrices.forEach(System.out::println);
    }

    private void saveFile(List<String> cars, String filename) {
        File file = new File(filename);
        System.out.println("\nSaving into file...");
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            for (String car : cars) {
                fileWriter.write(car + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Saved!");
    }

    private void readFile(String filename) {
        Path path = Paths.get(filename);
        System.out.println("\nReading file...");
        try {
            List<String> lines = Files.readAllLines(path)
                    .stream()
                    .sorted()
                    .filter(car -> car.toLowerCase(Locale.ROOT).contains("a4".toLowerCase(Locale.ROOT)))
                    .toList();

            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done!");
    }
}

