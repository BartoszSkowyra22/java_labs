package com.pollub.lab.model.lab3;

import lombok.Getter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

@Getter
public class FamilyCarImpl extends CarImpl {
    private final int seatingCapacity;

    public FamilyCarImpl(String brand, String model, int productionYear, int mileage, int rentalPrice, int seatingCapacity) {
        super(brand, model, productionYear, mileage, rentalPrice);
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public void writeXML(XMLStreamWriter writer) throws XMLStreamException {

        writer.writeStartElement("FamilyCar");
        writeBasicCarParameters(writer);
        writer.writeStartElement("SeatingCapacity");
        writer.writeCharacters(String.valueOf(this.getSeatingCapacity()));
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
                ", Liczba miejsc: " + this.getSeatingCapacity());
    }
}

