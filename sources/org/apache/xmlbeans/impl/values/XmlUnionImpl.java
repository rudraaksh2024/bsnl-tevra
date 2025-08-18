package org.apache.xmlbeans.impl.values;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDateSpecification;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.GDurationSpecification;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;

public class XmlUnionImpl extends XmlObjectBase implements XmlAnySimpleType {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int JAVA_BYTEARRAY = 50;
    private static final int JAVA_CALENDAR = 49;
    private static final int JAVA_DATE = 48;
    private static final int JAVA_LIST = 51;
    private static final int JAVA_NUMBER = 47;
    private final SchemaType _schemaType;
    private String _textvalue = "";
    private XmlAnySimpleType _value;

    /* access modifiers changed from: protected */
    public int get_wscanon_rule() {
        return 1;
    }

    public XmlUnionImpl(SchemaType schemaType, boolean z) {
        this._schemaType = schemaType;
        initComplexType(z, false);
    }

    public SchemaType schemaType() {
        return this._schemaType;
    }

    public SchemaType instanceType() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return null;
        }
        return ((SimpleValue) xmlAnySimpleType).instanceType();
    }

    /* access modifiers changed from: protected */
    public String compute_text(NamespaceManager namespaceManager) {
        return this._textvalue;
    }

    /* access modifiers changed from: protected */
    public boolean is_defaultable_ws(String str) {
        try {
            XmlAnySimpleType xmlAnySimpleType = this._value;
            set_text(str);
            this._value = xmlAnySimpleType;
            return false;
        } catch (XmlValueOutOfRangeException unused) {
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        boolean z;
        if (this._schemaType.matchPatternFacet(str) || !_validateOnSet()) {
            String str2 = this._textvalue;
            this._textvalue = str;
            SchemaType[] unionConstituentTypes = this._schemaType.getUnionConstituentTypes();
            if (has_store()) {
                NamespaceContext.push(new NamespaceContext(get_store()));
                z = true;
            } else {
                z = false;
            }
            boolean z2 = true;
            while (true) {
                if (!z2) {
                    try {
                        if (_validateOnSet()) {
                            break;
                        }
                    } catch (XmlValueOutOfRangeException unused) {
                    } catch (Exception e) {
                        throw new RuntimeException("Troublesome union exception caused by unexpected " + e, e);
                    } catch (Throwable th) {
                        if (z) {
                            NamespaceContext.pop();
                        }
                        throw th;
                    }
                }
                int length = unionConstituentTypes.length;
                int i = 0;
                while (i < length) {
                    XmlAnySimpleType newValue = ((SchemaTypeImpl) unionConstituentTypes[i]).newValue(str, z2);
                    if (!check(newValue, this._schemaType)) {
                        i++;
                    } else {
                        this._value = newValue;
                        if (z) {
                            NamespaceContext.pop();
                            return;
                        }
                        return;
                    }
                }
                if (!z2) {
                    break;
                }
                z2 = false;
            }
            if (z) {
                NamespaceContext.pop();
            }
            this._textvalue = str2;
            throw new XmlValueOutOfRangeException(XmlErrorCodes.DATATYPE_VALID$UNION, new Object[]{str, QNameHelper.readable(this._schemaType)});
        }
        throw new XmlValueOutOfRangeException(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{TypedValues.Custom.S_STRING, str, QNameHelper.readable(this._schemaType)});
    }

    /* access modifiers changed from: protected */
    public void set_nil() {
        this._value = null;
        this._textvalue = null;
    }

    public float getFloatValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return 0.0f;
        }
        return ((SimpleValue) xmlAnySimpleType).getFloatValue();
    }

    public double getDoubleValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return 0.0d;
        }
        return ((SimpleValue) xmlAnySimpleType).getDoubleValue();
    }

    public BigDecimal getBigDecimalValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return null;
        }
        return ((SimpleValue) xmlAnySimpleType).getBigDecimalValue();
    }

    public BigInteger getBigIntegerValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return null;
        }
        return ((SimpleValue) xmlAnySimpleType).getBigIntegerValue();
    }

    public byte getByteValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return 0;
        }
        return ((SimpleValue) xmlAnySimpleType).getByteValue();
    }

    public short getShortValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return 0;
        }
        return ((SimpleValue) xmlAnySimpleType).getShortValue();
    }

    public int getIntValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return 0;
        }
        return ((SimpleValue) xmlAnySimpleType).getIntValue();
    }

    public long getLongValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return 0;
        }
        return ((SimpleValue) xmlAnySimpleType).getLongValue();
    }

    public byte[] getByteArrayValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return null;
        }
        return ((SimpleValue) xmlAnySimpleType).getByteArrayValue();
    }

    public boolean getBooleanValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        return xmlAnySimpleType != null && ((SimpleValue) xmlAnySimpleType).getBooleanValue();
    }

    public Calendar getCalendarValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return null;
        }
        return ((SimpleValue) xmlAnySimpleType).getCalendarValue();
    }

    public Date getDateValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return null;
        }
        return ((SimpleValue) xmlAnySimpleType).getDateValue();
    }

    public GDate getGDateValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return null;
        }
        return ((SimpleValue) xmlAnySimpleType).getGDateValue();
    }

    public GDuration getGDurationValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return null;
        }
        return ((SimpleValue) xmlAnySimpleType).getGDurationValue();
    }

    public QName getQNameValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return null;
        }
        return ((SimpleValue) xmlAnySimpleType).getQNameValue();
    }

    public List<?> getListValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return null;
        }
        return ((SimpleValue) xmlAnySimpleType).getListValue();
    }

    public List<? extends XmlAnySimpleType> xgetListValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return null;
        }
        return ((SimpleValue) xmlAnySimpleType).xgetListValue();
    }

    public StringEnumAbstractBase getEnumValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return null;
        }
        return ((SimpleValue) xmlAnySimpleType).getEnumValue();
    }

    public String getStringValue() {
        check_dated();
        XmlAnySimpleType xmlAnySimpleType = this._value;
        if (xmlAnySimpleType == null) {
            return null;
        }
        return xmlAnySimpleType.getStringValue();
    }

    private static boolean logical_overlap(SchemaType schemaType, int i) {
        if (i > 46) {
            switch (i) {
                case 47:
                    if (schemaType.getSimpleVariety() != 1) {
                        return false;
                    }
                    int builtinTypeCode = schemaType.getPrimitiveType().getBuiltinTypeCode();
                    if (!(builtinTypeCode == 18 || builtinTypeCode == 20 || builtinTypeCode == 21)) {
                        switch (builtinTypeCode) {
                            case 9:
                            case 10:
                            case 11:
                                break;
                            default:
                                return false;
                        }
                    }
                    return true;
                case 48:
                    if (schemaType.getSimpleVariety() != 1) {
                        return false;
                    }
                    int builtinTypeCode2 = schemaType.getPrimitiveType().getBuiltinTypeCode();
                    return builtinTypeCode2 == 14 || builtinTypeCode2 == 16;
                case 49:
                    if (schemaType.getSimpleVariety() != 1) {
                        return false;
                    }
                    switch (schemaType.getPrimitiveType().getBuiltinTypeCode()) {
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                            return true;
                        default:
                            return false;
                    }
                case 50:
                    if (schemaType.getSimpleVariety() != 1) {
                        return false;
                    }
                    int builtinTypeCode3 = schemaType.getPrimitiveType().getBuiltinTypeCode();
                    return builtinTypeCode3 == 4 || builtinTypeCode3 == 5;
                case 51:
                    if (schemaType.getSimpleVariety() == 3) {
                        return true;
                    }
                    return false;
                default:
                    return false;
            }
        } else if (schemaType.getSimpleVariety() != 1) {
            return false;
        } else {
            if (schemaType.getPrimitiveType().getBuiltinTypeCode() == i) {
                return true;
            }
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r9._value = r10;
        r9._textvalue = r10.getStringValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0040, code lost:
        if (r1 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0042, code lost:
        org.apache.xmlbeans.impl.values.NamespaceContext.pop();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void set_primitive(int r10, java.lang.Object r11) {
        /*
            r9 = this;
            org.apache.xmlbeans.SchemaType r0 = r9._schemaType
            org.apache.xmlbeans.SchemaType[] r0 = r0.getUnionConstituentTypes()
            boolean r1 = r9.has_store()
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x001c
            org.apache.xmlbeans.impl.values.NamespaceContext r1 = new org.apache.xmlbeans.impl.values.NamespaceContext
            org.apache.xmlbeans.impl.values.TypeStore r4 = r9.get_store()
            r1.<init>((org.apache.xmlbeans.impl.values.TypeStore) r4)
            org.apache.xmlbeans.impl.values.NamespaceContext.push(r1)
            r1 = r2
            goto L_0x001d
        L_0x001c:
            r1 = r3
        L_0x001d:
            r4 = r2
        L_0x001e:
            if (r4 != 0) goto L_0x0026
            boolean r5 = r9._validateOnSet()     // Catch:{ all -> 0x006b }
            if (r5 != 0) goto L_0x004b
        L_0x0026:
            int r5 = r0.length     // Catch:{ all -> 0x006b }
            r6 = r3
        L_0x0028:
            if (r6 >= r5) goto L_0x0049
            r7 = r0[r6]     // Catch:{ all -> 0x006b }
            boolean r8 = logical_overlap(r7, r10)     // Catch:{ all -> 0x006b }
            if (r8 == 0) goto L_0x0046
            org.apache.xmlbeans.impl.schema.SchemaTypeImpl r7 = (org.apache.xmlbeans.impl.schema.SchemaTypeImpl) r7     // Catch:{ Exception | XmlValueOutOfRangeException -> 0x0046 }
            org.apache.xmlbeans.XmlAnySimpleType r10 = r7.newValue(r11, r4)     // Catch:{ Exception | XmlValueOutOfRangeException -> 0x0046 }
            r9._value = r10     // Catch:{ all -> 0x006b }
            java.lang.String r10 = r10.getStringValue()     // Catch:{ all -> 0x006b }
            r9._textvalue = r10     // Catch:{ all -> 0x006b }
            if (r1 == 0) goto L_0x0045
            org.apache.xmlbeans.impl.values.NamespaceContext.pop()
        L_0x0045:
            return
        L_0x0046:
            int r6 = r6 + 1
            goto L_0x0028
        L_0x0049:
            if (r4 != 0) goto L_0x0069
        L_0x004b:
            if (r1 == 0) goto L_0x0050
            org.apache.xmlbeans.impl.values.NamespaceContext.pop()
        L_0x0050:
            org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException r10 = new org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            java.lang.String r11 = r11.toString()
            r0[r3] = r11
            org.apache.xmlbeans.SchemaType r9 = r9._schemaType
            java.lang.String r9 = org.apache.xmlbeans.impl.common.QNameHelper.readable((org.apache.xmlbeans.SchemaType) r9)
            r0[r2] = r9
            java.lang.String r9 = "cvc-datatype-valid.1.2.3"
            r10.<init>(r9, r0)
            throw r10
        L_0x0069:
            r4 = r3
            goto L_0x001e
        L_0x006b:
            r9 = move-exception
            if (r1 == 0) goto L_0x0071
            org.apache.xmlbeans.impl.values.NamespaceContext.pop()
        L_0x0071:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.values.XmlUnionImpl.set_primitive(int, java.lang.Object):void");
    }

    /* access modifiers changed from: protected */
    public void set_boolean(boolean z) {
        set_primitive(3, Boolean.valueOf(z));
    }

    /* access modifiers changed from: protected */
    public void set_byte(byte b) {
        set_primitive(47, Byte.valueOf(b));
    }

    /* access modifiers changed from: protected */
    public void set_short(short s) {
        set_primitive(47, Short.valueOf(s));
    }

    /* access modifiers changed from: protected */
    public void set_int(int i) {
        set_primitive(47, Integer.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public void set_long(long j) {
        set_primitive(47, Long.valueOf(j));
    }

    /* access modifiers changed from: protected */
    public void set_float(float f) {
        set_primitive(47, Float.valueOf(f));
    }

    /* access modifiers changed from: protected */
    public void set_double(double d) {
        set_primitive(47, Double.valueOf(d));
    }

    /* access modifiers changed from: protected */
    public void set_ByteArray(byte[] bArr) {
        set_primitive(50, bArr);
    }

    /* access modifiers changed from: protected */
    public void set_hex(byte[] bArr) {
        set_primitive(50, bArr);
    }

    /* access modifiers changed from: protected */
    public void set_b64(byte[] bArr) {
        set_primitive(50, bArr);
    }

    /* access modifiers changed from: protected */
    public void set_BigInteger(BigInteger bigInteger) {
        set_primitive(47, bigInteger);
    }

    /* access modifiers changed from: protected */
    public void set_BigDecimal(BigDecimal bigDecimal) {
        set_primitive(47, bigDecimal);
    }

    /* access modifiers changed from: protected */
    public void set_QName(QName qName) {
        set_primitive(7, qName);
    }

    /* access modifiers changed from: protected */
    public void set_Calendar(Calendar calendar) {
        set_primitive(49, calendar);
    }

    /* access modifiers changed from: protected */
    public void set_Date(Date date) {
        set_primitive(48, date);
    }

    /* access modifiers changed from: protected */
    public void set_GDate(GDateSpecification gDateSpecification) {
        int builtinTypeCode = gDateSpecification.getBuiltinTypeCode();
        if (builtinTypeCode > 0) {
            set_primitive(builtinTypeCode, gDateSpecification);
            return;
        }
        throw new XmlValueOutOfRangeException();
    }

    /* access modifiers changed from: protected */
    public void set_GDuration(GDurationSpecification gDurationSpecification) {
        set_primitive(13, gDurationSpecification);
    }

    /* access modifiers changed from: protected */
    public void set_enum(StringEnumAbstractBase stringEnumAbstractBase) {
        set_primitive(12, stringEnumAbstractBase);
    }

    /* access modifiers changed from: protected */
    public void set_list(List<?> list) {
        set_primitive(51, list);
    }

    /* access modifiers changed from: protected */
    public void set_xmlfloat(XmlObject xmlObject) {
        set_primitive(9, xmlObject);
    }

    /* access modifiers changed from: protected */
    public void set_xmldouble(XmlObject xmlObject) {
        set_primitive(10, xmlObject);
    }

    /* access modifiers changed from: protected */
    public void set_xmldecimal(XmlObject xmlObject) {
        set_primitive(11, xmlObject);
    }

    /* access modifiers changed from: protected */
    public void set_xmlduration(XmlObject xmlObject) {
        set_primitive(13, xmlObject);
    }

    /* access modifiers changed from: protected */
    public void set_xmldatetime(XmlObject xmlObject) {
        set_primitive(14, xmlObject);
    }

    /* access modifiers changed from: protected */
    public void set_xmltime(XmlObject xmlObject) {
        set_primitive(15, xmlObject);
    }

    /* access modifiers changed from: protected */
    public void set_xmldate(XmlObject xmlObject) {
        set_primitive(16, xmlObject);
    }

    /* access modifiers changed from: protected */
    public void set_xmlgyearmonth(XmlObject xmlObject) {
        set_primitive(17, xmlObject);
    }

    /* access modifiers changed from: protected */
    public void set_xmlgyear(XmlObject xmlObject) {
        set_primitive(18, xmlObject);
    }

    /* access modifiers changed from: protected */
    public void set_xmlgmonthday(XmlObject xmlObject) {
        set_primitive(19, xmlObject);
    }

    /* access modifiers changed from: protected */
    public void set_xmlgday(XmlObject xmlObject) {
        set_primitive(20, xmlObject);
    }

    /* access modifiers changed from: protected */
    public void set_xmlgmonth(XmlObject xmlObject) {
        set_primitive(21, xmlObject);
    }

    private static boolean check(XmlObject xmlObject, SchemaType schemaType) {
        XmlAnySimpleType[] enumerationValues = schemaType.getEnumerationValues();
        if (enumerationValues == null) {
            return true;
        }
        for (XmlAnySimpleType valueEquals : enumerationValues) {
            if (valueEquals.valueEquals(xmlObject)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean equal_to(XmlObject xmlObject) {
        return this._value.valueEquals(xmlObject);
    }

    /* access modifiers changed from: protected */
    public int value_hash_code() {
        return this._value.hashCode();
    }

    /* access modifiers changed from: protected */
    public void validate_simpleval(String str, ValidationContext validationContext) {
        try {
            check_dated();
            XmlAnySimpleType xmlAnySimpleType = this._value;
            if (xmlAnySimpleType == null) {
                validationContext.invalid(XmlErrorCodes.UNION, new Object[]{"'" + str + "' does not match any of the member types for " + QNameHelper.readable(schemaType())});
                return;
            }
            ((XmlObjectBase) xmlAnySimpleType).validate_simpleval(str, validationContext);
        } catch (Exception unused) {
            validationContext.invalid(XmlErrorCodes.UNION, new Object[]{"'" + str + "' does not match any of the member types for " + QNameHelper.readable(schemaType())});
        }
    }
}
