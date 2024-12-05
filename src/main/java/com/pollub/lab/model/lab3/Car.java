package com.pollub.lab.model.lab3;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public interface Car {

    void writeXML(XMLStreamWriter writer) throws XMLStreamException;
    void writeBasicCarParameters(XMLStreamWriter writer) throws XMLStreamException;

}
