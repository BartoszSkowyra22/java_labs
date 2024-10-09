package com.pollub.lab.service.lab1;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Lab1 {

    public void runLab1() {
        System.out.println("------ Run Lab1 ------");

        System.out.println("\n\n------ ZESTAW 1 -------");
        List<String> cars = Arrays.asList(
                "BMW X5", "Audi A4", "Toyota Corolla", "Ford Mustang", "Mercedes C-Class",
                "BMW X5", "Audi A3", "Ford Fiesta"
        );
        runMethodsSet(cars, "Audi");


        System.out.println("\n\n------ ZESTAW 2 -------");
        List<String> cars2 = Arrays.asList(
                "Ferrari F8", "Lamborghini Huracan", "Porsche 911", "McLaren 720S",
                "Porsche 911 GT3-RS", "Audi R8", "BMW M4"
        );
        runMethodsSet(cars2, "Porsche");


        System.out.println("\n\n------ ZESTAW 3 -------");
        List<String> cars3 = Arrays.asList(
                "Mercedes S-Class", "Audi A8",
                "Jaguar XJ", "Maserati Quattroporte", "Porsche Panamera", "Maserati Ghibli"
        );
        runMethodsSet(cars3, "Maserati");
    }

    private void runMethodsSet(List<String> cars, String filterQuery) {
        filterCarBrands(cars, filterQuery);
        countDuplicates(cars);
        mapListToRentalPrices(cars);
        //TODO dopisać wywołania dla funkcji zapisu/odczytu z pliku
    }


    private void filterCarBrands(List<String> cars, String filterQuery) {
        System.out.println("------ Task 1: Filtrowanie marek samochodów ------");
        List<String> filteredCars = cars.stream()
                .filter(car -> car.toLowerCase(Locale.ROOT).contains(filterQuery.toLowerCase(Locale.ROOT)))
                .toList();

        System.out.println("Samochody marki " + filterQuery + ": " + filteredCars);
    }

    private void countDuplicates(List<String> cars) {
        System.out.println("\n------ Task 2: Zliczanie duplikatów ------");

        Map<String, Long> carFrequencies = cars.stream()
                .collect(Collectors.groupingBy(car -> car, Collectors.counting()));

        System.out.println("Liczba powtórzeń samochodów: " + carFrequencies);
    }

    private void mapListToRentalPrices(List<String> cars) {
        System.out.println("\n------ Task 3: Mapowanie samochodów na pseudolosową cenę najmu ------");

        Random random = new Random();
        Map<String, Integer> carRentalPrices = cars.stream()
                .collect(Collectors.toMap(
                        car -> car,
                        car -> random.nextInt(200) + 50,
                        (existing, replacement) -> existing
                ));

        System.out.println("Ceny wynajmu samochodów: " + carRentalPrices);
    }

    //TODO dopisać funkcje odczytu/zapisu z pliku
}

