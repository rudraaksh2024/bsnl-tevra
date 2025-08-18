package org.apache.xmlbeans.impl.richParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDateBuilder;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.XmlCalendar;
import org.apache.xmlbeans.impl.common.InvalidLexicalValueException;
import org.apache.xmlbeans.impl.common.XMLChar;
import org.apache.xmlbeans.impl.common.XmlWhitespace;
import org.apache.xmlbeans.impl.util.HexBin;
import org.apache.xmlbeans.impl.util.XsTypeConverter;

public class XMLStreamReaderExtImpl implements XMLStreamReaderExt {
    private final CharSeqTrimWS _charSeq;
    /* access modifiers changed from: private */
    public String _defaultValue;
    private final XMLStreamReader _xmlStream;

    public XMLStreamReaderExtImpl(XMLStreamReader xMLStreamReader) {
        if (xMLStreamReader != null) {
            this._xmlStream = xMLStreamReader;
            this._charSeq = new CharSeqTrimWS(this);
            return;
        }
        throw new IllegalArgumentException();
    }

    public XMLStreamReader getUnderlyingXmlStream() {
        return this._xmlStream;
    }

    public String getStringValue() throws XMLStreamException {
        this._charSeq.reload(1);
        return this._charSeq.toString();
    }

    public String getStringValue(int i) throws XMLStreamException {
        this._charSeq.reload(1);
        return XmlWhitespace.collapse(this._charSeq.toString(), i);
    }

