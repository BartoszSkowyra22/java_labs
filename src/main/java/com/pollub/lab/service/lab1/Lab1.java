package com.pollub.lab.service.lab1;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class Lab1 {

    public void runLab1() {
        System.out.println("------ Run Lab1 ------");

        System.out.println("\n\n------ ZESTAW 1 -------");
        List<String> cars = Arrays.asList(
                "BMW X5", "Audi A4", "Toyota Corolla", "Ford Mustang", "Mercedes C-Class",
                "BMW X5", "Audi A3", "Ford Fiesta"
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
        saveFile(filteredCars, filename);
        readFile(filename);
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

    private void saveFile(List<String> cars) {
        Path path = Paths.get("wynik.txt");
        System.out.println("Saving into file...");
        try {
            Files.write(path, cars);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Saved!");
    }

    private void readFile() {
        Path path = Paths.get("wynik.txt");
        System.out.println("Reading file...");
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println("Done!");
    }
}

