package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.w3c.dom.Node;

public class AbstractDocumentFactory<T> extends ElementFactory<T> {
    public AbstractDocumentFactory(SchemaTypeSystem schemaTypeSystem, String str) {
        super(schemaTypeSystem, str);
    }

    public T parse(String str) throws XmlException {
        return getTypeLoader().parse(str, getType(), (XmlOptions) null);
    }

    public T parse(String str, XmlOptions xmlOptions) throws XmlException {
        return getTypeLoader().parse(str, getType(), xmlOptions);
    }

    public T parse(File file) throws XmlException, IOException {
        return getTypeLoader().parse(file, getType(), (XmlOptions) null);
    }

    public T parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
        return getTypeLoader().parse(file, getType(), xmlOptions);
    }

    public T parse(URL url) throws XmlException, IOException {
        return getTypeLoader().parse(url, getType(), (XmlOptions) null);
    }

    public T parse(URL url, XmlOptions xmlOptions) throws XmlException, IOException {
        return getTypeLoader().parse(url, getType(), xmlOptions);
    }

    public T parse(InputStream inputStream) throws XmlException, IOException {
        return getTypeLoader().parse(inputStream, getType(), (XmlOptions) null);
    }

    public T parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
        return getTypeLoader().parse(inputStream, getType(), xmlOptions);
    }

    public T parse(Reader reader) throws XmlException, IOException {
        return getTypeLoader().parse(reader, getType(), (XmlOptions) null);
    }

    public T parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
        return getTypeLoader().parse(reader, getType(), xmlOptions);
    }

    public T parse(XMLStreamReader xMLStreamReader) throws XmlException {
        return getTypeLoader().parse(xMLStreamReader, getType(), (XmlOptions) null);
    }

    public T parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
        return getTypeLoader().parse(xMLStreamReader, getType(), xmlOptions);
    }

    public T parse(Node node) throws XmlException {
        return getTypeLoader().parse(node, getType(), (XmlOptions) null);
    }

    public T parse(Node node, XmlOptions xmlOptions) throws XmlException {
        return getTypeLoader().parse(node, getType(), xmlOptions);
    }
}
