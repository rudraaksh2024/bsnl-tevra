package org.apache.xmlbeans.impl.validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidatorListener;
import org.apache.xmlbeans.impl.common.XmlWhitespace;

public class ValidatingXMLStreamReader extends StreamReaderDelegate implements XMLStreamReader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String URI_XSI = "http://www.w3.org/2001/XMLSchema-instance";
    private static final QName XSI_NIL = new QName(URI_XSI, "nil");
    private static final QName XSI_NSL = new QName(URI_XSI, "noNamespaceSchemaLocation");
    private static final QName XSI_SL = new QName(URI_XSI, "schemaLocation");
    private static final QName XSI_TYPE = new QName(URI_XSI, "type");
    private final int STATE_ATTBUFFERING = 2;
    private final int STATE_ERROR = 3;
    private final int STATE_FIRSTEVENT = 0;
    private final int STATE_VALIDATING = 1;
    private final AttributeEventImpl _attEvent = new AttributeEventImpl();
    private List _attNamesList;
    private List _attValuesList;
    private SchemaType _contentType;
    private int _depth;
    private final ElementEventImpl _elemEvent = new ElementEventImpl();
    private Collection _errorListener;
    private XmlOptions _options;
    private PackTextXmlStreamReader _packTextXmlStreamReader = new PackTextXmlStreamReader();
    private final SimpleEventImpl _simpleEvent = new SimpleEventImpl();
    private int _state;
    private SchemaTypeLoader _stl;
    protected Validator _validator;
    private SchemaType _xsiType;

    public void init(XMLStreamReader xMLStreamReader, boolean z, SchemaType schemaType, SchemaTypeLoader schemaTypeLoader, XmlOptions xmlOptions, Collection collection) {
        this._packTextXmlStreamReader.init(xMLStreamReader);
        setParent(this._packTextXmlStreamReader);
        this._contentType = schemaType;
        this._stl = schemaTypeLoader;
        this._options = xmlOptions;
        this._errorListener = collection;
        this._elemEvent.setXMLStreamReader(this._packTextXmlStreamReader);
        this._attEvent.setXMLStreamReader(this._packTextXmlStreamReader);
        this._simpleEvent.setXMLStreamReader(this._packTextXmlStreamReader);
        this._validator = null;
        this._state = 0;
        List list = this._attNamesList;
        if (list != null) {
            list.clear();
            this._attValuesList.clear();
        }
        this._xsiType = null;
        this._depth = 0;
        if (z) {
            validate_event(getEventType());
        }
    }

    private static class PackTextXmlStreamReader extends StreamReaderDelegate implements XMLStreamReader {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private StringBuilder _buffer;
        private boolean _hasBufferedText;
        private int _textEventType;

        public int getTextStart() {
            return 0;
        }

        static {
            Class<ValidatingXMLStreamReader> cls = ValidatingXMLStreamReader.class;
        }

        private PackTextXmlStreamReader() {
            this._buffer = new StringBuilder();
        }

        /* access modifiers changed from: package-private */
        public void init(XMLStreamReader xMLStreamReader) {
            setParent(xMLStreamReader);
            this._hasBufferedText = false;
            StringBuilder sb = this._buffer;
            sb.delete(0, sb.length());
        }

        public int next() throws XMLStreamException {
            if (this._hasBufferedText) {
                clearBuffer();
                return ValidatingXMLStreamReader.super.getEventType();
            }
            int next = ValidatingXMLStreamReader.super.next();
            if (next == 4 || next == 12 || next == 6) {
                this._textEventType = next;
                bufferText();
            }
            return next;
        }

        private void clearBuffer() {
            StringBuilder sb = this._buffer;
            sb.delete(0, sb.length());
            this._hasBufferedText = false;
        }

        private void bufferText() throws XMLStreamException {
            if (ValidatingXMLStreamReader.super.hasText()) {
                this._buffer.append(ValidatingXMLStreamReader.super.getText());
            }
            this._hasBufferedText = true;
            while (hasNext()) {
                int next = ValidatingXMLStreamReader.super.next();
                if (next != 4) {
                    if (next == 5) {
                        continue;
                    } else if (!(next == 6 || next == 12)) {
                        return;
                    }
                }
                if (ValidatingXMLStreamReader.super.hasText()) {
                    this._buffer.append(ValidatingXMLStreamReader.super.getText());
                }
            }
        }

        public String getText() {
            return this._buffer.toString();
        }

        public int getTextLength() {
            return this._buffer.length();
        }

        public char[] getTextCharacters() {
            return this._buffer.toString().toCharArray();
        }

        public int getTextCharacters(int i, char[] cArr, int i2, int i3) {
            this._buffer.getChars(i, i + i3, cArr, i2);
            return i3;
        }

        public boolean isWhiteSpace() {
            return XmlWhitespace.isAllSpace((CharSequence) this._buffer);
        }

        public boolean hasText() {
            if (this._hasBufferedText) {
                return true;
            }
            return ValidatingXMLStreamReader.super.hasText();
        }

        public int getEventType() {
            if (this._hasBufferedText) {
                return this._textEventType;
            }
            return ValidatingXMLStreamReader.super.getEventType();
        }
    }

    private static class ElementEventImpl implements ValidatorListener.Event {
        private static final int BUF_LENGTH = 1024;
        private char[] _buf;
        private int _length;
        private boolean _supportForGetTextCharacters;
        private XMLStreamReader _xmlStream;

        public XmlCursor getLocationAsCursor() {
            return null;
        }

        private ElementEventImpl() {
            this._buf = new char[1024];
            this._supportForGetTextCharacters = true;
        }

        /* access modifiers changed from: private */
        public void setXMLStreamReader(XMLStreamReader xMLStreamReader) {
            this._xmlStream = xMLStreamReader;
        }

        public Location getLocation() {
            return this._xmlStream.getLocation();
        }

        public String getXsiType() {
            return this._xmlStream.getAttributeValue(ValidatingXMLStreamReader.URI_XSI, "type");
        }

        public String getXsiNil() {
            return this._xmlStream.getAttributeValue(ValidatingXMLStreamReader.URI_XSI, "nil");
        }

        public String getXsiLoc() {
            return this._xmlStream.getAttributeValue(ValidatingXMLStreamReader.URI_XSI, "schemaLocation");
        }

        public String getXsiNoLoc() {
            return this._xmlStream.getAttributeValue(ValidatingXMLStreamReader.URI_XSI, "noNamespaceSchemaLocation");
        }

        public QName getName() {
            if (this._xmlStream.hasName()) {
                return new QName(this._xmlStream.getNamespaceURI(), this._xmlStream.getLocalName());
            }
            return null;
        }

        public String getText() {
            this._length = 0;
            addTextToBuffer();
            return new String(this._buf, 0, this._length);
        }

        public String getText(int i) {
            return XmlWhitespace.collapse(this._xmlStream.getText(), i);
        }

        public boolean textIsWhitespace() {
            return this._xmlStream.isWhiteSpace();
        }

        public String getNamespaceForPrefix(String str) {
            return this._xmlStream.getNamespaceURI(str);
        }

        private void addTextToBuffer() {
            int textLength = this._xmlStream.getTextLength();
            ensureBufferLength(textLength);
            if (this._supportForGetTextCharacters) {
                try {
                    this._length = this._xmlStream.getTextCharacters(0, this._buf, this._length, textLength);
                } catch (Exception unused) {
                    this._supportForGetTextCharacters = false;
                }
            }
            if (!this._supportForGetTextCharacters) {
                System.arraycopy(this._xmlStream.getTextCharacters(), this._xmlStream.getTextStart(), this._buf, this._length, textLength);
                this._length += textLength;
            }
        }

        private void ensureBufferLength(int i) {
            int i2 = this._length;
            int i3 = i2 + i;
            char[] cArr = this._buf;
            if (i3 > cArr.length) {
                char[] cArr2 = new char[(i + i2)];
                if (i2 > 0) {
                    System.arraycopy(cArr, 0, cArr2, 0, i2);
                }
                this._buf = cArr2;
            }
        }
    }

    private static final class AttributeEventImpl implements ValidatorListener.Event {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private int _attIndex;
        private XMLStreamReader _xmlStream;

        public XmlCursor getLocationAsCursor() {
            return null;
        }

        static {
            Class<ValidatingXMLStreamReader> cls = ValidatingXMLStreamReader.class;
        }

        private AttributeEventImpl() {
        }

        /* access modifiers changed from: private */
        public void setXMLStreamReader(XMLStreamReader xMLStreamReader) {
            this._xmlStream = xMLStreamReader;
        }

        public Location getLocation() {
            return this._xmlStream.getLocation();
        }

        public String getXsiType() {
            throw new IllegalStateException();
        }

        public String getXsiNil() {
            throw new IllegalStateException();
        }

        public String getXsiLoc() {
            throw new IllegalStateException();
        }

        public String getXsiNoLoc() {
            throw new IllegalStateException();
        }

        public QName getName() {
            String attributeNamespace = this._xmlStream.getAttributeNamespace(this._attIndex);
            if (attributeNamespace == null) {
                attributeNamespace = "";
            }
            return new QName(attributeNamespace, this._xmlStream.getAttributeLocalName(this._attIndex));
        }

        public String getText() {
            return this._xmlStream.getAttributeValue(this._attIndex);
        }

        public String getText(int i) {
            return XmlWhitespace.collapse(this._xmlStream.getAttributeValue(this._attIndex), i);
        }

        public boolean textIsWhitespace() {
            throw new IllegalStateException();
        }

        public String getNamespaceForPrefix(String str) {
            return this._xmlStream.getNamespaceURI(str);
        }

        /* access modifiers changed from: private */
        public void setAttributeIndex(int i) {
            this._attIndex = i;
        }
    }

    private static final class SimpleEventImpl implements ValidatorListener.Event {
        /* access modifiers changed from: private */
        public QName _qname;
        /* access modifiers changed from: private */
        public String _text;
        private XMLStreamReader _xmlStream;

        public XmlCursor getLocationAsCursor() {
            return null;
        }

        public String getXsiLoc() {
            return null;
        }

        public String getXsiNil() {
            return null;
        }

        public String getXsiNoLoc() {
            return null;
        }

        public String getXsiType() {
            return null;
        }

        public boolean textIsWhitespace() {
            return false;
        }

        private SimpleEventImpl() {
        }

        /* access modifiers changed from: private */
        public void setXMLStreamReader(XMLStreamReader xMLStreamReader) {
            this._xmlStream = xMLStreamReader;
        }

        public Location getLocation() {
            return this._xmlStream.getLocation();
        }

        public QName getName() {
            return this._qname;
        }

        public String getText() {
            return this._text;
        }

        public String getText(int i) {
            return XmlWhitespace.collapse(this._text, i);
        }

        public String getNamespaceForPrefix(String str) {
            return this._xmlStream.getNamespaceURI(str);
        }
    }

    public Object getProperty(String str) throws IllegalArgumentException {
        return ValidatingXMLStreamReader.super.getProperty(str);
    }

    public int next() throws XMLStreamException {
        int next = ValidatingXMLStreamReader.super.next();
        validate_event(next);
        return next;
    }

    private void validate_event(int i) {
        int i2 = this._state;
        if (i2 != 3) {
            int i3 = this._depth;
            if (i3 >= 0) {
                switch (i) {
                    case 1:
                        this._depth = i3 + 1;
                        if (i2 == 2) {
                            pushBufferedAttributes();
                        }
                        if (this._validator == null) {
                            QName qName = new QName(getNamespaceURI(), getLocalName());
                            if (this._contentType == null) {
                                this._contentType = typeForGlobalElement(qName);
                            }
                            if (this._state != 3) {
                                initValidator(this._contentType);
                                this._validator.nextEvent(1, this._elemEvent);
                            } else {
                                return;
                            }
                        }
                        this._validator.nextEvent(1, this._elemEvent);
                        validate_attributes(getAttributeCount());
                        return;
                    case 2:
                    case 8:
                        this._depth = i3 - 1;
                        if (i2 == 2) {
                            pushBufferedAttributes();
                        }
                        this._validator.nextEvent(2, this._elemEvent);
                        return;
                    case 3:
                    case 5:
                    case 6:
                    case 9:
                    case 11:
                    case 13:
                    case 14:
                    case 15:
                        return;
                    case 4:
                    case 12:
                        if (i2 == 2) {
                            pushBufferedAttributes();
                        }
                        if (this._validator == null) {
                            SchemaType schemaType = this._contentType;
                            if (schemaType != null) {
                                initValidator(schemaType);
                                this._validator.nextEvent(1, this._simpleEvent);
                            } else if (!isWhiteSpace()) {
                                addError("No content type provided for validation of a content model.");
                                this._state = 3;
                                return;
                            } else {
                                return;
                            }
                        }
                        this._validator.nextEvent(3, this._elemEvent);
                        return;
                    case 7:
                        this._depth = i3 + 1;
                        return;
                    case 10:
                        if (getAttributeCount() != 0) {
                            int i4 = this._state;
                            if (i4 == 0 || i4 == 2) {
                                for (int i5 = 0; i5 < getAttributeCount(); i5++) {
                                    QName qName2 = new QName(getAttributeNamespace(i5), getAttributeLocalName(i5));
                                    if (qName2.equals(XSI_TYPE)) {
                                        String attributeValue = getAttributeValue(i5);
                                        this._xsiType = this._stl.findType(new QName(ValidatingXMLStreamReader.super.getNamespaceURI(QNameHelper.getPrefixPart(attributeValue)), QNameHelper.getLocalPart(attributeValue)));
                                    }
                                    if (this._attNamesList == null) {
                                        this._attNamesList = new ArrayList();
                                        this._attValuesList = new ArrayList();
                                    }
                                    if (!isSpecialAttribute(qName2)) {
                                        this._attNamesList.add(qName2);
                                        this._attValuesList.add(getAttributeValue(i5));
                                    }
                                }
                                this._state = 2;
                                return;
                            }
                            throw new IllegalStateException("ATT event must be only at the beggining of the stream.");
                        }
                        return;
                    default:
                        throw new IllegalStateException("Unknown event type.");
                }
            } else {
                throw new IllegalArgumentException("ValidatingXMLStreamReader cannot go further than the subtree is was initialized on.");
            }
        }
    }

    private void pushBufferedAttributes() {
        SchemaType schemaType = this._xsiType;
        if (schemaType != null) {
            SchemaType schemaType2 = this._contentType;
            if (schemaType2 != null) {
                if (schemaType2.isAssignableFrom(schemaType)) {
                    schemaType = this._xsiType;
                } else {
                    addError("Specified type '" + this._contentType + "' not compatible with found xsi:type '" + this._xsiType + "'.");
                    this._state = 3;
                    return;
                }
            }
        } else {
            schemaType = this._contentType;
            if (schemaType == null) {
                List list = this._attNamesList;
                if (list != null) {
                    schemaType = this._stl.findAttributeType((QName) list.get(0));
                    if (schemaType == null) {
                        addError("A schema global attribute with name '" + this._attNamesList.get(0) + "' could not be found in the current schema type loader.");
                        this._state = 3;
                        return;
                    }
                } else {
                    addError("No content type provided for validation of a content model.");
                    this._state = 3;
                    return;
                }
            }
        }
        initValidator(schemaType);
        this._validator.nextEvent(1, this._simpleEvent);
        validate_attributes(this._attNamesList.size());
        this._attNamesList = null;
        this._attValuesList = null;
        this._state = 1;
    }

    private boolean isSpecialAttribute(QName qName) {
        if (!qName.getNamespaceURI().equals(URI_XSI)) {
            return false;
        }
        if (qName.getLocalPart().equals(XSI_TYPE.getLocalPart()) || qName.getLocalPart().equals(XSI_NIL.getLocalPart()) || qName.getLocalPart().equals(XSI_SL.getLocalPart()) || qName.getLocalPart().equals(XSI_NSL.getLocalPart())) {
            return true;
        }
        return false;
    }

    private void initValidator(SchemaType schemaType) {
        this._validator = new Validator(schemaType, (SchemaField) null, this._stl, this._options, this._errorListener);
    }

    private SchemaType typeForGlobalElement(QName qName) {
        SchemaType findDocumentType = this._stl.findDocumentType(qName);
        if (findDocumentType == null) {
            addError("Schema document type not found for element '" + qName + "'.");
            this._state = 3;
        }
        return findDocumentType;
    }

    private void addError(String str) {
        Location location = getLocation();
        if (location != null) {
            String publicId = location.getPublicId();
            if (publicId == null) {
                publicId = location.getSystemId();
            }
            this._errorListener.add(XmlError.forLocation(str, publicId, location));
            return;
        }
        this._errorListener.add(XmlError.forMessage(str));
    }

    /* access modifiers changed from: protected */
    public void validate_attributes(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            validate_attribute(i2);
        }
        XmlOptions xmlOptions = this._options;
        if (xmlOptions == null || !xmlOptions.isAttributeValidationCompatMode()) {
            this._validator.nextEvent(5, this._simpleEvent);
        }
    }

    /* access modifiers changed from: protected */
    public void validate_attribute(int i) {
        ValidatorListener.Event event;
        List list = this._attNamesList;
        if (list == null) {
            this._attEvent.setAttributeIndex(i);
            if (!isSpecialAttribute(this._attEvent.getName())) {
                event = this._attEvent;
            } else {
                return;
            }
        } else {
            QName unused = this._simpleEvent._qname = (QName) list.get(i);
            String unused2 = this._simpleEvent._text = (String) this._attValuesList.get(i);
            event = this._simpleEvent;
        }
        this._validator.nextEvent(4, event);
    }

    public boolean isValid() {
        Validator validator;
        if (this._state == 3 || (validator = this._validator) == null) {
            return false;
        }
        return validator.isValid();
    }
}
