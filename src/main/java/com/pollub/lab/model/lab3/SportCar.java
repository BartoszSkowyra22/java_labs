package com.pollub.lab.model.lab3;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class SportCar extends Car {
    private int topSpeed;

    public SportCar(String brand, String model, int productionYear, int mileage, int rentalPrice, int topSpeed) {
        super(brand, model, productionYear, mileage, rentalPrice);
        this.topSpeed = topSpeed;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    @Override
    public void writeXML(XMLStreamWriter writer) throws XMLStreamException {

        writer.writeStartElement("SportCar");
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
        writer.writeStartElement("TopSpeed");
        writer.writeCharacters(String.valueOf(this.getTopSpeed()));
        writer.writeEndElement();
        writer.writeEndElement();

    }

    @Override
    public String toString() {
        return ("Marka: " + this.getBrand() +
                ", Model: " + this.getModel() +
                ", Rok produkcji: " + this.getProductionYear() +
                ", Przebieg: " + this.getMileage() +
                ", Cena wynajmu: " + this.getRentalPrice() +
                ", Maksymalna predkosc: " + this.getTopSpeed());
    }
}

