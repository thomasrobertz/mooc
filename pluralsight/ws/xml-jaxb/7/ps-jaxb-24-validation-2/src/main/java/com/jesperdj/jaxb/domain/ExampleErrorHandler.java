package com.jesperdj.jaxb.domain;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class ExampleErrorHandler implements ErrorHandler {

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        System.out.println("ExampleErrorHandler WARNING: " + exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        System.out.println("ExampleErrorHandler ERROR: " + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        System.out.println("ExampleErrorHandler FATAL ERROR: " + exception.getMessage());
    }
}
