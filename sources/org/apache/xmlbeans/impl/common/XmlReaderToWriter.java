package org.apache.xmlbeans.impl.common;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public final class XmlReaderToWriter {
    private XmlReaderToWriter() {
    }

    public static void writeAll(XMLStreamReader xMLStreamReader, XMLStreamWriter xMLStreamWriter) throws XMLStreamException {
        while (xMLStreamReader.hasNext()) {
            write(xMLStreamReader, xMLStreamWriter);
            xMLStreamReader.next();
        }
        write(xMLStreamReader, xMLStreamWriter);
        xMLStreamWriter.flush();
    }

    public static void write(XMLStreamReader xMLStreamReader, XMLStreamWriter xMLStreamWriter) throws XMLStreamException {
        switch (xMLStreamReader.getEventType()) {
            case 1:
                String localName = xMLStreamReader.getLocalName();
                String namespaceURI = xMLStreamReader.getNamespaceURI();
                if (namespaceURI == null || namespaceURI.length() <= 0) {
                    xMLStreamWriter.writeStartElement(localName);
                } else {
                    String prefix = xMLStreamReader.getPrefix();
                    if (prefix != null) {
                        xMLStreamWriter.writeStartElement(prefix, localName, namespaceURI);
                    } else {
                        xMLStreamWriter.writeStartElement(namespaceURI, localName);
                    }
                }
                int namespaceCount = xMLStreamReader.getNamespaceCount();
                for (int i = 0; i < namespaceCount; i++) {
                    xMLStreamWriter.writeNamespace(xMLStreamReader.getNamespacePrefix(i), xMLStreamReader.getNamespaceURI(i));
                }
                int attributeCount = xMLStreamReader.getAttributeCount();
                for (int i2 = 0; i2 < attributeCount; i2++) {
                    String attributeNamespace = xMLStreamReader.getAttributeNamespace(i2);
                    if (attributeNamespace != null) {
                        xMLStreamWriter.writeAttribute(attributeNamespace, xMLStreamReader.getAttributeLocalName(i2), xMLStreamReader.getAttributeValue(i2));
                    } else {
                        xMLStreamWriter.writeAttribute(xMLStreamReader.getAttributeLocalName(i2), xMLStreamReader.getAttributeValue(i2));
                    }
                }
                return;
            case 2:
                xMLStreamWriter.writeEndElement();
                return;
            case 3:
                xMLStreamWriter.writeProcessingInstruction(xMLStreamReader.getPITarget(), xMLStreamReader.getPIData());
                return;
            case 4:
            case 6:
                xMLStreamWriter.writeCharacters(xMLStreamReader.getTextCharacters(), xMLStreamReader.getTextStart(), xMLStreamReader.getTextLength());
                return;
            case 5:
                xMLStreamWriter.writeComment(xMLStreamReader.getText());
                return;
            case 7:
                String characterEncodingScheme = xMLStreamReader.getCharacterEncodingScheme();
                String version = xMLStreamReader.getVersion();
                if (characterEncodingScheme != null && version != null) {
                    xMLStreamWriter.writeStartDocument(characterEncodingScheme, version);
                    return;
                } else if (version != null) {
                    xMLStreamWriter.writeStartDocument(xMLStreamReader.getVersion());
                    return;
                } else {
                    return;
                }
            case 8:
                xMLStreamWriter.writeEndDocument();
                return;
            case 9:
                xMLStreamWriter.writeEntityRef(xMLStreamReader.getLocalName());
                return;
            case 11:
                xMLStreamWriter.writeDTD(xMLStreamReader.getText());
                return;
            case 12:
                xMLStreamWriter.writeCData(xMLStreamReader.getText());
                return;
            default:
                return;
        }
    }
}
