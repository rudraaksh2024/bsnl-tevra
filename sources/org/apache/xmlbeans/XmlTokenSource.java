package org.apache.xmlbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import javax.xml.stream.XMLStreamReader;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public interface XmlTokenSource {
    XmlDocumentProperties documentProperties();

    void dump();

    Node getDomNode();

    Object monitor();

    XmlCursor newCursor();

    Node newDomNode();

    Node newDomNode(XmlOptions xmlOptions);

    InputStream newInputStream();

    InputStream newInputStream(XmlOptions xmlOptions);

    Reader newReader();

    Reader newReader(XmlOptions xmlOptions);

    XMLStreamReader newXMLStreamReader();

    XMLStreamReader newXMLStreamReader(XmlOptions xmlOptions);

    void save(File file) throws IOException;

    void save(File file, XmlOptions xmlOptions) throws IOException;

    void save(OutputStream outputStream) throws IOException;

    void save(OutputStream outputStream, XmlOptions xmlOptions) throws IOException;

    void save(Writer writer) throws IOException;

    void save(Writer writer, XmlOptions xmlOptions) throws IOException;

    void save(ContentHandler contentHandler, LexicalHandler lexicalHandler) throws SAXException;

    void save(ContentHandler contentHandler, LexicalHandler lexicalHandler, XmlOptions xmlOptions) throws SAXException;

    String xmlText();

    String xmlText(XmlOptions xmlOptions);
}
