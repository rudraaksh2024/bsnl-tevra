package org.apache.xmlbeans.impl.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.util.XsTypeConverter;

public abstract class JavaLongHolder extends XmlObjectBase {
    private static final BigInteger _max = BigInteger.valueOf(Long.MAX_VALUE);
    private static final BigInteger _min = BigInteger.valueOf(Long.MIN_VALUE);
    private long _value;

    public SchemaType schemaType() {
        return BuiltinSchemaTypeSystem.ST_LONG;
    }

    /* access modifiers changed from: protected */
    public String compute_text(NamespaceManager namespaceManager) {
        return Long.toString(this._value);
    }

    /* access modifiers changed from: protected */
    public void set_text(String str) {
        try {
            set_long(XsTypeConverter.lexLong(str));
        } catch (Exception unused) {
            throw new XmlValueOutOfRangeException(XmlErrorCodes.LONG, new Object[]{str});
        }
    }

    /* access modifiers changed from: protected */
    public void set_nil() {
        this._value = 0;
    }

    public BigDecimal getBigDecimalValue() {
        check_dated();
        return BigDecimal.valueOf(this._value);
    }

    public BigInteger getBigIntegerValue() {
        check_dated();
        return BigInteger.valueOf(this._value);
    }

    public long getLongValue() {
        check_dated();
        return this._value;
    }

    /* access modifiers changed from: protected */
    public void set_BigDecimal(BigDecimal bigDecimal) {
        set_BigInteger(bigDecimal.toBigInteger());
    }

    /* access modifiers changed from: protected */
    public void set_BigInteger(BigInteger bigInteger) {
        if (bigInteger.compareTo(_max) > 0 || bigInteger.compareTo(_min) < 0) {
            throw new XmlValueOutOfRangeException();
        }
        this._value = bigInteger.longValue();
    }

    /* access modifiers changed from: protected */
    public void set_long(long j) {
        this._value = j;
    }

    /* access modifiers changed from: protected */
    public int compare_to(XmlObject xmlObject) {
        if (((SimpleValue) xmlObject).instanceType().getDecimalSize() > 64) {
            return -xmlObject.compareTo(this);
        }
        return Long.compare(this._value, ((XmlObjectBase) xmlObject).getLongValue());
    }

    /* access modifiers changed from: protected */
    public boolean equal_to(XmlObject xmlObject) {
        if (((SimpleValue) xmlObject).instanceType().getDecimalSize() > 64) {
            return xmlObject.valueEquals(this);
        }
        return this._value == ((XmlObjectBase) xmlObject).getLongValue();
    }

    /* access modifiers changed from: protected */
    public int value_hash_code() {
        long j = this._value;
        return (int) (((j >> 32) * 19) + j);
    }
}
