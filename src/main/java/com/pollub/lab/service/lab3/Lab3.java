package com.pollub.lab.service.lab3;

import com.pollub.lab.model.lab3.Car;
import com.pollub.lab.model.lab3.FamilyCar;
import com.pollub.lab.model.lab3.LuxuryCar;
import com.pollub.lab.model.lab3.SportCar;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class Lab3 {

    public void runLab3() {
        System.out.println("------ Run Lab3 ------");

        System.out.println("\nSamochody rodzinne");
        List<FamilyCar> familyCars = new ArrayList<>();
        familyCars.add(new FamilyCar("Toyota", "Sienna", 2018, 62345, 300, 7));
        familyCars.add(new FamilyCar("Ford", "Galaxy", 2020, 21327, 250, 6));

        saveXml(familyCars, "familyCars.xml");
        List<Car> savedFamilyCars = readXml("familyCars.xml", "FamilyCar");
        showParametersAfterReadXML(savedFamilyCars);

        System.out.println("\nSamochody sportowe");
        List<SportCar> sportCars = new ArrayList<>();
        sportCars.add(new SportCar("Ferrari", "488", 2021, 12348, 450, 330));
        sportCars.add(new SportCar("Lamborghini", "Aventador", 2023, 11234, 500, 350));

        saveXml(sportCars, "sportCars.xml");
        List<Car> savedSportCars = readXml("sportCars.xml", "SportCar");
        showParametersAfterReadXML(savedSportCars);

        System.out.println("\nSamochody luksusowe");
        List<LuxuryCar> luxuryCars = new ArrayList<>();
        luxuryCars.add(new LuxuryCar("Mercedes", "S-Class", 2022, 3278, 500, true));
        luxuryCars.add(new LuxuryCar("BMW", "7 Series", 2021, 24560, 400, false));

        saveXml(luxuryCars, "luxuryCars.xml");
        List<Car> savedLuxuryCars = readXml("luxuryCars.xml", "LuxuryCar");
        showParametersAfterReadXML(savedLuxuryCars);

        System.out.println("\n------ ODCZYT ZESTAWU Z BŁĘDAMI -------");
        try {
            List<Car> readCars4 = readXml("assets/test.xml", "SportCar");
            readCars4.forEach(car -> System.out.println("Marka: " + car.getBrand() + ", Model: " + car.getModel() +
                    ", Rok produkcji:  " + car.getProductionYear() + ", Przebieg: " + car.getMileage() +
                    ", Cena wynajmu: " + car.getRentalPrice()));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void saveXml(List<? extends Car> cars, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(fos);

            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement("Cars");

            for (Car car : cars) {
                car.writeXML(xmlStreamWriter);
            }

            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.flush();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public List<Car> readXml(String fileName, String carType) {
        List<Car> cars = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(fileName)) {
            XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(fis);

            while (xmlStreamReader.hasNext()) {
                xmlStreamReader.next();
                if (xmlStreamReader.isStartElement() && carType.equals(xmlStreamReader.getLocalName())) {
                    String brand = null;
                    String model = null;
                    int productionYear = 0;
                    int mileage = 0;
                    int rentalPrice = 0;
                    Car car = null;

                    while (xmlStreamReader.hasNext()) {
                        xmlStreamReader.next();
                        if (xmlStreamReader.isStartElement()) {
                            String elementName = xmlStreamReader.getLocalName();
                            switch (elementName) {
                                case "Brand":
                                    brand = xmlStreamReader.getElementText();
                                    break;
                                case "Model":
                                    model = xmlStreamReader.getElementText();
                                    break;
                                case "ProductionYear":
                                    productionYear = Integer.parseInt(xmlStreamReader.getElementText());
                                    break;
                                case "Mileage":
                                    mileage = Integer.parseInt(xmlStreamReader.getElementText());
                                    break;
                                case "RentalPrice":
                                    rentalPrice = Integer.parseInt(xmlStreamReader.getElementText());
                                    break;
                                case "SeatingCapacity":
                                    car = new FamilyCar(brand, model, productionYear, mileage, rentalPrice, Integer.parseInt(xmlStreamReader.getElementText()));
                                    break;
                                case "TopSpeed":
                                    car = new SportCar(brand, model, productionYear, mileage, rentalPrice, Integer.parseInt(xmlStreamReader.getElementText()));
                                    break;
                                case "HasMassageSeats":
                                    car = new LuxuryCar(brand, model, productionYear, mileage, rentalPrice, Boolean.parseBoolean(xmlStreamReader.getElementText()));
                                    break;
                            }
                        } else if (xmlStreamReader.isEndElement() && carType.equals(xmlStreamReader.getLocalName())) {
                            if (brand == null || model == null || productionYear <= 0 || mileage < 0 || rentalPrice <= 0) {
                                throw new IllegalArgumentException("Nieprawidłowe dane samochodu. Nie można utworzyć obiektu");
                            }
                            if (car != null) cars.add(car);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Błąd podczas przetwarzania samochodu: " + e.getMessage());
        }
        return cars;
    }

    void showParametersAfterReadXML(List<Car> savedCars) {
        savedCars.forEach(car -> {
            System.out.print(car + "\n");
        });
    }
}