    public boolean getBooleanValue() throws XMLStreamException, InvalidLexicalValueException {
        this._charSeq.reload(2);
        try {
            return XsTypeConverter.lexBoolean(this._charSeq);
        } catch (InvalidLexicalValueException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public byte getByteValue() throws XMLStreamException, InvalidLexicalValueException {
        this._charSeq.reload(2);
        try {
            return XsTypeConverter.lexByte(this._charSeq);
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public short getShortValue() throws XMLStreamException, InvalidLexicalValueException {
        this._charSeq.reload(2);
        try {
            return XsTypeConverter.lexShort(this._charSeq);
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public int getIntValue() throws XMLStreamException, InvalidLexicalValueException {
        this._charSeq.reload(2);
        try {
            return XsTypeConverter.lexInt(this._charSeq);
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public long getLongValue() throws XMLStreamException, InvalidLexicalValueException {
        this._charSeq.reload(2);
        try {
            return XsTypeConverter.lexLong(this._charSeq);
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public BigInteger getBigIntegerValue() throws XMLStreamException, InvalidLexicalValueException {
        this._charSeq.reload(2);
        try {
            return XsTypeConverter.lexInteger(this._charSeq);
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public BigDecimal getBigDecimalValue() throws XMLStreamException, InvalidLexicalValueException {
        this._charSeq.reload(2);
        try {
            return XsTypeConverter.lexDecimal(this._charSeq);
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public float getFloatValue() throws XMLStreamException, InvalidLexicalValueException {
        this._charSeq.reload(2);
        try {
            return XsTypeConverter.lexFloat(this._charSeq);
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public double getDoubleValue() throws XMLStreamException, InvalidLexicalValueException {
        this._charSeq.reload(2);
        try {
            return XsTypeConverter.lexDouble(this._charSeq);
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public InputStream getHexBinaryValue() throws XMLStreamException, InvalidLexicalValueException {
        this._charSeq.reload(2);
        byte[] decode = HexBin.decode(this._charSeq.toString().getBytes(StandardCharsets.ISO_8859_1));
        if (decode != null) {
            return new ByteArrayInputStream(decode);
        }
        throw new InvalidLexicalValueException("invalid hexBinary value", this._charSeq.getLocation());
    }

    public InputStream getBase64Value() throws XMLStreamException, InvalidLexicalValueException {
        this._charSeq.reload(2);
        byte[] decode = Base64.getDecoder().decode(this._charSeq.toString().getBytes(StandardCharsets.ISO_8859_1));
        if (decode != null) {
            return new ByteArrayInputStream(decode);
        }
        throw new InvalidLexicalValueException("invalid base64Binary value", this._charSeq.getLocation());
    }

    public XmlCalendar getCalendarValue() throws XMLStreamException, InvalidLexicalValueException {
        this._charSeq.reload(2);
        try {
            return new GDateBuilder((CharSequence) this._charSeq).getCalendar();
        } catch (IllegalArgumentException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public Date getDateValue() throws XMLStreamException, InvalidLexicalValueException {
        this._charSeq.reload(2);
        try {
            return new GDateBuilder((CharSequence) this._charSeq).getDate();
        } catch (IllegalArgumentException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public GDate getGDateValue() throws XMLStreamException, InvalidLexicalValueException {
        this._charSeq.reload(2);
        try {
            return XsTypeConverter.lexGDate(this._charSeq);
        } catch (IllegalArgumentException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public GDuration getGDurationValue() throws XMLStreamException, InvalidLexicalValueException {
        this._charSeq.reload(2);
        try {
            return new GDuration((CharSequence) this._charSeq);
        } catch (IllegalArgumentException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public QName getQNameValue() throws XMLStreamException, InvalidLexicalValueException {
        this._charSeq.reload(2);
        try {
            return XsTypeConverter.lexQName(this._charSeq, this._xmlStream.getNamespaceContext());
        } catch (InvalidLexicalValueException e) {
            throw new InvalidLexicalValueException(e.getMessage(), this._charSeq.getLocation());
        }
    }

    public String getAttributeStringValue(int i) throws XMLStreamException {
        return this._xmlStream.getAttributeValue(i);
    }

    public String getAttributeStringValue(int i, int i2) throws XMLStreamException {
        return XmlWhitespace.collapse(this._xmlStream.getAttributeValue(i), i2);
    }

    public boolean getAttributeBooleanValue(int i) throws XMLStreamException {
        try {
            return XsTypeConverter.lexBoolean(this._charSeq.reloadAtt(i, 2));
        } catch (InvalidLexicalValueException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public byte getAttributeByteValue(int i) throws XMLStreamException {
        try {
            return XsTypeConverter.lexByte(this._charSeq.reloadAtt(i, 2));
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public short getAttributeShortValue(int i) throws XMLStreamException {
        try {
            return XsTypeConverter.lexShort(this._charSeq.reloadAtt(i, 2));
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public int getAttributeIntValue(int i) throws XMLStreamException {
        try {
            return XsTypeConverter.lexInt(this._charSeq.reloadAtt(i, 2));
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public long getAttributeLongValue(int i) throws XMLStreamException {
        try {
            return XsTypeConverter.lexLong(this._charSeq.reloadAtt(i, 2));
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public BigInteger getAttributeBigIntegerValue(int i) throws XMLStreamException {
        try {
            return XsTypeConverter.lexInteger(this._charSeq.reloadAtt(i, 2));
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public BigDecimal getAttributeBigDecimalValue(int i) throws XMLStreamException {
        try {
            return XsTypeConverter.lexDecimal(this._charSeq.reloadAtt(i, 2));
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public float getAttributeFloatValue(int i) throws XMLStreamException {
        try {
            return XsTypeConverter.lexFloat(this._charSeq.reloadAtt(i, 2));
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public double getAttributeDoubleValue(int i) throws XMLStreamException {
        try {
            return XsTypeConverter.lexDouble(this._charSeq.reloadAtt(i, 2));
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public InputStream getAttributeHexBinaryValue(int i) throws XMLStreamException {
        byte[] decode = HexBin.decode(this._charSeq.reloadAtt(i, 2).toString().getBytes(StandardCharsets.ISO_8859_1));
        if (decode != null) {
            return new ByteArrayInputStream(decode);
        }
        throw new InvalidLexicalValueException("invalid hexBinary value", this._charSeq.getLocation());
    }

    public InputStream getAttributeBase64Value(int i) throws XMLStreamException {
        byte[] decode = Base64.getDecoder().decode(this._charSeq.reloadAtt(i, 2).toString().getBytes(StandardCharsets.ISO_8859_1));
        if (decode != null) {
            return new ByteArrayInputStream(decode);
        }
        throw new InvalidLexicalValueException("invalid base64Binary value", this._charSeq.getLocation());
    }

    public XmlCalendar getAttributeCalendarValue(int i) throws XMLStreamException {
        try {
            return new GDateBuilder(this._charSeq.reloadAtt(i, 2)).getCalendar();
        } catch (IllegalArgumentException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public Date getAttributeDateValue(int i) throws XMLStreamException {
        try {
            return new GDateBuilder(this._charSeq.reloadAtt(i, 2)).getDate();
        } catch (IllegalArgumentException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public GDate getAttributeGDateValue(int i) throws XMLStreamException {
        try {
            return new GDate(this._charSeq.reloadAtt(i, 2));
        } catch (IllegalArgumentException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public GDuration getAttributeGDurationValue(int i) throws XMLStreamException {
        try {
            return new GDuration(this._charSeq.reloadAtt(i, 2));
        } catch (IllegalArgumentException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public QName getAttributeQNameValue(int i) throws XMLStreamException {
        try {
            return XsTypeConverter.lexQName(this._charSeq.reloadAtt(i, 2), this._xmlStream.getNamespaceContext());
        } catch (InvalidLexicalValueException e) {
            throw new InvalidLexicalValueException(e.getMessage(), this._charSeq.getLocation());
        }
    }

    public String getAttributeStringValue(String str, String str2) throws XMLStreamException {
        return this._charSeq.reloadAtt(str, str2, 1).toString();
    }

    public String getAttributeStringValue(String str, String str2, int i) throws XMLStreamException {
        return XmlWhitespace.collapse(this._xmlStream.getAttributeValue(str, str2), i);
    }

    public boolean getAttributeBooleanValue(String str, String str2) throws XMLStreamException {
        try {
            return XsTypeConverter.lexBoolean(this._charSeq.reloadAtt(str, str2, 2));
        } catch (InvalidLexicalValueException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public byte getAttributeByteValue(String str, String str2) throws XMLStreamException {
        try {
            return XsTypeConverter.lexByte(this._charSeq.reloadAtt(str, str2, 2));
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public short getAttributeShortValue(String str, String str2) throws XMLStreamException {
        try {
            return XsTypeConverter.lexShort(this._charSeq.reloadAtt(str, str2, 2));
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public int getAttributeIntValue(String str, String str2) throws XMLStreamException {
        try {
            return XsTypeConverter.lexInt(this._charSeq.reloadAtt(str, str2, 2));
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public long getAttributeLongValue(String str, String str2) throws XMLStreamException {
        try {
            return XsTypeConverter.lexLong(this._charSeq.reloadAtt(str, str2, 2));
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public BigInteger getAttributeBigIntegerValue(String str, String str2) throws XMLStreamException {
        try {
            return XsTypeConverter.lexInteger(this._charSeq.reloadAtt(str, str2, 2));
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public BigDecimal getAttributeBigDecimalValue(String str, String str2) throws XMLStreamException {
        try {
            return XsTypeConverter.lexDecimal(this._charSeq.reloadAtt(str, str2, 2));
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public float getAttributeFloatValue(String str, String str2) throws XMLStreamException {
        try {
            return XsTypeConverter.lexFloat(this._charSeq.reloadAtt(str, str2, 2));
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public double getAttributeDoubleValue(String str, String str2) throws XMLStreamException {
        try {
            return XsTypeConverter.lexDouble(this._charSeq.reloadAtt(str, str2, 2));
        } catch (NumberFormatException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public InputStream getAttributeHexBinaryValue(String str, String str2) throws XMLStreamException {
        byte[] decode = HexBin.decode(this._charSeq.reloadAtt(str, str2, 2).toString().getBytes(StandardCharsets.ISO_8859_1));
        if (decode != null) {
            return new ByteArrayInputStream(decode);
        }
        throw new InvalidLexicalValueException("invalid hexBinary value", this._charSeq.getLocation());
    }

    public InputStream getAttributeBase64Value(String str, String str2) throws XMLStreamException {
        byte[] decode = Base64.getDecoder().decode(this._charSeq.reloadAtt(str, str2, 2).toString().getBytes(StandardCharsets.ISO_8859_1));
        if (decode != null) {
            return new ByteArrayInputStream(decode);
        }
        throw new InvalidLexicalValueException("invalid base64Binary value", this._charSeq.getLocation());
    }

    public XmlCalendar getAttributeCalendarValue(String str, String str2) throws XMLStreamException {
        try {
            return new GDateBuilder(this._charSeq.reloadAtt(str, str2, 2)).getCalendar();
        } catch (IllegalArgumentException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public Date getAttributeDateValue(String str, String str2) throws XMLStreamException {
        try {
            return new GDateBuilder(this._charSeq.reloadAtt(str, str2, 2)).getDate();
        } catch (IllegalArgumentException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public GDate getAttributeGDateValue(String str, String str2) throws XMLStreamException {
        try {
            return new GDate(this._charSeq.reloadAtt(str, str2, 2));
        } catch (IllegalArgumentException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public GDuration getAttributeGDurationValue(String str, String str2) throws XMLStreamException {
        try {
            return new GDuration(this._charSeq.reloadAtt(str, str2, 2));
        } catch (IllegalArgumentException e) {
            throw new InvalidLexicalValueException((Throwable) e, this._charSeq.getLocation());
        }
    }

    public QName getAttributeQNameValue(String str, String str2) throws XMLStreamException {
        try {
            return XsTypeConverter.lexQName(this._charSeq.reloadAtt(str, str2, 2), this._xmlStream.getNamespaceContext());
        } catch (InvalidLexicalValueException e) {
            throw new InvalidLexicalValueException(e.getMessage(), this._charSeq.getLocation());
        }
    }

    public void setDefaultValue(String str) throws XMLStreamException {
        this._defaultValue = str;
    }

    private static class CharSeqTrimWS implements CharSequence {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static int INITIAL_SIZE = 100;
        static final int XMLWHITESPACE_PRESERVE = 1;
        static final int XMLWHITESPACE_TRIM = 2;
        private char[] _buf = new char[INITIAL_SIZE];
        private boolean _hasText;
        private int _length = 0;
        private final ExtLocation _location;
        private int _nonWSEnd = 0;
        private int _nonWSStart = 0;
        private int _start;
        private String _toStringValue;
        private XMLStreamReaderExtImpl _xmlSteam;

        static {
            Class<XMLStreamReaderExtImpl> cls = XMLStreamReaderExtImpl.class;
        }

        CharSeqTrimWS(XMLStreamReaderExtImpl xMLStreamReaderExtImpl) {
            this._xmlSteam = xMLStreamReaderExtImpl;
            this._location = new ExtLocation();
        }

        /* access modifiers changed from: package-private */
        public void reload(int i) throws XMLStreamException {
            this._toStringValue = null;
            this._location.reset();
            this._hasText = false;
            fillBuffer();
            if (i == 1) {
                this._nonWSStart = 0;
                this._nonWSEnd = this._length;
                if (!this._hasText && this._xmlSteam._defaultValue != null) {
                    this._length = 0;
                    fillBufferFromString(this._xmlSteam._defaultValue);
                }
            } else if (i == 2) {
                this._nonWSStart = 0;
                while (true) {
                    int i2 = this._nonWSStart;
                    if (i2 >= this._length || !XMLChar.isSpace(this._buf[i2])) {
                        int i3 = this._length;
                    } else {
                        this._nonWSStart++;
                    }
                }
                int i32 = this._length;
                while (true) {
                    this._nonWSEnd = i32;
                    int i4 = this._nonWSEnd;
                    if (i4 > this._nonWSStart && XMLChar.isSpace(this._buf[i4 - 1])) {
                        i32 = this._nonWSEnd - 1;
                    }
                }
                if (length() == 0 && this._xmlSteam._defaultValue != null) {
                    this._length = 0;
                    fillBufferFromString(this._xmlSteam._defaultValue);
                    this._nonWSStart = 0;
                    while (true) {
                        int i5 = this._nonWSStart;
                        if (i5 >= this._length || !XMLChar.isSpace(this._buf[i5])) {
                            int i6 = this._length;
                        } else {
                            this._nonWSStart++;
                        }
                    }
                    int i62 = this._length;
                    while (true) {
                        this._nonWSEnd = i62;
                        int i7 = this._nonWSEnd;
                        if (i7 <= this._nonWSStart || !XMLChar.isSpace(this._buf[i7 - 1])) {
                            break;
                        }
                        i62 = this._nonWSEnd - 1;
                    }
                }
            }
            String unused = this._xmlSteam._defaultValue = null;
        }

        private void fillBuffer() throws XMLStreamException {
            int i = 0;
            this._length = 0;
            if (this._xmlSteam.getEventType() == 7) {
                this._xmlSteam.next();
            }
            if (this._xmlSteam.isStartElement()) {
                this._xmlSteam.next();
            }
            int eventType = this._xmlSteam.getEventType();
            String str = null;
            while (true) {
                if (eventType == 1) {
                    i++;
                    this._location.set(this._xmlSteam.getLocation());
                    str = "Unexpected element '" + this._xmlSteam.getName() + "' in text content.";
                } else if (eventType != 2) {
                    if (eventType != 4 && eventType != 6 && eventType != 12) {
                        if (eventType == 8) {
                            this._location.set(this._xmlSteam.getLocation());
                            break;
                        } else if (eventType == 9) {
                            this._location.set(this._xmlSteam.getLocation());
                            addEntityToBuffer();
                        }
                    } else {
                        this._location.set(this._xmlSteam.getLocation());
                        if (i == 0) {
                            addTextToBuffer();
                        }
                    }
                } else {
                    this._location.set(this._xmlSteam.getLocation());
                    i--;
                    if (i < 0) {
                        break;
                    }
                }
                eventType = this._xmlSteam.next();
            }
            if (str != null) {
                throw new XMLStreamException(str);
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

        private void fillBufferFromString(CharSequence charSequence) {
            int length = charSequence.length();
            ensureBufferLength(length);
            for (int i = 0; i < length; i++) {
                this._buf[i] = charSequence.charAt(i);
            }
            this._length = length;
        }

        private void addTextToBuffer() {
            this._hasText = true;
            int textLength = this._xmlSteam.getTextLength();
            ensureBufferLength(textLength);
            System.arraycopy(this._xmlSteam.getTextCharacters(), this._xmlSteam.getTextStart(), this._buf, this._length, textLength);
            this._length += textLength;
        }

        private void addEntityToBuffer() {
            String text = this._xmlSteam.getText();
            ensureBufferLength(text.length());
            text.getChars(0, text.length(), this._buf, this._length);
            this._length += text.length();
        }

        /* access modifiers changed from: package-private */
        public CharSequence reloadAtt(int i, int i2) throws XMLStreamException {
            this._location.reset();
            this._location.set(this._xmlSteam.getLocation());
            String attributeValue = this._xmlSteam.getAttributeValue(i);
            if (attributeValue == null && this._xmlSteam._defaultValue != null) {
                attributeValue = this._xmlSteam._defaultValue;
            }
            String unused = this._xmlSteam._defaultValue = null;
            int length = attributeValue.length();
            if (i2 == 1) {
                return attributeValue;
            }
            if (i2 == 2) {
                int i3 = 0;
                while (i3 < length && XMLChar.isSpace(attributeValue.charAt(i3))) {
                    i3++;
                }
                int i4 = length;
                while (i4 > i3 && XMLChar.isSpace(attributeValue.charAt(i4 - 1))) {
                    i4--;
                }
                if (i3 == 0 && i4 == length) {
                    return attributeValue;
                }
                return attributeValue.subSequence(i3, i4);
            }
            throw new IllegalStateException("unknown style");
        }

        /* access modifiers changed from: package-private */
        public CharSequence reloadAtt(String str, String str2, int i) throws XMLStreamException {
            this._location.reset();
            this._location.set(this._xmlSteam.getLocation());
            String attributeValue = this._xmlSteam.getAttributeValue(str, str2);
            if (attributeValue == null && this._xmlSteam._defaultValue != null) {
                attributeValue = this._xmlSteam._defaultValue;
            }
            String unused = this._xmlSteam._defaultValue = null;
            int length = attributeValue.length();
            if (i == 1) {
                return attributeValue;
            }
            if (i == 2) {
                int i2 = 0;
                while (true) {
                    this._nonWSStart = i2;
                    int i3 = this._nonWSStart;
                    if (i3 >= length || !XMLChar.isSpace(attributeValue.charAt(i3))) {
                        this._nonWSEnd = length;
                    } else {
                        i2 = this._nonWSStart + 1;
                    }
                }
                this._nonWSEnd = length;
                while (true) {
                    int i4 = this._nonWSEnd;
                    if (i4 <= this._nonWSStart || !XMLChar.isSpace(attributeValue.charAt(i4 - 1))) {
                        int i5 = this._nonWSStart;
                    } else {
                        this._nonWSEnd--;
                    }
                }
                int i52 = this._nonWSStart;
                if (i52 == 0 && this._nonWSEnd == length) {
                    return attributeValue;
                }
                return attributeValue.subSequence(i52, this._nonWSEnd);
            }
            throw new IllegalStateException("unknown style");
        }

        /* access modifiers changed from: package-private */
        public Location getLocation() {
            ExtLocation extLocation = new ExtLocation();
            extLocation.set(this._location);
            return extLocation;
        }

        public int length() {
            return this._nonWSEnd - this._nonWSStart;
        }

        public char charAt(int i) {
            return this._buf[this._nonWSStart + i];
        }

        public CharSequence subSequence(int i, int i2) {
            return new String(this._buf, this._nonWSStart + i, i2 - i);
        }

        public String toString() {
            String str = this._toStringValue;
            if (str != null) {
                return str;
            }
            char[] cArr = this._buf;
            int i = this._nonWSStart;
            String str2 = new String(cArr, i, this._nonWSEnd - i);
            this._toStringValue = str2;
            return str2;
        }

        private static class ExtLocation implements Location {
            private int _col;
            private boolean _isSet = false;
            private int _line;
            private int _off;
            private String _pid;
            private String _sid;

            ExtLocation() {
            }

            public int getLineNumber() {
                if (this._isSet) {
                    return this._line;
                }
                throw new IllegalStateException();
            }

            public int getColumnNumber() {
                if (this._isSet) {
                    return this._col;
                }
                throw new IllegalStateException();
            }

            public int getCharacterOffset() {
                if (this._isSet) {
                    return this._off;
                }
                throw new IllegalStateException();
            }

            public String getPublicId() {
                if (this._isSet) {
                    return this._pid;
                }
                throw new IllegalStateException();
            }

            public String getSystemId() {
                if (this._isSet) {
                    return this._sid;
                }
                throw new IllegalStateException();
            }

            /* access modifiers changed from: package-private */
            public void set(Location location) {
                if (!this._isSet) {
                    this._isSet = true;
                    this._line = location.getLineNumber();
                    this._col = location.getColumnNumber();
                    this._off = location.getCharacterOffset();
                    this._pid = location.getPublicId();
                    this._sid = location.getSystemId();
                }
            }

            /* access modifiers changed from: package-private */
            public void reset() {
                this._isSet = false;
            }
        }
    }

    public Object getProperty(String str) throws IllegalArgumentException {
        return this._xmlStream.getProperty(str);
    }

    public int next() throws XMLStreamException {
        return this._xmlStream.next();
    }

    public void require(int i, String str, String str2) throws XMLStreamException {
        this._xmlStream.require(i, str, str2);
    }

    public String getElementText() throws XMLStreamException {
        return this._xmlStream.getElementText();
    }

    public int nextTag() throws XMLStreamException {
        return this._xmlStream.nextTag();
    }

    public boolean hasNext() throws XMLStreamException {
        return this._xmlStream.hasNext();
    }

    public void close() throws XMLStreamException {
        this._xmlStream.close();
    }

    public String getNamespaceURI(String str) {
        return this._xmlStream.getNamespaceURI(str);
    }

    public boolean isStartElement() {
        return this._xmlStream.isStartElement();
    }

    public boolean isEndElement() {
        return this._xmlStream.isEndElement();
    }

    public boolean isCharacters() {
        return this._xmlStream.isCharacters();
    }

    public boolean isWhiteSpace() {
        return this._xmlStream.isWhiteSpace();
    }

    public String getAttributeValue(String str, String str2) {
        return this._xmlStream.getAttributeValue(str, str2);
    }

    public int getAttributeCount() {
        return this._xmlStream.getAttributeCount();
    }

    public QName getAttributeName(int i) {
        return this._xmlStream.getAttributeName(i);
    }

    public String getAttributeNamespace(int i) {
        return this._xmlStream.getAttributeNamespace(i);
    }

    public String getAttributeLocalName(int i) {
        return this._xmlStream.getAttributeLocalName(i);
    }

    public String getAttributePrefix(int i) {
        return this._xmlStream.getAttributePrefix(i);
    }

    public String getAttributeType(int i) {
        return this._xmlStream.getAttributeType(i);
    }

    public String getAttributeValue(int i) {
        return this._xmlStream.getAttributeValue(i);
    }

    public boolean isAttributeSpecified(int i) {
        return this._xmlStream.isAttributeSpecified(i);
    }

    public int getNamespaceCount() {
        return this._xmlStream.getNamespaceCount();
    }

    public String getNamespacePrefix(int i) {
        return this._xmlStream.getNamespacePrefix(i);
    }

    public String getNamespaceURI(int i) {
        return this._xmlStream.getNamespaceURI(i);
    }

    public NamespaceContext getNamespaceContext() {
        return this._xmlStream.getNamespaceContext();
    }

    public int getEventType() {
        return this._xmlStream.getEventType();
    }

    public String getText() {
        return this._xmlStream.getText();
    }

    public char[] getTextCharacters() {
        return this._xmlStream.getTextCharacters();
    }

    public int getTextCharacters(int i, char[] cArr, int i2, int i3) throws XMLStreamException {
        return this._xmlStream.getTextCharacters(i, cArr, i2, i3);
    }

    public int getTextStart() {
        return this._xmlStream.getTextStart();
    }

    public int getTextLength() {
        return this._xmlStream.getTextLength();
    }

    public String getEncoding() {
        return this._xmlStream.getEncoding();
    }

    public boolean hasText() {
        return this._xmlStream.hasText();
    }

    public Location getLocation() {
        return this._xmlStream.getLocation();
    }

    public QName getName() {
        return this._xmlStream.getName();
    }

    public String getLocalName() {
        return this._xmlStream.getLocalName();
    }

    public boolean hasName() {
        return this._xmlStream.hasName();
    }

    public String getNamespaceURI() {
        return this._xmlStream.getNamespaceURI();
    }

    public String getPrefix() {
        return this._xmlStream.getPrefix();
    }

    public String getVersion() {
        return this._xmlStream.getVersion();
    }

    public boolean isStandalone() {
        return this._xmlStream.isStandalone();
    }

    public boolean standaloneSet() {
        return this._xmlStream.standaloneSet();
    }

    public String getCharacterEncodingScheme() {
        return this._xmlStream.getCharacterEncodingScheme();
    }

    public String getPITarget() {
        return this._xmlStream.getPITarget();
    }

    public String getPIData() {
        return this._xmlStream.getPIData();
    }
}
