package org.apache.xmlbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.w3c.dom.Node;

public interface XmlBase64Binary extends XmlAnySimpleType {
    public static final SchemaType type = XmlBeans.getBuiltinTypeSystem().typeForHandle("_BI_base64Binary");

    byte[] getByteArrayValue();

    void setByteArrayValue(byte[] bArr);

    public static final class Factory {
        public static XmlBase64Binary newInstance() {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().newInstance(XmlBase64Binary.type, (XmlOptions) null);
        }

        public static XmlBase64Binary newInstance(XmlOptions xmlOptions) {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().newInstance(XmlBase64Binary.type, xmlOptions);
        }

        public static XmlBase64Binary newValue(Object obj) {
            return (XmlBase64Binary) XmlBase64Binary.type.newValue(obj);
        }

        public static XmlBase64Binary parse(String str) throws XmlException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(str, XmlBase64Binary.type, (XmlOptions) null);
        }

        public static XmlBase64Binary parse(String str, XmlOptions xmlOptions) throws XmlException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(str, XmlBase64Binary.type, xmlOptions);
        }

        public static XmlBase64Binary parse(File file) throws XmlException, IOException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(file, XmlBase64Binary.type, (XmlOptions) null);
        }

        public static XmlBase64Binary parse(File file, XmlOptions xmlOptions) throws XmlException, IOException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(file, XmlBase64Binary.type, xmlOptions);
        }

        public static XmlBase64Binary parse(URL url) throws XmlException, IOException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(url, XmlBase64Binary.type, (XmlOptions) null);
        }

        public static XmlBase64Binary parse(URL url, XmlOptions xmlOptions) throws XmlException, IOException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(url, XmlBase64Binary.type, xmlOptions);
        }

        public static XmlBase64Binary parse(InputStream inputStream) throws XmlException, IOException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(inputStream, XmlBase64Binary.type, (XmlOptions) null);
        }

        public static XmlBase64Binary parse(InputStream inputStream, XmlOptions xmlOptions) throws XmlException, IOException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(inputStream, XmlBase64Binary.type, xmlOptions);
        }

        public static XmlBase64Binary parse(Reader reader) throws XmlException, IOException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(reader, XmlBase64Binary.type, (XmlOptions) null);
        }

        public static XmlBase64Binary parse(Reader reader, XmlOptions xmlOptions) throws XmlException, IOException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(reader, XmlBase64Binary.type, xmlOptions);
        }

        public static XmlBase64Binary parse(Node node) throws XmlException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(node, XmlBase64Binary.type, (XmlOptions) null);
        }

        public static XmlBase64Binary parse(Node node, XmlOptions xmlOptions) throws XmlException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(node, XmlBase64Binary.type, xmlOptions);
        }

        public static XmlBase64Binary parse(XMLStreamReader xMLStreamReader) throws XmlException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(xMLStreamReader, XmlBase64Binary.type, (XmlOptions) null);
        }

        public static XmlBase64Binary parse(XMLStreamReader xMLStreamReader, XmlOptions xmlOptions) throws XmlException {
            return (XmlBase64Binary) XmlBeans.getContextTypeLoader().parse(xMLStreamReader, XmlBase64Binary.type, xmlOptions);
        }

        private Factory() {
        }
    }
}
