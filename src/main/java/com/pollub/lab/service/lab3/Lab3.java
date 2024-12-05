package com.pollub.lab.service.lab3;

import com.pollub.lab.model.lab3.CarImpl;
import com.pollub.lab.model.lab3.FamilyCarImpl;
import com.pollub.lab.model.lab3.LuxuryCarImpl;
import com.pollub.lab.model.lab3.SportCarImpl;
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
        List<FamilyCarImpl> familyCars = new ArrayList<>();
        familyCars.add(new FamilyCarImpl("Toyota", "Sienna", 2018, 62345, 300, 7));
        familyCars.add(new FamilyCarImpl("Ford", "Galaxy", 2020, 21327, 250, 6));

        saveXml(familyCars, "familyCars.xml");
        List<CarImpl> savedFamilyCars = readXml("familyCars.xml", "FamilyCar");
        showParametersAfterReadXML(savedFamilyCars);

        System.out.println("\nSamochody sportowe");
        List<SportCarImpl> sportCars = new ArrayList<>();
        sportCars.add(new SportCarImpl("Ferrari", "488", 2021, 12348, 450, 330));
        sportCars.add(new SportCarImpl("Lamborghini", "Aventador", 2023, 11234, 500, 350));

        saveXml(sportCars, "sportCars.xml");
        List<CarImpl> savedSportCars = readXml("sportCars.xml", "SportCar");
        showParametersAfterReadXML(savedSportCars);

        System.out.println("\nSamochody luksusowe");
        List<LuxuryCarImpl> luxuryCars = new ArrayList<>();
        luxuryCars.add(new LuxuryCarImpl("Mercedes", "S-Class", 2022, 3278, 500, true));
        luxuryCars.add(new LuxuryCarImpl("BMW", "7 Series", 2021, 24560, 400, false));

        saveXml(luxuryCars, "luxuryCars.xml");
        List<CarImpl> savedLuxuryCars = readXml("luxuryCars.xml", "LuxuryCar");
        showParametersAfterReadXML(savedLuxuryCars);

        System.out.println("\n------ ODCZYT ZESTAWU Z BŁĘDAMI -------");
        try {
            List<CarImpl> readCars4 = readXml("assets/test.xml", "SportCar");
            readCars4.forEach(car -> System.out.println("Marka: " + car.getBrand() + ", Model: " + car.getModel() +
                    ", Rok produkcji:  " + car.getProductionYear() + ", Przebieg: " + car.getMileage() +
                    ", Cena wynajmu: " + car.getRentalPrice()));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    public void saveXml(List<? extends CarImpl> cars, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(fos);

            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement("Cars");

            for (CarImpl car : cars) {
                car.writeXML(xmlStreamWriter);
            }

            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.flush();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public List<CarImpl> readXml(String fileName, String carType) {
        List<CarImpl> cars = new ArrayList<>();

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
                    CarImpl car = null;

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
                                    car = new FamilyCarImpl(brand, model, productionYear, mileage, rentalPrice, Integer.parseInt(xmlStreamReader.getElementText()));
                                    break;
                                case "TopSpeed":
                                    car = new SportCarImpl(brand, model, productionYear, mileage, rentalPrice, Integer.parseInt(xmlStreamReader.getElementText()));
                                    break;
                                case "HasMassageSeats":
                                    car = new LuxuryCarImpl(brand, model, productionYear, mileage, rentalPrice, Boolean.parseBoolean(xmlStreamReader.getElementText()));
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

    void showParametersAfterReadXML(List<CarImpl> savedCars) {
        savedCars.forEach(car -> System.out.print(car + "\n"));
    }
}