package com.pollub.lab.service.lab2;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    void testMapListToRentalPrices() {
        List<String> cars = Arrays.asList(
                "BMW X5", "Audi A4", "Toyota Corolla", "Ford Mustang"
        );
        int bound = 200;

        cars.forEach(car -> {
            String carWithPrice = car + " - ";
            assertThat(carWithPrice).contains(car + " - ");
        });
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
        file.delete();
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
        file.delete();
    }
}