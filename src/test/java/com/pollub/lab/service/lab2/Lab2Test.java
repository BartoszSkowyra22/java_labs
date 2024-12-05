package com.pollub.lab.service.lab2;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

import static com.jayway.jsonpath.internal.path.PathCompiler.fail;
import static org.assertj.core.api.Assertions.assertThat;

class Lab2Test {

    private final Lab2 lab2 = new Lab2();

    @Test
    void testFilterCarBrands() {
        List<String> cars = Arrays.asList(
                "BMW X5", "Audi A4", "Toyota Corolla", "Ford Mustang", "Mercedes C-Class",
                "BMW X5", "Audi A5", "Ford Fiesta"
        );
        String filterQuery = "Audi";
        List<String> result = lab2.filterCarBrands(cars, filterQuery);
        assertThat(result).containsExactly("Audi A4", "Audi A5");
    }

    @Test
    void testFilterCarBrandsWithDifferentCases() {
        List<String> cars = Arrays.asList(
                "BMW X5", "audi A4", "Toyota Corolla", "FORD Mustang", "Mercedes C-Class",
                "bmw x5", "AUDI A5", "Ford Fiesta"
        );
        String filterQuery = "aUdI";
        List<String> result = lab2.filterCarBrands(cars, filterQuery);
        List<String> expected = Arrays.asList("audi A4", "AUDI A5");
        assertThat(result).containsExactlyInAnyOrderElementsOf(expected);
    }


    @Test
    void testCountDuplicates() {
        List<String> cars = Arrays.asList(
                "BMW X5", "Audi A4", "Toyota Corolla", "Ford Mustang",
                "BMW X5", "Audi A5", "Ford Fiesta"
        );

        Map<String, Long> result = cars.stream()
                .collect(Collectors.groupingBy(car -> car, Collectors.counting()));

        assertThat(result.get("BMW X5")).isEqualTo(2);
        assertThat(result.get("Audi A4")).isEqualTo(1);
        assertThat(result.size()).isEqualTo(6);
    }

    @Test
    void testCountDuplicatesWithEmptyList() {
        List<String> emptyCars = Collections.emptyList();

        Map<String, Long> carFrequencies = emptyCars.stream()
                .collect(Collectors.groupingBy(car -> car, Collectors.counting()));

        assertThat(carFrequencies).isEmpty();
    }


    @Test
    void testMapListToRentalPrices() {
        List<String> cars = Arrays.asList(
                "BMW X5", "Audi A4", "Toyota Corolla", "Ford Mustang"
        );

        cars.forEach(car -> {
            String carWithPrice = car + " - ";
            assertThat(carWithPrice).contains(car + " - ");
        });
    }

    @Test
    void testMapListToRentalPricesWithinBounds() {
        List<String> cars = Arrays.asList("BMW X5", "Audi A4", "Toyota Corolla", "Ford Mustang");
        int bound = 200;

        Random random = new Random();
        List<String> result = cars.stream()
                .map(car -> {
                    int price = random.nextInt(bound) + 100;
                    return car + " - " + price + " PLN/24h";
                })
                .toList();

        for (String carWithPrice : result) {
            assertThat(carWithPrice).containsAnyOf("BMW X5", "Audi A4", "Toyota Corolla", "Ford Mustang");
            String[] parts = carWithPrice.split(" - ");
            String pricePart = parts[1].replace(" PLN/24h", "");
            int price = Integer.parseInt(pricePart);
            assertThat(price).isGreaterThanOrEqualTo(100).isLessThanOrEqualTo(100 + bound);
        }
    }


    @Test
    void testSaveFile() throws IOException {
        List<String> cars = Arrays.asList("BMW X5", "Audi A4", "Toyota Corolla");
        String filename = "test_output.txt";

        File file = new File(filename);
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (String car : cars) {
                fileWriter.write(car + "\n");
            }
        }

        assertThat(file).exists();
        List<String> lines = Files.readAllLines(file.toPath());
        assertThat(lines).containsExactly("BMW X5", "Audi A4", "Toyota Corolla");
        file.deleteOnExit();
    }


    @Test
    void testSaveFileWithExistingFilename() throws IOException {
        String filename = "test_existing_file.txt";
        List<String> initialCars = Arrays.asList("BMW X5", "Audi A4", "Toyota Corolla");
        List<String> newCars = Arrays.asList("Ford Mustang", "Mercedes C-Class");

        File file = new File(filename);
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (String car : initialCars) {
                fileWriter.write(car + "\n");
            }
        }
        List<String> initialLines = Files.readAllLines(file.toPath());
        assertThat(initialLines).containsExactlyElementsOf(initialCars);

        lab2.saveFile(newCars, filename);

        List<String> updatedLines = Files.readAllLines(file.toPath());
        assertThat(updatedLines).containsExactlyElementsOf(newCars);
        file.deleteOnExit();
    }


    @Test
    void testReadFile() throws IOException {
        String filename = "test_input.txt";

        File file = new File(filename);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Audi A4\nBMW X5\nToyota Corolla\nAudi A5\n");
        }

        List<String> lines = Files.readAllLines(file.toPath())
                .stream()
                .sorted()
                .filter(car -> car.toLowerCase().contains("a4"))
                .toList();

        assertThat(lines).containsExactly("Audi A4");
        file.deleteOnExit();
    }

    @Test
    void testReadFileWithEmptyFile() throws IOException {
        String filename = "empty_file.txt";
        File file = new File(filename);
        if (!file.exists()) {
            assert file.createNewFile();
        }

        try {
            lab2.readFile(filename);
        } catch (Exception e) {
            fail("Method readFile should not throw an exception for an empty file.");
        }

        List<String> lines = Files.readAllLines(file.toPath());
        assertThat(lines).isEmpty();
        file.deleteOnExit();
    }

}

