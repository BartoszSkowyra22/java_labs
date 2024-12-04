package com.pollub.lab.model.lab3;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.List;

public class FamilyCar extends Car {
    private int seatingCapacity;

    public FamilyCar(String brand, String model, int productionYear, int mileage, int rentalPrice, int seatingCapacity) {
        super(brand, model, productionYear, mileage, rentalPrice);
        this.seatingCapacity = seatingCapacity;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    @Override
    public void writeXML(XMLStreamWriter writer) throws XMLStreamException {

        writer.writeStartElement("FamilyCar");
        writer.writeStartElement("Brand");
        writer.writeCharacters(this.getBrand());
        writer.writeEndElement();

        writer.writeStartElement("Model");
        writer.writeCharacters(this.getModel());
        writer.writeEndElement();

        writer.writeStartElement("ProductionYear");
        writer.writeCharacters(String.valueOf(this.getProductionYear()));
        writer.writeEndElement();

        writer.writeStartElement("Mileage");
        writer.writeCharacters(String.valueOf(this.getMileage()));
        writer.writeEndElement();

        writer.writeStartElement("RentalPrice");
        writer.writeCharacters(String.valueOf(this.getRentalPrice()));
        writer.writeEndElement();
        writer.writeStartElement("SeatingCapacity");
        writer.writeCharacters(String.valueOf(this.getSeatingCapacity()));
        writer.writeEndElement();
        writer.writeEndElement();

    }
    private void showParametersAfterReadXML(List<Car> savedCars) {
        savedCars.forEach(car -> {
            System.out.print("Marka: " + car.getBrand() + ", Model: " + car.getModel() +
                    ", Rok produkcji: " + car.getProductionYear() +
                    ", Przebieg: " + car.getMileage() +
                    ", Cena wynajmu: " + car.getRentalPrice());

            if (car instanceof FamilyCar) {
                System.out.println(", Liczba miejsc: " + ((FamilyCar) car).getSeatingCapacity());
            } else if (car instanceof SportCar) {
                System.out.println(", Prędkość maksymalna: " + ((SportCar) car).getTopSpeed() + " km/h");
            } else if (car instanceof LuxuryCar) {
                System.out.println(", Fotele masujące: " + (((LuxuryCar) car).hasMassageSeats() ? "Tak" : "Nie"));
            }
        });
    }

    @Override
    public String toString() {
        return ("Marka: " + this.getBrand() +
                ", Model: " + this.getModel() +
                ", Rok produkcji: " + this.getProductionYear() +
                ", Przebieg: " + this.getMileage() +
                ", Cena wynajmu: " + this.getRentalPrice() +
                ", Liczba miejsc: " + this.getSeatingCapacity());
    }
}

