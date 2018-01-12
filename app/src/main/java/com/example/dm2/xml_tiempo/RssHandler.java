package com.example.dm2.xml_tiempo;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RssHandler extends DefaultHandler{


    private Web webActual;
    private StringBuilder sbTexto;

    public Web getWebActual() {
        return webActual;
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);
        if (this.webActual != null)
            sbTexto.append(ch, start, length);
    }
    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {
        super.endElement(uri, localName, name);
        if (this.webActual != null) {
            if (localName.equals("nombre")) {
                webActual.setNombre(sbTexto.toString());
            } else if (localName.equals("maxima")) {
                webActual.setMax(sbTexto.toString());
            }
            else if (localName.equals("minima")) {
                webActual.setMin(sbTexto.toString());
            }

            sbTexto.setLength(0);
        }
    }
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();

        sbTexto = new StringBuilder();
    }
    @Override
    public void startElement(String uri, String localName,
                             String name, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, name, attributes);
        if (localName.equals("root")) {
            webActual = new Web();
        }
    }

}

