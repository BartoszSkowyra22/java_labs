package com.pollub.lab.model.lab3;

import lombok.Getter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

@Getter
public class SportCarImpl extends CarImpl {
    private final int topSpeed;

    public SportCarImpl(String brand, String model, int productionYear, int mileage, int rentalPrice, int topSpeed) {
        super(brand, model, productionYear, mileage, rentalPrice);
        this.topSpeed = topSpeed;
    }

    @Override
    public void writeXML(XMLStreamWriter writer) throws XMLStreamException {

        writer.writeStartElement("SportCar");
        writeBasicCarParameters(writer);
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

