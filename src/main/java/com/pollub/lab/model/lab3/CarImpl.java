package com.pollub.lab.model.lab3;

import lombok.Getter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

@Getter
public class CarImpl implements Car {
    private final String brand;
    private final String model;
    private final int productionYear;
    private final int mileage;
    private final int rentalPrice;

    public CarImpl(String brand, String model, int productionYear, int mileage, int rentalPrice) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.mileage = mileage;
        this.rentalPrice = rentalPrice;
    }

    public void writeXML(XMLStreamWriter writer) throws XMLStreamException {

        writer.writeStartElement("SimpleCar");
        writeBasicCarParameters(writer);
        writer.writeEndElement();

    }

    public void writeBasicCarParameters(XMLStreamWriter writer) throws XMLStreamException {
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
    }
}
