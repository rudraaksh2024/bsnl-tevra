package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlSaxHandler;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Node;

public class XmlObjectFactory<T> extends DocumentFactory<T> {
    private final boolean isAnyType;

    public XmlObjectFactory(String str) {
        this(XmlBeans.getBuiltinTypeSystem(), str);
    }

    public XmlObjectFactory(SchemaTypeSystem schemaTypeSystem, String str) {
        super(schemaTypeSystem, str);
        this.isAnyType = "_BI_anyType".equals(str);
    }

    public T newInstance() {
        return XmlBeans.getContextTypeLoader().newInstance(getInnerType(), (XmlOptions) null);
    }

    public T newInstance(XmlOptions xmlOptions) {
        return XmlBeans.getContextTypeLoader().newInstance(getInnerType(), xmlOptions);
    }

    public T newValue(Object obj) {
        return getType().newValue(obj);
    }

    public T parse(String str) throws XmlException {
        return XmlBeans.getContextTypeLoader().parse(str, getInnerType(), (XmlOptions) null);
    }

    public T parse(String str, XmlOptions xmlOptions) throws XmlException {
        return XmlBeans.getContextTypeLoader().parse(str, getInnerType(), xmlOptions);
    }

    public T parse(File file) throws XmlException, IOException {
        return XmlBeans.getContextTypeLoader().parse(file, getInnerType(), (XmlOptions) null);
    }

    public T parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
        return XmlBeans.getContextTypeLoader().parse(file, getInnerType(), xmlOptions);
    }

    public T parse(URL url) throws XmlException, IOException {
        return XmlBeans.getContextTypeLoader().parse(url, getInnerType(), (XmlOptions) null);
    }

    public T parse(URL url, XmlOptions xmlOptions) throws XmlException, IOException {
        return XmlBeans.getContextTypeLoader().parse(url, getInnerType(), xmlOptions);
    }

    public T parse(InputStream inputStream) throws XmlException, IOException {
        return XmlBeans.getContextTypeLoader().parse(inputStream, getInnerType(), (XmlOptions) null);
    }

    public T parse(XMLStreamReader xMLStreamReader) throws XmlException {
        return XmlBeans.getContextTypeLoader().parse(xMLStreamReader, getInnerType(), (XmlOptions) null);
    }

    public T parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
        return XmlBeans.getContextTypeLoader().parse(inputStream, getInnerType(), xmlOptions);
    }

    public T parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
        return XmlBeans.getContextTypeLoader().parse(xMLStreamReader, getInnerType(), xmlOptions);
    }

    public T parse(Reader reader) throws XmlException, IOException {
        return XmlBeans.getContextTypeLoader().parse(reader, getInnerType(), (XmlOptions) null);
    }

    public T parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
        return XmlBeans.getContextTypeLoader().parse(reader, getInnerType(), xmlOptions);
    }

    public T parse(Node node) throws XmlException {
        return XmlBeans.getContextTypeLoader().parse(node, getInnerType(), (XmlOptions) null);
    }

    public T parse(Node node, XmlOptions xmlOptions) throws XmlException {
        return XmlBeans.getContextTypeLoader().parse(node, getInnerType(), xmlOptions);
    }

    public XmlSaxHandler newXmlSaxHandler() {
        return XmlBeans.getContextTypeLoader().newXmlSaxHandler(getInnerType(), (XmlOptions) null);
    }

    public XmlSaxHandler newXmlSaxHandler(XmlOptions xmlOptions) {
        return XmlBeans.getContextTypeLoader().newXmlSaxHandler(getInnerType(), xmlOptions);
    }

    public DOMImplementation newDomImplementation() {
        return XmlBeans.getContextTypeLoader().newDomImplementation((XmlOptions) null);
    }

    public DOMImplementation newDomImplementation(XmlOptions xmlOptions) {
        return XmlBeans.getContextTypeLoader().newDomImplementation(xmlOptions);
    }

    private SchemaType getInnerType() {
        if (this.isAnyType) {
            return null;
        }
        return getType();
    }
}
