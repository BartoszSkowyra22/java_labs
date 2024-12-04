package com.pollub.lab.model.lab3;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class LuxuryCar extends Car {
    private boolean hasMassageSeats;

    public LuxuryCar(String brand, String model, int productionYear, int mileage, int rentalPrice, boolean hasMassageSeats) {
        super(brand, model, productionYear, mileage, rentalPrice);
        this.hasMassageSeats = hasMassageSeats;
    }

    public boolean hasMassageSeats() {
        return hasMassageSeats;
    }

    @Override
    public void writeXML(XMLStreamWriter writer) throws XMLStreamException {

        writer.writeStartElement("LuxuryCar");
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
        writer.writeStartElement("HasMassageSeats");
        writer.writeCharacters(String.valueOf(this.hasMassageSeats()));
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
                ", Fotele masujące: " + (this.hasMassageSeats() ? "Tak" : "Nie"));
    }
}
