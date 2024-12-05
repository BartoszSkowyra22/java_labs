package com.pollub.lab.service.lab3;

import com.pollub.lab.model.lab3.CarImpl;
import com.pollub.lab.model.lab3.FamilyCarImpl;
import com.pollub.lab.model.lab3.SportCarImpl;
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
        List<FamilyCarImpl> familyCars = new ArrayList<>();
        familyCars.add(new FamilyCarImpl("Toyota", "Sienna", 2018, 62345, 300, 7));
        familyCars.add(new FamilyCarImpl("Ford", "Galaxy", 2020, 21327, 250, 6));
        String fileName = "test_family_cars.xml";
        lab3.saveXml(familyCars, fileName);
        File file = new File(fileName);
        assertThat(file).exists();
        String content = Files.readString(file.toPath());
        assertThat(content).contains("FamilyCar", "Toyota", "Sienna", "Galaxy");
        file.deleteOnExit();
    }

    @Test
    void testSaveXmlWithEmptyList() throws Exception {
        List<FamilyCarImpl> emptyCars = new ArrayList<>();
        String fileName = "test_empty_cars.xml";
        lab3.saveXml(emptyCars, fileName);
        File file = new File(fileName);
        assertThat(file).exists();
        String content = Files.readString(file.toPath());
        assertThat(content).contains("</Cars>");
        file.deleteOnExit();
    }

    @Test
    void testReadXmlWithValidSportCars(){
        String fileName = "test_sport_cars.xml";
        List<SportCarImpl> sportCars = new ArrayList<>();
        sportCars.add(new SportCarImpl("Ferrari", "488", 2021, 12348, 450, 330));
        lab3.saveXml(sportCars, fileName);

        List<CarImpl> result = lab3.readXml(fileName, "SportCar");

        assertThat(result).hasSize(1);
        assertThat(result.getFirst()).isInstanceOf(SportCarImpl.class);
        assertThat(result.getFirst().getBrand()).isEqualTo("Ferrari");
        new File(fileName).deleteOnExit();
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
            new File(fileName).deleteOnExit();
        }
    }

    @Test
    void testShowParametersAfterReadXMLWithFamilyCars() {
        List<CarImpl> cars = new ArrayList<>();
        cars.add(new FamilyCarImpl("Toyota", "Sienna", 2018, 62345, 300, 7));
        lab3.showParametersAfterReadXML(cars);
    }

    @Test
    void testShowParametersAfterReadXMLWithEmptyList() {
        List<CarImpl> emptyCars = new ArrayList<>();
        lab3.showParametersAfterReadXML(emptyCars);
    }
}
