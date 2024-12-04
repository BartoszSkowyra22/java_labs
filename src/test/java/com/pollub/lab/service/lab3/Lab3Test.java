package com.pollub.lab.service.lab3;

import com.pollub.lab.model.lab3.Car;
import com.pollub.lab.model.lab3.FamilyCar;
import com.pollub.lab.model.lab3.SportCar;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class Lab3Test {

    private final Lab3 lab3 = new Lab3();

    @Test
    void testSaveXmlWithFamilyCars() throws Exception {
        List<FamilyCar> familyCars = new ArrayList<>();
        familyCars.add(new FamilyCar("Toyota", "Sienna", 2018, 62345, 300, 7));
        familyCars.add(new FamilyCar("Ford", "Galaxy", 2020, 21327, 250, 6));
        String fileName = "test_family_cars.xml";
        lab3.saveXml(familyCars, fileName);
        File file = new File(fileName);
        assertThat(file).exists();
        String content = Files.readString(file.toPath());
        assertThat(content).contains("FamilyCar", "Toyota", "Sienna", "Galaxy");
        file.delete();
    }

    @Test
    void testSaveXmlWithEmptyList() throws Exception {
        List<FamilyCar> emptyCars = new ArrayList<>();
        String fileName = "test_empty_cars.xml";
        lab3.saveXml(emptyCars, fileName);
        File file = new File(fileName);
        assertThat(file).exists();
        String content = Files.readString(file.toPath());
        assertThat(content).contains("</Cars>");
        file.delete();
    }

    @Test
    void testReadXmlWithValidSportCars() throws Exception {
        String fileName = "test_sport_cars.xml";
        List<SportCar> sportCars = new ArrayList<>();
        sportCars.add(new SportCar("Ferrari", "488", 2021, 12348, 450, 330));
        lab3.saveXml(sportCars, fileName);

        List<Car> result = lab3.readXml(fileName, "SportCar");

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isInstanceOf(SportCar.class);
        assertThat(result.get(0).getBrand()).isEqualTo("Ferrari");

        new File(fileName).delete();
    }

    @Test
    void testReadXmlWithInvalidData() {
        String fileName = "invalid_data.xml";
        String invalidXml = "<Cars><SportCar><Brand>Ferrari</Brand></SportCar></Cars>";

        try {
            Files.writeString(new File(fileName).toPath(), invalidXml);

            assertThatThrownBy(() -> lab3.readXml(fileName, "SportCar"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Nieprawidłowe dane samochodu");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            new File(fileName).delete();
        }
    }

    @Test
    void testShowParametersAfterReadXMLWithFamilyCars() {
        List<Car> cars = new ArrayList<>();
        cars.add(new FamilyCar("Toyota", "Sienna", 2018, 62345, 300, 7));
        lab3.showParametersAfterReadXML(cars);
    }

    @Test
    void testShowParametersAfterReadXMLWithEmptyList() {
        List<Car> emptyCars = new ArrayList<>();
        lab3.showParametersAfterReadXML(emptyCars);
    }
}
