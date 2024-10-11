package com.pollub.lab.service.lab1;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Lab3 {

    public void runLab3() {
        System.out.println("------ Run Lab1 ------");


        System.out.println("\n\n------ ZESTAW 1 -------");
        List<Car> cars1 = new ArrayList<>();
        cars1.add(new Car("Audi", "A3", 2018, 62345, 300));
        cars1.add(new Car("BMW", "I8", 2021, 12348, 450));
        cars1.add(new Car("Mercedes", "E300", 2020, 21327, 250));
        cars1.add(new Car("Porsche", "Panamera", 2023, 11234, 500));


        saveXml(cars1, "cars.xml");
        List<Car> saveCars1 = readXml("cars.xml");
        saveCars1.forEach(System.out::println);


        System.out.println("\n\n------ ZESTAW 2 -------");
        List<Car> cars2 = new ArrayList<>();
        cars2.add(new Car("Opel", "Mokka", 2019, 37945, 200));
        cars2.add(new Car("Ford", "Puma", 2018, 55004, 150));
        cars2.add(new Car("Hyundai", "Tucson", 2022, 9564, 250));
        cars2.add(new Car("Kia", "XCeed", 2024, 1234, 300));


        saveXml(cars2, "cars2.xml");
        List<Car> saveCars2 = readXml("cars2.xml");
        saveCars2.forEach(System.out::println);


        System.out.println("\n\n------ ZESTAW 3 -------");
        List<Car> cars3 = new ArrayList<>();
        cars3.add(new Car("Dodge", "Charger", 2021, 24560, 400));
        cars3.add(new Car("Chevrolet", "Camaro", 2023, 3278, 500));
        cars3.add(new Car("Dodge", "Challenger", 2017, 49652, 300));
        cars3.add(new Car("Ford", "Mustang", 2021, 29543, 350));


        saveXml(cars3, "cars3.xml");
        List<Car> saveCars3 = readXml("cars3.xml");
        saveCars3.forEach(System.out::println);


        System.out.println("\n\n------ ODCZYT ZESTAWU 1 -------");
        List<Car> readCars1 = readXml("cars.xml");
        readCars1.forEach(car -> System.out.println("Marka: " + car.getBrand() + ", Model: " + car.getModel() + ", Rok produkcji:  " + car.getProduction_year() +
                ", Przebieg: " + car.getMileage() + ", Cena wynajmu: " + car.getRental_price()));

        System.out.println("\n\n------ ODCZYT ZESTAWU 2 -------");
        List<Car> readCars2 = readXml("cars2.xml");
        readCars2.forEach(car -> System.out.println("Marka: " + car.getBrand() + ", Model: " + car.getModel() + ", Rok produkcji:  " + car.getProduction_year() +
                ", Przebieg: " + car.getMileage() + ", Cena wynajmu: " + car.getRental_price()));

        System.out.println("\n\n------ ODCZYT ZESTAWU 3 -------");
        List<Car> readCars3 = readXml("cars3.xml");
        readCars3.forEach(car -> System.out.println("Marka: " + car.getBrand() + ", Model: " + car.getModel() + ", Rok produkcji:  " + car.getProduction_year() +
                ", Przebieg: " + car.getMileage() + ", Cena wynajmu: " + car.getRental_price()));


        System.out.println("\n\n------ ODCZYT ZESTAWU TESTOWEGO -------");
        List<Car> readCars4 = readXml("assets/test.xml");
        readCars4.forEach(car -> System.out.println("Marka: " + car.getBrand() + ", Model: " + car.getModel() + ", Rok produkcji:  " + car.getProduction_year() +
                ", Przebieg: " + car.getMileage() + ", Cena wynajmu: " + car.getRental_price()));
    }

    public void saveXml(List<Car> cars, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            XMLStreamWriter xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(fos);

            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement("Cars");

            for (Car car : cars) {
                xmlStreamWriter.writeStartElement("Car");

                xmlStreamWriter.writeStartElement("Brand");
                xmlStreamWriter.writeCharacters(car.getBrand());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("Model");
                xmlStreamWriter.writeCharacters(car.getModel());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("ProductionYear");
                xmlStreamWriter.writeCharacters(String.valueOf(car.getProduction_year()));
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("Mileage");
                xmlStreamWriter.writeCharacters(String.valueOf(car.getMileage()));
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("RentalPrice");
                xmlStreamWriter.writeCharacters(String.valueOf(car.getRental_price()));
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeEndElement();
            }

            xmlStreamWriter.writeEndElement(); //Cars
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.flush();

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }


    public List<Car> readXml(String fileName) {
        List<Car> cars = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(fileName)) {
            XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(fis);

            while (xmlStreamReader.hasNext()) {
                xmlStreamReader.next();
                if (xmlStreamReader.isStartElement() && "Car".equals(xmlStreamReader.getLocalName())) {
                    String brand = null;
                    String model = null;
                    int productionYear = 0;
                    int mileage = 0;
                    int rentalPrice = 0;

                    try {
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
                                }
                            } else if (xmlStreamReader.isEndElement() && "Car".equals(xmlStreamReader.getLocalName())) {
                                if (brand == null || model == null || productionYear <= 0 || mileage < 0 || rentalPrice <= 0) {
                                    throw new IllegalArgumentException("Nieprawidłowe dane samochodu. Nie można utworzyć obiektu Car.");
                                }
                                cars.add(new Car(brand, model, productionYear, mileage, rentalPrice));
                                break;
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Błąd podczas przetwarzania samochodu: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return cars;
    }


}
