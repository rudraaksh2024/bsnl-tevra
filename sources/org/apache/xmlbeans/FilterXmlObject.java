package org.apache.xmlbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public abstract class FilterXmlObject implements XmlObject, SimpleValue, DelegateXmlObject {
    public SchemaType schemaType() {
        return underlyingXmlObject().schemaType();
    }

    public boolean validate() {
        return underlyingXmlObject().validate();
    }

    public boolean validate(XmlOptions xmlOptions) {
        return underlyingXmlObject().validate(xmlOptions);
    }

    public XmlObject[] selectPath(String str) {
        return underlyingXmlObject().selectPath(str);
    }

    public XmlObject[] selectPath(String str, XmlOptions xmlOptions) {
        return underlyingXmlObject().selectPath(str, xmlOptions);
    }

    public XmlObject[] execQuery(String str) {
        return underlyingXmlObject().execQuery(str);
    }

    public XmlObject[] execQuery(String str, XmlOptions xmlOptions) {
        return underlyingXmlObject().execQuery(str, xmlOptions);
    }

    public XmlObject changeType(SchemaType schemaType) {
        return underlyingXmlObject().changeType(schemaType);
    }

    public boolean isNil() {
        return underlyingXmlObject().isNil();
    }

    public void setNil() {
        underlyingXmlObject().setNil();
    }

    public boolean isImmutable() {
        return underlyingXmlObject().isImmutable();
    }

    public XmlObject set(XmlObject xmlObject) {
        return underlyingXmlObject().set(xmlObject);
    }

    public XmlObject copy() {
        return underlyingXmlObject().copy();
    }

    public XmlObject copy(XmlOptions xmlOptions) {
        return underlyingXmlObject().copy(xmlOptions);
    }

    public boolean valueEquals(XmlObject xmlObject) {
        return underlyingXmlObject().valueEquals(xmlObject);
    }

    public int valueHashCode() {
        return underlyingXmlObject().valueHashCode();
    }

    public int compareTo(Object obj) {
        return underlyingXmlObject().compareTo(obj);
    }

    public int compareValue(XmlObject xmlObject) {
        return underlyingXmlObject().compareValue(xmlObject);
    }

    public Object monitor() {
        return underlyingXmlObject().monitor();
    }

    public XmlDocumentProperties documentProperties() {
        return underlyingXmlObject().documentProperties();
    }

    public XmlCursor newCursor() {
        return underlyingXmlObject().newCursor();
    }

    public XMLStreamReader newXMLStreamReader() {
        return underlyingXmlObject().newXMLStreamReader();
    }

    public String xmlText() {
        return underlyingXmlObject().xmlText();
    }

    public InputStream newInputStream() {
        return underlyingXmlObject().newInputStream();
    }

    public Reader newReader() {
        return underlyingXmlObject().newReader();
    }

    public Node newDomNode() {
        return underlyingXmlObject().newDomNode();
    }

    public Node getDomNode() {
        return underlyingXmlObject().getDomNode();
    }

    public void save(ContentHandler contentHandler, LexicalHandler lexicalHandler) throws SAXException {
        underlyingXmlObject().save(contentHandler, lexicalHandler);
    }

    public void save(File file) throws IOException {
        underlyingXmlObject().save(file);
    }

    public void save(OutputStream outputStream) throws IOException {
        underlyingXmlObject().save(outputStream);
    }

    public void save(Writer writer) throws IOException {
        underlyingXmlObject().save(writer);
    }

    public XMLStreamReader newXMLStreamReader(XmlOptions xmlOptions) {
        return underlyingXmlObject().newXMLStreamReader(xmlOptions);
    }

    public String xmlText(XmlOptions xmlOptions) {
        return underlyingXmlObject().xmlText(xmlOptions);
    }

    public InputStream newInputStream(XmlOptions xmlOptions) {
        return underlyingXmlObject().newInputStream(xmlOptions);
    }

    public Reader newReader(XmlOptions xmlOptions) {
        return underlyingXmlObject().newReader(xmlOptions);
    }

    public Node newDomNode(XmlOptions xmlOptions) {
        return underlyingXmlObject().newDomNode(xmlOptions);
    }

    public void save(ContentHandler contentHandler, LexicalHandler lexicalHandler, XmlOptions xmlOptions) throws SAXException {
        underlyingXmlObject().save(contentHandler, lexicalHandler, xmlOptions);
    }

    public void save(File file, XmlOptions xmlOptions) throws IOException {
        underlyingXmlObject().save(file, xmlOptions);
    }

    public void save(OutputStream outputStream, XmlOptions xmlOptions) throws IOException {
        underlyingXmlObject().save(outputStream, xmlOptions);
    }

    public void save(Writer writer, XmlOptions xmlOptions) throws IOException {
        underlyingXmlObject().save(writer, xmlOptions);
    }

    public SchemaType instanceType() {
        return ((SimpleValue) underlyingXmlObject()).instanceType();
    }

    public String getStringValue() {
        return ((SimpleValue) underlyingXmlObject()).getStringValue();
    }

    public boolean getBooleanValue() {
        return ((SimpleValue) underlyingXmlObject()).getBooleanValue();
    }

    public byte getByteValue() {
        return ((SimpleValue) underlyingXmlObject()).getByteValue();
    }

    public short getShortValue() {
        return ((SimpleValue) underlyingXmlObject()).getShortValue();
    }

    public int getIntValue() {
        return ((SimpleValue) underlyingXmlObject()).getIntValue();
    }

    public long getLongValue() {
        return ((SimpleValue) underlyingXmlObject()).getLongValue();
    }

    public BigInteger getBigIntegerValue() {
        return ((SimpleValue) underlyingXmlObject()).getBigIntegerValue();
    }

    public BigDecimal getBigDecimalValue() {
        return ((SimpleValue) underlyingXmlObject()).getBigDecimalValue();
    }

    public float getFloatValue() {
        return ((SimpleValue) underlyingXmlObject()).getFloatValue();
    }

    public double getDoubleValue() {
        return ((SimpleValue) underlyingXmlObject()).getDoubleValue();
    }

    public byte[] getByteArrayValue() {
        return ((SimpleValue) underlyingXmlObject()).getByteArrayValue();
    }

    public StringEnumAbstractBase getEnumValue() {
        return ((SimpleValue) underlyingXmlObject()).getEnumValue();
    }

    public Calendar getCalendarValue() {
        return ((SimpleValue) underlyingXmlObject()).getCalendarValue();
    }

    public Date getDateValue() {
        return ((SimpleValue) underlyingXmlObject()).getDateValue();
    }

    public GDate getGDateValue() {
        return ((SimpleValue) underlyingXmlObject()).getGDateValue();
    }

    public GDuration getGDurationValue() {
        return ((SimpleValue) underlyingXmlObject()).getGDurationValue();
    }

    public QName getQNameValue() {
        return ((SimpleValue) underlyingXmlObject()).getQNameValue();
    }

    public List<?> getListValue() {
        return ((SimpleValue) underlyingXmlObject()).getListValue();
    }

    public List<? extends XmlAnySimpleType> xgetListValue() {
        return ((SimpleValue) underlyingXmlObject()).xgetListValue();
    }

    public Object getObjectValue() {
        return ((SimpleValue) underlyingXmlObject()).getObjectValue();
    }

    public void setStringValue(String str) {
        ((SimpleValue) underlyingXmlObject()).setStringValue(str);
    }

    public void setBooleanValue(boolean z) {
        ((SimpleValue) underlyingXmlObject()).setBooleanValue(z);
    }

    public void setByteValue(byte b) {
        ((SimpleValue) underlyingXmlObject()).setByteValue(b);
    }

    public void setShortValue(short s) {
        ((SimpleValue) underlyingXmlObject()).setShortValue(s);
    }

    public void setIntValue(int i) {
        ((SimpleValue) underlyingXmlObject()).setIntValue(i);
    }

    public void setLongValue(long j) {
        ((SimpleValue) underlyingXmlObject()).setLongValue(j);
    }

    public void setBigIntegerValue(BigInteger bigInteger) {
        ((SimpleValue) underlyingXmlObject()).setBigIntegerValue(bigInteger);
    }

    public void setBigDecimalValue(BigDecimal bigDecimal) {
        ((SimpleValue) underlyingXmlObject()).setBigDecimalValue(bigDecimal);
    }

    public void setFloatValue(float f) {
        ((SimpleValue) underlyingXmlObject()).setFloatValue(f);
    }

    public void setDoubleValue(double d) {
        ((SimpleValue) underlyingXmlObject()).setDoubleValue(d);
    }

    public void setByteArrayValue(byte[] bArr) {
        ((SimpleValue) underlyingXmlObject()).setByteArrayValue(bArr);
    }

    public void setEnumValue(StringEnumAbstractBase stringEnumAbstractBase) {
        ((SimpleValue) underlyingXmlObject()).setEnumValue(stringEnumAbstractBase);
    }

    public void setCalendarValue(Calendar calendar) {
        ((SimpleValue) underlyingXmlObject()).setCalendarValue(calendar);
    }

    public void setDateValue(Date date) {
        ((SimpleValue) underlyingXmlObject()).setDateValue(date);
    }

    public void setGDateValue(GDate gDate) {
        ((SimpleValue) underlyingXmlObject()).setGDateValue(gDate);
    }

    public void setGDurationValue(GDuration gDuration) {
        ((SimpleValue) underlyingXmlObject()).setGDurationValue(gDuration);
    }

    public void setQNameValue(QName qName) {
        ((SimpleValue) underlyingXmlObject()).setQNameValue(qName);
    }

    public void setListValue(List<?> list) {
        ((SimpleValue) underlyingXmlObject()).setListValue(list);
    }

    public void setObjectValue(Object obj) {
        ((SimpleValue) underlyingXmlObject()).setObjectValue(obj);
    }

    public XmlObject[] selectChildren(QName qName) {
        return underlyingXmlObject().selectChildren(qName);
    }

    public XmlObject[] selectChildren(String str, String str2) {
        return underlyingXmlObject().selectChildren(str, str2);
    }

    public XmlObject[] selectChildren(QNameSet qNameSet) {
        return underlyingXmlObject().selectChildren(qNameSet);
    }

    public XmlObject selectAttribute(QName qName) {
        return underlyingXmlObject().selectAttribute(qName);
    }

    public XmlObject selectAttribute(String str, String str2) {
        return underlyingXmlObject().selectAttribute(str, str2);
    }

    public XmlObject[] selectAttributes(QNameSet qNameSet) {
        return underlyingXmlObject().selectAttributes(qNameSet);
    }
}
